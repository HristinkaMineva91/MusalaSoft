package musalasofttests.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {
    protected WebDriver driver;
    protected String url;

    public Page(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;

        driver.get(url);
        PageFactory.initElements(driver, this);
    }
}
