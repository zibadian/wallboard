package zs.wallboard.servies.jenkins2.httpclient.impl;

import zs.wallboard.commons.AbstractHTTPClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HTTPClientImpl extends AbstractHTTPClient {

    public HTTPClientImpl(final Client client) {
        super(client);
    }

    @Override
    protected void handleErrorResponse(final Response response) {
        throw new RuntimeException("Unable to contact jenkins" + response);
    }

    /**
     * Alter the options for the request object before the request is invoked.
     *
     * @param requestBuilder builder of the request object
     */
    protected void addRequestOptions(final Invocation.Builder requestBuilder) {
        requestBuilder.accept(MediaType.APPLICATION_JSON_TYPE);
    }


}
