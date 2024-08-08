package com.hulimova.task_1;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.hulimova.util.AbstractPage;
import com.hulimova.util.ConfigProvider;
import com.hulimova.util.CustomConditions;

import java.time.Duration;

public class SeleniumPasteBinPage extends AbstractPage {

    @FindBy(id = "postform-text")
    private WebElement pasteTextArea;

    @FindBy(id = "select2-postform-format-container")
    private WebElement formatSelectContainer;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement expirationContainer;

    @FindBy(id = "postform-name")
    private WebElement pasteNameInput;

    @FindBy(id = "qc-cmp2-ui")
    private WebElement privacyInfo;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createPasteButton;

    public SeleniumPasteBinPage(WebDriver driver) {
        super(driver);
    }

    public SeleniumPasteBinPage openPage() {
        driver.get(ConfigProvider.URL_PASTE_BIN);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(CustomConditions.documentStateIsReady());

        closePrivacyInfo();

        return this;
    }

    public SeleniumPasteBinPage setFormat(Format format) {
        formatSelectContainer.click();
        driver.findElement(By.xpath(format.format)).click();

        return this;
    }

    public SeleniumPasteBinPage setExpiration(Expiration expiration) {
        expirationContainer.click();
        driver.findElement(By.xpath(expiration.expiration)).click();

        return this;
    }

    public SeleniumPasteBinPage setPasteName(String pasteName) {
        pasteNameInput.sendKeys(pasteName);

        return this;
    }

    public SeleniumPasteBinPage setCode(String code) {
        pasteTextArea.sendKeys(code);

        return this;
    }

    public SeleniumPasteBinPage createPaste() {
        createPasteButton.click();

        return this;
    }

    private void closePrivacyInfo() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(privacyInfo))
                    .findElement(By.xpath("//button[@mode='primary']//span[text()='AGREE']")).click();
        } catch (TimeoutException ignored) {
        }
    }

    public enum Format {
        BASH("//li[text()='Bash']"),
        C("//li[text()='C']"),
        JAVA("//li[text()='Java']"),
        JSON("//li[text()='JSON']");

        private final String format;

        Format(String format) {
            this.format = format;
        }
    }

    public enum Expiration {
        NEVER("//li[text()='Never']"),
        TEN_MINUTES("//li[text()='10 Minutes']"),
        ONE_HOUR("//li[text()='1 Hour']");

        private final String expiration;

        Expiration(String expiration) {
            this.expiration = expiration;
        }
    }
}
