package com.hulimova.task_2;

import com.typesafe.config.Config;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.hulimova.util.ConfigProvider;

public class SeleniumGoogleCloudPageTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void shutDown() {
        driver.quit();
    }

    @Test
    public void test_google_calculator_for_compute_engine() {
        test_google_calculator_for_compute_engine(ConfigProvider.readConfig()
                .getConfigList("task_2.options")
                .get(0));
    }

    private void test_google_calculator_for_compute_engine(Config config) {
        SeleniumComputeEnginePage computeEnginePage = findByTextInGoogleCloud(ConfigProvider.SEARCH_TEXT_GOOGLE)
                .openGoogleCalculator()
                .addComputeEngine();

        SeleniumEstimatePreviewPage estimatePreviewPage = setParametersToGoogleCalculator(computeEnginePage, config)
                .openPreview();


        Assert.assertEquals(config.getString("vm_class"), estimatePreviewPage.getProvisionWebModel());
        Assert.assertEquals(config.getString("machine_type"), estimatePreviewPage.getMachineType());
        Assert.assertEquals(config.getString("region"), estimatePreviewPage.getRegion());
        Assert.assertEquals(config.getString("local_SSD"), estimatePreviewPage.getLocalSSD());
        Assert.assertEquals(config.getString("manual_compute"), estimatePreviewPage.getCompute());
    }

    private SeleniumComputeEnginePage setParametersToGoogleCalculator(SeleniumComputeEnginePage computeEnginePage, Config config) {
        return computeEnginePage
                .setNumberOfInstances(config.getInt("number_of_instances"))
                .setOperationSystem(SeleniumComputeEnginePage.OperationSystemType.valueOf(config.getString("operation_system")))
                .setProvisionModel(SeleniumComputeEnginePage.ProvisionModel.valueOf(config.getString("vm_class")))
                .setMachineType(SeleniumComputeEnginePage.MachineType.valueOf(config.getString("machine_type")))
                .clickAddGPUButton()
                .setGPUModel(SeleniumComputeEnginePage.GPUModel.valueOf(config.getString("gpu_model")))
                .setNumberOgGPU(SeleniumComputeEnginePage.NumberOfGPU.valueOf(config.getString("number_of_gpu")))
                .setLocalSSD(SeleniumComputeEnginePage.LocalSSD.valueOf(config.getString("local_SSD")))
                .setRegion(SeleniumComputeEnginePage.Region.valueOf(config.getString("region")))
                .setCommittedUsage(SeleniumComputeEnginePage.CommittedUsage.valueOf(config.getString("committed_usage")));
    }

    private SeleniumGoogleCloudPage findByTextInGoogleCloud(String text) {
        return new SeleniumGoogleCloudPage(driver)
                .openPage()
                .clickSearchLink()
                .setTextToSearchInput(text);
    }
}
