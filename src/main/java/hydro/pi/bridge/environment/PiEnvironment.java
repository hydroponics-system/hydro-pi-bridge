package hydro.pi.bridge.environment;

import java.util.Arrays;

import org.springframework.util.Assert;

/**
 * Enum for representing the currently active environment running on the Pi
 * Bridge.
 * 
 * @author Sam Butler
 * @since September 4, 2022
 */
public enum PiEnvironment {
    PRODUCTION("--prod", "wss://hydro-api-microservice.herokuapp.com/subscription/socket",
            "https://hydro-api-microservice.herokuapp.com"),
    TEST("--test", "wss://hydro-api-microservice.herokuapp.com/subscription/socket",
            "https://hydro-api-microservice.herokuapp.com"),
    LOCAL("--local", "ws://localhost:8000/subscription/socket", "http://localhost:8000");

    private String tag;
    private String socket;
    private String api;

    private PiEnvironment(String tag, String socket, String api) {
        this.tag = tag;
        this.socket = socket;
        this.api = api;
    }

    public String tag() {
        return tag;
    }

    public String socket() {
        return socket;
    }

    public String api() {
        return api;
    }

    /**
     * Will get the environment object enum from the passed in text value. If the
     * enum is invalid it will return the {@link PiEnvironment#LOCAL} environment by
     * default.
     * 
     * @param text The text to process.
     * @return {@link PiEnvironment} Object
     */
    public static PiEnvironment get(String text) {
        Assert.notNull(text, "Tag can not be null");
        return Arrays.asList(PiEnvironment.values()).stream().filter(e -> e.tag().equals(text.toLowerCase())).findAny()
                .orElse(LOCAL);
    }
}
