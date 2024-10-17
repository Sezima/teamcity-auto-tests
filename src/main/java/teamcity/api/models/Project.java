package teamcity.api.models;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Project extends BaseModel {


    private String id;
    private String name;
    private String parentProject;

    // Установка локатора по умолчанию
    @Builder.Default
    private String locator = "_Root";

    // Метод для получения локатора проекта
    public String getLocator() {
        return String.format("id:%s", id != null ? id : locator);
    }



}