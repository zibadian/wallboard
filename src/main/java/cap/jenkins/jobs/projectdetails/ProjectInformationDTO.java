package cap.jenkins.jobs.projectdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectInformationDTO {

    private String name;
    private String url;
    private String color;

}
