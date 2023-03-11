package study.stepup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import study.stepup.pages.PobedaHomePageSelenide;

public class PobedaTestsSelenide {


    @Before
    public void setup() {
        Configuration.browserSize="1920x1080";
    }

    @Test
    public void pobeda1Test() {
        PobedaHomePageSelenide pobedaHomePageSelenide = new PobedaHomePageSelenide();
        pobedaHomePageSelenide.checkTitle("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы");
        pobedaHomePageSelenide.checkLogo();
        pobedaHomePageSelenide.checkInfoSections();
    }

    @Test
    public void pobeda2Test() {
        PobedaHomePageSelenide pobedaHomePageSelenide = new PobedaHomePageSelenide();
        pobedaHomePageSelenide.checkTitle("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы");
        pobedaHomePageSelenide.checkLogo();
        pobedaHomePageSelenide.checkTicketSearchSection();
        pobedaHomePageSelenide.tryFindingTicketsWithoutReqParams();
    }

    @Test
    public void pobeda3Test() throws InterruptedException {
        PobedaHomePageSelenide pobedaHomePageSelenide = new PobedaHomePageSelenide();
        pobedaHomePageSelenide.checkTitle("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы");
        pobedaHomePageSelenide.checkLogo();
        pobedaHomePageSelenide.bookingSearch();
    }


    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
