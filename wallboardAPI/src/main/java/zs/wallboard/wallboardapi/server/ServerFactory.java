package zs.wallboard.wallboardapi.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;

public interface ServerFactory {

    HttpServer createServer();

    ResourceConfig decorateResourceConfig(ResourceConfig resourceConfig);

    String getBaseURI();

    <T> Class<T> getContainerClass();

}
