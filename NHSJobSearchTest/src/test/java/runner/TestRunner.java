package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/Features/jobsearch.feature",
    glue = {"stepdefinitions"},
    plugin = {
              "pretty", // Pretty format for console output
              "html:target/cucumber-reports/cucumber-pretty/index.html", // HTML report
              "json:target/cucumber-reports/cucumber.json"
              },
    monochrome = true, // Makes console output more readable
    tags = "@crossbrowser" // This filters test only for the specified scenarios
)
public class TestRunner extends AbstractTestNGCucumberTests {
}