package hydro.pi.bridge.subscription.domain;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;

import hydro.pi.bridge.common.mapper.HydroMapper;
import rx.subjects.BehaviorSubject;

/**
 * Default frame handler for determining what to do with the data when the topic
 * passes a payload.
 * 
 * @author Sam Butler
 * @since March 30, 2022
 */
public class SocketFrameHandler<T> implements StompFrameHandler {

    private Class<T> clazz;

    private BehaviorSubject<T> subject;

    private ObjectMapper objectMapper;

    /**
     * Constructor for handling async calls with a subject and mapping the request
     * payload to the desired class type.
     * 
     * @param clazz   The Object the payload should be mapped too.
     * @param subject The subject handler.
     */
    public SocketFrameHandler(Class<T> clazz, BehaviorSubject<T> subject) {
        this.clazz = clazz;
        this.subject = subject;
        this.objectMapper = HydroMapper.get();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return clazz;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        T data = this.objectMapper.convertValue(payload, this.clazz);
        this.subject.onNext(data);
    }
}
