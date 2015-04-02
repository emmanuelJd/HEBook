/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client.jersey;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * Jersey REST client generated for REST resource:HotelFacadeREST
 * [org.hebook.model.hotel]<br>
 * USAGE:
 * <pre>
 *        HotelClient client = new HotelClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Emmanuel
 */
public class HotelClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/HEBookProjet/webresources";

    /**
     *
     */
    public HotelClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("org.hebook.model.hotel");
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findWithoutLoop_XML(Class<T> responseType, String id) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("findWithoutLoop");
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
    public <T> T findWithoutLoop_JSON(Class<T> responseType, String id) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("findWithoutLoop");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findAllByDisponibilite_XML(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getHotelByDisponibilite");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findAllByDisponibilite_JSON(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getHotelByDisponibilite");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param nombreEtoile
     * @param prixSup
     * @param prix
     * @param prixInf
     * @param ville
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findAllByFilter_XML(Class<T> responseType, String nombreEtoile, String prixSup, String prix, String prixInf, String ville) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (nombreEtoile != null) {
            resource = resource.queryParam("nombreEtoile", nombreEtoile);
        }
        if (prixSup != null) {
            resource = resource.queryParam("prixSup", prixSup);
        }
        if (prix != null) {
            resource = resource.queryParam("prix", prix);
        }
        if (prixInf != null) {
            resource = resource.queryParam("prixInf", prixInf);
        }
        if (ville != null) {
            resource = resource.queryParam("ville", ville);
        }
        resource = resource.path("getHotel");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param nombreEtoile
     * @param prixSup
     * @param prix
     * @param prixInf
     * @param ville
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findAllByFilter_JSON(Class<T> responseType, String nombreEtoile, String prixSup, String prix, String prixInf, String ville) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (nombreEtoile != null) {
            resource = resource.queryParam("nombreEtoile", nombreEtoile);
        }
        if (prixSup != null) {
            resource = resource.queryParam("prixSup", prixSup);
        }
        if (prix != null) {
            resource = resource.queryParam("prix", prix);
        }
        if (prixInf != null) {
            resource = resource.queryParam("prixInf", prixInf);
        }
        if (ville != null) {
            resource = resource.queryParam("ville", ville);
        }
        resource = resource.path("getHotel");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
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
     * @param <T>
     * @param responseType
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findHotelAndChambreCountDisponible_XML(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getHotelAndChambreCountDisponible");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findHotelAndChambreCountDisponible_JSON(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("getHotelAndChambreCountDisponible");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     *
     */
    public void close() {
        client.destroy();
    }
    
}
