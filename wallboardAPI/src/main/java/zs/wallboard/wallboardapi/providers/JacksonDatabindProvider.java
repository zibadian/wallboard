package zs.wallboard.wallboardapi.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import zs.wallboard.commons.model.Version;

public final class JacksonDatabindProvider {

    private JacksonDatabindProvider() {
    }

    public static ObjectMapper objectMapper() {
        final ObjectMapper result = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Version.class, new VersionSerializer());
        result.registerModule(module);

        return result;
    }

    public static JacksonJaxbJsonProvider jacksonJaxbJsonProvider() {
        JacksonJaxbJsonProvider result = new JacksonJaxbJsonProvider();
        result.setMapper(objectMapper());
        return result;
    }

}
