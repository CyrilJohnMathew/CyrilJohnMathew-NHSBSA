package stepdefinitions;

import org.testng.Assert;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.NHScostsonlinePages;

public class NHScostsonlineSteps {
	static WebDriver driver;
	NHScostsonlinePages pages;
	
    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome"); // Default to Chrome if not specified
        if (driver != null) {
            driver.quit(); // Ensure the previous browser is closed
        }
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        pages = new NHScostsonlinePages(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {	
            driver.quit();
        }
    }	
		
	@Given("I am on the NHS Costs Checker start page")
	public void i_am_on_the_nhs_costs_checker_start_page() throws InterruptedException {
	    driver.get("https://services.nhsbsa.nhs.uk/check-for-help-paying-nhs-costs/start");
        Thread.sleep(3000);
	}

    @Given("I am on the NHS Costs Checker start page using {string}")
    public void i_am_on_the_nhs_costs_checker_start_page(String browser) throws InterruptedException {
        System.setProperty("browser", browser); // Set browser dynamically
        setUp(); // Call setup to initialize driver with the correct browser   	
        driver.get("https://services.nhsbsa.nhs.uk/check-for-help-paying-nhs-costs/start");
        Thread.sleep(3000);
    }	
	
	@Given("I am a citizen of the UK")
	public void i_am_a_citizen_of_the_uk() throws InterruptedException {
        pages.acceptCookies();
        Thread.sleep(2000);
        pages.clickStartButton();
        Thread.sleep(2000);
	}

	@When("I put my circumstances into the Checker tool")
	public void i_put_my_circumstances_into_the_checker_tool() throws InterruptedException {
		
		//Which country do you live in?
        pages.selectCountry("England");
        Thread.sleep(2000);
                
        //Is your GP practice in Scotland or Wales?
        pages.selectGPPracticeScotlandWales("No");
        Thread.sleep(2000);
		
        //Which country is your dental practice in?
        pages.selectDentalPractice("England");
        Thread.sleep(2000);
        
        //What is your date of birth?
        pages.enterDateOfBirth("22", "01", "1993");
        Thread.sleep(2000); 
        
        //Do you live with a partner?
        pages.selectLiveWithPartner("Yes");
        Thread.sleep(2000);

        //Do you or your partner claim any benefits or tax credits?
        pages.selectClaimBenefitOrTax("No");
        Thread.sleep(2000);       
        
        //Are you pregnant or have you given birth in the last 12 months?
        pages.selectPregnant12Month("Yes");
        Thread.sleep(2000);
        
        //Do you have an injury or illness caused by serving in the armed forces?
        pages.selectInjuryArmedForces("No");
        Thread.sleep(2000);       

        //Do you have diabetes?
        pages.selectDiabetes("No");
        Thread.sleep(2000);
        
        //To check what help you can get with NHS charges, we need to know if you're affected by any of these conditions
        pages.selectAnyOtherConditions("No");
        Thread.sleep(2000);

        //Do you have glaucoma?
        pages.selectGlaucoma("Yes");
        Thread.sleep(2000);
        
        //Do you or your partner live permanently in a care home?
        pages.selectLivePermanentlyCareHome("No");
        Thread.sleep(3000);
        
        //Do you and your partner have more than £16,000 in savings, investments or property?
        pages.selectSavingsMoreThan1600("No");
        Thread.sleep(2000);  
	}

	@When("I try to proceed without selecting a country")
	public void i_try_to_proceed_without_selecting_a_country() throws InterruptedException {
	    pages.clickNextWithoutSelectingCountry(); 
	    Thread.sleep(2000);
        String condition = pages.clickNextWithoutSelectingCountry();
        Assert.assertEquals(condition, "Select the country you live in");
        System.out.println("There is a problem: " + condition);
        Thread.sleep(2000);  
	}
	
	@When("I put my country as Northern Ireland into the Checker tool")
	public void i_put_my_country_as_NorthernIreland() throws InterruptedException {
		
		//Which country do you live in?
        pages.selectCountry("NorthernIreland");
        Thread.sleep(2000);		
	}	
	
