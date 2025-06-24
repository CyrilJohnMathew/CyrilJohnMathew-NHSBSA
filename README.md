# CyrilJohnMathew-NHSBSA

## NHS Job Search - Cross-Browser Automation Test Suite
### Overview 
Ensure the following are installed:
- Java JDK 17+ (Recommended: JDK 21)
- Maven 3.8+
- Chrome and Firefox browsers
- Internet access (required for WebDriverManager to download drivers)
- Environment variables correctly configured:
  - `JAVA_HOME`
  - `MAVEN_HOME`
  - System `PATH` including Maven and Java bin directories
--------------------------------------------------------------
## Project Structure

src
├── test
│   ├── java
│   │   ├── pages                # Page Object Model for NHS job search page
│   │   │   └── NHSSearchPage.java
│   │   ├── runner               # TestNG-based Cucumber runner
│   │   │   └── TestRunner.java
│   │   ├── stepdefinitions      # Glue code for feature steps
│   │   │   └── NHSJobSearchSteps.java
│   │   └── utils                # Utilities like DriverFactory and Axe Checker
│   │       ├── DriverFactory.java
│   │       └── AccessibilityChecker.java
│   └── resources
│       ├── Features             # Cucumber feature files
│       │   └── jobsearch.feature
│       ├── config               # Config files
│       │   └── config.properties
│       └── axe.min.js           # Local copy of axe-core for accessibility checks
├── pom.xml                      # Project dependencies and build configuration
└── testng.xml                   # TestNG suite configuration

-------------------------------------------------------------

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo/nhs-jobsearch-automation.git
2. **Navigate to the project directory:**
  ```bash
  cd nhs-jobsearch-automation
 ```
3. **Install dependencies:**
  ```bash
mvn clean install
```
-------------------------------------------------------------
## Running the Tests
* Open command prompt or terminal.
* Navigate to the project root. 
### Run All Tests:
* mvn test  (will pick Chrome as default browser)
### Run Tests in Specific Browser (Default is Chrome):
* Use -Dbrowser=<browser> to override the default browser. 
Example:
* mvn test -Dbrowser=chrome (to run the program in Chrome Browser)
* mvn test -Dbrowser=firefox (to run the program in Firefox Browser)
### Run with Tags (e.g., @crossbrowser):
* mvn test -Dcucumber.filter.tags="@crossbrowser"

--------------------------------------------------------------
## What’s Covered
•	Basic Job Search functionality with job title and location
•	Sorting Results by "Date Posted (newest)"
•	Cross-browser Execution with Chrome and Firefox
•	Post-search Field Persistence (ensuring filters remain after search)
•	Accessibility Compliance using axe-core

-------------------------------------------------------------
## Reports
•	HTML Report:
1. Cucumber: /NHSJobSearchTest/target/cucumber-reports/cucumber-pretty/index.html
2. TestNG: /NHSJobSearchTest/target/surefire-reports/index.html
•	JSON Report: target/cucumber-reports/cucumber.json
