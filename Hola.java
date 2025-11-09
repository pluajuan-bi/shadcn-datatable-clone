import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;
import java.io.OutputStream;
import java.io.IOException;

public class Hola {
    public static void main(String[] args) throws IOException {
        int port = 8082;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RootHandler());
        server.setExecutor(null);
        System.out.println("Servidor escuchando en http://0.0.0.0:" + port);
        server.start();
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Configurar CORS
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

            // Manejar preflight OPTIONS
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String response = "Prueba con Java - Juan Plua OpenShift";
            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.getBytes("UTF-8").length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes("UTF-8"));
            }
        }
    }
}
