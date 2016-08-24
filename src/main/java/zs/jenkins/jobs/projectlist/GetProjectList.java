package zs.jenkins.jobs.projectlist;

import zs.jenkins.httpclient.AbstractClientService;
import zs.jenkins.httpclient.HTTPClient;
import zs.jenkins.jobs.projectdetails.ProjectInformationDTO;
import java.io.IOException;
import java.util.List;

public class GetProjectList extends AbstractClientService {

    public GetProjectList(final HTTPClient client) {
        super(client);
    }

    public List<ProjectInformationDTO> getProjects(final String url) throws IOException {
        ProjectListDTO projectListDTO = client.readValue(url, ProjectListDTO.class);
        return projectListDTO.getJobs();
    }

}
