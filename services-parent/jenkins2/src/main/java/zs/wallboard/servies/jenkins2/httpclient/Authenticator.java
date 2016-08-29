package zs.wallboard.servies.jenkins2.httpclient;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

public class Authenticator implements ClientRequestFilter {

    private final char[] token;

    public Authenticator(final String user, final char[] password) {
        token = new char[user.length() + 1 + password.length];
        System.arraycopy(user.toCharArray(), 0, token, 0, user.length());
        token[user.length()] = ':';
        System.arraycopy(password, 0, token, user.length() + 1, password.length);
    }

    public void filter(ClientRequestContext requestContext) throws IOException {
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
        final String basicAuthentication = getBasicAuthentication();
        headers.add("Authorization", basicAuthentication);

    }

    private String getBasicAuthentication() {
        ByteBuffer buf = StandardCharsets.UTF_8.encode(CharBuffer.wrap(token));
        byte[] array = new byte[buf.limit()];
        buf.get(array);
        return "Basic " + DatatypeConverter.printBase64Binary(array);
    }
}
