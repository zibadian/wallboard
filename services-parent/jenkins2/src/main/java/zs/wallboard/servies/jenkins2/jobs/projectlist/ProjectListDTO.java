package zs.wallboard.servies.jenkins2.jobs.projectlist;

import zs.wallboard.servies.jenkins2.jobs.projectdetails.ProjectInformationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectListDTO {

    private List<ProjectInformationDTO> jobs;

    public List<ProjectInformationDTO> getJobs() {
        return jobs;
    }
}
