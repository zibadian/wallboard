package zs.wallboard.servies.jenkins2.jobs.jobdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobInformationDTO {

    private long duration;
    private String fullDisplayName;
    private String id;
    private JobRunResult result;
    private long timestamp;
    private String url;

    public long getDuration() {
        return duration;
    }

    public String getFullDisplayName() {
        return fullDisplayName;
    }

    public String getId() {
        return id;
    }

    public JobRunResult getResult() {
        return result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setBuilding(final boolean building) {
        if (building) {
            result = JobRunResult.RUNNING;
        }
    }

    public void setResult(final JobRunResult result) {
        if (this.result == null) {
            this.result = result;
        }
    }

}
