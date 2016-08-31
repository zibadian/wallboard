package zs.wallboard.wallboardapi;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import zs.wallboard.wallboardapi.providers.JacksonDatabindProvider;

import javax.ws.rs.ProcessingException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Application {

    public static void main(String[] args) {
    }

}
