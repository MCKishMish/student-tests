package study.stepup.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import junit.framework.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.Duration;
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

    @Step("Проверка, что заголовок сайта - {title}")
    public void checkTitle(String title) {
        Assert.assertEquals(title, title());
    }
    @Step("Проверка присутстия лого")
    public void checkLogo() {
        Assert.assertTrue(pobedaLogo.isDisplayed());
    }

    @Step ("Проверка, что при наведении курсора на секцию Информация отображены подразделы")
    public void checkInfoSections() {
        infoSection.hover();
        Assert.assertTrue(prepareToFlightSubsection.isDisplayed());
        Assert.assertTrue(usefulInfoSubsection.isDisplayed());
        Assert.assertTrue(aboutSubsection.isDisplayed());
    }

    @Step ("Проверка отображения секций блока Поиск билета")
    public void checkTicketSearchSection() {
        Assert.assertTrue(startingPoint.isDisplayed());
        Assert.assertTrue(finishPoint.isDisplayed());
        Assert.assertTrue(dateTo.isDisplayed());
        Assert.assertTrue(dateBack.isDisplayed());
    }

    @Step ("Попытка поиска без обязательного параметра (выделение красным обязательного поля)")
    public void tryFindingTicketsWithoutReqParams() {
        startingPoint.setValue("Москва");
        startingPoint.press(Keys.ARROW_DOWN);
        startingPoint.press(Keys.ENTER);
        finishPoint.setValue("Санкт-Петербург");
        finishPoint.press(Keys.ARROW_DOWN);
        finishPoint.press(Keys.ENTER);
        ticketSearchButton.click();
        Assert.assertTrue(dateTo.getCssValue("color").equals("rgba(185, 0, 85, 1)"));
    }

    @Step ("Поиск несуществующего билета и проверка сообщения об отсутствии результатов поиска")
    public void bookingSearch() {
        bookingManagementTab.click();
        Assert.assertTrue(clientLastname.isDisplayed());
        Assert.assertTrue(orderNumber.isDisplayed());
        orderNumber.setValue("XXXXXX");
        clientLastname.setValue("Ivanov");
        bookingSearchButton.click();
        switchTo().window(1);
        bookingSearchErrorMessage.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Assert.assertTrue(bookingSearchErrorMessage.isDisplayed());
    }
}
