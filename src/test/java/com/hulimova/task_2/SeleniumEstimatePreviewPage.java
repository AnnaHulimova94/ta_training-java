package com.hulimova.task_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumEstimatePreviewPage {

    private WebDriver driver;

    @FindBy(xpath = "//span[text()='Provisioning Model']/../span[@class='Kfvdz']")
    private WebElement provisionWebModel;

    @FindBy(xpath = "//span[text()='Machine type']/../span[@class='Kfvdz']")
    private WebElement machineType;

    @FindBy(xpath = "//span[text()='Region']/../span[@class='Kfvdz']")
    private WebElement region;

    @FindBy(xpath = "//span[text()='Local SSD']/../span[@class='Kfvdz']")
    private WebElement localSSD;

    @FindBy(xpath = "//span[text()='Compute']/../span[@class='OtcLZb OIcOye']")
    private WebElement compute;

    public SeleniumEstimatePreviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleIs("Google Cloud Estimate Summary"));
    }

    public String getProvisionWebModel() {
        return provisionWebModel.getText().toUpperCase();
    }

    public String getMachineType() {
        return machineType.getText().split(",")[0].replace("-", "_").toUpperCase();
    }

    public String getRegion() {
        return region.getText().split(" ")[0].toUpperCase();
    }

    public String getLocalSSD() {
        switch (localSSD.getText().split(" ")[0]) {
            case "0": {
                return "ZERO";
            }
            case "1x375": {
                return "ONEx375";
            }
            case "2x375": {
                return "TWOx375";
            }
        }

        return null;
    }

    public String getCompute() {
        return compute.getText();
    }
}
