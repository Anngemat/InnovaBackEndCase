package Takip;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.ResultSet;

public class HttpServer1 {
	public static void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("HTTP server started on port 8000");
    }

    
}
