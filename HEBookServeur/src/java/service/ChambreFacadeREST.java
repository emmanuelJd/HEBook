/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
import org.hebook.querymodel.ChambreResult;

/**
 * Classe qui fournit les services utiles pour l'acces à l'élément Chambre. C'est-à-dire de la création à la suppression
 * @author Emmanuel
 * @version 1.0
 */
@Stateless
@Path("org.hebook.model.chambre")
public class ChambreFacadeREST extends AbstractFacade<Chambre> {
    @PersistenceContext(unitName = "HEBookProjetPU")
    private EntityManager em;

    /**
     *
     */
    public ChambreFacadeREST() {
        super(Chambre.class);
    }

    /**
     * Ajoute l'objet entity de type chambre dans la persistence de données, dans notre cas la base de données.
     * @param entity la chambre à ajouter
     */
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Chambre entity) {
        super.create(entity);
    }

    /**
     * Edite l'objet entity de type chambre dans la persistence de données retrouvé par son id.
     * @param entity l'objet chambre que l'on souhaite éditer.
     */
    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Chambre entity) {
        super.edit(entity);
    }

    /**
     * Supprime l'objet entity de type chambre dans la persistence de données.
     * @param id l'id de l'objet chambre que l'on souhaite supprimer
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    /**
     * Retourne un objet représentatif de l'objet chambre
     * @param id identifiant de la chambre que l'on souhaite retourner
     * @see ChambreResult
     * @return un objet de type ChambreResult
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public ChambreResult find(@PathParam("id") Long id) {
        
        Chambre chambre = super.find(id);
        ChambreResult chamb = new ChambreResult();
        chamb.setIdChambre(chambre.getId());
        chamb.setNumero(chambre.getNumero());
        chamb.setPrix(chambre.getPrix());
        chamb.setCategorie(chambre.getCategorie());
        chamb.setEtage(chambre.getEtage());
        chamb.setIdHotel(chambre.getHotel().getId());
        
        return chamb;
    }

    /**
     * Retourne la liste de toute les chambres enregistrées dans la base de données.
     * @return List<Chambre> la liste de toutes les chambres enregistrées
     */
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Chambre> findAll() {
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
    public List<Chambre> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     *
     * @return String le nombre au format chaine de caractère du nombre d'occurence de chambre trouvé dans la base.
     */
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    /**
     * Retourne toutes les chambres liées à un hotel représenté par son identifiant
     * @see ChambreResult
     * @param idHotel identifiant de l'hotel
     * @return List<ChambreResult> liste des chambres associéesà l'hotel
     */
    @GET
    @Path("getChambreByHotel")
    @Produces({"application/xml", "application/json"})
    public List<ChambreResult> findChambreByHotel(@QueryParam("idHotel") Long idHotel){

        Query query = em.createQuery("select c from Chambre c join c.hotel h where h.idHotel="+idHotel);
        
        List<Chambre> chambres = query.getResultList();

        ArrayList<ChambreResult> result = new ArrayList<ChambreResult>();
        for (Chambre chambre : chambres) {
            
            ChambreResult chamb = new ChambreResult();
            
            chamb.setIdChambre(chambre.getId());
            chamb.setNumero(chambre.getNumero());
            chamb.setPrix(chambre.getPrix());
            chamb.setCategorie(chambre.getCategorie());
            chamb.setEtage(chambre.getEtage());
            chamb.setIdHotel(chambre.getHotel().getId());
            result.add(chamb);
        }
        
        return result;
    }
    
    /**
     * Retourne les chambres disponibles de l'hotel c'est-à-dire ceux sans réservation ou sans réservation active.
     * @see ChambreResult
     * @param idHotel identifiant de l'hotel
     * @return List<ChambreResult> liste des chambres répondant au critère
     */
    @GET
    @Path("getChambreByHotelDispo")
    @Produces({"application/xml", "application/json"})
    public List<ChambreResult> findChambreByHotelDisponible(@QueryParam("idHotel") Long idHotel){

        Query query = em.createQuery("select ch from Reservation r join r.chambre ch join ch.hotel h where h.idHotel="+idHotel+" and r.dateFin < CURRENT_TIMESTAMP");
        
        List<Chambre> chambres = query.getResultList();

        
        Query queryDeux = em.createQuery("select c from Chambre c join c.hotel h where h.idHotel="+idHotel+" and c.idChambre NOT IN (select ch.idChambre from Reservation r join r.chambre ch join ch.hotel ho where ho.idHotel="+idHotel+" )");
        
        List<Chambre> chambresDeux = queryDeux.getResultList();
        
        
        ArrayList<ChambreResult> result = new ArrayList<ChambreResult>();
        for (Chambre chambre : chambres) {
            
            ChambreResult chamb = new ChambreResult();
            
            chamb.setIdChambre(chambre.getId());
            chamb.setNumero(chambre.getNumero());
            chamb.setPrix(chambre.getPrix());
            chamb.setCategorie(chambre.getCategorie());
            chamb.setEtage(chambre.getEtage());
            chamb.setIdHotel(chambre.getHotel().getId());
            result.add(chamb);
        }
        
        for (Chambre chambre : chambresDeux) {
            
            ChambreResult chamb = new ChambreResult();
            
            chamb.setIdChambre(chambre.getId());
            chamb.setNumero(chambre.getNumero());
            chamb.setPrix(chambre.getPrix());
            chamb.setCategorie(chambre.getCategorie());
            chamb.setEtage(chambre.getEtage());
            chamb.setIdHotel(chambre.getHotel().getId());
            result.add(chamb);
        }
        
        return result;
    }
    
    
    /**
     * Retourne les chambres indisponibles de l'hotel c'est-à-dire ceux avec une réservation active.
     * @see ChambreResult
     * @param idHotel identifiant de l'hotel
     * @return List<ChambreResult> liste des chambres répondant au critère
     */
    @GET
    @Path("getChambreByHotelNoDispo")
    @Produces({"application/xml", "application/json"})
    public List<ChambreResult> findChambreByHotelNoDisponible(@QueryParam("idHotel") Long idHotel){

        Query query = em.createQuery("select ch from Reservation r join r.chambre ch join ch.hotel h where h.idHotel="+idHotel+" and r.dateFin > CURRENT_TIMESTAMP");
        
        List<Chambre> chambres = query.getResultList();

        ArrayList<ChambreResult> result = new ArrayList<ChambreResult>();
        for (Chambre chambre : chambres) {
            
            ChambreResult chamb = new ChambreResult();
            
            chamb.setIdChambre(chambre.getId());
            chamb.setNumero(chambre.getNumero());
            chamb.setPrix(chambre.getPrix());
            chamb.setCategorie(chambre.getCategorie());
            chamb.setEtage(chambre.getEtage());
            chamb.setIdHotel(chambre.getHotel().getId());
            result.add(chamb);
        }
        
        return result;
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
