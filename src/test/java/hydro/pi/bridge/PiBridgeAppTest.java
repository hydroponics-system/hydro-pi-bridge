package hydro.pi.bridge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Pi Bridge Application Test
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class PiBridgeAppTest {

    @Test
    public void testApp() {
        PiBridgeApp app = new PiBridgeApp();
        assertNotNull(app, "App should be defined");
    }
}
