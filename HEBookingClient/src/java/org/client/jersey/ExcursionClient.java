/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client.jersey;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * Jersey REST client generated for REST resource:ExcursionFacadeREST
 * [org.hebook.model.excursion]<br>
 * USAGE:
 * <pre>
 *        ExcursionClient client = new ExcursionClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Emmanuel
 */
public class ExcursionClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/HEBookProjet/webresources";

    /**
     *
     */
    public ExcursionClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("org.hebook.model.excursion");
    }

    /**
     *
     * @param requestEntity
     * @throws UniformInterfaceException
     */
    public void createSecure_XML(Object requestEntity) throws UniformInterfaceException {
        webResource.path("createSecure").type(javax.ws.rs.core.MediaType.APPLICATION_XML).post(requestEntity);
    }

    /**
     *
     * @param requestEntity
     * @throws UniformInterfaceException
     */
    public void createSecure_JSON(Object requestEntity) throws UniformInterfaceException {
        webResource.path("createSecure").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(requestEntity);
    }

    /**
     *
     * @param id
     * @throws UniformInterfaceException
     */
    public void remove(String id) throws UniformInterfaceException {
        webResource.path(java.text.MessageFormat.format("{0}", new Object[]{id})).delete();
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param idExcursion
     * @param idReservation
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T addReservationSecure(Class<T> responseType, String idExcursion, String idReservation) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (idExcursion != null) {
            resource = resource.queryParam("idExcursion", idExcursion);
        }
        if (idReservation != null) {
            resource = resource.queryParam("idReservation", idReservation);
        }
        resource = resource.path("addReservationSecure");
        return resource.get(responseType);
    }

    /**
     *
     * @return
     * @throws UniformInterfaceException
     */
    public String countREST() throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("count");
        return resource.accept(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findAll_XML(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findAll_JSON(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     * @param requestEntity
     * @throws UniformInterfaceException
     */
    public void edit_XML(Object requestEntity) throws UniformInterfaceException {
        webResource.type(javax.ws.rs.core.MediaType.APPLICATION_XML).put(requestEntity);
    }

    /**
     *
     * @param requestEntity
     * @throws UniformInterfaceException
     */
    public void edit_JSON(Object requestEntity) throws UniformInterfaceException {
        webResource.type(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(requestEntity);
    }

    /**
     *
     * @param requestEntity
     * @throws UniformInterfaceException
     */
    public void create_XML(Object requestEntity) throws UniformInterfaceException {
        webResource.type(javax.ws.rs.core.MediaType.APPLICATION_XML).post(requestEntity);
    }

    /**
     *
     * @param requestEntity
     * @throws UniformInterfaceException
     */
    public void create_JSON(Object requestEntity) throws UniformInterfaceException {
        webResource.type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(requestEntity);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param from
     * @param to
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findRange_XML(Class<T> responseType, String from, String to) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param from
     * @param to
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findRange_JSON(Class<T> responseType, String from, String to) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T getExcursionByNbrePlaceDisponible_XML(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getExcursionByNbrePlaceDisponible");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T getExcursionByNbrePlaceDisponible_JSON(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getExcursionByNbrePlaceDisponible");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param prixSup
     * @param prix
     * @param theme
     * @param prixInf
     * @param dateFin
     * @param idHotel
     * @param dateDebut
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findAllByFilter_XML(Class<T> responseType, String prixSup, String prix, String theme, String prixInf, String dateFin, String idHotel, String dateDebut) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (prixSup != null) {
            resource = resource.queryParam("prixSup", prixSup);
        }
        if (prix != null) {
            resource = resource.queryParam("prix", prix);
        }
        if (theme != null) {
            resource = resource.queryParam("theme", theme);
        }
        if (prixInf != null) {
            resource = resource.queryParam("prixInf", prixInf);
        }
        if (dateFin != null) {
            resource = resource.queryParam("dateFin", dateFin);
        }
        if (idHotel != null) {
            resource = resource.queryParam("idHotel", idHotel);
        }
        if (dateDebut != null) {
            resource = resource.queryParam("dateDebut", dateDebut);
        }
        resource = resource.path("getExcursion");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param prixSup
     * @param prix
     * @param theme
     * @param prixInf
     * @param dateFin
     * @param idHotel
     * @param dateDebut
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findAllByFilter_JSON(Class<T> responseType, String prixSup, String prix, String theme, String prixInf, String dateFin, String idHotel, String dateDebut) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (prixSup != null) {
            resource = resource.queryParam("prixSup", prixSup);
        }
        if (prix != null) {
            resource = resource.queryParam("prix", prix);
        }
        if (theme != null) {
            resource = resource.queryParam("theme", theme);
        }
        if (prixInf != null) {
            resource = resource.queryParam("prixInf", prixInf);
        }
        if (dateFin != null) {
            resource = resource.queryParam("dateFin", dateFin);
        }
        if (idHotel != null) {
            resource = resource.queryParam("idHotel", idHotel);
        }
        if (dateDebut != null) {
            resource = resource.queryParam("dateDebut", dateDebut);
        }
        resource = resource.path("getExcursion");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param idExcursion
     * @param idReservation
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T deleteInscriptionExcursion_XML(Class<T> responseType, String idExcursion, String idReservation) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (idExcursion != null) {
            resource = resource.queryParam("idExcursion", idExcursion);
        }
        if (idReservation != null) {
            resource = resource.queryParam("idReservation", idReservation);
        }
        resource = resource.path("deleteInscriptionExcursion");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param idExcursion
     * @param idReservation
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T deleteInscriptionExcursion_JSON(Class<T> responseType, String idExcursion, String idReservation) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (idExcursion != null) {
            resource = resource.queryParam("idExcursion", idExcursion);
        }
        if (idReservation != null) {
            resource = resource.queryParam("idReservation", idReservation);
        }
        resource = resource.path("deleteInscriptionExcursion");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param idClient
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T getExcursionByClient_XML(Class<T> responseType, String idClient) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (idClient != null) {
            resource = resource.queryParam("idClient", idClient);
        }
        resource = resource.path("getExcursionByClient");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param idClient
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T getExcursionByClient_JSON(Class<T> responseType, String idClient) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (idClient != null) {
            resource = resource.queryParam("idClient", idClient);
        }
        resource = resource.path("getExcursionByClient");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param nombreVisiteur
     * @param dateFin
     * @param dateDebut
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T getMostReservExcursion_XML(Class<T> responseType, String nombreVisiteur, String dateFin, String dateDebut) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (nombreVisiteur != null) {
            resource = resource.queryParam("nombreVisiteur", nombreVisiteur);
        }
        if (dateFin != null) {
            resource = resource.queryParam("dateFin", dateFin);
        }
        if (dateDebut != null) {
            resource = resource.queryParam("dateDebut", dateDebut);
        }
        resource = resource.path("getMostReservExcursion");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param nombreVisiteur
     * @param dateFin
     * @param dateDebut
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T getMostReservExcursion_JSON(Class<T> responseType, String nombreVisiteur, String dateFin, String dateDebut) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (nombreVisiteur != null) {
            resource = resource.queryParam("nombreVisiteur", nombreVisiteur);
        }
        if (dateFin != null) {
            resource = resource.queryParam("dateFin", dateFin);
        }
        if (dateDebut != null) {
            resource = resource.queryParam("dateDebut", dateDebut);
        }
        resource = resource.path("getMostReservExcursion");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T find_XML(Class<T> responseType, String id) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T find_JSON(Class<T> responseType, String id) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     */
    public void close() {
        client.destroy();
    }
    
}
