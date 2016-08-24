package zs.jenkins.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Wrapper around the Jersey http client with conveniance methods to connect to Jenkins API
 */
public class HTTPClient {

    private final Client client;

    public HTTPClient() {
        client = ClientBuilder.newClient();
    }

    /**
     * Run a GET request to the url. By default the client expects a JSON string. Headers, media type, etc. can be added or changed by overriding
     * the {@link HTTPClient#addRequestOptions}
     *
     * @param url the url to get
     * @return String with the response body
     * @throws IOException error when something went wrong
     */
    public String performGet(final String url) throws IOException {
        WebTarget webTarget = client.target(url);
        final Invocation.Builder requestBuilder = webTarget.request();
        addRequestOptions(requestBuilder);
        Response response = null;
        try {
            response = requestBuilder.buildGet().invoke();
            if (response.getStatus() != 200) {
                throw new IOException("Unable to contact jenkins");
            } else {
                return response.readEntity(String.class);
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * Expand the default url with API call
     *
     * @param url
     * @return
     */
    protected String transformUrl(final String url) {
        return String.format("%sapi/json?pretty=true", url);
    }

    /**
     * Expand the default url with API call and additional url parameters
     *
     * @param url
     * @param options
     * @return
     */
    protected String transformUrl(final String url, final String options) {
        return String.format("%sapi/json?pretty=true&%s", url, options);
    }

    /**
     * Alter the options for the request object before the request is invoked.
     *
     * @param requestBuilder builder of the request object
     */
    protected void addRequestOptions(final Invocation.Builder requestBuilder) {
        requestBuilder.accept(MediaType.APPLICATION_JSON_TYPE);
    }

    /**
     * Read a JSON string from the given url and transform it to the dataClass
     *
     * @param url source of the JSON string
     * @param dataClass expected data class
     * @param <T> Resulting type
     * @return Instance of T filled with the data from the url
     * @throws IOException
     */
    public <T> T readValue(final String url, Class<T> dataClass) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(performGet(transformUrl(url)), dataClass);
    }

    /**
     * Read a JSON string from the given url and the url parameters; then transform it to the dataClass
     *
     * @param url source of the JSON string
     * @param options Additional url parameters
     * @param dataClass expected data class
     * @param <T> Resulting type
     * @return Instance of T filled with the data from the url
     * @throws IOException
     */
    public <T> T readValue(final String url, final String options, Class<T> dataClass) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(performGet(transformUrl(url, options)), dataClass);
    }

}
