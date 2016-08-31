package zs.wallboard.wallboardapi.providers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import zs.wallboard.commons.model.Version;

import java.io.IOException;

public class VersionSerializer extends StdSerializer<Version> {

    protected VersionSerializer() {
        super(Version.class);
    }

    @Override
    public void serialize(final Version value, final JsonGenerator gen, final SerializerProvider provider) throws
            IOException {
        final String versionValue;
        if (value.getAdditional() == null) {
            versionValue = String.format("%d.%d.%d", value.getMajor(), value.getMinor(), value.getPatch());
        } else {
            versionValue = String.format("%d.%d.%d-%s", value.getMajor(), value.getMinor(), value.getPatch(),
                    value.getAdditional());
        }
        gen.writeString(versionValue);
    }

}
