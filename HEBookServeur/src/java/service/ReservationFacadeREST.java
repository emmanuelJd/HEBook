/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.hebook.model.Chambre;
import org.hebook.model.Client;
import org.hebook.model.Excursion;
import org.hebook.model.Reservation;
import org.hebook.querymodel.RecapReservationByClient;
import org.hebook.querymodel.ReservationResult;

/**
 *
 * Classe qui fournit les services utiles pour l'acces à l'élément de Reservation. C'est-à-dire de la création à la suppression
 * @author Emmanuel
 * @version 1.0
 */
@Stateless
@Path("org.hebook.model.reservation")
public class ReservationFacadeREST extends AbstractFacade<Reservation> {
    @PersistenceContext(unitName = "HEBookProjetPU")
    private EntityManager em;

    /**
     *
     */
    public ReservationFacadeREST() {
        super(Reservation.class);
    }

    /**
     * Ajoute l'objet entity de type Reservation dans la persistence de données, dans notre cas la base de données.
     * @param entity la réservation à rajouter
     */
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Reservation entity) {
        super.create(entity);
    }

    /**
     *
     * Version modifié de la création d'une réservation car la précédente ne fonctionne pas correctement.
     * @param entity la réservation à ajouter
     * @see ReservationResult
     * @return String true | false en chaine de caractère pour confirmer ou non la création de la réservation
     */
    @POST
    @Path("createSecure")
    @Consumes({"application/xml", "application/json"})
    public String createSecure(ReservationResult entity){
        
        String createSucess = "true";
        boolean createProcess = true;
        Date now = new Date();
        if(entity.getDateDebut() > entity.getDateFin() || now.getTime() > entity.getDateDebut()){

            System.out.println("je ne devrais pas passez par ici  lvl 63 ReservationFacade. now() = "+now.getTime()+ " date Début de la reservation = "+entity.getDateDebut());
           createSucess = "false";
        }else{
            try{
                Query queryclient = getEntityManager().createQuery("select c from Client c where c.idClient = "+entity.getIdClient());
                Client client = (Client)queryclient.getSingleResult();

                Query querychambreverif = getEntityManager().createQuery("select r from Reservation r join r.chambre c where c.idChambre = "+entity.getIdChambre());
                List<Reservation> re = querychambreverif.getResultList();

                for(Reservation rese : re){

                    if(rese.getDateFin().getTime() > entity.getDateDebut()){
                        createProcess = false;

                    }
                }

                Query querychambre = getEntityManager().createQuery("select c from Chambre c where c.idChambre = "+entity.getIdChambre());
                Chambre chambre = (Chambre)querychambre.getSingleResult();

                List<Excursion> excursion = new ArrayList<Excursion>();
                for(Long idEx : entity.getIdExcursions()){

                    Query queryexcursion = getEntityManager().createQuery("select e from Excursion e where e.idExcursion = "+idEx);
                    Excursion excurs = (Excursion)queryexcursion.getSingleResult();

                    excursion.add(excurs);
                }

                Reservation reservation = new Reservation();

                Query queryid = getEntityManager().createQuery("select r from Reservation r order by r.idReservation desc");
                List<Reservation> reservat = queryid.setMaxResults(1).getResultList();

                if(reservat.size()>0){
                    Long idC = reservat.get(0).getId();
                    reservation.setId((idC+1));
                }else{
                    reservation.setId(1L);
                }

                reservation.setChambre(chambre);
                reservation.setClient(client);
                reservation.setExcursions(excursion);
                reservation.setNumeroCarteBleu(entity.getNumeroCarteBleu());
                reservation.setDateFin(new java.sql.Date(entity.getDateFin()));
                reservation.setDateDebut(new java.sql.Date(entity.getDateDebut()));
                if(createProcess){
                    
                    Query query = getEntityManager().createNativeQuery("INSERT INTO USERS_BOOKING.HEBOOKING_RESERVATION (RESERVATION_ID, CLIENT_ID, CHAMBRE_ID, RESERVATION_DEBUT, RESERVATION_FIN, RESERVATION_NUMERO_CARTE_BLEU) VALUES(?, ?, ?, ?, ?, ?)");
                    query.setParameter(1, reservation.getId());
                    query.setParameter(2, reservation.getClient().getId());
                    query.setParameter(3, reservation.getChambre().getId());
                    query.setParameter(4, new Timestamp(reservation.getDateDebut().getTime()));
                    query.setParameter(5, new Timestamp(reservation.getDateFin().getTime()));
                    query.setParameter(6, reservation.getNumeroCarteBleu());
                    
                    System.out.println("Insert working or not ? verifie dans la base de donnée "+query.toString());
                    
                    query.executeUpdate();
                    
                    ExcursionFacadeREST excu = new ExcursionFacadeREST();
                    for(int i =0;i < excursion.size();i++){
                        System.out.println("excursion => "+excursion.get(i).getId()+" || reservation = > "+reservation.getId());
                        Query queryInsert = getEntityManager().createNativeQuery("INSERT INTO USERS_BOOKING.EXCURSION_RESERVATION (RESERVATION_ID, EXCURSION_ID) VALUES(?,?)");
                        queryInsert.setParameter(1, reservation.getId());
                        queryInsert.setParameter(2, excursion.get(i).getId());
                        queryInsert.executeUpdate();
                    }
                }else{
                    createSucess = "false";
                }

            }catch(Exception e){
                
                System.out.println("Erreur ligne =>118 fichier ReservationFacadeRest" + e);
            }
        }
        return createSucess;
    }
    
    /**
     * Edite l'objet entity de type réservation dans la persistence de données retrouvé par son id.
     * @param entity l'objet réservation que l'on souhaite éditer
     */
    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Reservation entity) {
        super.edit(entity);
    }

    /**
     * Supprime l'objet entity de type réservation dans la persistence de données.
     * @param id l'identifiant de la réservation à supprimer
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    /**
     * Retourne l'objet représentatif de la réservation
     * @see ReservationResult
     * @param id identifiant de la réservation
     * @return ReservationResult l'objet représentatif de la réservation
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public ReservationResult find(@PathParam("id") Long id) {
        ReservationResult reservResult = new ReservationResult();
        try{
            Reservation reservation = super.find(id);

            reservResult.setIdChambre(reservation.getChambre().getId());
            reservResult.setIdClient(reservation.getClient().getId());
                        
            reservResult.setDateDebut(reservation.getDateDebut().getTime());
            reservResult.setDateFin(reservation.getDateFin().getTime());
            
            System.out.println("Debut dans reservationResult en timestamp  => "+reservResult.getDateDebut());
            System.out.println("Fin dans reservationResult en timestamp  => "+reservResult.getDateFin());
            
            ArrayList<Long> excurs= new ArrayList<Long>();
            for(Excursion exc :reservation.getExcursions() ){
                excurs.add(exc.getId());
            }
            reservResult.setIdExcursions(excurs);
            reservResult.setIdReservation(reservation.getId());
            reservResult.setNumeroCarteBleu(reservation.getNumeroCarteBleu());
            
        }catch(Exception except){
            except.printStackTrace();
        }
        
        return reservResult;
    }

    /**
     * Retourne la liste complète de Reservation
     * @return List<Reservation>
     */
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Reservation> findAll() {
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
    public List<Reservation> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     *
     * @return String le nombre au format chaine de caractère du nombre d'occurence de réservation trouvé dans la base.
     */
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    
    /**
     * Valide pour un type de carte donnée sa validité
     * @param typeCarte le type de carte peut être master card, carte visa, etc. 
     * @param numero le numéro de la carte qui doit valider le format du type choisi
     * @return Boolean true|false qui valide ou non le numéro d'une carte pour un type de carte donnée
     */
    @GET
    @Path("getVerifCard")
    @Produces({"application/xml", "application/json"})
    public Boolean getVerifCard(@QueryParam("typeCarte") int typeCarte,@QueryParam("numero") String numero){
        
        boolean validate = false;
        
        switch(typeCarte){
            /*carte visa Card*/
            case 1: validate = numero.matches("^([4]{1})([0-9]{12,15})$");
                    break;
            /*carte insta Payment Card */  
            case 2: validate = numero.matches("^63[7-9][0-9]{13}$");
                    break;
            /* carte de Maestro Carde */
            case 3: validate = numero.matches("^(5018|5020|5038|6304|6759|6761|6763)[0-9]{8,15}$");
                    break;
        };
 
        return validate;
    }
    
    /**
     *  Retourne une liste récapitulatif des réservations du client, pour chaque réservation le temps écoulé le prix par jour, ainsi que le prix total
     * @param idClient identifiant client
     * @see RecapReservationByClient
     * @return List<RecapReservationByClient>
     */
    @GET
    @Path("getListReservationByClient")
    @Produces({"application/xml", "application/json"})
    public List<RecapReservationByClient> listReservationByClient(@QueryParam("idClient") Long idClient){
        
        String request = "select c, r, ch  from Client c join c.reservations r join r.chambre ch where c.idClient = "+idClient;
        Query query = getEntityManager().createQuery(request);
        
        ArrayList<RecapReservationByClient> result = new ArrayList<RecapReservationByClient>();
        List<Object[]> reservationQuery = query.getResultList();
        
        for (Object[] reservation : reservationQuery) {
            
            RecapReservationByClient reserv = new RecapReservationByClient();
            Client client = (Client)reservation[0];
            Reservation reservationRequest = (Reservation) reservation[1];
            Chambre chambre = (Chambre) reservation[2];
            int duree = (int) (((new java.util.Date(reservationRequest.getDateFin().getTime())).getTime() - (new java.util.Date(reservationRequest.getDateDebut().getTime())).getTime())/86400000) ;//reservationRequest.getDateDebut()
            double price = chambre.getPrix() * duree;
            
            reserv.setIdClient(client.getId());
            reserv.setIdReservation(reservationRequest.getId());
            reserv.setNombreJour(duree);
            reserv.setPrixSejour(chambre.getPrix());
            reserv.setPrixTotal(price);
            
            result.add(reserv);
        }
        return result.subList(0, result.size());
    }
    
    
    /**
     * Retourne la liste des réservations pour un client
     * @see ReservationResult
     * @param idClient identifiant client
     * @return List<ReservationResult>
     */
    @GET
    @Path("getReservationByClient")
    @Produces({"application/xml", "application/json"})
    public List<ReservationResult> reservationByClient(@QueryParam("idClient") Long idClient){
        
        String request = "select r from Reservation r where r.client.idClient = "+idClient;
        Query query = getEntityManager().createQuery(request);
        
        ArrayList<ReservationResult> result = new ArrayList<ReservationResult>();
        List<Reservation> reservationQuery = query.getResultList();
        
        for (Reservation reservation : reservationQuery) {
            
            ReservationResult reserv = new ReservationResult();
            
            reserv.setIdClient(reservation.getClient().getId());
            reserv.setDateDebut(reservation.getDateDebut().getTime());
            reserv.setDateFin(reservation.getDateFin().getTime());
            reserv.setIdChambre(reservation.getChambre().getId());
            reserv.setIdReservation(reservation.getId());
            reserv.setNumeroCarteBleu(reservation.getNumeroCarteBleu());
            
            List<Long> idExcursions = new ArrayList<Long>();
            for(Excursion excursion: reservation.getExcursions()){
                idExcursions.add(excursion.getId());
            }
            reserv.setIdExcursions(idExcursions);
            
            
            result.add(reserv);
        }
        return result.subList(0, result.size());
    }
    
    /**
     * Edite une réservation 
     * @see ReservationResult
     * @param reservation
     * @return Boolean true | false si l'édition à réussi ou pas
     */
    @POST
    @Path("editReservation")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/json"})
    public Boolean editReservation(ReservationResult reservation){
        
        Boolean success = true;
        try{
            Date now = new Date();
            
            if(reservation.getDateDebut() > reservation.getDateFin()){
                success = false;
            }else{
                if(success){
                    try{
                        Query query = getEntityManager().createNativeQuery("UPDATE USERS_BOOKING.HEBOOKING_RESERVATION SET CLIENT_ID = ?, CHAMBRE_ID = ?, RESERVATION_DEBUT = ?, RESERVATION_FIN = ?, RESERVATION_NUMERO_CARTE_BLEU = ? WHERE RESERVATION_ID = ?");
                        query.setParameter(1, reservation.getIdClient());
                        query.setParameter(2, reservation.getIdChambre());
                        query.setParameter(3, new Timestamp(reservation.getDateDebut()));
                        query.setParameter(4, new Timestamp(reservation.getDateFin()));
                        query.setParameter(5, reservation.getNumeroCarteBleu());
                        query.setParameter(6, reservation.getIdReservation());

                        query.executeUpdate();
                        
                        Query queryDelete = getEntityManager().createNativeQuery("DELETE FROM USERS_BOOKING.EXCURSION_RESERVATION WHERE RESERVATION_ID = ?");
                        queryDelete.setParameter(1, reservation.getIdReservation());
                        queryDelete.executeUpdate();
                        ExcursionFacadeREST excu = new ExcursionFacadeREST();
                        for(int i =0;i < reservation.getIdExcursions().size();i++){
                            
                            System.out.println("Excursion = > "+reservation.getIdExcursions().get(i) + " la reservation = > "+reservation.getIdReservation());
                            //excu.addReservationSecure(reservation.getIdExcursions().get(i), reservation.getIdReservation());
                            boolean successEx = true;
                            System.out.println("Les deux parametre => reserv : "+reservation.getIdReservation()+ " & Excursion : "+reservation.getIdExcursions().get(i));
                            Query queryReservation = getEntityManager().createQuery("select r from Reservation r where r.idReservation = "+reservation.getIdReservation());
                            Reservation reservationx = (Reservation) queryReservation.getSingleResult();
                            
                            Query queryRecupExcursion = getEntityManager().createQuery("select e from Excursion e where e.idExcursion = "+reservation.getIdExcursions().get(i));
                            Excursion excursion = (Excursion) queryRecupExcursion.getSingleResult();
                            //Excursion excursionx = find(reservation.getIdExcursions().get(i));

                            if(reservationx.getChambre().getHotel().getId() != excursion.getHotel().getId()){
                                System.out.println("Id chambre hotel "+reservationx.getChambre().getHotel().getId()+" VS Id excursion hotel "+excursion.getHotel().getId() );
                                successEx = false;
                            }
                            int placeDisponible = excursion.getNombrePlace();
                            int nombrePlaceInscript = excursion.getReservations().size();

                            if(placeDisponible <= nombrePlaceInscript){
                               System.out.println("Nombre de place dispo "+placeDisponible+" VS nombre de PlaceInscript "+nombrePlaceInscript);
                               successEx = false;
                            }

                            if(reservationx.getDateDebut().getTime() > excursion.getDateDebut().getTime() || reservationx.getDateFin().getTime() < excursion.getDateFin().getTime()){
                                System.out.println("time 2 : "+reservationx.getDateDebut().getTime()+" < "+excursion.getDateDebut().getTime()+" && "+ reservationx.getDateFin().getTime()+" > "+excursion.getDateFin().getTime());
                                successEx = false;
                            }

                            if(successEx){
                                 Query queryInsert = getEntityManager().createNativeQuery("INSERT INTO USERS_BOOKING.EXCURSION_RESERVATION (RESERVATION_ID, EXCURSION_ID) VALUES(?,?)");
                                 queryInsert.setParameter(1, reservation.getIdReservation());
                                 queryInsert.setParameter(2, excursion.getId());
                                 queryInsert.executeUpdate();
                                 System.out.println();
                            }

                            //entity.getIdExcursion();

                            System.out.println("Le résultat de la tentative d'ajout de l'excursion  dans la réservation est  "+successEx);
                        
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Erreur 373 ReservationFacade => "+e);
                    }
                    
                }else{
                    success = false;
                }
            }
  
        }catch(NoResultException nre){
            nre.printStackTrace();
            System.out.println("Erreur 383 ReservationFacade => "+nre);
        }
        
        return success;
    }
    
    /**
     * Supprime une réservation identifié par son id
     * @param idReservation identifiant de la réservation
     */
    @GET
    @Path("deleteReservation")
    @Produces({"application/xml", "application/json"})
    public void deleteReservation(@QueryParam("idReservation") Long idReservation){
        
        try{
            
            String requestDeux = "DELETE FROM USERS_BOOKING.EXCURSION_RESERVATION WHERE RESERVATION_ID = "+idReservation;
            Query queryDeux = getEntityManager().createNativeQuery(requestDeux);
            
            String requestUn = "DELETE FROM USERS_BOOKING.HEBOOKING_RESERVATION WHERE RESERVATION_ID = "+idReservation;
            Query queryUn = getEntityManager().createNativeQuery(requestUn);

            queryDeux.executeUpdate();
            queryUn.executeUpdate();
                        
        }catch(NoResultException nre){
            System.out.println("Erreur!");
            nre.printStackTrace();
        }
    }
    
    /**
     *
     * La fonction qui retourne l'objet permettant d'intéragir avec les données persistantes.
     * @see EntityManager
     * @return EntityManager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
