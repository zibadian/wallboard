package zs.wallboard.commons.model;

public final class ProjectLink {

    private final String name;
    private final Status status;
    private final Version version;
    private final String url;
    private final ProjectPartialLink[] partials;

    public ProjectLink(final String name, final ProjectPartialLink[] partials, final Status status, final String url,
                       final Version version) {
        this.name = name;
        this.partials = partials;
        this.status = status;
        this.url = url;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public ProjectPartialLink[] getPartials() {
        return partials;
    }

    public Status getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }

    public Version getVersion() {
        return version;
    }
}
