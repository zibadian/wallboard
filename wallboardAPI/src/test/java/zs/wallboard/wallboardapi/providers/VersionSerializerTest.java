package zs.wallboard.wallboardapi.providers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import zs.wallboard.commons.model.Version;

import static org.junit.Assert.assertEquals;

public class VersionSerializerTest {

    private ObjectMapper fixture; // Serializer is call via the object mapper

    @Before
    public void setUp() {
        fixture = JacksonDatabindProvider.objectMapper();
    }

    @Test
    public void minimal_version() throws JsonProcessingException {
        TestObject data = new TestObject(new Version("1.0"));

        final String result = fixture.writeValueAsString(data);

        assertEquals("{\"version\":\"1.0.0\"}", result);
    }

    @Test
    public void version_with_build_no_additional() throws JsonProcessingException {
        TestObject data = new TestObject(new Version(1, 2, 3, 4, null));

        final String result = fixture.writeValueAsString(data);

        assertEquals("{\"version\":\"1.2.3\"}", result);
    }

    @Test
    public void full_version() throws JsonProcessingException {
        TestObject data = new TestObject(new Version(1, 2, 3, 4, "TEST"));

        final String result = fixture.writeValueAsString(data);

        assertEquals("{\"version\":\"1.2.3-TEST\"}", result);
    }

    public class TestObject {
        private final Version version;

        public TestObject(final Version version) {
            this.version = version;
        }

        public Version getVersion() {
            return version;
        }
    }

}