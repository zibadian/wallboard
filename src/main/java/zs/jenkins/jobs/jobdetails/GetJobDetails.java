package zs.jenkins.jobs.jobdetails;

import zs.jenkins.httpclient.AbstractClientService;
import zs.jenkins.httpclient.HTTPClient;
import java.io.IOException;

public class GetJobDetails extends AbstractClientService {

    public GetJobDetails(final HTTPClient client) {
        super(client);
    }

    public JobDetailsDTO getJob(final String jobUrl) throws IOException {
        return client.readValue(jobUrl, JobDetailsDTO.class);
    }

}
