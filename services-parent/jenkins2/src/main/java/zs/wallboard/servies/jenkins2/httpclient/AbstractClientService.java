package zs.wallboard.servies.jenkins2.httpclient;

import zs.wallboard.commons.AbstractHTTPClient;

import java.io.IOException;

public abstract class AbstractClientService<DATA> {

    protected final AbstractHTTPClient client;

    public AbstractClientService(final AbstractHTTPClient client) {
        this.client = client;
    }

    protected final <T> T readUrl(final String url, Class<T> valueClass) {
        try {
            return client.performGet(url, valueClass);
        } catch (IOException e) {
            return null;
        }
    }

    public abstract DATA getData(final String url);

}
