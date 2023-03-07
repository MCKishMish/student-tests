package study.stepup;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import study.stepup.pages.PobedaHomePage;

import java.time.Duration;

public class PobedaTests {

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/shalatonov/Downloads/chromedriver_mac64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        //webDriverWait=new WebDriverWait(wd,Duration.ofSeconds(10));
    }

    @Test
    public void pobeda1Test() throws InterruptedException {
        PobedaHomePage pobedaHomePage = new PobedaHomePage(driver);
        pobedaHomePage.checkTitle("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы");
        pobedaHomePage.checkLogo();
        pobedaHomePage.checkInfoSections();
    }



    @After
    public void tearDown() {
        driver.quit();
    }
}
