package zs.wallboard.commons;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;

public abstract class AbstractHTTPClient {

    private final Client client;

    protected AbstractHTTPClient(final Client client) {
        this.client = client;
    }

    /**
     * Run a GET request to the url. By default the client expects a JSON string. Headers, media type, etc. can be added or changed by overriding
     * the {@link AbstractHTTPClient#addRequestOptions}
     *
     * @param url the url to get
     * @return String with the response body
     * @throws IOException error when something went wrong
     */
    public String performGet(final String url) {
        WebTarget webTarget = client.target(url);
        final Invocation.Builder requestBuilder = webTarget.request();
        addRequestOptions(requestBuilder);
        Response response = null;
        try {
            response = requestBuilder.buildGet().invoke();
            if (response.getStatus() != 200) {
                handleErrorResponse(response);
                return null;
            } else {
                return response.readEntity(String.class);
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public <T> T performGet(final String url, final Class<? extends T> valueClass) throws IOException {
        final String responseString = performGet(url);
        return readValue(responseString, valueClass);
    }

    protected abstract void handleErrorResponse(final Response response);

    private void addRequestOptions(final Invocation.Builder requestBuilder) {

    }

    public final <T> T readValue(final String responseString, final Class<? extends T> valueClass) throws IOException {
        return new ObjectMapper().readValue(responseString, valueClass);
    }

}
