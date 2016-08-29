package zs.wallboard.servies.jenkins2.jobs.projectdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectInformationDTO {

    private String name;
    private String url;
    private String color;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getColor() {
        return color;
    }
}
