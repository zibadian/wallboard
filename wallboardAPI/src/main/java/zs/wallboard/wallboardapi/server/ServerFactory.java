package zs.wallboard.wallboardapi.server;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;

public interface ServerFactory {

    HttpServer createServer();

    Class<? extends HttpHandler> getContainerClass();

}
