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
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.hebook.model.Client;
import org.hebook.model.Reservation;
import org.hebook.querymodel.ClientResult;

/**
 *
 * Classe qui fournit les services utiles pour l'acces à l'élément Client. C'est-à-dire de la création à la suppression
 * @author Emmanuel
 * @version 1.0     
 */
@Stateless
@Path("org.hebook.model.client")
public class ClientFacadeREST extends AbstractFacade<Client> {
    @PersistenceContext(unitName = "HEBookProjetPU")
    private EntityManager em;

    /**
     *
     */
    public ClientFacadeREST() {
        super(Client.class);
    }

    /**
     * Ajoute l'objet entity de type client dans la persistence de données, dans notre cas la base de données.
     * @param entity le client à ajouter
     */
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Client entity) {
        Query query = getEntityManager().createQuery("select c from Client c order by c.idClient desc");
        List<Client> client = query.setMaxResults(1).getResultList();
        if(client.size()>0){
            Long idC = client.get(0).getId();
            entity.setId((idC+1));
        }else{
            entity.setId(1L);
        }
        
        super.create(entity);
    }

    
    /**
     * Version modifié de la création du client car la précédente ne fonctionne pas correctement.
     * @param entity le client à ajouter
     * @return true|false Retourne un String de validité de l'ajout
     */
    @POST
    @Path("createSecure")
    @Consumes({"application/xml", "application/json"})
    public String createSecure(Client entity) {
               
        String createSucess = "false";
        Query queryverification = getEntityManager().createQuery("select c from Client c where c.nomClient = '"+entity.getNomClient()+"' and c.prenomClient='"+entity.getPrenomClient()+"' and c.motdepasse = '"+entity.getMotdepasse()+"'");
        List<Client> clientverif = queryverification.setMaxResults(1).getResultList();
        if(clientverif.isEmpty()){
            Query query = getEntityManager().createQuery("select c from Client c order by c.idClient desc");
            List<Client> client = query.setMaxResults(1).getResultList();
            if(client.size()>0){
                Long idC = client.get(0).getId();
                entity.setId((idC+1));
            }else{
                entity.setId(1L);
            }

            super.create(entity);
            createSucess = "true";
        }
        return createSucess;
    }
    
    /**
     * Edite l'objet entity de type client dans la persistence de données retrouvé par son id.
     * @param entity l'objet client que l'on souhaite éditer.
     */
    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Client entity) {
        super.edit(entity);
    }

    /**
     * Supprime l'objet entity de type client dans la persistence de données.
     * @param id l'id de l'objet client que l'on souhaite supprimer
     * 
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    /**
     * Retourne l'objet représentatif du client
     * @see ClientResult
     * @param id l'identifiant du client à retourner
     * @return ClientResult le client répondant au critère
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public ClientResult find(@PathParam("id") Long id) {
        
        
        Client client = super.find(id);
        ClientResult clientResult  = new ClientResult();
        clientResult.setIdClient(client.getId());
        clientResult.setNomClient(client.getNomClient());
        clientResult.setMotDePasse(client.getMotdepasse());
        clientResult.setPrenomClient(client.getPrenomClient());
        List<Long> reserv = new ArrayList<Long>();
        
        for(Reservation res : client.getReservations() ){
                reserv.add(res.getId());
        }
        clientResult.setReservations(reserv);
        
        return clientResult;
    }

    /**
     * La liste complète de tous les clients
     * @return List<Client>
     */
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Client> findAll() {
        return super.findAll();
    }
    
    /**
     * Similaire à la fonction findAll sauf qu'elle utilise un objet de type ClientResult qui représente un client.
     * @see ClientResult
     * @return List<ClientResult> la liste des clients représentés par l'objet ClientResult
     */
    @GET
    @Path("/findAllWithoutLoop")
    @Produces({"application/xml", "application/json"})
    public List<ClientResult> findAllWithoutLoop() {
        
        List<ClientResult> resultlist = new ArrayList<ClientResult>();
        
        for(Client client: super.findAll() ){
            
            ClientResult clientResult  = new ClientResult();
            clientResult.setIdClient(client.getId());
            clientResult.setNomClient(client.getNomClient());
            clientResult.setMotDePasse(client.getMotdepasse());
            clientResult.setPrenomClient(client.getPrenomClient());
            List<Long> reserv = new ArrayList<Long>();

            for(Reservation res : client.getReservations() ){
                    reserv.add(res.getId());
            }
            clientResult.setReservations(reserv);
            resultlist.add(clientResult);
        }
        
        return resultlist;
    }

    /**
     *
     * Vérifie les informations données en paramètre pour vérifier si un utilisateur avec ces informations existes dans la base
     * 
     * @param nom le nom dans la base d'un client
     * @param prenom le prenom dans la base d'un client
     * @param motDePasse le mot de passe du client enregistré dans la base
     * @see ClientResult
     * @return ClientResult si les informations sont validées alors on retourne l'objet client qui répond à cette validité
     */
    @GET
    @Path("connectionClient")
    @Produces({"application/xml", "application/json"})
    public ClientResult connectionClient(@QueryParam("nom") @DefaultValue("null") String nom,
                                    @QueryParam("prenom") @DefaultValue("null") String prenom,
                                    @QueryParam("motdepasse") @DefaultValue("null") String motDePasse) {
    
        ClientResult clientResult  = new ClientResult();
        clientResult.setIdClient(-1L);
        Query query = getEntityManager().createQuery("select c from Client c where c.nomClient = '"+nom+"' and c.prenomClient = '"+prenom+"' and c.motdepasse ='"+motDePasse+"'");
        List<Client> client = query.getResultList();
        
        System.out.println("nom => "+nom+" || prenom => "+prenom+" || motdepasse => "+motDePasse );
        if(client.size() == 1){
            //System.out.println("bon test");
            clientResult.setIdClient(client.get(0).getId());
            clientResult.setNomClient(client.get(0).getNomClient());
            clientResult.setMotDePasse(client.get(0).getMotdepasse());
            clientResult.setPrenomClient(client.get(0).getPrenomClient());
            List<Long> reserv = new ArrayList<Long>();

            for(Reservation res : client.get(0).getReservations() ){
                    reserv.add(res.getId());
            }
            
            clientResult.setReservations(reserv);
        }
        return clientResult;
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
    public List<Client> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     *
     * @return String le nombre au format chaine de caractère du nombre d'occurence de client trouvé dans la base.
     */
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
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
