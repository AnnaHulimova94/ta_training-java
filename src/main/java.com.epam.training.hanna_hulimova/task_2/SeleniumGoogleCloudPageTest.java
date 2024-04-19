package task_2;

import com.typesafe.config.Config;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.ConfigProvider;

import java.util.List;

public class SeleniumGoogleCloudPageTest {

    private WebDriver driver;

    private List<? extends Config> optionList;

    public SeleniumGoogleCloudPageTest() {
        optionList = ConfigProvider.readConfig().getConfigList("task_2.options");
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void shutDown() {
        driver.quit();
    }

    @Test
    public void test_google_calculator_for_compute_engine() {
        test_google_calculator_for_compute_engine(optionList.get(0));
    }

    private void test_google_calculator_for_compute_engine(Config config) {
        SeleniumComputeEnginePage computeEnginePage = findByTextInGoogleCloud(new SeleniumGoogleCloudPage(driver), ConfigProvider.SEARCH_TEXT_GOOGLE)
                .openGoogleCalculator()
                .addComputeEngine();

        setParametersToGoogleCalculator(computeEnginePage, config);
    }

    private void setParametersToGoogleCalculator(SeleniumComputeEnginePage computeEnginePage, Config config) {
        computeEnginePage
                .setNumberOfInstances(config.getInt("number_of_instances"))
                .setOperationSystem(SeleniumComputeEnginePage.OperationSystemType.valueOf(config.getString("operation_system")))
                .setProvisionModel(SeleniumComputeEnginePage.ProvisionModel.valueOf(config.getString("vm_class")))
                .setMachineType(SeleniumComputeEnginePage.MachineType.valueOf(config.getString("machine_type")))
                .clickAddGPUButton()
                .setGPUModel(SeleniumComputeEnginePage.GPUModel.valueOf(config.getString("gpu_model")))
                .setNumberOgGPU(SeleniumComputeEnginePage.NumberOfGPU.valueOf(config.getString("number_of_gpu")));
    }

    private SeleniumGoogleCloudPage findByTextInGoogleCloud(SeleniumGoogleCloudPage googleCloudPage, String text) {
        return googleCloudPage
                .openPage()
                .clickSearchLink()
                .setTextToSearchInput(text);
    }
}
