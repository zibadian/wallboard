package zs.wallboard.wallboardapi.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Path("/")
public class StaticResource {

    private static Logger logger = LoggerFactory.getLogger(StaticResource.class);

    private final String docBase;

    public StaticResource(final String docBase) {
        this.docBase = docBase;
    }

    @GET
    @Path("/{docPath:.*}.{ext}")
    public Response getHtml(@PathParam("docPath") String docPath, @PathParam("ext") String ext) {
        return serveFile(docPath, ext);
    }

    @GET
    @Path("/{docPath:.*}")
    public Response getFolder(@PathParam("docPath") String docPath) {
        if ("".equals(docPath) || "/".equals(docPath)) {
            return serveFile("index", "html");
        } else if (docPath.endsWith("/")) {
            return serveFile(docPath + "index", "html");
        } else {
            return serveFile(docPath + "/index", "html");
        }
    }

    private Response serveFile(final @PathParam("docPath") String docPath, final @PathParam("ext") String ext) {
        final String pathname = cleanDocPath(docPath) + "." + ext;
        final File file = new File(pathname);
        if (file.canRead()) {
            logger.trace("Serving " + pathname);
            return Response.ok(file).build();
        } else {
            return serveResource(pathname);
        }
    }

    private Response serveResource(final String pathname) {
        final URL url = getClass().getClassLoader().getResource(pathname);
        if (url == null) {
            logger.warn("Requested not found: " + pathname);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            return Response.ok(url.getContent()).build();
        } catch (IOException e) {
            logger.error("Error reading from "+url, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String cleanDocPath(String docPath) {
        return docBase + (docPath.startsWith("/") ? docPath.substring(1) : docPath);
    }

}
