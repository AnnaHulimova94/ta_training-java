package com.hulimova.task_2;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.hulimova.util.AbstractPage;
import com.hulimova.util.ConfigProvider;
import com.hulimova.util.CustomConditions;

import java.time.Duration;

public class SeleniumGoogleCloudPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='p1o4Hf']")
    private WebElement searchLink;

    @FindBy(xpath = "//input[@class='mb2a7b']")
    private WebElement searchInput;

    @FindBy(xpath = "//a[@class='gs-title'][1]")
    private WebElement googleCalculatorLink;

    @FindBy(xpath = "//button[@class='UywwFc-LgbsSe UywwFc-LgbsSe-OWXEXe-Bz112c-M1Soyc UywwFc-LgbsSe-OWXEXe-dgl2Hf xhASFc']")
    private WebElement addEstimateButton;

    @FindBy(xpath = "//div[@class='d5NbRd-EScbFb-JIbuQc PtwYlf'][1]")
    private WebElement addComputeEngineLink;

    public SeleniumGoogleCloudPage(WebDriver driver) {
        super(driver);
    }

    public SeleniumGoogleCloudPage openPage() {
        driver.get(ConfigProvider.URL_GOOGLE_CLOUD);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(CustomConditions.documentStateIsReady());

        return this;
    }

    public SeleniumGoogleCloudPage clickSearchLink() {
        searchLink.click();

        return this;
    }

    public SeleniumGoogleCloudPage setTextToSearchInput(String text) {
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);

        return this;
    }

    public SeleniumGoogleCloudPage openGoogleCalculator() {
        googleCalculatorLink.click();

        return this;
    }

    public SeleniumComputeEnginePage addComputeEngine() {
        addEstimateButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(addComputeEngineLink))
                .click();

        return new SeleniumComputeEnginePage(driver);
    }
}
