package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NHScostsonlinePages {
	// Declaring the WebDriver instance
	WebDriver driver; 
    
    // Constructor to initialize WebDriver
    public NHScostsonlinePages(WebDriver driver) {
        this.driver = driver;
    }

    // All locators
    private By acceptCookies = By.id("nhsuk-cookie-banner__link_accept_analytics");
    private By startButton = By.id("next-button");
    private By nextButton = By.id("next-button");
    private By countryEngland = By.id("radio-england");
    private By countryScotland = By.id("radio-scotland");
    private By countryWales = By.id("radio-wales");
    private By countryNorthernIreland = By.id("radio-nire");
    
    private By errorResult = By.xpath("//*[@id='error-summary-list']/li"); 
    
    private By dentalPracticeEng = By.id("radio-england");
    private By dentalPracticeScot = By.id("radio-scotland");
    private By dentalPracticeWales = By.id("radio-wales");
    private By dentalPracticeNorthIre = By.id("radio-nire");
    private By dentalPracticeNotReg = By.id("radio-not-registered");
    
    private By dobDay = By.id("dob-day");
    private By dobMonth = By.id("dob-month");
    private By dobYear = By.id("dob-year");
    
    private By radioButtonYes = By.id("radio-yes");  
    private By radioButtonNo = By.id("radio-no"); 

    private By result = By.xpath("//*[@id='content']/div/div/div[1]/h1");
    private By resultTextNI = By.id("result-heading");    
    private By resultText2 = By.xpath("//*[@id='content']/div/div/div[1]/h2");    
    
    //All methods    
    private void clickNext() {
        driver.findElement(nextButton).click();
    }
    
    public void acceptCookies() {
        driver.findElement(acceptCookies).click();
    }
    
    public void clickStartButton() {
        driver.findElement(startButton).click();
    }
    
    public void selectCountry(String country) {      
        if (country.equalsIgnoreCase("England")) {
            driver.findElement(countryEngland).click();
        } else if (country.equalsIgnoreCase("Scotland")) {
            driver.findElement(countryScotland).click();
        } else if (country.equalsIgnoreCase("Wales")) {
            driver.findElement(countryWales).click();
        } else if (country.equalsIgnoreCase("NorthernIreland")) {
            driver.findElement(countryNorthernIreland).click();
        } else {
            throw new IllegalArgumentException("Invalid country: " + country);
        }
        clickNext();
    }
    
    public void selectGPPracticeScotlandWales(String gpCountry) {
        driver.findElement(gpCountry.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }
    
    public void selectDentalPractice(String dentalCountry) {
        if (dentalCountry.equalsIgnoreCase("England")) {
            driver.findElement(dentalPracticeEng).click();
        } else if (dentalCountry.equalsIgnoreCase("Scotland")) {
            driver.findElement(dentalPracticeScot).click();
        } else if (dentalCountry.equalsIgnoreCase("Wales")) {
            driver.findElement(dentalPracticeWales).click();
        } else if (dentalCountry.equalsIgnoreCase("NorthernIreland")) {
            driver.findElement(dentalPracticeNorthIre).click();
        } else if (dentalCountry.equalsIgnoreCase("Not Registered")) {
            driver.findElement(dentalPracticeNotReg).click();
        } else {
            throw new IllegalArgumentException("Invalid country: " + dentalCountry);
        }
        clickNext();
    }
    
    public void enterDateOfBirth(String day, String month, String year) {
        driver.findElement(dobDay).sendKeys(day);
        driver.findElement(dobMonth).sendKeys(month);
        driver.findElement(dobYear).sendKeys(year);
        clickNext();
    }
    
    public void selectLiveWithPartner(String partner) {
        driver.findElement(partner.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }
    
    public void selectClaimBenefitOrTax(String Claim ) {
        driver.findElement(Claim.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }
    
    public void selectPregnant12Month(String pregnant) {   
        driver.findElement(pregnant.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }
    
    public void selectInjuryArmedForces(String armedForce) {
        driver.findElement(armedForce.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }
    
    public void selectDiabetes(String diabetes) {
        driver.findElement(diabetes.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }

    public void selectDiabetesMedication(String diabetesMed) {
        driver.findElement(diabetesMed.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }
    
    public void selectAnyOtherConditions(String otherCondition) {
        driver.findElement(otherCondition.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }

    public void selectGlaucoma(String glaucoma) {
        driver.findElement(glaucoma.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }

    public void selectLivePermanentlyCareHome(String careHome) {
        driver.findElement(careHome.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }

    public void selectCareHomePay(String careHomePay) {
        driver.findElement(careHomePay.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }    
    
    public void selectSavingsMoreThan1600(String saving) {
        driver.findElement(saving.equalsIgnoreCase("Yes") ? radioButtonYes : radioButtonNo).click();
        clickNext();
    }
    
    public String clickNextWithoutSelectingCountry() {
         clickNext();
         return driver.findElement(errorResult).getText();
    }
    
    public String getResultStatus() {
        return driver.findElement(result).getText();
    }

    public String getResultText() {
        return driver.findElement(resultText2).getText();
    }
    public String getResultTextNI() {
        return driver.findElement(resultTextNI).getText();
    }
}