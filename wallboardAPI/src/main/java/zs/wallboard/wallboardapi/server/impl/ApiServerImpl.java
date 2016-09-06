package zs.wallboard.wallboardapi.server.impl;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.jersey.server.ContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import zs.wallboard.wallboardapi.providers.TimeProvider;
import zs.wallboard.wallboardapi.server.ApiServer;
import zs.wallboard.wallboardapi.server.ServerFactory;
import zs.wallboard.wallboardapi.server.internal.ApplicationHandler;
import zs.wallboard.wallboardapi.server.internal.KeepAliveState;
import zs.wallboard.wallboardapi.server.internal.KeepaliveResource;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ApiServerImpl implements ApiServer {

    private final HttpServer server;
    private KeepAliveState state;

    public ApiServerImpl(final ServerFactory factory, final ResourceConfig configuration,
                         final TimeProvider timeProvider) {
        configuration.register(new KeepaliveResource(this, timeProvider));
        HttpHandler handler = ContainerFactory.createContainer(factory.getContainerClass(), configuration);
        this.server = factory.createServer();
        ServerConfiguration config = server.getServerConfiguration();
        config.addHttpHandler(handler, "/");
        config.addHttpHandler(new ApplicationHandler(this), "/application/*");
    }

    public KeepAliveState getState() {
        return state;
    }

    public ApiServerImpl setState(final KeepAliveState state) {
        this.state = state;
        return this;
    }

    public void start() throws IOException {
        server.start();
    }

    public void shutdown() {
        try {
            state = KeepAliveState.STOPPING;
            server.shutdown(10000, TimeUnit.MILLISECONDS).get();
            state = KeepAliveState.STOPPED;
        } catch (InterruptedException | ExecutionException e) {
            System.exit(2);
        }
    }

    @Override
    public void pause() {
        state = KeepAliveState.UNAVAILABLE;
    }

    @Override
    public void resume() {
        state = KeepAliveState.AVAILABLE;
    }

}
