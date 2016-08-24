package zs.jenkins.jobs.projectdetails;

import zs.jenkins.jobs.jobdetails.JobInformationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDTO {

    private String displayName;
    private String url;
    private List<JobInformationDTO> builds;
    private JobInformationDTO lastBuild;
    private JobInformationDTO lastCompletedBuild;
    private JobInformationDTO lastFailedBuild;
    private JobInformationDTO lastStableBuild;
    private JobInformationDTO lastSuccessfulBuild;
    private JobInformationDTO lastUnstableBuild;
    private JobInformationDTO lastUnsuccessfulBuild;

    public String getDisplayName() {
        return displayName;
    }

    public String getUrl() {
        return url;
    }

    public List<JobInformationDTO> getBuilds() {
        return builds;
    }

    public JobInformationDTO getLastBuild() {
        return lastBuild;
    }

    public JobInformationDTO getLastCompletedBuild() {
        return lastCompletedBuild;
    }

    public JobInformationDTO getLastFailedBuild() {
        return lastFailedBuild;
    }

    public JobInformationDTO getLastStableBuild() {
        return lastStableBuild;
    }

    public JobInformationDTO getLastSuccessfulBuild() {
        return lastSuccessfulBuild;
    }

    public JobInformationDTO getLastUnstableBuild() {
        return lastUnstableBuild;
    }

    public JobInformationDTO getLastUnsuccessfulBuild() {
        return lastUnsuccessfulBuild;
    }

}
