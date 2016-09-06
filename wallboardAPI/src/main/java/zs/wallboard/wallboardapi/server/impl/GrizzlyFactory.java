package zs.wallboard.wallboardapi.server.impl;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zs.wallboard.wallboardapi.server.ServerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class GrizzlyFactory implements ServerFactory {

    private Logger logger = LoggerFactory.getLogger(GrizzlyFactory.class);

    @Override
    public HttpServer createServer() {
        try {
            return GrizzlyHttpServerFactory.createHttpServer(new URI(getBaseURI()), false);
        } catch (URISyntaxException e) {
            logger.error("Failed to create server", e);
            System.exit(1);
            return null;
        }
    }

    private String getBaseURI() {
        return "http://0.0.0.0:8080";
    }

    @Override
    public Class<? extends HttpHandler> getContainerClass() {
        return GrizzlyHttpContainer.class;
    }

}
