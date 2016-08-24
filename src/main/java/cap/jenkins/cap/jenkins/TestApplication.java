package cap.jenkins.cap.jenkins;

import cap.jenkins.httpclient.HTTPClient;
import cap.jenkins.jobs.jobdetails.GetJobDetails;
import cap.jenkins.jobs.jobdetails.JobDetailsDTO;
import cap.jenkins.jobs.projectdetails.GetProjectDetails;
import cap.jenkins.jobs.projectdetails.ProjectDTO;
import java.io.IOException;

public class TestApplication {

    public static void main(String[] args) throws IOException {
        final HTTPClient httpClient = new HTTPClient("localhost:9000");
        final ProjectDTO project = new GetProjectDetails(httpClient).getProject("http://localhost:9000/job/happy-test/");
        System.out.println(project.getDisplayName());
        final JobDetailsDTO job = new GetJobDetails(httpClient).getJob(project.getBuilds().get(0).getUrl());
        System.out.println(job.getFullDisplayName());
    }

}
