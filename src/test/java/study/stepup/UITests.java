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

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.with;

public class UITests {
    private WebDriver wd;
    private WebDriverWait webDriverWait;

    public static void waitVisibleElement(WebElement we) {
        with().pollDelay(100, TimeUnit.MILLISECONDS).await().atMost
                (90, TimeUnit.SECONDS).until(we::isDisplayed);
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/shalatonov/Downloads/chromedriver_mac64/chromedriver");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        webDriverWait=new WebDriverWait(wd,Duration.ofSeconds(10));
    }

    @Test
    public void pikabuCheck() {

        wd.get("https://pikabu.ru/");

        Assert.assertEquals("Горячее – самые интересные и обсуждаемые посты | Пикабу", wd.getTitle());
        By loginButton = By.xpath("//span[@class='header-right-menu__login-button']");
        wd.findElement(loginButton).click();


        By loginInputInModal = By.xpath("//div[@class='auth-modal auth-modal_new']//input[@placeholder='Логин']");
        By passwordInputInModal = By.xpath("//div[@class='auth-modal auth-modal_new']//input[@placeholder='Пароль']");
        By loginButtonInModal = By.xpath("//div[text()='Необходимо войти или зарегистрироваться']/following-sibling::div/div/div/div/div/following-sibling::div/form/div[7]/button");

        Assert.assertTrue(wd.findElement(By.xpath("//div[@class='auth-modal auth-modal_new']")).isDisplayed());
        Assert.assertTrue(wd.findElement(loginInputInModal).isDisplayed());
        Assert.assertTrue(wd.findElement(passwordInputInModal).isDisplayed());
        Assert.assertTrue(wd.findElement(loginButtonInModal).isDisplayed());

        wd.findElement(loginInputInModal).sendKeys("Qwerty");
        wd.findElement(passwordInputInModal).sendKeys("Qwerty");
        wd.findElement(loginButtonInModal).click();

        Assert.assertTrue(wd.findElement(By.xpath("//span[text()='Ошибка. Вы ввели неверные данные авторизации']")).isDisplayed());
    }

    @Test
    public void googleSearchPobeda() {

        wd.get("https://google.com/");
        By searchInput = By.cssSelector("[title='Поиск']");
        wd.findElement(searchInput).sendKeys("Сайт компании Победа");
        wd.findElement(searchInput).sendKeys(Keys.ENTER);

        By pobedaLink = By.cssSelector("h3");
        wd.findElement(pobedaLink).click();

        WebElement goToKaliningrad = wd.findElement(By.cssSelector("[alt='banner_pobedanew_winter_kaliningrad.jpeg']"));
        waitVisibleElement(goToKaliningrad);
        Assert.assertTrue(wd.findElement(By.xpath("//img[@alt='banner_pobedanew_winter_kaliningrad.jpeg']/following-sibling::div[3]/div/div")).getText().equals("Полетели в Калининград!"));

        wd.findElement(By.xpath("//div[text()='РУС']")).click();
        wd.findElement(By.xpath("//div[text()='English']")).click();
        By ticketSearch = By.xpath("//div[@class=\"__mantine-ref-text dp-vbfp0a\"][contains(text(),'Ticket search')]");
        By onlineCheckIn = By.xpath("//div[@class=\"__mantine-ref-text dp-vbfp0a\"][contains(text(),'Online check-in')]");
        By manageMyBooking = By.xpath("//div[@class=\"__mantine-ref-text dp-vbfp0a\"][contains(text(),'Manage my booking')]");

        webDriverWait.until(ExpectedConditions.textToBePresentInElement(wd.findElement(ticketSearch),"Ticket search"));
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(wd.findElement(onlineCheckIn),"Online check-in"));
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(wd.findElement(manageMyBooking),"Manage my booking"));
    }

    @After
    public void tearDown() {
        wd.quit();
    }
}
