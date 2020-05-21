package musalasofttests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import musalasofttests.pageobject.ArchivePage;
import musalasofttests.utility.DriverUtility;
import musalasofttests.pageobject.MeetMastersPage;
import musalasofttests.pageobject.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignInTests {
    private static final String INVALID_CREDENTIALS_FILE = "invalidCredentials.json";
    private List<WebDriver> drivers = new ArrayList<>();

    @Test
    public void verifyThatErrorMessageIsShown() throws IOException {
        WebDriver driver = DriverUtility.createDriver();
        drivers.add(driver);

        SignInPage signInPage = new SignInPage(driver);

        ObjectMapper mapper = new ObjectMapper();
        InputStream json = getClass().getClassLoader().getResourceAsStream(INVALID_CREDENTIALS_FILE);
        List<Map<String, String>> data = mapper.readValue(json, new TypeReference<List<Map<String, String>>>() {
        });
        for (Map<String, String> pair : data) {
            signInPage.fillSignInForm(pair.get("username"), pair.get("password"));
            signInPage.assertThatErrorMessageIsShown("Wrong user or password.");
        }

        DriverUtility.quitDriver(driver);
    }

    @Test
    public void verifyThatTheUrlIsLoadedAndLogoIsShown() throws InterruptedException {
        WebDriver driver = DriverUtility.createDriver();
        drivers.add(driver);

        MeetMastersPage meetMasterPage = new MeetMastersPage(driver);

        meetMasterPage.clickOnMusalaSoftLink();

        meetMasterPage.verifyThatTheCorrectUrlIsLoaded("https://masters.musala.com/");
        meetMasterPage.verifyThatTheCompanyLogoIsDisplayed();

        meetMasterPage.clickOnFacebookLink();

        meetMasterPage.verifyThatTheProfilePictureIsDisplayed();

        DriverUtility.quitDriver(driver);
    }

    @Test
    public void verifyThatTheFullEventScheduleIsShown() {
        WebDriver driver = DriverUtility.createDriver();
        drivers.add(driver);

        ArchivePage archievePage = new ArchivePage(driver);

        archievePage.findAndSelectTheLastEvent();

        archievePage.printEventSchedule();

        DriverUtility.quitDriver(driver);
    }

    @AfterTest
    public void tearDown() {
        drivers.forEach(d -> {
            DriverUtility.quitDriver(d);
        });
    }
}
