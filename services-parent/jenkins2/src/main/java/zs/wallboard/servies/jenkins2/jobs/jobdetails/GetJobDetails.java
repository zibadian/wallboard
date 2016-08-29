package zs.wallboard.servies.jenkins2.jobs.jobdetails;

import zs.wallboard.commons.AbstractHTTPClient;
import zs.wallboard.servies.jenkins2.httpclient.AbstractClientService;

public class GetJobDetails extends AbstractClientService<JobDetailsDTO> {

    public GetJobDetails(final AbstractHTTPClient client) {
        super(client);
    }

    @Override
    public JobDetailsDTO getData(final String url) {
        return readUrl(url + "api/json/", JobDetailsDTO.class);
    }

}
