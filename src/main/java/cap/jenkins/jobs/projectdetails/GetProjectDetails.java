package cap.jenkins.jobs.projectdetails;

import cap.jenkins.httpclient.AbstractClientService;
import cap.jenkins.httpclient.HTTPClient;
import cap.jenkins.jobs.jobdetails.JobInformationDTO;
import java.io.IOException;
import java.util.List;

public class GetProjectDetails extends AbstractClientService {

    public GetProjectDetails(final HTTPClient client) {
        super(client);
    }

    public ProjectDTO getProject(final String projectUrl) throws IOException {
        ProjectDTO projectDTO = client.readValue(projectUrl, ProjectDTO.class);
        List<JobInformationDTO> jobs = projectDTO.getBuilds();
        for (int i = 0; i < jobs.size(); i++) {
            final JobInformationDTO expanded = client.readValue(jobs.get(i).getUrl(), "tree=building,id,result,timestamp,url,fullDisplayName",
                    JobInformationDTO.class);
            jobs.set(i, expanded);
        }
        return projectDTO;
    }

}
