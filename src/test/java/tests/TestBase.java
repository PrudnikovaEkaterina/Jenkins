package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.remote = "https://user1:1234@"+System.getProperty("selenoidUrl","selenoid.autotests.cloud/wd/hub");//запускает автотесты не локально а через selenoid
        Configuration.browser = System.getProperty("browser","chrome");
        Configuration.browserVersion = System.getProperty("browser_version","100.0"); //нельзя ставить версию больше чем на selenoid
        Configuration.browserSize = System.getProperty("browser_size", "1920x1080");
        Configuration.baseUrl = System.getProperty("base_url","https://demoqa.com");


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }
    @BeforeEach
    void addListener() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    // добавляется сценарий теста как в IDEA (шаги)
    // + в build.gradle добавили сначало зависимость "io.qameta.allure:allure-selenide:2.13.6"

    //Перед этими настройками добавили файл в дирректорию java/helpers название файла Attach c методами
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }


    @AfterAll
    static void afterAll() {
        Configuration.holdBrowserOpen = false;
    }

}

