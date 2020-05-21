package musalasofttests.pageobject;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

import static musalasofttests.utility.PropertiesLoader.*;

public class MeetMastersPage extends Page {

    public MeetMastersPage(WebDriver driver) {
        super(driver, getURL());
    }

    public void clickOnMusalaSoftLink() {
        musalaSoftLink.click();
    }

    public void clickOnFacebookLink() {
        closeCurrentTab();
        facebookLink.click();
        driver.getCurrentUrl();
        switchTab();
    }

    public void verifyThatTheCorrectUrlIsLoaded(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    public void verifyThatTheCompanyLogoIsDisplayed(String message) {
        Assert.assertTrue(message, companyLogo.isDisplayed());
    }

    public void verifyThatTheProfilePictureIsDisplayed(String message) {
        Assert.assertTrue(message, profilePicture.isDisplayed());
    }

    private void closeCurrentTab() {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        driver.close();
        driver.switchTo().window(tabs2.get(0));
    }

    private void switchTab() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    @FindBy(css = ".leftPartFooter .footer-image")
    private WebElement musalaSoftLink;

    @FindBy(css = ".muffin-logo-pic")
    private WebElement companyLogo;

    @FindBy(css = ".rightPartFooter .pull-right")
    private WebElement facebookLink;

    @FindBy(css = "._6tb5.img")
    private WebElement profilePicture;

}


