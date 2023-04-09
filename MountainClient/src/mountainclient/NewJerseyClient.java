/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mountainclient;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:MountainRest [Mountains]<br>
 * USAGE:
 * <pre>
 *        NewJerseyClient client = new NewJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author ummiaishaibrahim
 */
public class NewJerseyClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CS-253/webresources";

    public NewJerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Mountains");
    }

    public <T> T getMountainByHemisphere(Class<T> responseType, String hemisphere) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (hemisphere != null) {
            resource = resource.queryParam("hemisphere", hemisphere);
        }
        resource = resource.path("hemisphere");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addMountain(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getMountainByCountry(Class<T> responseType, String country) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (country != null) {
            resource = resource.queryParam("country", country);
        }
        resource = resource.path("country");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateMountainHeight(String mountainName, String mountainRange, String country, String newHeight) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}/{1}/{2}/{3}", new Object[]{mountainName, mountainRange, country, newHeight})).request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).put(null);
    }

    public void deleteMountain(String mountainName, String mountainRange, String country) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}/{1}/{2}", new Object[]{mountainName, mountainRange, country})).request().delete();
    }

    public <T> T getMountainList(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getMountainHeight(Class<T> responseType, String mountainName, String country, String mountainRange) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (mountainName != null) {
            resource = resource.queryParam("mountainName", mountainName);
        }
        if (country != null) {
            resource = resource.queryParam("country", country);
        }
        if (mountainRange != null) {
            resource = resource.queryParam("mountainRange", mountainRange);
        }
        resource = resource.path("mountainName/mountainRange/country");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    public <T> T getMountainRangeAndCountry(Class<T> responseType, String country, String mountainRange) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (country != null) {
            resource = resource.queryParam("country", country);
        }
        if (mountainRange != null) {
            resource = resource.queryParam("mountainRange", mountainRange);
        }
        resource = resource.path("mountainRange/country");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
