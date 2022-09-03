package hydro.pi.bridge;

import hydro.pi.bridge.logger.LoggerConfig;
import hydro.pi.bridge.susbcription.client.SubscriptionClient;
import hydro.pi.bridge.susbcription.listeners.GeneralNotficationListener;
import hydro.pi.bridge.susbcription.service.SubscriptionListeners;

/**
 * Main Application to run
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class PiBridgeApp {
    private static final String url = "ws://localhost:8000/subscription/socket?eyJhbGciOiJIUzUxMiJ9.eyJ3ZWJSb2xlIjoiVVNFUiIsImZpcnN0TmFtZSI6IlNhbXVlbCIsImxhc3ROYW1lIjoiQnV0bGVyIiwicGFzc3dvcmRSZXNldCI6ZmFsc2UsImVudiI6IkxPQ0FMIiwiZXhwIjoxNjYyMjI2Mzk2LCJ1c2VySWQiOjEsImlhdCI6MTY2MjIwODM5NiwiZW1haWwiOiJzYW1idXRsZXIxMDE3QGljbG91ZC5jb20iLCJqd3RUeXBlIjoiV0VCIn0.U2lWvxLeuw3JSctvo9vBjiIgD3_0pgbWOxR92dhOssXd6_y38ZpkuDRJjl5Iey9rIt_qBKQF-_Jgd0KcCVjk9g";

    public static void main(String[] args) {
        LoggerConfig.init();
        startSubscription();

        while(true) {}
    }

    private static void startSubscription() {
        SubscriptionClient client = new SubscriptionClient();
        client.connectAsync(url).subscribe(res -> addListeners(client));
    }

    private static void addListeners(SubscriptionClient client) {
        SubscriptionListeners listeners = new SubscriptionListeners(client);
        listeners.register(new GeneralNotficationListener());
    }
}
