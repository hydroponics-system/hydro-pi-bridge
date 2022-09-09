package hydro.pi.bridge.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import hydro.pi.bridge.common.mapper.HydroMapper;
import hydro.pi.bridge.environment.PiBridgeEnvironmentService;
import hydro.pi.bridge.system.auth.SystemJwtHolder;

/**
 * Api client class for consuming restful endpoints.
 * 
 * @author Sam Butler
 * @since April 8, 2022
 */
public class ApiClient {

    private final RestTemplate restTemplate;

    private final SystemJwtHolder systemJwtHolder;

    /**
     * Default constructor for the {@link ApiClient}. It will set the rest template
     * for the given instance and also pull the active {@link SystemJwtHolder} for
     * authenticated request.
     */
    public ApiClient() {
        this.systemJwtHolder = new SystemJwtHolder();
        this.restTemplate = getRestTemplate();
    }

    /**
     * This will do a get on the passed in API. It will then cast the results to an
     * {@link Object} type.
     * 
     * @param api The endpoint to hit.
     */
    public <T> Object get(String api) {
        return get(api, Object.class);
    }

    /**
     * This will do a get on the passed in API. It will then cast the results to the
     * passed in class.
     * 
     * @param api   The endpoint to hit.
     * @param clazz The class to cast it too.
     */
    public <T> T get(String api, Class<T> clazz) {
        return exchange(api, HttpMethod.GET, buildRequestEntity(Void.class), clazz).getBody();
    }

    /**
     * This will do a post on the passed in API. It will then cast the results to
     * the a basic {@link Object} type.
     * 
     * @param api     The endpoint to hit.
     * @param request The body to pass with the post request.
     * @return {@link Object} type of the return data.
     */
    public Object post(String api, Object request) {
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
     * @return The passed in class.
     */
    public <T> T post(String api, Object request, Class<T> clazz) {
        return exchange(api, HttpMethod.POST, buildRequestEntity(request, clazz), clazz).getBody();
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
    public <T> ResponseEntity<T> exchange(String api, HttpMethod method, HttpEntity<?> entity, Class<T> clazz) {
        return restTemplate.exchange(buildUrl(api), method, entity, clazz);
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
     * Builds out the request entity to be sent with the request. It will by default
     * set the authorization and content type on the headers to be sent. This will
     * just build the headers with no request body sent.
     * 
     * @param <T>   The generic object type to return.
     * @param clazz The class to cast the object too
     * @return A {@link HttpEntity} object.
     */
    private <T> HttpEntity<Object> buildRequestEntity(Class<T> clazz) {
        return buildRequestEntity(null, clazz);
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
    private <T> HttpEntity<Object> buildRequestEntity(Object request, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer: " + systemJwtHolder.getToken());
        headers.set("Content-Type", "application/json");

        return request == null ? new HttpEntity<Object>(headers) : new HttpEntity<Object>(request, headers);
    }

    /**
     * Builds a rest template with the correct mapper to use.
     * 
     * @return The rest template instance.
     */
    private RestTemplate getRestTemplate() {
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, HydroMapper.getHttpConverter());
        return rest;
    }
}
