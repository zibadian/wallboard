package zs.jenkins.jobs.projectlist;

import zs.jenkins.jobs.projectdetails.ProjectInformationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectListDTO {

    private List<ProjectInformationDTO> jobs;

    public List<ProjectInformationDTO> getJobs() {
        return jobs;
    }
}
