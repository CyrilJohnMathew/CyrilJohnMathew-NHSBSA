Feature: NHS Job Search functionality
	As a jobseeker on NHS Jobs website
	I want to search for a job with my preferences
	So that I can get recently posted job results
   
@crossbrowser 
Scenario Outline: NHS job Search with preferences in multiple browsers
  Given I open the NHS job search page on "<browser>"
  When I put my preferences "<title>" and "<location>" into the Search functionality
  Then I should get a list of jobs that match my preferences
  And I sort my search results with the sort option "<sortOption>"

  Examples:
    | title  | location   | sortOption           | browser |
    | Nurse  | London     | Date Posted (newest) | chrome  |
    | Doctor | Sheffield  | Date Posted (newest) | firefox |
    | Carer  | London			| Date Posted (newest) | chrome  |
    | Admin  | Manchester | Date Posted (newest) | firefox |