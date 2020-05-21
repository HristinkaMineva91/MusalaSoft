package musalasofttests.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static musalasofttests.utility.PropertiesLoader.getURL;

public class ArchivePage extends Page {

    public ArchivePage(WebDriver driver) {
        super(driver, getURL());
    }

    public void findAndSelectTheLastEvent() {
        archieveTab.click();
        Actions action = new Actions(driver);
        action.moveToElement(lastEvent).click(lastEvent).release().perform();
    }

    public void printEventSchedule() {
        for (int i = 0; i < eventLists.size(); i++) {
            WebElement ul = eventLists.get(i);
            WebElement timeSchedule = ul.findElement(By.xpath(".//li[1]/span"));
            System.out.println(timeSchedule.getText());

            WebElement speakerDiv = ul.findElement(By.xpath(".//div"));
            if (speakerDiv.findElements(By.xpath(".//*")).size() > 0) {
                WebElement speakerInfo = speakerDiv.findElement(By.cssSelector(".speaker-info"));
                String[] spInfo = speakerInfo.getText().split("\\r*\\n");
                String text = null;
                if (spInfo.length == 2) {
                    text = spInfo[1];
                } else if (spInfo.length == 3) {
                    text = spInfo[2];
                }
                System.out.println(text);
            }
        }
    }

    @FindBy(css = "li:nth-child(3) > a")
    private WebElement archieveTab;

    @FindBy(css = "div:nth-child(38) > div > div.event-magnifier")
    private WebElement lastEvent;

    @FindBy(xpath = "//*[@id=\"content\"]/ul")
    private List<WebElement> eventLists;
}

