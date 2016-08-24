package cap.jenkins.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HTTPClient {

    private final Client client;
    private final String hostName;

    public HTTPClient(final String hostName) {
        this.hostName = hostName;
        client = ClientBuilder.newClient();
    }

    public String performGet(final String url) throws IOException {
        WebTarget webTarget = client.target(url);
        final Invocation.Builder requestBuilder = webTarget.request(MediaType.APPLICATION_JSON_TYPE);
        addRequestOptions(requestBuilder);
        final Response response = requestBuilder.buildGet().invoke();
        if (response.getStatus() != 200) {
            throw new IOException("Unable to contact jenkins");
        } else {
            return response.readEntity(String.class);
        }
    }

    protected String transformUrl(final String url) {
        return String.format("%sapi/json?pretty=true", url);
    }

    protected String transformUrl(final String url, final String options) {
        return String.format("%sapi/json?pretty=true&%s", url, options);
    }

    protected void addRequestOptions(final Invocation.Builder requestBuilder) {

    }

    public <T> T readValue(final String url, Class<T> dataClass) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(performGet(transformUrl(url)), dataClass);
    }

    public <T> T readValue(final String url, final String options, Class<T> dataClass) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(performGet(transformUrl(url, options)), dataClass);
    }

    public String getHostName() {
        return hostName;
    }

}
