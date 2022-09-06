package hydro.pi.bridge.common.mapper;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Configured Object mapper.
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class HydroMapper {

    /**
     * Object Mapper config setup
     * 
     * @return The object mapper with the correct configs.
     */
    public static ObjectMapper get() {
        return get(null);
    }

    /**
     * Object Mapper config setup that takes in a factory to be set.
     * 
     * @param f the factory to be set on the mapper.
     * @return The object mapper with the correct configs.
     */
    public static ObjectMapper get(JsonFactory f) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    /**
     * Gets a http mapping converter with the defined object mapper.
     * 
     * @return The message converter.
     */
    public static MappingJackson2HttpMessageConverter getHttpConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(get());
        return converter;
    }

    /**
     * Gets a message mapping converter with the defined object mapper.
     * 
     * @return The message converter.
     */
    public static MappingJackson2MessageConverter getMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(get());
        return converter;
    }
}
