/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client.jersey;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * Jersey REST client generated for REST resource:ReservationFacadeREST
 * [org.hebook.model.reservation]<br>
 * USAGE:
 * <pre>
 *        ReservationClient client = new ReservationClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Emmanuel
 */
public class ReservationClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/HEBookProjet/webresources";

    /**
     *
     */
    public ReservationClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("org.hebook.model.reservation");
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param idClient
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T listReservationByClient_XML(Class<T> responseType, String idClient) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (idClient != null) {
            resource = resource.queryParam("idClient", idClient);
        }
        resource = resource.path("getListReservationByClient");
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
    public <T> T listReservationByClient_JSON(Class<T> responseType, String idClient) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (idClient != null) {
            resource = resource.queryParam("idClient", idClient);
        }
        resource = resource.path("getListReservationByClient");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param typeCarte
     * @param numero
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T getVerifCard_XML(Class<T> responseType, String typeCarte, String numero) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (typeCarte != null) {
            resource = resource.queryParam("typeCarte", typeCarte);
        }
        if (numero != null) {
            resource = resource.queryParam("numero", numero);
        }
        resource = resource.path("getVerifCard");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param typeCarte
     * @param numero
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T getVerifCard_JSON(Class<T> responseType, String typeCarte, String numero) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (typeCarte != null) {
            resource = resource.queryParam("typeCarte", typeCarte);
        }
        if (numero != null) {
            resource = resource.queryParam("numero", numero);
        }
        resource = resource.path("getVerifCard");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     * @param requestEntity
     * @return
     * @throws UniformInterfaceException
     */
    public String createSecure_XML(Object requestEntity) throws UniformInterfaceException {
        return webResource.path("createSecure").type(javax.ws.rs.core.MediaType.APPLICATION_XML).post(String.class, requestEntity);
    }

    /**
     *
     * @param requestEntity
     * @return
     * @throws UniformInterfaceException
     */
    public String createSecure_JSON(Object requestEntity) throws UniformInterfaceException {
        return webResource.path("createSecure").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(String.class, requestEntity);
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
     * @param requestEntity
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T editReservation_XML(Class<T> responseType, Object requestEntity) throws UniformInterfaceException {
        return webResource.path("editReservation").type(javax.ws.rs.core.MediaType.APPLICATION_XML).post(responseType, requestEntity);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param requestEntity
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T editReservation_JSON(Class<T> responseType, Object requestEntity) throws UniformInterfaceException {
        return webResource.path("editReservation").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(responseType, requestEntity);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param idClient
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T reservationByClient_XML(Class<T> responseType, String idClient) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (idClient != null) {
            resource = resource.queryParam("idClient", idClient);
        }
        resource = resource.path("getReservationByClient");
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
    public <T> T reservationByClient_JSON(Class<T> responseType, String idClient) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (idClient != null) {
            resource = resource.queryParam("idClient", idClient);
        }
        resource = resource.path("getReservationByClient");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
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
     * @param <T>
     * @param responseType
     * @param idReservation
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T deleteReservation_XML(Class<T> responseType, String idReservation) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (idReservation != null) {
            resource = resource.queryParam("idReservation", idReservation);
        }
        resource = resource.path("deleteReservation");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param idReservation
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T deleteReservation_JSON(Class<T> responseType, String idReservation) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (idReservation != null) {
            resource = resource.queryParam("idReservation", idReservation);
        }
        resource = resource.path("deleteReservation");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
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
