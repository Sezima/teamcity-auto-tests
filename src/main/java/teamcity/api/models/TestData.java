package teamcity.api.models;

import java.util.UUID;

import lombok.Data;

@Data
public class TestData extends BaseModel {
    private Project project;
    private User user;
    private BuildType buildType;

    public Project getProject() {
        if (project == null) {
            project = createRandomProject();
        }
        return project;
    }

    private Project createRandomProject() {
        return Project.builder()
                .id("P" + UUID.randomUUID().toString().replace("-", "").substring(0, 8)) // Генерация уникального ID, начинающегося с 'P'
                .name("Project " + UUID.randomUUID().toString().substring(0, 8)) // Генерация случайного имени
                .locator("_Root")
                .build();
    }
}
