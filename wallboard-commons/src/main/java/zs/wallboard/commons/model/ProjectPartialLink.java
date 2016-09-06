package zs.wallboard.commons.model;

import zs.wallboard.commons.model.Status;

public final class ProjectPartialLink {

    private final Status status;
    private final String url;

    public ProjectPartialLink(final Status status, final String url) {
        this.status = status;
        this.url = url;
    }

    public Status getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }
}
