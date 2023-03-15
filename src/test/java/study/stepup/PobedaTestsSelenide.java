package study.stepup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.stepup.pages.PobedaHomePageSelenide;

@Epic("Pobeda")
public class PobedaTestsSelenide {


    @BeforeEach
    public void setup() {
        Configuration.browserSize="1920x1080";
    }

    @Test
    @Feature("Отображение информации")
    @Description ("Отображение лого и информационных секций")
    public void pobeda1Test() {
        PobedaHomePageSelenide pobedaHomePageSelenide = new PobedaHomePageSelenide();
        pobedaHomePageSelenide.checkTitle("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы");
        pobedaHomePageSelenide.checkLogo();
        pobedaHomePageSelenide.checkInfoSections();
    }

    @Test
    @Feature("Поиск билетов")
    @Description ("Попытка поиска билета без обязательных параметров")
    public void pobeda2Test() {
        PobedaHomePageSelenide pobedaHomePageSelenide = new PobedaHomePageSelenide();
        pobedaHomePageSelenide.checkTitle("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы");
        pobedaHomePageSelenide.checkLogo();
        pobedaHomePageSelenide.checkTicketSearchSection();
        pobedaHomePageSelenide.tryFindingTicketsWithoutReqParams();
    }

    @Test
    @Feature("Поиск бронирования")
    @Description ("Поиск несуществующего бронирования")
    public void pobeda3Test() {
        PobedaHomePageSelenide pobedaHomePageSelenide = new PobedaHomePageSelenide();
        pobedaHomePageSelenide.checkTitle("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы");
        pobedaHomePageSelenide.checkLogo();
        pobedaHomePageSelenide.bookingSearch();
    }

    @Test
    @Feature("Отображение информации")
    @Description ("Описания для проваленного теста")
    public void pobeda4TestError() {
        PobedaHomePageSelenide pobedaHomePageSelenide = new PobedaHomePageSelenide();
        pobedaHomePageSelenide.checkTitle("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы1");
    }


    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
