package cap.jenkins.httpclient;

public abstract class AbstractClientService {

    protected final HTTPClient client;

    public AbstractClientService(final HTTPClient client) {
        this.client = client;
    }

}
