package teamcity.api;

import teamcity.api.requests.checked.CheckedRequests;
import teamcity.api.specifications.Specifications;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import teamcity.api.models.testData; // Импортируем класс testData

public class BaseTest {
    protected SoftAssert softy;
    protected CheckedRequests superUserCheckRequests = new CheckedRequests(Specifications.superUserSpec());
    protected testData testData; // Объявляем переменную testData

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        softy = new SoftAssert();
        testData = generate(); // Теперь переменная объявлена
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        softy.assertAll();
    }

    // Добавляем метод generate(), если он необходим
    protected testData generate() {
        // Реализация метода для генерации тестовых данных
        return new testData();
    }
}
