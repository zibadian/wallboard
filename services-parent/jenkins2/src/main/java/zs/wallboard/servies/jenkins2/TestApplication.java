package zs.wallboard.servies.jenkins2;

import com.fasterxml.jackson.databind.ObjectMapper;
import zs.wallboard.commons.AbstractHTTPClient;
import zs.wallboard.servies.jenkins2.httpclient.Authenticator;
import zs.wallboard.servies.jenkins2.httpclient.impl.HTTPClientImpl;
import zs.wallboard.servies.jenkins2.jobs.jobdetails.GetJobDetails;
import zs.wallboard.servies.jenkins2.jobs.jobdetails.JobDetailsDTO;
import zs.wallboard.servies.jenkins2.jobs.jobdetails.JobInformationDTO;
import zs.wallboard.servies.jenkins2.jobs.projectdetails.GetProjectDetails;
import zs.wallboard.servies.jenkins2.jobs.projectdetails.ProjectDTO;
import zs.wallboard.servies.jenkins2.jobs.projectdetails.ProjectInformationDTO;
import zs.wallboard.servies.jenkins2.jobs.projectlist.GetProjectList;

import javax.ws.rs.client.ClientBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestApplication {

    private static final String BASE_URL = "http://233.10.11.12:8080/";
    private static final String USER_ID = "user";
    private static final String USER_PASSWORD = "password";

    public static void main(String[] args) throws IOException {
        final AbstractHTTPClient httpClient = new HTTPClientImpl(ClientBuilder
                .newClient()
                .register(new Authenticator(USER_ID, USER_PASSWORD.toCharArray())));
        final List<ProjectInformationDTO> projectList = new GetProjectList(httpClient).getData(BASE_URL);

        final GetProjectDetails getProjectDetails = new GetProjectDetails(httpClient);
        final GetJobDetails getJobDetails = new GetJobDetails(httpClient);

        final List<JobDetailsDTO> jobs = new ArrayList<>();

        for (ProjectInformationDTO projectInformationDTO : projectList) {
            System.out.println(projectInformationDTO.getName() + "(" + projectInformationDTO.getColor() + ")");
            final ProjectDTO project = getProjectDetails.getData(projectInformationDTO.getUrl());
            for (JobInformationDTO jobInformationDTO : project.getBuilds()) {
                jobs.add(getJobDetails.getData(jobInformationDTO.getUrl()));
            }
        }

        for (JobDetailsDTO jobDetailsDTO : jobs) {
            System.out.println(new ObjectMapper().writeValueAsString(jobDetailsDTO));
        }
    }

}
