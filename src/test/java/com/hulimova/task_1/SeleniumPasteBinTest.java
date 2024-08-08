package com.hulimova.task_1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.hulimova.util.ConfigProvider;

public class SeleniumPasteBinTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void shutDown() {
        driver.quit();
    }

    //The website is protected from robot.

    @Test
    public void test_create_paste_bin() {
        SeleniumPasteBinPage pasteBinPage = new SeleniumPasteBinPage(driver);

        createPasteBin(pasteBinPage,
                SeleniumPasteBinPage.Format.BASH,
                SeleniumPasteBinPage.Expiration.TEN_MINUTES,
                ConfigProvider.config.getString("task_1.paste_1.paste_name"),
                ConfigProvider.config.getString("task_1.paste_1.code"));
    }

    private SeleniumPasteBinPage createPasteBin(SeleniumPasteBinPage pasteBinPage,
                                                SeleniumPasteBinPage.Format format,
                                                SeleniumPasteBinPage.Expiration expiration,
                                                String pasteName,
                                                String code) {
        return pasteBinPage
                .openPage()
                .setFormat(format)
                .setExpiration(expiration)
                .setPasteName(pasteName)
                .setCode(code)
                .createPaste();
    }
}
