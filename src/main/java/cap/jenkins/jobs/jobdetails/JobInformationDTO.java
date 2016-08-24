package cap.jenkins.jobs.jobdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobInformationDTO {

    private boolean building;
    private long duration;
    private String fullDisplayName;
    private String id;
    private JobRunResult result;
    private long timestamp;
    private String url;

    public boolean isBuilding() {
        return building;
    }

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

}
