package hydro.pi.bridge.system.auth;

import com.hydro.common.dictionary.data.HydroSystem;

import hydro.pi.bridge.api.domain.SystemAuthToken;

/**
 * System Jwt Holder class to store authentication token in a thread local
 * instance to be accessed. Although the JWT is held in a static thread local,
 * the methods are non-static so that JwtHolder can be mocked in tests.
 * 
 * @author Sam Butler
 * @since August 8, 2020
 */
public class SystemJwtHolder {
    private static final ThreadLocal<SystemPair> TOKEN = new ThreadLocal<>();

    /**
     * Set the system pair on the current thread local instance.
     * 
     * @param token The token to store.
     */
    public void set(SystemAuthToken auth) {
        SystemPair pair = new SystemPair(auth);
        TOKEN.set(pair);
    }

    /**
     * Clears the system pair from the current thread local instance.
     */
    public void clear() {
        TOKEN.remove();
    }

    /**
     * Gets the current System Pair from the thread local instance.
     * 
     * @return {@link SystemPair} of the thread local.
     */
    public SystemPair get() {
        return TOKEN.get();
    }

    /**
     * Gets the hydro system from the thread local instance. If the pair of the
     * local instance is null then it will return null.
     * 
     * @return {@link HydroSystem} with the set data.
     */
    public HydroSystem getSystem() {
        SystemPair pair = get();
        return pair == null ? null : pair.getSystem();
    }

    /**
     * Gets the token from the thread local instance. If the pair of the local
     * instance is null then it will return null.
     * 
     * @return {@link String} of the set token.
     */
    public String getToken() {
        SystemPair pair = get();
        return pair == null ? null : pair.getToken();
    }
}