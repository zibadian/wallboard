package zs.wallboard.servies.jenkins2.jobs.projectdetails;

import zs.wallboard.commons.AbstractHTTPClient;
import zs.wallboard.servies.jenkins2.httpclient.AbstractClientService;
import zs.wallboard.servies.jenkins2.jobs.jobdetails.JobInformationDTO;

import java.util.List;

public class GetProjectDetails extends AbstractClientService<ProjectDTO> {

    public GetProjectDetails(final AbstractHTTPClient client) {
        super(client);
    }

    @Override
    public ProjectDTO getData(final String url) {
        ProjectDTO projectDTO = readUrl(url+"api/json", ProjectDTO.class);
        List<JobInformationDTO> jobs = projectDTO.getBuilds();
        for (int i = 0; i < jobs.size(); i++) {
            readJobInformation(jobs, i);
        }
        return projectDTO;
    }

    private void readJobInformation(final List<JobInformationDTO> jobs, final int index) {
        final JobInformationDTO jobInfo = readUrl(jobs.get(index).getUrl()
                + "api/json/?tree=building,id,result,timestamp,url,fullDisplayName,duration", JobInformationDTO.class);
        if (jobInfo == null) {
            jobs.set(index, new JobInformationDTO());
        } else {
            jobs.set(index, jobInfo);
        }

    }

}
