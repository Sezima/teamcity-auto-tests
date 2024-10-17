package teamcity.api;

import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeMethod;
import teamcity.api.models.User;
import teamcity.api.requests.CheckedRequests;
import teamcity.api.spec.Specifications;

public class BaseApiTest extends BaseTest {
    protected CheckedRequests superUserCheckRequests;

//    @BeforeMethod(alwaysRun = true)
    @BeforeEach
    public void setupSuperUserRequests() {
        // Установите значения для superUser
        User superUser = User.builder()
                .username("admin")
                .password("admin")
                .build();

        // Инициализация superUserCheckRequests с аутентифицированным запросом
        superUserCheckRequests = new CheckedRequests(Specifications.authSpec(superUser));
    }
}
