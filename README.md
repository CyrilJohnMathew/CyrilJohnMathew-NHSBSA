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

![image](https://github.com/user-attachments/assets/dc207cf2-2605-43ac-8108-8295117b73a1)

-------------------------------------------------------------

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/CyrilJohnMathew/CyrilJohnMathew-NHSBSA.git
2. **Navigate to the project directory:**
  ```bash
  cd NHSJobSearchTest
 ```
3. **Install dependencies:**
  ```bash
mvn clean install
```
-------------------------------------------------------------
## Running the Tests
* Open **command prompt**.
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
