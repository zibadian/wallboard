package zs.wallboard.wallboardapi;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.servlet.ServletProperties;
import org.slf4j.impl.SimpleLogger;
import zs.wallboard.wallboardapi.providers.impl.TimeProviderImpl;
import zs.wallboard.wallboardapi.resources.BoardResource;
import zs.wallboard.wallboardapi.resources.StaticResource;
import zs.wallboard.wallboardapi.server.ApiServerFactory;
import zs.wallboard.wallboardapi.server.impl.GrizzlyFactory;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Wallboard {

    public static void main(String[] args) {
        try {
            System.getProperties().put(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "trace");
            System.getProperties().put(SimpleLogger.DATE_TIME_FORMAT_KEY, "yyyy-MM-dd hh:mm:ss.SSS");
            System.getProperties().put(SimpleLogger.SHOW_DATE_TIME_KEY, "true");
            final Logger logger = Logger.getLogger("");
            logger.setLevel(Level.WARNING);
            for (Handler handler : logger.getHandlers()) {
                handler.setLevel(logger.getLevel());
            }
            new Wallboard().run(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private int run(final String[] args) throws Exception {
        new ApiServerFactory(new GrizzlyFactory(), new TimeProviderImpl())
                .register(BoardResource.class)
                .register(new StaticResource("static/"))
                .register(LoggingFeature.class)
                .register(ServletProperties.FILTER_FORWARD_ON_404, true)
                .start();
        return 0;
    }

}
