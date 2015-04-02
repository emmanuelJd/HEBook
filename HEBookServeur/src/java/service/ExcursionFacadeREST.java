/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.hebook.model.Chambre;
import org.hebook.model.Excursion;
import org.hebook.model.Hotel;
import org.hebook.model.Reservation;
import org.hebook.querymodel.ExcursionByPlaceDisponible;
import org.hebook.querymodel.ExcursionPopulaire;
import org.hebook.querymodel.ExcursionResult;
import org.hebook.querymodel.HotelResult;

/**
 *
 * Classe qui fournit les services utiles pour l'acces à l'élément Excursion. C'est-à-dire de la création à la suppression
 * @author Emmanuel
 * @version 1.0  
 */
@Stateless
@Path("org.hebook.model.excursion")
public class ExcursionFacadeREST extends AbstractFacade<Excursion> {
    @PersistenceContext(unitName = "HEBookProjetPU")
    private EntityManager em;

    /**
     *
     */
    public ExcursionFacadeREST() {
        super(Excursion.class);
    }

    /**
     * Ajoute l'objet entity de type excursion dans la persistence de données, dans notre cas la base de données.
     * @param entity l'excursion à rajouter
     */
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Excursion entity) {
        super.create(entity);
    }

    /**
     *
     * Version modifié de la création de l'excursion car la précédente ne fonctionne pas correctement.
     * @param entity l'excursion à ajouter
     * @see ExcursionResult
     *
     */
    @POST
    @Path("createSecure")
    @Consumes({"application/xml", "application/json"})
    public void createSecure(ExcursionResult entity) {
        
        Query queryExId = getEntityManager().createQuery("select e from Excursion e order by e.idExcursion desc");
        List<Excursion> excursionId = queryExId.setMaxResults(1).getResultList();
        
        Excursion excursion = new Excursion();
        
        excursion.setPrix(entity.getPrix());
        excursion.setDateDebut(new java.sql.Date(entity.getDateDebut().getTime()));
        excursion.setDateFin(new java.sql.Date(entity.getDateFin().getTime()));
        
        Query queryHotel = getEntityManager().createQuery("select h from Hotel h where h.idHotel = "+entity.getIdHotel());
        Hotel hotel = (Hotel) queryHotel.getSingleResult();
        
        excursion.setTheme(entity.getTheme());
        excursion.setNombrePlace(entity.getNombrePlace());
        Timestamp timestampDebut = new Timestamp(entity.getDateDebut().getTime());
        Timestamp timestampFin = new Timestamp(entity.getDateFin().getTime());
        Query query = getEntityManager().createNativeQuery("INSERT INTO USERS_BOOKING.HEBOOKING_EXCURSION (EXCURSION_ID,EXCURSION_DATE_DEBUT,EXCURSION_DATE_FIN,EXCURSION_NOMBRE_PLACE,EXCURSION_PRIX,EXCURSION_THEME,HOTEL_ID) VALUES ("+(excursionId.get(0).getId()+1)+",'"+timestampDebut.toString()+"','"+timestampFin.toString()+"',"+entity.getNombrePlace()+","+entity.getPrix()+",'"+entity.getTheme()+"',"+entity.getIdHotel()+")");
        query.executeUpdate();
        
    }
    
    /**
     *
     * Edite l'objet entity de type excursion dans la persistence de données retrouvé par son id.
     * @param entity l'objet excursion que l'on souhaite éditer.
     */
    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Excursion entity) {
        super.edit(entity);
    }

    /**
     * Ajoute une excursion dans la réservation cilbé d'un client
     * @param idExcursion l'identifiant de l'excursion 
     * @param idReservation l'identifiant de la réservation
     * @return String(true|false) de la réussite ou non de l'ajout de la réservation 
     */
    @GET
    @Path("addReservationSecure")
    @Consumes({"application/xml", "application/json"})
    public String addReservationSecure(@QueryParam("idExcursion") @DefaultValue("-1") Long idExcursion, @QueryParam("idReservation") @DefaultValue("-1")Long idReservation) {
        
        /* Il faut vérifier que la réservation soit faite dans l'hotel ou est rataché l'excursion
         * Il faut que l'excursion soit comprise entre le date de debut de réservation et la date de fin de réservation
         * pour réserver il faut que sur cette excursion il reste une place libre.
         * 
         */
        boolean success = true;
        System.out.println("Les deux parametre => reserv : "+idReservation+ " & Excursion : "+idExcursion);
        Query queryReservation = getEntityManager().createQuery("select r from Reservation r where r.idReservation = "+idReservation);
        Reservation reservation = (Reservation) queryReservation.getSingleResult();
       
        Excursion excursion = this.find(idExcursion);
        
        if(reservation.getChambre().getHotel().getId() != excursion.getHotel().getId()){
            System.out.println("Id chambre hotel "+reservation.getChambre().getHotel().getId()+" VS Id excursion hotel "+excursion.getHotel().getId() );
            success = false;
        }
        int placeDisponible = excursion.getNombrePlace();
        int nombrePlaceInscript = excursion.getReservations().size();
        
        if(placeDisponible <= nombrePlaceInscript){
           System.out.println("Nombre de place dispo "+placeDisponible+" VS nombre de PlaceInscript "+nombrePlaceInscript);
           success = false;
        }
        
        if(reservation.getDateDebut().getTime() > excursion.getDateDebut().getTime() || reservation.getDateFin().getTime() < excursion.getDateFin().getTime()){
            System.out.println("time "+reservation.getDateDebut().getTime()+" < "+excursion.getDateDebut().getTime()+" && "+ reservation.getDateFin().getTime()+" > "+excursion.getDateFin().getTime());
            success = false;
        }
        
        if(success){
            Collection<Excursion> tamponExcursion = reservation.getExcursions();
            tamponExcursion.add(excursion);
            reservation.setExcursions(tamponExcursion);
            getEntityManager().merge(reservation);getEntityManager().flush();
        }
        
        
        System.out.println("Le résultat de la tentative d'ajout de l'excursion  dans la réservation est  "+success);
        return String.valueOf(success);
    }
    
    /**
     * Supprime l'objet entity de type excursion dans la persistence de données.
     * @param id l'identifiant de l'excursion à supprimer
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    /**
     * Retourne l'objet représentatif de l'excursion
     * @param id l'identifiant de l'excursion
     * @return l'excursion correspondant à l'identifiant
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Excursion find(@PathParam("id") Long id) {
        return super.find(id);
    }

    /**
     * La liste complète de tous les excursions
     * @return List<Excursion>
     */
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Excursion> findAll() {
        return super.findAll();
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Excursion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     *
     * @return String le nombre au format chaine de caractère du nombre d'occurence d'excursion trouvé dans la base.
     */
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    /**
     * Retourne les excursions qui correspondent aux critères sélectionnés
     * @see ExcursionResult
     * @param idHotel identifiant de l'hotel rattaché à l'excursion
     * @param dateDebut date de début de l'excursion
     * @param dateFin date de fin de l'excursion
     * @param prixSup selectionnera les excursion avec les prix supérieurs au prix indiqué dans cette variable
     * @param prixInf selectionnera les excursion avec les prix inférieurs au prix indiqué dans cette variable
     * @param prix selectionnera les excursions avec le prix indique au prix indiqué dans cette variable
     * @param theme selectionnera les excursions avec le theme indiqué dans celle-ci
     * @return List<ExcursionResult> la liste des excursions qui valide toutes les contraintes imposées par les paramètres
     */
    @GET
    @Path("getExcursion")
    @Produces({"application/xml", "application/json"})
    public List<ExcursionResult> findAllByFilter(@QueryParam("idHotel") @DefaultValue("-1") Long idHotel,
                                       @QueryParam("dateDebut") @DefaultValue("-1") Long dateDebut,
                                       @QueryParam("dateFin") @DefaultValue("-1") Long dateFin, 
                                       @QueryParam("prixSup") @DefaultValue("-1") double prixSup, 
                                       @QueryParam("prixInf") @DefaultValue("-1") double prixInf, 
                                       @QueryParam("prix") @DefaultValue("-1") double prix,
                                       @QueryParam("theme") @DefaultValue("null") String theme) {
        
        int start = 0;
        Timestamp datedeb = null;
        Timestamp datefi = null; 
        String request = "select e from Excursion e where ";
        if(idHotel != -1){
            request += " e.hotel.idHotel = "+idHotel;
            start++;
        }
        if(dateDebut != -1){
            if(start > 0){request += " AND ";}
             datedeb = new Timestamp(dateDebut);
            request += " e.dateDebut = :debut";
            start++;
        }
        if(dateFin != -1){
            if(start > 0){request += " AND ";}
            datefi = new Timestamp(dateFin);
            request += " e.dateFin = :fin";
            start++;
        }
        if(prixSup != -1){
            if(start > 0){request += " AND ";}
            request += " e.prix > "+prixSup;
            start++;
        }
        if(prixInf != -1){
            if(start > 0){request += " AND ";}
            request += " e.prix < "+prixInf;
            start++;
        }
        if(prix != -1){
            if(start > 0){request += " AND ";}
            request += " e.prix = "+prix;
            start++;
        }
        if(!theme.equals("null")){
            if(start > 0){request += " AND ";}
            request += " e.theme = '"+theme+"'";
            start++;
        }
        
        Query query = getEntityManager().createQuery(request);
        
        if(datedeb != null){
            query.setParameter("debut", datedeb);
        }
        if(datefi != null){
            query.setParameter("fin", datefi);
        }
        
        ArrayList<ExcursionResult> result = new ArrayList<ExcursionResult>();
        List<Excursion> excursions = query.getResultList();
        
        for (Excursion excursion : excursions) {
            System.out.println(" Dans find all by filter = > id Excursion = "+excursion.getId());
            ExcursionResult excurs = new ExcursionResult();
            
            excurs.setIdExcursion(excursion.getId());
            excurs.setIdHotel(excursion.getHotel().getId());
            excurs.setPrix(excursion.getPrix());
            excurs.setTheme(excursion.getTheme());
            excurs.setNombrePlace(excursion.getNombrePlace());
            excurs.setDateDebut(new java.util.Date(excursion.getDateDebut().getTime()));
            excurs.setDateFin(new java.util.Date(excursion.getDateFin().getTime()));
            
            
            
            List<Long> idReservations = new ArrayList<Long>();
            for(Reservation res : excursion.getReservations()){
                idReservations.add(res.getId());
            }
            excurs.setIdReservations(idReservations);
           
            result.add(excurs);
        }
        return result.subList(0, result.size());
    }

    /**
     * Retourne un couple Excursion et nombre de place disponible pour celle-ci représenté par la classe ExcursionByPlaceDisponible
     * @see ExcursionByPlaceDisponible
     * @return List<ExcursionByPlaceDisponible>
     */
    @GET
    @Path("getExcursionByNbrePlaceDisponible")
    @Produces({"application/xml", "application/json"})
    public List<ExcursionByPlaceDisponible> getExcursionByNbrePlaceDisponible(){
        
        Query query = getEntityManager().createQuery("select e from Excursion e");
        
        ArrayList<ExcursionByPlaceDisponible> excursionresult = new ArrayList<ExcursionByPlaceDisponible>();
        List<Excursion> excursions = query.getResultList();
        
        for (Excursion excursion : excursions) {
            System.out.println(excursion.getHotel().getId());
        }
        
        for (Excursion excursion : excursions) {
            
            Query queryPlus = getEntityManager().createQuery("select count(r) from Reservation r join r.excursions e where e.idExcursion =  "+excursion.getId());
            Integer nombreReservation = excursion.getNombrePlace() - ((Long)queryPlus.getSingleResult()).intValue();
            
            ExcursionByPlaceDisponible excursionTampon = new ExcursionByPlaceDisponible();
            excursionTampon.setIdExcursion(excursion.getId());
            excursionTampon.setNbrePlaceDisponible(nombreReservation);
            
            excursionresult.add(excursionTampon);
        }
        return excursionresult;
    }
    
    /**
     * Retourne la liste des excursions d'un client
     * @see ExcursionResult
     * @param idClient identifiant du client
     * @return List<ExcursionResult> la liste des excursions pour un client identifié par son id
     */
    @GET
    @Path("getExcursionByClient")
    @Produces({"application/xml", "application/json"})
    public List<ExcursionResult> getExcursionByClient(@QueryParam("idClient") Long idClient){
    
        Query query = getEntityManager().createQuery("select e from Excursion e join e.reservations r join r.client c where c.idClient = "+idClient);
        
        ArrayList<ExcursionResult> excursionresult = new ArrayList<ExcursionResult>();
        List<Excursion> excursions = query.getResultList();
        
        for( Excursion excursion : excursions){
           
            ExcursionResult excurs = new ExcursionResult();
            
            excurs.setIdExcursion(excursion.getId());
            excurs.setIdHotel(excursion.getHotel().getId());
            excurs.setPrix(excursion.getPrix());
            excurs.setTheme(excursion.getTheme());
            excurs.setNombrePlace(excursion.getNombrePlace());
            excurs.setDateDebut(new java.util.Date(excursion.getDateDebut().getTime()));
            excurs.setDateFin(new java.util.Date(excursion.getDateFin().getTime()));
            List<Long> idRe = new ArrayList<Long>();
            for(Reservation re :excursion.getReservations()){
                idRe.add(re.getId());
            }
            excurs.setIdReservations(idRe);
                
            excursionresult.add(excurs);
        }
        
        return excursionresult;
    }
    
    /**
     * Retourne la liste des excursions dans une période donnée et qui on un nombre d'inscription supérieur au nombre donné en paramètre
     * @param timestampDebut date de début de la période de recherche
     * @param timestampFin date de fin de la période de recherche
     * @param nbreVisit le nombre de visite minimum pour filtrer les excursions
     * @return List<ExcursionPopulaire> la liste des excursions qui ont un nombre inscrit supérieur à celui paramétré
     */
    @GET
    @Path("getMostReservExcursion")
    @Produces({"application/xml", "application/json"})
    public List<ExcursionPopulaire> getMostReservExcursion(@QueryParam("dateDebut") Long timestampDebut,
                                                        @QueryParam("dateFin") Long timestampFin,
                                                        @QueryParam("nombreVisiteur") @DefaultValue("0") int nbreVisit){
        
        Query query = getEntityManager().createQuery("select e, count(r) from Excursion e join e.reservations r where e.dateDebut >= :dateDeb and e.dateDebut <= :dateFin group by e having count(r)>="+nbreVisit,Object[].class);

        query.setParameter("dateDeb", new Timestamp(timestampDebut));
        query.setParameter("dateFin", new Timestamp(timestampFin));
        
        ArrayList<ExcursionPopulaire> excursionresult = new ArrayList<ExcursionPopulaire>();
        List<Object[]> excursions = query.getResultList();
        //System.out.println("");
        for( Object[] excursion : excursions){ 
           
            ExcursionPopulaire excurs = new ExcursionPopulaire();
            excurs.setIdExcursion(((Excursion)excursion[0]).getId());
            excurs.setNbreParticipant((Long) excursion[1]);
            
            excursionresult.add(excurs);
        }
        
        return excursionresult;
    }
    
    /**
     * Supprime la réservation d'inscription en fournissant le couple identifiant de réservation et d'identifiant d'excursion
     * @param idReservation identifiant de la réservation 
     * @param idExcursion identifiant de l'excursion
     * @return String chaine de caractère true|false validant la suppression de l'excursion pour une réservation
     */
    @GET
    @Path("deleteInscriptionExcursion")
    @Produces({"application/xml", "application/json"})
    public String deleteInscriptionExcursion(@QueryParam("idReservation") Long idReservation, @QueryParam("idExcursion") Long idExcursion){
        
        Boolean success = false;
        try{
            
            String request = "DELETE FROM USERS_BOOKING.EXCURSION_RESERVATION WHERE RESERVATION_ID = "+idReservation+" AND EXCURSION_ID = "+idExcursion;
            Query query = getEntityManager().createNativeQuery(request);

            query.executeUpdate();
            
            success = true;
            
        }catch(NoResultException nre){
            nre.printStackTrace();
        }
        
        return String.valueOf(success);
    }
    
    /**
     * La fonction qui retourne l'objet permettant d'intéragir avec les données persistantes.
     * @see EntityManager
     * @return EntityManager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
