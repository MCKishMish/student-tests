package study.stepup.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.windows.WindowByNameOrHandle;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;
import static org.awaitility.Awaitility.with;

public class PobedaHomePageSelenide {



    private SelenideElement pobedaLogo = $x("//div[@class='dp-2ri4z4']/a/img");

    private SelenideElement infoSection = $x("//div[text()='Информация']");

    private SelenideElement prepareToFlightSubsection = $x("//div[text()='Подготовка к полёту']");

    private SelenideElement usefulInfoSubsection = $x("//div[text()='Полезная информация']");

    private SelenideElement aboutSubsection = $x("//div[text()='О компании']");

    private SelenideElement startingPoint = $("#mantine-R2qualmqm-target");

    private SelenideElement finishPoint = $("#mantine-R3qualmqm-target");

    private SelenideElement dateTo = $("[id=':Rbcualmqm:']");

    private SelenideElement dateBack = $("[id=':Rrcualmqm:']");

    private SelenideElement ticketSearchButton = $x("//div[text()='Поиск']");

    private SelenideElement bookingManagementTab = $x("//div[@class='__mantine-ref-text dp-vbfp0a' and text()='Управление бронированием']");

    private SelenideElement clientLastname = $("[id=':r3:']");

    private SelenideElement orderNumber = $("[id=':r4:']");

    private SelenideElement bookingSearchButton = $("[class='dp-ujv363']");

    private SelenideElement bookingSearchErrorMessage = $x("//div[text()='Заказ с указанными параметрами не найден']");



    public PobedaHomePageSelenide() {
        open("https://pobeda.aero/");
    }

    public static void waitVisibleElement(WebElement we) {
        with().pollDelay(100, TimeUnit.MILLISECONDS).await().atMost
                (90, TimeUnit.SECONDS).until(we::isDisplayed);
    }

    public void checkTitle(String title) {
        Assert.assertEquals(title, title());
    }

    public void checkLogo() {
        Assert.assertTrue(pobedaLogo.isDisplayed());
    }

    public void checkInfoSections() {
        infoSection.hover();
        Assert.assertTrue(prepareToFlightSubsection.isDisplayed());
        Assert.assertTrue(usefulInfoSubsection.isDisplayed());
        Assert.assertTrue(aboutSubsection.isDisplayed());
    }

    public void checkTicketSearchSection() {
        Assert.assertTrue(startingPoint.isDisplayed());
        Assert.assertTrue(finishPoint.isDisplayed());
        Assert.assertTrue(dateTo.isDisplayed());
        Assert.assertTrue(dateBack.isDisplayed());
    }

    public void tryFindingTicketsWithoutReqParams () {
        startingPoint.setValue("Москва");
        startingPoint.press(Keys.ARROW_DOWN);
        startingPoint.press(Keys.ENTER);
        finishPoint.setValue("Санкт-Петербург");
        finishPoint.press(Keys.ARROW_DOWN);
        finishPoint.press(Keys.ENTER);
        ticketSearchButton.click();
        Assert.assertTrue(dateTo.getCssValue("color").equals("rgba(185, 0, 85, 1)"));
    }

    public void bookingSearch () throws InterruptedException {
        bookingManagementTab.click();
        Assert.assertTrue(clientLastname.isDisplayed());
        Assert.assertTrue(orderNumber.isDisplayed());
        orderNumber.setValue("XXXXXX");
        clientLastname.setValue("Qwerty");
        bookingSearchButton.click();
        switchTo().window(1);
        bookingSearchErrorMessage.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Assert.assertTrue(bookingSearchErrorMessage.isDisplayed());
    }
}
