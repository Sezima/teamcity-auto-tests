package teamcity.api.models;

import lombok.Data;

@Data
public class TestData extends BaseModel {
    public Project getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

    public BuildType getBuildType() {
        return buildType;
    }

    private Project project;
    private User user;
    private BuildType buildType;


}

