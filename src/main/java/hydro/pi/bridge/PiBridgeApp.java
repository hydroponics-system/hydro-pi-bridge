package hydro.pi.bridge;

import hydro.pi.bridge.susbcription.WebSocketClient;

/**
 * Main Application to run
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class PiBridgeApp {
    private static final String url = "ws://localhost:8000/subscription/socket?eyJhbGciOiJIUzUxMiJ9.eyJ3ZWJSb2xlIjoiVVNFUiIsImZpcnN0TmFtZSI6IlNhbXVlbCIsImxhc3ROYW1lIjoiQnV0bGVyIiwicGFzc3dvcmRSZXNldCI6ZmFsc2UsImVudiI6IkxPQ0FMIiwiZXhwIjoxNjYyMTY4ODQ3LCJ1c2VySWQiOjEsImlhdCI6MTY2MjE1MDg0NywiZW1haWwiOiJzYW1idXRsZXIxMDE3QGljbG91ZC5jb20iLCJqd3RUeXBlIjoiV0VCIn0.6lSkuCpGtwtMrequNqUiBKindpV7waaL-fm6BigBGtA5tu2YzcE5y5YYcgVXgJMyDmSREU1hThyNdAlhnMDADA";

    public static void main(String[] args) {
        WebSocketClient client = new WebSocketClient();
        client.connectAsync(url).subscribe(res -> addListeners(client));
        while(true) {}
    }

    private static void addListeners(WebSocketClient socketClient) {
        socketClient.listen("/topic/general/notification", Object.class).subscribe(res -> System.out.println(res));

    }
}
