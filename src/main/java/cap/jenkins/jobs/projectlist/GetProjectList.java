package cap.jenkins.jobs.projectlist;

import cap.jenkins.httpclient.AbstractClientService;
import cap.jenkins.httpclient.HTTPClient;

public class GetProjectList extends AbstractClientService {

    public GetProjectList(final HTTPClient client) {
        super(client);
    }

}
