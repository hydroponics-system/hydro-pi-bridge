package hydro.pi.bridge.api;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import hydro.pi.bridge.common.mapper.HydroMapper;
import hydro.pi.bridge.environment.PiBridgeEnvironmentService;

/**
 * Api client class for consuming restful endpoints.
 * 
 * @author Sam Butler
 * @since April 8, 2022
 */
public class ApiClient {

    private final RestTemplate restTemplate;

    private String AUTH;

    public ApiClient() {
        this.restTemplate = getRestTemplate();
    }

    /**
     * Method for setting the authorization on requests being sent.
     * 
     * @param auth The authorization
     */
    public void setAuthorization(String auth) {
        this.AUTH = auth;
    }

    /**
     * This will do a get on the passed in API. It will then cast the results to the
     * passed in object.
     * 
     * @param api   The endpoint to hit.
     * @param clazz The class to cast it too.
     * @throws Exception
     */
    public <T> T get(String api, Class<T> clazz) throws Exception {
        return exchange(api, HttpMethod.GET, buildRequestEntity(null, Void.class), clazz).getBody();
    }

    /**
     * This will do a post on the passed in API. It will then cast the results to
     * the passed in object.
     * 
     * @param <T>  The object to cast the result as.
     * @param api  The endpoint to hit.
     * @param body The body to pass with the post request.
     * @return The passed in object class.
     * @throws InterruptedException
     * @throws IOException
     */
    public <T> Object post(String api, T request) throws Exception {
        return post(api, request, Object.class);
    }

    /**
     * This will do a post on the passed in API. It will then cast the results to
     * the passed in object.
     * 
     * @param <T>     The object to cast the result as.
     * @param api     The endpoint to hit.
     * @param request The body to pass with the post request.
     * @param clazz   The class to cast it too.
     * @return The passed in object class.
     * @throws InterruptedException
     * @throws IOException
     */
    public <T> T post(String api, Object request, Class<T> clazz) throws Exception {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(request);
        return exchange(api, HttpMethod.POST, requestEntity, clazz).getBody();
    }

    /**
     * Build out the absolute path for the api.
     * 
     * @param api The api to build.
     * @return Completed url with the attached api.
     */
    private String buildUrl(String api) {
        return String.format("%s%s", PiBridgeEnvironmentService.active().api(), api);
    }

    /**
     * Make an exchange call through the rest template.
     * 
     * @param <T>    Typed parameter of the response type.
     * @param api    The api to hit.
     * @param method The method to perform on the endpoint.
     * @param entity The entity instance to pass.
     * @param clazz  The class to return the response as.
     * @return Response entity of the returned data.
     */
    protected <T> ResponseEntity<T> exchange(String api, HttpMethod method, HttpEntity<?> entity, Class<T> clazz) {
        return restTemplate.exchange(buildUrl(api), method, entity, clazz);
    }

    /**
     * Builds out the request entity to be sent with the request. It will by default
     * set the authorization and content type on the headers to be sent.
     * 
     * @param <T>     The generic object type to return.
     * @param request The request to put on the headers.
     * @param clazz   The class to cast the object too
     * @return A {@link HttpEntity} object.
     */
    private <T> HttpEntity<T> buildRequestEntity(T request, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer: " + this.AUTH);
        headers.set("Content-Type", "application/json");

        if(request == null) {
            return new HttpEntity<T>(headers);
        }
        else {
            return new HttpEntity<T>(request, headers);
        }
    }

    /**
     * Builds a rest template with the correct mapper to use.
     * 
     * @return The rest template instance.
     */
    private RestTemplate getRestTemplate() {
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, HydroMapper.getConverter());
        return rest;
    }
}
