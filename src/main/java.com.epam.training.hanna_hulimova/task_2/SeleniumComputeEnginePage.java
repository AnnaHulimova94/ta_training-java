package task_2;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumComputeEnginePage {

    private WebDriver driver;

    @FindBy(id = "c11")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//div[@data-field-type='106']")
    private WebElement selectOperationSystem;

    @FindBy(xpath = "//div[@aria-controls='c32']")
    private WebElement selectMachineType;

    @FindBy(xpath = "//button[@aria-label='Add GPUs']")
    private WebElement addGPUButton;

    @FindBy(xpath = "//div[@data-field-type='158']")
    private WebElement selectGPUModel;

    @FindBy(xpath = "//div[@data-field-type='174']")
    private WebElement selectNumberOfGPU;

    @FindBy(xpath = "//button[@class='glue-cookie-notification-bar__accept']")
    private WebElement closeInfoButton;


    public SeleniumComputeEnginePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(closeInfoButton))
                    .click();
        } catch (TimeoutException ignored) {
        }
    }

    public SeleniumComputeEnginePage setNumberOfInstances(int numberOfInstances) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(numberOfInstancesInput))
                .clear();
        numberOfInstancesInput.sendKeys(Integer.toString(numberOfInstances));

        return this;
    }

    public SeleniumComputeEnginePage setOperationSystem(OperationSystemType operationSystem) {
        selectOperationSystem.click();
        driver.findElement(By.xpath(operationSystem.xpath)).click();

        return this;
    }

    public SeleniumComputeEnginePage setProvisionModel(ProvisionModel provisionModel) {
        driver.findElements(By.xpath("//div[@class=' e2WL2b MYT3K pV2hx oLWDHd']"))
                .get(provisionModel.index)
                .click();

        return this;
    }

    public SeleniumComputeEnginePage setMachineType(MachineType machineType) {
        selectMachineType.click();
        driver.findElement(By.xpath(machineType.xpath)).click();

        return this;
    }

    public SeleniumComputeEnginePage clickAddGPUButton() {
        addGPUButton.click();

        return this;
    }

    public SeleniumComputeEnginePage setGPUModel(GPUModel gpuModel) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(selectGPUModel))
                .click();

        driver.findElement(By.xpath(gpuModel.xpath)).click();

        return this;
    }

    public SeleniumComputeEnginePage setNumberOgGPU(NumberOfGPU numberOgGPU) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(selectNumberOfGPU))
                .click();

        driver.findElement(By.xpath(numberOgGPU.xpath)).click();

        return this;
    }

    public enum OperationSystemType {
        FREE("//li[@data-value='free-debian-centos-coreos-ubuntu-or-byol-bring-your-own-license']");

        private String xpath;

        OperationSystemType(String xpath) {
            this.xpath = xpath;
        }
    }

    public enum ProvisionModel {
        REGULAR(0),
        SPOT(1);

        int index;

        ProvisionModel(int index) {
            this.index = index;
        }
    }

    public enum MachineType {
        CUSTOM("//li[@data-value='custom']"),
        STANDARD_4("//li[@data-value='n1-standard-4']"),
        STANDARD_8("//li[@data-value='n1-standard-8']");

        String xpath;

        MachineType(String xpath) {
            this.xpath = xpath;
        }
    }

    public enum GPUModel {
        T4("//li[@data-value='nvidia-tesla-t4']"),
        V100("//li[@data-value='nvidia-tesla-v100']");

        String xpath;

        GPUModel(String xpath) {
            this.xpath = xpath;
        }
    }

    public enum NumberOfGPU {
        ONE("//li[@data-value='1']"),
        TWO("//li[@data-value='2']"),
        FOUR("//li[@data-value='4']");

        String xpath;

        NumberOfGPU(String xpath) {
            this.xpath = xpath;
        }
    }
}
