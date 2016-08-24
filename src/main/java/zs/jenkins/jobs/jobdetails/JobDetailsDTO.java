package zs.jenkins.jobs.jobdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobDetailsDTO extends JobInformationDTO {

    private List<Object> actions;
    private List<Object> artifacts;
    private String description;
    private String displayName;
    private long estimatedDuration;
    private boolean keepLog;
    private int number;
    private int queueId;
    private String builtOn;
    private Object changeSet;
    private List<Object> culprits;

}
