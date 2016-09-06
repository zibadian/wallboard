package zs.wallboard.wallboardapi.server.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.*;
import java.io.IOException;

@PreMatching
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        logger.trace(requestContext.getMethod()+' '+requestContext.getUriInfo().getRequestUri());
    }

    @Override
    public void filter(final ContainerRequestContext requestContext,
                       final ContainerResponseContext responseContext) throws
            IOException {
        logger.trace(requestContext.getMethod()+' '+requestContext.getUriInfo().getRequestUri());
    }

}
