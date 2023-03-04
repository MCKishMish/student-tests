package study.stepup;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UITests {
    WebDriver wd;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/shalatonov/Downloads/chromedriver_mac64/chromedriver");
        wd = new ChromeDriver();
    }

    @Test
    public void pikabuCheck() throws InterruptedException {

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

        WebElement loginError = new WebDriverWait(wd, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Ошибка. Вы ввели неверные данные авторизации']")));
        Assert.assertTrue(loginError.isDisplayed());
    }

    @After
    public void tearDown() {
        wd.quit();
    }
}
