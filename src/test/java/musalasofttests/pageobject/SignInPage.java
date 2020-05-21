package musalasofttests.pageobject;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static musalasofttests.utility.PropertiesLoader.*;

public class SignInPage extends Page {

    public SignInPage(WebDriver driver) {
        super(driver, getURL());
    }

    public void fillSignInForm(String user, String pass) {
        signFormButton.click();
        username.sendKeys(user);
        password.sendKeys(pass);
        signInButton.click();
    }

    public void assertThatErrorMessageIsShown(String expectedText) {
        Assert.assertEquals(expectedText, actualErrorMessage.getText());
    }

    @FindBy(css = "li:nth-child(5) > a")
    private WebElement signFormButton;

    @FindBy(id = "login-form_username")
    private WebElement username;

    @FindBy(id = "login-form_password")
    private WebElement password;

    @FindBy(id = "btn-sign-in")
    private WebElement signInButton;

    @FindBy(css = "div:nth-child(2) > p")
    private WebElement actualErrorMessage;
}
