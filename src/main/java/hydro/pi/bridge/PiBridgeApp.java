package hydro.pi.bridge;

import hydro.pi.bridge.susbcription.WebSocketClient;

/**
 * Main Application to run
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class PiBridgeApp {
    private static final String url = "wss://hydro-api-microservice.herokuapp.com/subscription/socket?";

    public static void main(String[] args) {
        WebSocketClient client = new WebSocketClient();
        client.connectAsync(url).subscribe(res -> addListeners(client));
        while(true) {}
    }

    private static void addListeners(WebSocketClient socketClient) {
        socketClient.listen("/topic/general/notification", Object.class).subscribe(res -> System.out.println(res));

    }
}
