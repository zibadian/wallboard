package cap.jenkins.jobs.jobdetails;

import cap.jenkins.httpclient.HTTPClient;
import java.io.IOException;

public class GetJobDetails {

    private final HTTPClient client;

    public GetJobDetails(final HTTPClient client) {
        this.client = client;
    }

    public JobDetailsDTO getJob(final String jobUrl) throws IOException {
        return client.readValue(jobUrl, JobDetailsDTO.class);
    }

}
