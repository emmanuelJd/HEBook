/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client.jersey;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * Jersey REST client generated for REST resource:ClientFacadeREST
 * [org.hebook.model.client]<br>
 * USAGE:
 * <pre>
 *        ClientClient client = new ClientClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Emmanuel
 */
public class ClientClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/HEBookProjet/webresources";

    /**
     *
     */
    public ClientClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("org.hebook.model.client");
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findAllWithoutLoop_XML(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("findAllWithoutLoop");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T findAllWithoutLoop_JSON(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("findAllWithoutLoop");
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
     * @param prenom
     * @param motdepasse
     * @param nom
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T connectionClient_XML(Class<T> responseType, String prenom, String motdepasse, String nom) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (prenom != null) {
            resource = resource.queryParam("prenom", prenom);
        }
        if (motdepasse != null) {
            resource = resource.queryParam("motdepasse", motdepasse);
        }
        if (nom != null) {
            resource = resource.queryParam("nom", nom);
        }
        resource = resource.path("connectionClient");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     *
     * @param <T>
     * @param responseType
     * @param prenom
     * @param motdepasse
     * @param nom
     * @return
     * @throws UniformInterfaceException
     */
    public <T> T connectionClient_JSON(Class<T> responseType, String prenom, String motdepasse, String nom) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (prenom != null) {
            resource = resource.queryParam("prenom", prenom);
        }
        if (motdepasse != null) {
            resource = resource.queryParam("motdepasse", motdepasse);
        }
        if (nom != null) {
            resource = resource.queryParam("nom", nom);
        }
        resource = resource.path("connectionClient");
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
