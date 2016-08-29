package zs.wallboard.servies.jenkins2.jobs.projectlist;

import zs.wallboard.commons.AbstractHTTPClient;
import zs.wallboard.servies.jenkins2.httpclient.AbstractClientService;
import zs.wallboard.servies.jenkins2.jobs.projectdetails.ProjectInformationDTO;

import java.util.List;

public class GetProjectList extends AbstractClientService<List<ProjectInformationDTO>> {

    public GetProjectList(final AbstractHTTPClient client) {
        super(client);
    }

    @Override
    public List<ProjectInformationDTO> getData(final String url) {
        final ProjectListDTO dto = readUrl(url + "api/json/", ProjectListDTO.class);
        return dto.getJobs();
    }

}
