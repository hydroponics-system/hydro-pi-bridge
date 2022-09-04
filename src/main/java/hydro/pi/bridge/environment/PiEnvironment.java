package hydro.pi.bridge.environment;

import java.util.Arrays;

import org.springframework.util.Assert;

/**
 * Enum for representing the currently active environment
 * running on the Pi Bridge.
 * 
 * @author Sam Butler
 * @since September 4, 2022
 */
public enum PiEnvironment {
    PRODUCTION("--prod", ""),
    TEST("--test", ""),
    LOCAL("--local", "ws://localhost:8000/subscription/socket");

    private String tag;
    private String path;

    private PiEnvironment(String tag, String path) {
        this.tag = tag;
        this.path = path;
    }

    public String tag() {
        return tag;
    }

    public String path() {
        return path;
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
        return Arrays.asList(PiEnvironment.values()).stream().filter(e -> e.tag().equals(text.toUpperCase()))
                .findAny().orElse(LOCAL);
    }
}
