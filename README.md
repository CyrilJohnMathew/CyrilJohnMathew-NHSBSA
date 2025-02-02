# NHS Costs Online Checker - Automation Test Suite

## Overview
This project is a Selenium-based automation framework using Java and Cucumber for behavior-driven development (BDD) testing.
This repository contains automated tests for the NHS Costs Online Checker tool using **Selenium**, **Java**, and **Cucumber**. 
It covers various scenarios to ensure the tool works as expected for different user circumstances.

## Prerequisites
Ensure you have the following installed before running the tests:
1. Java JDK 11+
2. Maven (for dependency management and build automation)
3. Google Chrome Browser (for default execution)
4. Microsift Edge Browser (to run in Edge)

## **Project Structure**
The project is structured as follows:
```
src
└── test
    ├── java
    │   ├── stepdefinitions
    │   │   └── NHScostsonlineSteps.java
    │   ├── pages
    │   │   └── NHScostsonlinePages.java
    │   └── runner
    │       └── TestRunner.java
    └── resources
        └── features
            └── NHScostsonline.feature
```
- **`stepdefinitions`**: Contains the step definitions for the Cucumber scenarios.
- **`pages`**: Contains the Page Object Model (POM) classes for interacting with the NHS Costs Checker tool.
- **`runner`**: Contains the `TestRunner` class to execute the Cucumber tests.
- **`features`**: Contains the Cucumber feature files with test scenarios.

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/CyrilJohnMathew/CyrilJohnMathew-NHSBSA.git
   ```
2. Navigate to the project directory:
   ```sh
   cd NHSBSAtest
   ```
   (Navigate to Downloaded Project location.
    If the project location is C:\Users\cyril\eclipse-workspace\NHSBSAtest , then cd C:\Users\cyril\eclipse-workspace\NHSBSAtest )
4. Install dependencies:
   ```sh
   mvn clean install
   ```

## Running Tests
### Running all tests
```sh
mvn test
```

### Running tests with specific tag
```sh
mvn test -Dcucumber.options="--tags @smoke"
```

## Test Coverage
### The test suite covers the following scenarios:
 1.Basic eligibility check for a UK citizen.  
 2. Specific case for Northern Ireland citizens. (Included positive and negative tests)  
 3. Various combinations of multiple circumstances.   
 4. Exceptional cases (e.g., diabetes, care home).  
 5. Cross-browser compatibility (Chrome, Edge).

## Test Coverage Matrix

| Test Scenario | Acceptance Criteria Covered | Status |
|----------|----------|----------|
| Basic eligibility check for a UK citizen | Given I am a citizen of the UK, When I put my circumstances into the Checker tool, Then I should get a result of whether I will get help or not | Pass |
| Check for a Northern Ireland citizen | Given I am a citizen of the UK, When I put my country as Northern Ireland, Then I should get a result based on my country: Northern Ireland | Pass |  
| Check with various combinations of multiple circumstances | Given I am a citizen of the UK, When I put multiple circumstances into the Checker tool with details, Then I should get a result of whether I will get help or not | Pass |  
| Check with various exceptional workflows like Diabetes and Care home | Given I am a citizen of the UK, When I put multiple circumstances including Diabetes and Care home into the Checker tool with details, Then I should get a result of whether I will get help or not | Pass |  
| Cross-browser compatibility (Chrome, Edge) | Given I am a citizen of the UK, I am on the NHS Costs Checker start page using multiple browsers, When I put my circumstances into the Checker tool, Then I should get a result of whether I will get help or not. | Pass |

## Test Reports
After execution, test reports can be found in:
```
target/cucumber-reports/cucumber-pretty/index.html
