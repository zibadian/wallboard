package zs.wallboard.wallboardapi.server.internal;

import zs.wallboard.wallboardapi.providers.TimeProvider;
import zs.wallboard.wallboardapi.server.ApiServer;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Singleton
@Path("/keepalive")
public class KeepaliveResource {

    private final TimeProvider timeProvider;
    private ApiServer server;

    public KeepaliveResource(final ApiServer httpApiServer, final TimeProvider timeProvider) {
        this.server = httpApiServer;
        this.timeProvider = timeProvider;
    }

    @GET
    @Produces("application/json")
    public KeepaliveDTO keepalive() {
        return new KeepaliveDTO(server.getState(), timeProvider.getNowAsMillis());
    }

}
