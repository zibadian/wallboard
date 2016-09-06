package zs.wallboard.wallboardapi.server;

import org.glassfish.jersey.server.ResourceConfig;
import zs.wallboard.wallboardapi.providers.JacksonDatabindProvider;
import zs.wallboard.wallboardapi.providers.TimeProvider;
import zs.wallboard.wallboardapi.server.impl.ApiServerImpl;
import zs.wallboard.wallboardapi.server.internal.KeepAliveState;
import zs.wallboard.wallboardapi.server.internal.LoggingFilter;

import java.io.IOException;

public class ApiServerFactory {

    private final ServerFactory factory;
    private final TimeProvider timeProvider;
    private final ResourceConfig configuration;

    public ApiServerFactory(final ServerFactory factory, final TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
        this.factory = factory;
        configuration = new ResourceConfig();
        configuration.register(new LoggingFilter());
        configuration.register(JacksonDatabindProvider.jacksonJaxbJsonProvider());
    }

    public ApiServerFactory register(final Object component) {
        configuration.register(component);
        return this;
    }

    public ApiServerFactory register(final Class<?> componentClass) {
        configuration.register(componentClass);
        return this;
    }

    public ApiServerFactory register(final String featureKey, final Object featureValue) {
        configuration.property(featureKey, featureValue);
        return this;
    }

    public ApiServer start() throws IOException {
        ApiServerImpl server = new ApiServerImpl(factory, new ResourceConfig(configuration), timeProvider);
        server.start();
        server.setState(KeepAliveState.AVAILABLE);
        return server;
    }

}
