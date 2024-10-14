package teamcity.api;

import org.apache.hc.core5.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import teamcity.api.enums.Endpoint;
import teamcity.api.models.BuildType;
import teamcity.api.models.Project;
import teamcity.api.models.TestData;
import teamcity.api.models.User;
import teamcity.api.requests.CheckedRequests;
import teamcity.api.requests.checked.CheckedBase;
import teamcity.api.requests.unchecked.UncheckedBase;
import teamcity.api.spec.Specifications;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static teamcity.api.enums.Endpoint.*;
import static teamcity.api.generator.TestDataGenerator.generate;
@Test(groups = {"Regression"})
public class BuildTypeTest extends BaseApiTest {

    @Test(description = "User should be able to create build type", groups = {"Positive", "CRUD"})
    public void userCreatesBuildTypeTest() {
        var userCheckRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        superUserCheckRequests.getRequest(USERS).create(testData.getUser());


        // Создаём проект
        userCheckRequests.<Project>getRequest(PROJECT).create(testData.getProject());

        // Создаём тип билда
        userCheckRequests.<BuildType>getRequest(BUILD_TYPES).create(testData.getBuildType());

        // Читаем созданный тип билда и проверяем его
        var createdBuildType = userCheckRequests.<BuildType>getRequest(BUILD_TYPES).read(testData.getBuildType().getId());

        // Проверяем, что имя типа билда совпадает
        softy.assertEquals(testData.getBuildType().getName(), createdBuildType.getName(), "Build type name is not correct");
    }

    @Test(description = "User should not be able to create two build types with the same id", groups = {"Negative", "CRUD"})
    public void userCreatesTwoBuildTypesWithTheSameIdTest() {
        var buildTypeWithSameId = generate(BuildType.class, testData.getBuildType().getId());

        superUserCheckRequests.getRequest(USERS).create(testData.getUser());

        var userCheckRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        // Создаём проект и тип билда
        userCheckRequests.<Project>getRequest(PROJECT).create(testData.getProject());
        userCheckRequests.<BuildType>getRequest(BUILD_TYPES).create(testData.getBuildType());

        // Пытаемся создать тип билда с тем же ID
        new UncheckedBase(Specifications.authSpec(testData.getUser()), BUILD_TYPES)
                .create(buildTypeWithSameId)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(Matchers.containsString("The build configuration / template ID \"%s\" is already used by another configuration or template".formatted(testData.getBuildType().getId())));
    }

    @Test(description = "Project admin should be able to create build type for their project", groups = {"Positive", "Roles"})
    public void projectAdminCreatesBuildTypeTest() {
        step("Create user");
        step("Create project");
        step("Grant user PROJECT_ADMIN role in project");

        step("Create buildType for project by user (PROJECT_ADMIN)");
        step("Check buildType was created successfully");

        // Здесь могут быть ваши конкретные шаги для создания юзера, проекта и т.д.
    }

    @Test(description = "Project admin should not be able to create build type for not their project", groups = {"Negative", "Roles"})
    public void projectAdminCreatesBuildTypeForAnotherUserProjectTest() {
        step("Create user1");
        step("Create project1");
        step("Grant user1 PROJECT_ADMIN role in project1");

        step("Create user2");
        step("Create project2");
        step("Grant user2 PROJECT_ADMIN role in project2");

        step("Create buildType for project1 by user2");
        step("Check buildType was not created with forbidden code");

        // Здесь добавьте шаги для проверки ошибки при создании типа билда в чужом проекте
    }
}
