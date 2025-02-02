#User Story:
#As a citizen of the UK
#I need to know what help I can get with my NHS costs
#So that I can get the treatment I need

Feature: NHS costs online service checker tool
  
  Background:
    Given I am on the NHS Costs Checker start page
 
  @smoke
  Scenario: Checking eligibility for NHS costs online service help
    Given I am a citizen of the UK
    When I put my circumstances into the Checker tool
    Then I should get a result of whether I will get help or not
      
  @northernireland
  Scenario: Checking eligibility for NHS costs online service help for Northern Ireland user
    Given I am a citizen of the UK
    When I try to proceed without selecting a country
    And I put my country as Northern Ireland into the Checker tool
    Then I should get a result based on my country: NorthernIreland    

  @datadriven      
  Scenario Outline: NHS Costs online Checker with different user circumstances
    Given I am a citizen of the UK
    When I put multiple circumstances into the Checker tool with the following details:
      | Field                                | Value                         |
      | Country                              | <country>                     |
      | GP Practice in Scotland/Wales        | <gpPracticeScotlandWales>     |
      | Dental Practice Location             | <dentalPractice>              |
      | Date of Birth (DD/MM/YYYY)           | <dobDay>/<dobMonth>/<dobYear> |
      | Living with a Partner                | <liveWithPartner>             |
      | Claiming Benefits or Tax Credits     | <claimBenefitOrTax>           |
      | Pregnant/Gave Birth in Last 12 Months| <pregnant12Month>             |
      | Injury or Illness from Armed Forces  | <injuryArmedForces>           |
      | Diabetes                             | <diabetes>                    |
      | Other Medical Conditions             | <otherConditions>             |
      | Glaucoma                             | <glaucoma>                    |
      | Living in a Care Home                | <careHome>                    |
      | Savings or Property > £16,000        | <savingsMoreThan1600>         |
    Then I should get a result of whether I will get help or not

    Examples:
      | country      | gpPracticeScotlandWales | dentalPractice | dobDay | dobMonth | dobYear | liveWithPartner | claimBenefitOrTax | pregnant12Month | injuryArmedForces | diabetes | otherConditions | glaucoma | careHome | savingsMoreThan1600 |
      | England      | No                      | England        | 20     | 10       | 1983    | Yes             | No                | Yes             | No                | No       | Yes             | No       | No       | Yes                 |
      | Wales        | No                      | Wales          | 10     | 12       | 1990    | Yes             | No                | Yes             | No                | No       | No              | Yes      | No       | Yes                 |    
      | Scotland     | Yes                     | Scotland       | 15     | 07       | 1985    | No              | No                | No              | Yes               | -        | -               | -        | No       | No                  |
  
  @exceptional    
  Scenario Outline: NHS Costs online Checker with different user circumstances with Diabetes and Care home 
    Given I am a citizen of the UK
    When I put multiple circumstances including Diabetes and Care home into the Checker tool with the following details:
      | Field                                | Value                         |
      | Country                              | <country>                     |
      | GP Practice in Scotland/Wales        | <gpPracticeScotlandWales>     |
      | Dental Practice Location             | <dentalPractice>              |
      | Date of Birth (DD/MM/YYYY)           | <dobDay>/<dobMonth>/<dobYear> |
      | Living with a Partner                | <liveWithPartner>             |
      | Claiming Benefits or Tax Credits     | <claimBenefitOrTax>           |
      | Pregnant/Gave Birth in Last 12 Months| <pregnant12Month>             |
      | Injury or Illness from Armed Forces  | <injuryArmedForces>           |
      | Diabetes                             | <diabetes>                    |
      | Diabetes Medication                  | <diabetesMed>                 |       
      | Other Medical Conditions             | <otherConditions>             |
      | Living in a Care Home                | <careHome>                    |
      | Help to pay for your care home       | <careHomePay>                 |      
      | Savings or Property > £16,000        | <savingsMoreThan1600>         |
    Then I should get a result of whether I will get help or not

    Examples: 
      | country      | gpPracticeScotlandWales | dentalPractice | dobDay | dobMonth | dobYear | liveWithPartner | claimBenefitOrTax | pregnant12Month | injuryArmedForces | diabetes | diabetesMed| otherConditions | careHome | careHomePay | savingsMoreThan1600 |
      | England      | No                      | England        | 12     | 02       | 1989    | Yes             | No                | Yes             | No                | Yes      | No         | Yes             | No       | -           | Yes                 |
      | England      | Yes                     | Wales          | 25     | 10       | 1991    | Yes             | No                | No              | No                | Yes      | Yes        | -               | No       | -           | No                  |      
      | England      | No                      | England        | 05     | 06       | 1978    | No              | No                | Yes             | Yes               | Yes      | Yes        | -               | Yes      | No          | No                  | 
      | Wales        | Yes                     | Wales          | 17     | 03       | 1982    | Yes             | No                | No              | No                | Yes      | Yes        | -               | Yes      | Yes         | -                   |
  
  @crossbrowser
  Scenario Outline: Checking eligibility for NHS costs online service help in multiple browser
    Given I am on the NHS Costs Checker start page using "<browser>"
    Given I am a citizen of the UK
    When I put my circumstances into the Checker tool
    Then I should get a result of whether I will get help or not

    Examples:
      | browser |
      | chrome  |
      | edge    |