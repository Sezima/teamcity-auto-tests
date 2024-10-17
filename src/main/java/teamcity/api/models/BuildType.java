package teamcity.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildType extends BaseModel {
    private String id;
    private String name;
    private Project project;
    private Steps steps;
    private String status; // Добавляем поле для статуса

    // Метод для получения статуса
    public String getStatus() {
        return status;
    }

    // Метод для установки статуса
    public void setStatus(String status) {
        this.status = status;
    }
}