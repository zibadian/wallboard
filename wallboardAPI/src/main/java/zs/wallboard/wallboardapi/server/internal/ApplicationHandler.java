package zs.wallboard.wallboardapi.server.internal;

import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.util.HttpStatus;
import zs.wallboard.wallboardapi.server.ApiServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

public class ApplicationHandler extends HttpHandler {

    private final ApiServer server;
    private String securityCode;
    private LocalDateTime accessibleAfter = LocalDateTime.of(1900, 01, 01, 01, 01);
    private File securityCodeFile = new File(".securitycode");

    public ApplicationHandler(final ApiServer server) {
        this.server = server;
        refreshSecurityCode();
        securityCodeFile.deleteOnExit();
    }

    @Override
    public void service(final Request request, final Response response) throws Exception {
        HttpStatus status = HttpStatus.CONINTUE_100;
        String[] uriParts = request.getRequestURI().split("/");
        status = validateTime(LocalDateTime.now(), status);
        status = validateMethod(request, status);
        status = validateLength(uriParts, status);
        status = validateSecurity(uriParts, status);
        if (status != HttpStatus.CONINTUE_100) {
            response.sendError(status.getStatusCode());
            return;
        }
        refreshSecurityCode();
        if ("stop".equals(uriParts[2])) {
            stopServer(response);
        } else if ("pause".equals(uriParts[2])) {
            pauseServer(response);
        } else if ("resume".equals(uriParts[2])) {
            resumeServer(response);
        } else {
            response.sendError(HttpStatus.NOT_FOUND_404.getStatusCode());
        }
    }

    private void resumeServer(final Response response) throws IOException {
        server.resume();
        response.setStatus(HttpStatus.OK_200);
        response.sendAcknowledgement();
    }

    private void pauseServer(final Response response) throws IOException {
        server.pause();
        response.setStatus(HttpStatus.OK_200);
        response.sendAcknowledgement();
    }

    private static int getPid() {
        final String pidString = ManagementFactory.getRuntimeMXBean().getName();
        try {
            return Integer.parseInt(pidString.split("@")[0]);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void refreshSecurityCode() {
        System.out.println("Refreshing securityCode");
        final int pid = getPid();
        byte[] bytes = new byte[42];
        new SecureRandom().nextBytes(bytes);
        securityCode = "Aa" + pid + "aA" + Base64.getUrlEncoder().encodeToString(bytes);
        try {
            PrintWriter pw = new PrintWriter(securityCodeFile);
            pw.print(securityCode);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(securityCode);
        }
    }

    private void stopServer(final Response response) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Server shutting down");
                server.shutdown();
                System.out.println("Server shutdown successfully");
            }
        }).start();
        response.setStatus(HttpStatus.OK_200);
        response.sendAcknowledgement();
    }

    private HttpStatus validateTime(final LocalDateTime now, final HttpStatus status) {
        if (status == HttpStatus.CONINTUE_100 & now.isBefore(accessibleAfter)) {
            return HttpStatus.NOT_FOUND_404;
        }
        accessibleAfter = now.plusMinutes(1);
        return status;
    }

    private HttpStatus validateSecurity(final String[] receivedSecCode, final HttpStatus status) {
        if (status == HttpStatus.CONINTUE_100 & !receivedSecCode[3].equals(securityCode)) {
            return HttpStatus.NOT_FOUND_404;
        }
        return status;
    }

    private HttpStatus validateLength(final String[] uriParts, final HttpStatus status) {
        if (status == HttpStatus.CONINTUE_100 & uriParts.length != 4) {
            return HttpStatus.NOT_FOUND_404;
        }
        return status;
    }

    private HttpStatus validateMethod(final Request request, final HttpStatus status) {
        if (status == HttpStatus.CONINTUE_100 & request.getMethod() != Method.GET) {
            return HttpStatus.METHOD_NOT_ALLOWED_405;
        }
        return status;
    }

}
