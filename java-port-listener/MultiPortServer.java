import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class MultiPortServer {

    public static void main(String[] args) {
        // List of ports to listen on, provided as arguments
        if (args.length < 1) {
            System.err.println("Please specify at least one port number.");
            System.exit(1);
        }

        for (String arg : args) {
            int port;
            try {
                port = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number: " + arg);
                continue;
            }

            startServer(port);
        }
    }

    private static void startServer(int port) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new HelloHandler(port));
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("Server started on port " + port);
        } catch (IOException e) {
            System.err.println("Failed to start the server on port " + port);
            e.printStackTrace();
        }
    }

    static class HelloHandler implements HttpHandler {
        private final int port;

        public HelloHandler(int port) {
            this.port = port;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello from port " + port + "!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        }
    }
}
