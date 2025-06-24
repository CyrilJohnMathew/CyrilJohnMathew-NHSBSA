package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pages.NHSSearchPage;
import utils.DriverFactory;
import utils.AccessibilityChecker;
import java.text.ParseException;

public class NHSJobSearchSteps {
    WebDriver driver;
    NHSSearchPage searchPage;
    private String searchTitle;
    private String searchLocation;

    @Given("I open the NHS job search page on {string}")
    public void openNhsJobSearch(String browser) {
        System.setProperty("browser", browser);  
        driver = DriverFactory.initDriver();     
        searchPage = new NHSSearchPage(driver);  
        searchPage.acceptCookies();              
        System.out.println("Accepted Cookies");
    }
    
    @When("I put my preferences {string} and {string} into the Search functionality")
    public void putPreferences(String title, String location) {  	
        this.searchTitle = title; // Save for later
        this.searchLocation = location; // Save for later
    	searchPage.enterKeyword(title);
        searchPage.enterLocation(location);
        searchPage.clickSearch();
    }

    @Then("I should get a list of jobs that match my preferences")
    public void verifyResults() throws Exception {
		String titleOfSearchResult = driver.getTitle();
		System.out.println("Result title displayed: " + titleOfSearchResult);
		//checking result is displayed.
        Assert.assertTrue(searchPage.isResultsDisplayed(), "No search results were found!");
		//checking result contains provided preference - job title.
        Assert.assertTrue(searchPage.isResultJobTitleMatchingInputJobTitle(searchTitle),
                "None of the job titles contain the keyword: " + searchTitle); 
		//checking search filters (keyword and location) persist after search.
        Assert.assertEquals(searchPage.getKeywordField(), searchTitle,"Keyword field was not persisted after Search");
        Assert.assertEquals(searchPage.getLocationField(), searchLocation,"Location field was not persisted after Search");
        // Accessibility check after search
        AccessibilityChecker.runAxeCheck(driver);
    }
    
    @Then ("I sort my search results with the sort option {string}")
    public void sortResults(String sortOption) throws ParseException {
        searchPage.sortResultByDatePosted(sortOption);  
        Assert.assertTrue(searchPage.isSortedByNewest(), "Results are not sorted by Date Posted (newest)!");
    }
    
    /**
     * Closing the browsers
     */
    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
