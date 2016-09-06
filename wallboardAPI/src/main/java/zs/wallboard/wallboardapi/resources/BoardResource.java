package zs.wallboard.wallboardapi.resources;

import zs.wallboard.commons.model.Status;
import zs.wallboard.commons.model.Version;
import zs.wallboard.commons.model.ProjectLink;
import zs.wallboard.commons.model.ProjectPartialLink;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/api/board")
public class BoardResource {

    @GET
    @Produces("application/json")
    public ProjectLink[] getProjectLink() {
        return new ProjectLink[] { new ProjectLink("Test", new ProjectPartialLink[] {
                new ProjectPartialLink(Status.GREEN, "/testproject/build"),
                new ProjectPartialLink(Status.GREEN, "/testproject/sonar"),
                new ProjectPartialLink(Status.UNKNOWN, "/testproject/test"),
                new ProjectPartialLink(Status.UNKNOWN, "/testproject/accp"),
                new ProjectPartialLink(Status.UNKNOWN, "/testproject/prod")
        }, Status.GREEN,
                "/testproject", new Version(1, 0, 1, 142, null))};
    }

}
