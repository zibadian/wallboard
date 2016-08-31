package zs.wallboard.commons.model;

public final class Version {

    private int major;
    private int minor;
    private int patch;
    private int build;
    private String additional;

    public Version(final String versionString) {
        String[] parts = versionString.split("[.]", 5);
        major = safeConvert(parts[0], -1);
        minor = safeConvert(parts[1], -1);
        if (parts.length > 2) {
            patch = safeConvert(parts[2], 0);
        }
        if (parts.length > 3) {
            build = safeConvert(parts[3], 0);
        }
        if (parts.length > 4) {
            additional = parts[4];
        }
    }

    public Version(final int major, final int minor, final int patch, final int build, final String additional) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.build = build;
        this.additional = additional;
    }

    private int safeConvert(final String part, final int defaultValue) {
        try {
            return Integer.parseInt(part);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public String getAdditional() {
        return additional;
    }

    public int getBuild() {
        return build;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

}
