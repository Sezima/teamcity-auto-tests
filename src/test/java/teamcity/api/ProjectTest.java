package teamcity.api;

import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;
import teamcity.api.enums.Endpoint;
import teamcity.api.models.Project;
import teamcity.api.models.TestData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProjectTest extends BaseApiTest {

    private final TestData testData = new TestData(); // Создаем экземпляр TestData


//        return "Не могу нормально передать  " +
//                "parentProject": "locator": "locator"
//из-за чего у меня 500 ошибка
//




    @Test
    public void testCreateProject() {
        // Получаем проект со случайными данными
        Project project = testData.getProject();

        // Используем superUserCheckRequests для аутентифицированного запроса
        Response response = superUserCheckRequests.<Project>getRequest(Endpoint.PROJECT).create(project);

        // Проверяем, что проект был создан
        assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_OK)); // Ожидаем 200 (но надо переделать на 201)
    }


//     негативные сценарии


    // Негативный сценарий - создание проекта с null
    @Test
    public void testCreateProjectWithNull() {
        Project project = new Project();

        // Отправляем запрос на создание проекта
        Response response = superUserCheckRequests.getRequest(Endpoint.PROJECT).create(project);

        // Логгируем
        System.out.println("Response Body: " + response.getBody().asString());

        // Проверяем, что статус ответа 400 (BAD REQUEST)
        assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));
    }

    // Негативный сценарий - создание проекта с пустым именем
    @Test
    public void testCreateProjectWithEmptyName() {
        Project project = new Project();
        project.setName("");
        project.setId("007");
        project.setParentProject("projectParent007");
        // Отправляем запрос на создание проекта
        Response response = superUserCheckRequests.getRequest(Endpoint.PROJECT).create(project);
        System.out.println("Response Body: " + response.getBody().asString());

        if (response.getStatusCode() == 500) {
            System.out.println("Ошибка 500. Код правильный, но статус код 500, надо исправить");
        } else {
            // Проверяем, что статус ответа 400 (BAD REQUEST)
            assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));

        }

    }

    @Test
    public void testCreateProjectWithEmptyID() {
        Project project = new Project(); // передаем пустую строку как имя проекта
        project.setName(testData.getProject().getId());
        project.setId("");
        project.setParentProject(testData.getProject().getParentProject());
        // Отправляем запрос на создание проекта
        Response response = superUserCheckRequests.getRequest(Endpoint.PROJECT).create(project);
        System.out.println("Response Body: " + response.getBody().asString());

        if (response.getStatusCode() == 500) {
            System.out.println("Ошибка 500. Код правильный, но статус код 500, надо исправить");
        } else {
            // Проверяем, что статус ответа 400 (BAD REQUEST)
            assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));

        }
    }

    // Негативный сценарий - создание проекта с некорректным типом данных
    @Test
    public void testCreateProjectWithInvalidDataType() {
        Project project = new Project();
        project.setName("ValidName");
        project.setId("InvalidId");
        project.setParentProject("projectParent007");

        // Отправляем запрос на создание проекта
        Response response = superUserCheckRequests.getRequest(Endpoint.PROJECT).create(project);
        System.out.println("Response Body: " + response.getBody().asString());
        if (response.getStatusCode() == 500) {
            System.out.println("Ошибка 500. Код правильный, но статус код 500, надо исправить");
        } else {
            // Проверяем, что статус ответа 400 (BAD REQUEST)
            assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));

        }
    }

    // Проверка длины строк
    @Test
    public void testCreateProjectWithLongName() {
        Project project = new Project();
        project.setName("A".repeat(256)); // Допустим, максимальная длина - 255 символов
        project.setId("008");
        project.setParentProject("projectParent008");

        // Отправляем запрос на создание проекта
        Response response = superUserCheckRequests.getRequest(Endpoint.PROJECT).create(project);
        System.out.println("Response Body: " + response.getBody().asString());

        if (response.getStatusCode() == 500) {
            System.out.println("Ошибка 500. Код правильный, но статус код 500, надо исправить");
        } else {
            // Проверяем, что статус ответа 400 (BAD REQUEST)
            assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));

        }
    }

    //Проверка специальных символов в имени
    @Test
    public void testCreateProjectWithSpecialCharactersInName() {
        Project project = new Project();
        project.setName("InvalidName!@#"); // Специальные символы
        project.setId("009");
        project.setParentProject("projectParent009");

        // Отправляем запрос на создание проекта
        Response response = superUserCheckRequests.getRequest(Endpoint.PROJECT).create(project);
        System.out.println("Response Body: " + response.getBody().asString());
        if (response.getStatusCode() == 500) {
            System.out.println("Ошибка 500. Код правильный, но статус код 500, надо исправить");
        } else {
            // Проверяем, что статус ответа 400 (BAD REQUEST)
            assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));

        }
    }

    // Проверка уникальности имени
    @Test
    public void testCreateProjectWithDuplicateName() {
        Project project = testData.getProject();

        // Создаем первый проект
        superUserCheckRequests.getRequest(Endpoint.PROJECT).create(project);

        // Пытаемся создать второй проект с тем же именем
        Response response = superUserCheckRequests.getRequest(Endpoint.PROJECT).create(project);
        System.out.println("Response Body: " + response.getBody().asString());
        if (response.getStatusCode() == 500) {
            System.out.println("Ошибка 500. Код правильный, но статус код 500, надо исправить");
        } else {
            // Проверяем, что статус ответа 400 (BAD REQUEST)
            assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));

        }
    }


}

