package Takip;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HttpServer1 {
	public static void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/toplamHarcama", new ToplamHarcamaHandler());
        server.createContext("/guncelle", new GuncelleHandler());
        server.createContext("/ekle", new EkleHandler());
        server.createContext("/sil", new SilHandler());
        server.createContext("/görüntüle", new GörüntüleHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("HTTP server started on port 8000");
    }
	static class ToplamHarcamaHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            baglanti db = new baglanti();
            StringBuilder response = new StringBuilder();
            response.append(db.toplamHarcama());
            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }
	static class GuncelleHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            baglanti db = new baglanti();
            StringBuilder response = new StringBuilder();
            response.append(db.toplamHarcama());
            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }
    
	static class EkleHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            baglanti db = new baglanti();
            StringBuilder response = new StringBuilder();
            response.append(db.toplamHarcama());
            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }
	static class SilHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            baglanti db = new baglanti();
            StringBuilder response = new StringBuilder();
            response.append(db.toplamHarcama());
            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }
	static class GörüntüleHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            baglanti db = new baglanti();
            StringBuilder response = new StringBuilder();
            response.append(db.toplamHarcama());
            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }
}
