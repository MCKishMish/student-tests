package study.stepup.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PobedaHomePage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='dp-2ri4z4']/a/img")
    private WebElement pobedaLogo;

    @FindBy(xpath = "//div[text()='Информация']")
    private WebElement infoSection;

    @FindBy(xpath = "//div[text()='Подготовка к полёту']")
    private WebElement prepareToFlightSubsection;

    @FindBy(xpath = "//div[text()='Полезная информация']")
    private WebElement usefulInfoSubsection;

    @FindBy(xpath = "//div[text()='О компании']")
    private WebElement aboutSubsection;

    public PobedaHomePage (WebDriver driver) {
        this.driver=driver;
        driver.get("https://pobeda.aero/");
        PageFactory.initElements(driver, this);
    }

    public void checkTitle(String title) {
        Assert.assertEquals(title, driver.getTitle());
    }

    public void checkLogo() {
        Assert.assertTrue(pobedaLogo.isDisplayed());
    }

    public void checkInfoSections() {
        Actions actions = new Actions(driver);
        actions.moveToElement(infoSection).perform();
        Assert.assertTrue(prepareToFlightSubsection.isDisplayed());
        Assert.assertTrue(usefulInfoSubsection.isDisplayed());
        Assert.assertTrue(aboutSubsection.isDisplayed());
    }
}