	//General case: multiple circumstances
    @When("I put multiple circumstances into the Checker tool with the following details:")
    public void i_provide_mult_details_to_NHS_Costs_Checker(DataTable dataTable) throws InterruptedException {
        Map<String, String> userDetails = dataTable.asMap(String.class, String.class);

        pages.selectCountry(userDetails.get("Country"));
        Thread.sleep(2000);

        pages.selectGPPracticeScotlandWales(userDetails.get("GP Practice in Scotland/Wales"));
        Thread.sleep(2000);

        pages.selectDentalPractice(userDetails.get("Dental Practice Location"));
        Thread.sleep(2000);

        pages.enterDateOfBirth(userDetails.get("Date of Birth (DD/MM/YYYY)").split("/")[0], 
                               userDetails.get("Date of Birth (DD/MM/YYYY)").split("/")[1], 
                               userDetails.get("Date of Birth (DD/MM/YYYY)").split("/")[2]);
        Thread.sleep(2000);

        pages.selectLiveWithPartner(userDetails.get("Living with a Partner"));
        Thread.sleep(2000);

        pages.selectClaimBenefitOrTax(userDetails.get("Claiming Benefits or Tax Credits"));
        Thread.sleep(2000);

        pages.selectPregnant12Month(userDetails.get("Pregnant/Gave Birth in Last 12 Months"));
        Thread.sleep(2000);

        pages.selectInjuryArmedForces(userDetails.get("Injury or Illness from Armed Forces"));
        Thread.sleep(2000);

        // Skip diabetes, otherConditions, and glaucoma for Scotland
        if (!userDetails.get("Country").equalsIgnoreCase("Scotland")) {
            pages.selectDiabetes(userDetails.get("Diabetes"));
            Thread.sleep(2000);

            pages.selectAnyOtherConditions(userDetails.get("Other Medical Conditions"));
            Thread.sleep(2000);

            pages.selectGlaucoma(userDetails.get("Glaucoma"));
            Thread.sleep(2000);
        }

        pages.selectLivePermanentlyCareHome(userDetails.get("Living in a Care Home"));
        Thread.sleep(3000);

        pages.selectSavingsMoreThan1600(userDetails.get("Savings or Property > £16,000"));
        Thread.sleep(2000);
    }

	//Diabetes and Care home: multiple circumstances
    @When("I put multiple circumstances including Diabetes and Care home into the Checker tool with the following details:")
    public void i_provide_mult_details_with_Diabetes_CareHome_to_NHS_Costs_Checker(DataTable dataTable) throws InterruptedException {
        Map<String, String> userDetails = dataTable.asMap(String.class, String.class);

        pages.selectCountry(userDetails.get("Country"));
        Thread.sleep(2000);

        pages.selectGPPracticeScotlandWales(userDetails.get("GP Practice in Scotland/Wales"));
        Thread.sleep(2000);

        pages.selectDentalPractice(userDetails.get("Dental Practice Location"));
        Thread.sleep(2000);

        pages.enterDateOfBirth(userDetails.get("Date of Birth (DD/MM/YYYY)").split("/")[0], 
                               userDetails.get("Date of Birth (DD/MM/YYYY)").split("/")[1], 
                               userDetails.get("Date of Birth (DD/MM/YYYY)").split("/")[2]);
        Thread.sleep(2000);

        pages.selectLiveWithPartner(userDetails.get("Living with a Partner"));
        Thread.sleep(2000);

        pages.selectClaimBenefitOrTax(userDetails.get("Claiming Benefits or Tax Credits"));
        Thread.sleep(2000);

        pages.selectPregnant12Month(userDetails.get("Pregnant/Gave Birth in Last 12 Months"));
        Thread.sleep(2000);

        pages.selectInjuryArmedForces(userDetails.get("Injury or Illness from Armed Forces"));
        Thread.sleep(2000);

        pages.selectDiabetes(userDetails.get("Diabetes"));
        Thread.sleep(2000);
            
        pages.selectDiabetesMedication(userDetails.get("Diabetes Medication"));
        Thread.sleep(2000);
        
            // Skip 'otherConditions' if Diabetes Medication is Yes
            if (userDetails.get("Diabetes Medication").equalsIgnoreCase("No")) {            
            pages.selectAnyOtherConditions(userDetails.get("Other Medical Conditions"));
            Thread.sleep(2000);
            }

        pages.selectLivePermanentlyCareHome(userDetails.get("Living in a Care Home"));
        Thread.sleep(3000);
        
        // Handle CarehomePay only if Living in a Care Home is Yes
        if (userDetails.get("Living in a Care Home").equalsIgnoreCase("Yes")) {
            pages.selectCareHomePay(userDetails.get("Help to pay for your care home"));
            Thread.sleep(2000);
        }

        // Skip 'Savings or Property > £16,000' if 'Help to pay for your care home' is Yes
        if (!userDetails.get("Help to pay for your care home").equalsIgnoreCase("Yes")) { 
            pages.selectSavingsMoreThan1600(userDetails.get("Savings or Property > £16,000"));
            Thread.sleep(2000);        	
        }
    }    
    
	@Then("I should get a result of whether I will get help or not")
	public void i_should_get_a_result_of_whether_i_will_get_help_or_not() throws InterruptedException {
        //Checking the NHS Cost online service check completed or not
		Assert.assertEquals(pages.getResultStatus(), "Done");
        System.out.println("NHS Cost Online Check completed");
        
		//Fetching the result details text for the user        
        String resultText = pages.getResultText();
        System.out.println("Result Details: " + resultText);
        Thread.sleep(2000);
	}
	
	@Then("I should get a result based on my country: NorthernIreland")
	public void i_should_get_a_result_based_on_country_NorthernIreland() throws InterruptedException {
        //Checking the NHS Cost online service check completed or not
        String resultText = pages.getResultTextNI();
        System.out.println("Result Details: " + resultText);
        Assert.assertEquals(resultText, "You cannot use this service because you live in Northern Ireland");
        System.out.println("NHS Cost Online Check completed");
        Thread.sleep(2000);      
	}
}