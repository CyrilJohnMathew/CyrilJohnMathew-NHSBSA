package pages;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.ParseException;

public class NHSSearchPage {
	WebDriver driver; 
    
    public NHSSearchPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // All locators
    private By acceptCookies = By.id("accept-cookies");
    private By keyword = By.id("keyword"); 
    private By location = By.id("location");
    private By searchButton = By.xpath("//*[@id='search']");
    private By searchHeading = By.id("search-results-heading");
    private By sortButton = By.xpath("//select[@id='sort']");
    private By resultJobTitles = By.xpath("//main//ul/li//h2/a");
    private By listAllPostDates = By.xpath("//main//ul/li/div[3]/div[1]/ul/li[contains(normalize-space(.), 'Date posted:')]");
    
    
    public void acceptCookies() {
    	new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(acceptCookies))
        .click();
    }
    
    public void enterKeyword(String job) {
        driver.findElement(keyword).sendKeys(job);
    }

    public void enterLocation(String loc) {
        driver.findElement(location).sendKeys(loc);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }
    
    //Checking whether results are displayed
    public boolean isResultsDisplayed() {
        String headingText = driver.findElement(searchHeading).getText();
        System.out.println("Search result: " + headingText);
        if (headingText.contains("No result found")) {
            return false;
        }
        return driver.getPageSource().contains("jobs found for");
    }
    
    // Returns true if at least one listed job title contains the provided title keyword
    public boolean isResultJobTitleMatchingInputJobTitle(String keyword) {
        List<WebElement> jobTitleElements = driver.findElements(resultJobTitles);
        String lowerKeyword = keyword.toLowerCase();

        for (WebElement jobElement : jobTitleElements) {
            String jobTitle = jobElement.getText().toLowerCase();
            if (jobTitle.contains(lowerKeyword)) {
                return true; // Found at least one match
            }
        }
        System.out.println("No result job title contains the provided job title: " + keyword);
        return false;
    }
    
    // Accessors for the input fields
    public String getKeywordField() {
        return driver.findElement(keyword).getAttribute("value");
    }
    public String getLocationField() {
        return driver.findElement(location).getAttribute("value");
    }
    
    // Sort by: Date Posted (newest)
    public void sortResultByDatePosted(String sortOption) {
    	WebElement dropdown = driver.findElement(sortButton);
		new Select(dropdown).selectByVisibleText(sortOption);
    }
    
    // Checking for newest dated results: Return true if jobs are sorted by most recent
    public boolean isSortedByNewest() throws java.text.ParseException {
         // Find all date elements
         List<WebElement> dateElements = driver.findElements(listAllPostDates);
         List<Date> postedDates = new ArrayList<>();
         SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);

            for (WebElement dateElement : dateElements) {
                try {
                    String dateText = dateElement.getText().trim(); // sample o/p "Date posted: 23 June 2025"
                    String dateOnly = dateText.replace("Date posted: ", "").trim();  // sample o/p "23 June 2025"
                    Date parsedDate = dateFormat.parse(dateOnly);              
                    postedDates.add(parsedDate);
                } catch (ParseException e) {
                    System.out.println("Date parsing failed for: " + dateElement.getText() + " -> " + e.getMessage());
                    return false;
                }
            }

            // Check that the dates are in descending order (newest first)
            for (int i = 0; i < postedDates.size() - 1; i++) {
                if (postedDates.get(i).before(postedDates.get(i + 1))) {
                    return false;
                }
            }
            return true;
        }
    }