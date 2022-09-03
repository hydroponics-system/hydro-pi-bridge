package hydro.pi.bridge.susbcription.abstracts;

/**
 * Listener interface for subscriptions.
 * 
 * @author Sam Butler
 * @since Sepetember 2, 2022
 */
public interface Listener<T> {

    /**
     * Run method for the listener. This is what will get processed when the
     * subscription is hit.
     * 
     * @param result The result from the data.
     */
    public void run(T result);

    /**
     * The result type that is expected to be returned.
     * 
     * @return The class value of the object.
     */
    public Class<T> resultType();

    /**
     * The string path of the topic or queue to listen on.
     * 
     * @return String of the path.
     */
    public String url();
}
