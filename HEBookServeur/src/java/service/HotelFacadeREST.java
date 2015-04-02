/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
import org.hebook.querymodel.ChambrePerHotel;
import org.hebook.querymodel.ChambreResult;
import org.hebook.querymodel.HotelResult;

/**
 *
 * Classe qui fournit les services utiles pour l'acces à l'élément de l'Hotel. C'est-à-dire de la création à la suppression
 * @author Emmanuel
 * @version 1.0  
 */
@Stateless
@Path("org.hebook.model.hotel")
public class HotelFacadeREST extends AbstractFacade<Hotel> {
    @PersistenceContext(unitName = "HEBookProjetPU")
    private EntityManager em;

    /**
     *
     */
    public HotelFacadeREST() {
        super(Hotel.class);
    }

    /**
     * Ajoute l'objet entity de type hotel dans la persistence de données, dans notre cas la base de données.
     * @param entity l'hotel à rajouter
     */
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Hotel entity) {
        super.create(entity);
    }

    /**
     * Edite l'objet entity de type hotel dans la persistence de données retrouvé par son id.
     * @param entity l'objet hotel que l'on souhaite éditer
     */
    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Hotel entity) {
        super.edit(entity);
    }

    /**
     * Supprime l'objet entity de type hotel dans la persistence de données.
     * @param id l'identifiant de l'hotel à supprimer
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    /**
     *
     * Retourne l'objet représentatif de l'hotel
     * @param id l'identifiant de l'hotel
     * @return l'hotel correspondant à l'identifiant
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Hotel find(@PathParam("id") Long id) {
        return super.find(id);
    }

    /**
     * Similaire à find mais avec un objet HotelResult qui représente l'hotel et évite les boucles infinies
     * @see HotelResult
     * @param id l'identifiant de l'objet
     * @return HotelResult l'hotel identifié
     */
    @GET
    @Path("findWithoutLoop")
    @Produces({"application/xml", "application/json"})
    public HotelResult findWithoutLoop(@QueryParam("id") Long id) {
            
            Hotel hotel = super.find(id);
            HotelResult hot = new HotelResult();
            hot.setIdHotel(hotel.getId());
            hot.setNomHotel(hotel.getNomHotel());
            hot.setImageHotel(hotel.getImageHotel());
            hot.setDescription(hotel.getDescription());
            hot.setNombreChambre(hotel.getNombreChambre());
            hot.setNombreEtoile(hotel.getNombreEtoile());
            hot.setVille(hotel.getVille());
            List<Long> idChambres = new ArrayList<Long>();
            for(Chambre chambre : hotel.getChambre()){
                idChambres.add(chambre.getId());
            }
            hot.setIdChambres(idChambres);
            
            List<Long> idExcursion = new ArrayList<Long>();
            for(Excursion esxcurs : hotel.getExcursions()){
                idExcursion.add(esxcurs.getId());
            }
            hot.setIdExcursions(idExcursion);
            
            return hot;
    }
    
    /**
     * Retourne la liste de tout les hotels
     * @return List<Hotel> liste des hotels dans la base
     */
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Hotel> findAll() {
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
    public List<Hotel> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     *
     * @return String le nombre au format chaine de caractère du nombre d'occurence d'hotel trouvé dans la base.
     */
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    /**
     * Retourne les hotels qui correspondent aux critères sélectionnés
     * @see HotelResult
     * @param ville selectionnera les hotel avec la ville indiqué dans cette variable
     * @param nbreEtoile selectionnera les hotel avec le nombre d'étoile indiqué dans cette variable
     * @param prixSup selectionnera les hotel avec une chambre dont le prix de réservation d'une journée est supérieur à la valeur de la variable
     * @param prixInf selectionnera les hotel avec une chambre dont le prix de réservation d'une journée est inférieur à la valeur de la variable
     * @param prix selectionnera les hotel avec une chambre dont le prix de réservation d'une journée est égale à la valeur de la variable
     * @return List<HotelResult> la liste des hotels qui valide toutes les contraintes imposées par les paramètres
     */
    @GET
    @Path("getHotel")
    @Produces({"application/xml", "application/json"})
    public List<HotelResult> findAllByFilter(@QueryParam("ville") @DefaultValue("null") String ville,
                                       @QueryParam("nombreEtoile") @DefaultValue("-1") int nbreEtoile, 
                                       @QueryParam("prixSup") @DefaultValue("-1") double prixSup, 
                                       @QueryParam("prixInf") @DefaultValue("-1") double prixInf, 
                                       @QueryParam("prix") @DefaultValue("-1") double prix) {
        
        int start = 0;
        String request = "select distinct h FROM Hotel h join h.chambre c ";
        if(!ville.equals("null")){
            request += "where h.ville = '"+ville+"'";
            start++;
        }
        if(nbreEtoile != -1){
            if(start > 0){request += " AND ";}
            else if(start == 0){request += " where ";}
            request += " h.nombreEtoile ="+nbreEtoile;
            start++;
        }
        if(prixSup != -1){
            if(start > 0){request += " AND ";}
            else if(start == 0){request += " where ";}
            request += " c.prix > "+prixSup;
            start++;
        }
        if(prixInf != -1){
            if(start > 0){request += " AND ";}
            else if(start == 0){request += " where ";}
            request += " c.prix < "+prixInf;
            start++;
        }
        if(prix != -1){
            if(start > 0){request += " AND ";}
            else if(start == 0){request += " where ";}
            request += " c.prix = "+prix;
            start++;
        }
        
        Query query = getEntityManager().createQuery(request);
        
        ArrayList<HotelResult> result = new ArrayList<HotelResult>();
        List<Hotel> hotel = query.getResultList();
        
        for (Hotel hotels : hotel) {
            
            HotelResult hot = new HotelResult();
            hot.setIdHotel(hotels.getId());
            hot.setNomHotel(hotels.getNomHotel());
            hot.setDescription(hotels.getDescription());
            hot.setNombreChambre(hotels.getNombreChambre());
            hot.setNombreEtoile(hotels.getNombreEtoile());
            hot.setVille(hotels.getVille());
            List<Long> idChambres = new ArrayList<Long>();
            for(Chambre chambre : hotels.getChambre()){
                idChambres.add(chambre.getId());
            }
            hot.setIdChambres(idChambres);
            
            List<Long> idExcursion = new ArrayList<Long>();
            for(Excursion esxcurs : hotels.getExcursions()){
                idExcursion.add(esxcurs.getId());
            }
            hot.setIdExcursions(idExcursion);
           
            result.add(hot);
        }
        return result.subList(0, result.size());
    }
    
    
    /**
     * Retourne les hotels qui ont au moins une chambre non réservé
     * @see HotelResult
     * @return List<HotelResult> la liste d'hotel répondant au critère
     */
    @GET
    @Path("getHotelByDisponibilite")
    @Produces({"application/xml", "application/json"})
    public List<HotelResult> findAllByDisponibilite() {
        
        
        String request = "select distinct h from Hotel h join h.chambre c where c.idChambre not in"
                + "(select ch.idChambre from Reservation r join r.chambre ch where r.dateFin > CURRENT_TIMESTAMP)";
        Query query = getEntityManager().createQuery(request);
        
        ArrayList<HotelResult> result = new ArrayList<HotelResult>();
        List<Hotel> hotel = query.getResultList();
        
        for (Hotel hotels : hotel) {
            
            HotelResult hot = new HotelResult();
            hot.setIdHotel(hotels.getId());
            hot.setNomHotel(hotels.getNomHotel());
            hot.setDescription(hotels.getDescription());
            hot.setNombreChambre(hotels.getNombreChambre());
            hot.setNombreEtoile(hotels.getNombreEtoile());
            hot.setVille(hotels.getVille());
            List<Long> idChambres = new ArrayList<Long>();
            for(Chambre chambre : hotels.getChambre()){
                idChambres.add(chambre.getId());
            }
            hot.setIdChambres(idChambres);
           
            result.add(hot);
        }
        return result.subList(0, result.size());
    }
    
    /**
     * Retourne le couple hotel et nombre de chambre disponible représenté par la classe ChambrePerHotel
     * @see ChambrePerHotel 
     * @return List<ChambrePerHotel> la liste des hotels avec leurs nombres de chambres disponibles.
     */
    @GET
    @Path("getHotelAndChambreCountDisponible")
    @Produces({"application/xml", "application/json"})
    public List<ChambrePerHotel> findHotelAndChambreCountDisponible() {
        
        
        String request = "select count(c),c.hotel.idHotel from Chambre c where c.idChambre not in (select ch.idChambre from Reservation r join r.chambre ch where r.dateFin > CURRENT_TIMESTAMP) group by c.hotel";
        Query query = getEntityManager().createQuery(request,Object[].class);
        
        ArrayList<ChambrePerHotel> result = new ArrayList<ChambrePerHotel>();
        List<Object[]> hotel = query.getResultList();
        
        for (Object[] hotels : hotel) {
            
            ChambrePerHotel chbrePerHotel = new ChambrePerHotel();
            chbrePerHotel.setIdHotel((Long) hotels[1]);
            chbrePerHotel.setNbreChambre(((Long)hotels[0]).intValue());
            
            result.add(chbrePerHotel);
        }
        return result.subList(0, result.size());
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
