package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features/NHScostsonline.feature", // Path to your feature file
        glue = {"stepdefinitions"}, // Package where your step definitions are located
        plugin = {
                "pretty", // Pretty format for console output
                "html:target/cucumber-reports/cucumber-pretty/index.html" // HTML report
        },
        monochrome = true, // Makes console output more readable
        tags = "@smoke or @northernireland or @datadriven or @exceptional or @crossbrowser" // This filters test only for the specified scenarios
)
public class TestRunner extends AbstractTestNGCucumberTests {
}