package teamcity.api;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import teamcity.api.models.TestData;

import static teamcity.api.generator.TestDataGenerator.generate;

public class BaseTest {
    protected SoftAssert softy;
    protected TestData testData;


    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        softy = new SoftAssert();
        testData = generate();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        softy.assertAll();
    }
}