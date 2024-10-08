package teamcity.api.models;

import lombok.Data;

@Data
public class testData extends BaseModel {
    private Project project;
    private User user;
    private BuildType buildType;


}

