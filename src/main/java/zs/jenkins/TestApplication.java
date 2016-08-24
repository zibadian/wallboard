package zs.jenkins;

import zs.jenkins.httpclient.HTTPClient;
import zs.jenkins.jobs.jobdetails.GetJobDetails;
import zs.jenkins.jobs.jobdetails.JobDetailsDTO;
import zs.jenkins.jobs.jobdetails.JobInformationDTO;
import zs.jenkins.jobs.projectdetails.GetProjectDetails;
import zs.jenkins.jobs.projectdetails.ProjectDTO;
import zs.jenkins.jobs.projectdetails.ProjectInformationDTO;
import zs.jenkins.jobs.projectlist.GetProjectList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestApplication {

    public static void main(String[] args) throws IOException {
        final HTTPClient httpClient = new HTTPClient("localhost:9000");
        final List<ProjectInformationDTO> projectList = new GetProjectList(httpClient).getProjects("http://localhost:9000/");

        final GetProjectDetails getProjectDetails = new GetProjectDetails(httpClient);
        final GetJobDetails getJobDetails = new GetJobDetails(httpClient);

        final List<JobDetailsDTO> jobs = new ArrayList<>();

        for (ProjectInformationDTO projectInformationDTO : projectList) {
            System.out.println(projectInformationDTO.getName()+"("+projectInformationDTO.getColor()+")");
            final ProjectDTO project = getProjectDetails.getProject(projectInformationDTO.getUrl());
            for (JobInformationDTO jobInformationDTO : project.getBuilds()) {
                jobs.add(getJobDetails.getJob(jobInformationDTO.getUrl()));
            }
        }

        for (JobDetailsDTO jobDetailsDTO : jobs) {
            System.out.println(jobDetailsDTO.getFullDisplayName()
                    + ": "+ jobDetailsDTO.getTimestamp()
                    + ", "+ jobDetailsDTO.getResult());
        }

    }

}
