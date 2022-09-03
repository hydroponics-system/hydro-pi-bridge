package hydro.pi.bridge;

import hydro.pi.bridge.logger.LoggerConfig;
import hydro.pi.bridge.susbcription.SubscriptionClient;

/**
 * Main Application to run
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class PiBridgeApp {
    private static final String url = "ws://localhost:8000/subscription/socket?eyJhbGciOiJIUzUxMiJ9.eyJ3ZWJSb2xlIjoiQURNSU4iLCJmaXJzdE5hbWUiOiJTYW11ZWwiLCJsYXN0TmFtZSI6IkJ1dGxlciIsInBhc3N3b3JkUmVzZXQiOmZhbHNlLCJlbnYiOiJMT0NBTCIsImV4cCI6MTY2MjE5NjI2NSwidXNlcklkIjoxLCJpYXQiOjE2NjIxNzgyNjUsImVtYWlsIjoic2FtYnV0bGVyMTAxN0BpY2xvdWQuY29tIiwiand0VHlwZSI6IldFQiJ9.nqOFUa6Zhj-KFqcqrO9r0ul1gKSfH0on1TFEnJ7oBA59hln8gwdGFPqPMNbQGI6NMmHxftKz6O9wwBG8eALPhw";

    public static void main(String[] args) {
        LoggerConfig.init();
        SubscriptionClient client = new SubscriptionClient();
        client.connectAsync(url).subscribe(res -> addListeners(client));
        while (true) {
        }
    }

    private static void addListeners(SubscriptionClient client) {
        client.listen("/topic/general/notification", Object.class)
                .subscribe(res -> System.out.println("DATA: " + res));

    }
}
