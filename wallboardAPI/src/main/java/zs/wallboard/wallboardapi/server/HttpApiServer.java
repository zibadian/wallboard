package zs.wallboard.wallboardapi.server;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.jersey.server.ContainerFactory;
import zs.wallboard.wallboardapi.providers.JacksonDatabindProvider;

public class HttpApiServer {

    private final HttpServer server;

    public HttpApiServer(final ServerFactory factory) {
        HttpHandler handler = ContainerFactory.createContainer(
                factory.getContainerClass(), factory.decorateResourceConfig(JacksonDatabindProvider.resourceConfig()));
        this.server = factory.createServer();
        ServerConfiguration config = server.getServerConfiguration();
        config.addHttpHandler(handler, "/");
    }

}
