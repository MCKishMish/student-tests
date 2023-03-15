package study.stepup;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import study.stepup.pages.PobedaHomePage;

import java.time.Duration;

public class PobedaTests {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/shalatonov/Downloads/chromedriver_mac64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    @Test
    public void pobeda1Test() {
        PobedaHomePage pobedaHomePage = new PobedaHomePage(driver);
        pobedaHomePage.checkTitle("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы");
        pobedaHomePage.checkLogo();
        pobedaHomePage.checkInfoSections();
    }

    @Test
    public void pobeda2Test() {
        PobedaHomePage pobedaHomePage = new PobedaHomePage(driver);
        pobedaHomePage.checkTitle("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы");
        pobedaHomePage.checkLogo();
        pobedaHomePage.checkTicketSearchSection();
        pobedaHomePage.tryFindingTicketsWithoutReqParams();
    }

    @Test
    public void pobeda3Test() throws InterruptedException {
        PobedaHomePage pobedaHomePage = new PobedaHomePage(driver);
        pobedaHomePage.checkTitle("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы");
        pobedaHomePage.checkLogo();
        pobedaHomePage.bookingSearch();
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
