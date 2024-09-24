#Author: Irina Vignanker
#Task:Create an automation framework in Java using Cucumber and Selenium WebDriver 
#     to test a web application's search functionality. 
#     The framework should include feature files, step definitions, and page objects. 
#     Write test scenarios to cover different search scenarios such as valid/invalid search terms, search suggestions, and search results pagination. 
#     search suggestions, and search results pagination.  
#     Provide a report of the test results in a readable format. 
 
Feature: Search Bar Functionality (BestBuy and Iherb)
  @Veeva
  @BestBuy1 
  Scenario: Search Bar excepts correct input and returns results
    Given I am on the bestbuy main page
    When I enter text "Apple MacBook"
    Then I can see search results "Apple MacBook"

  @Veeva
  @BestBuy2  
  Scenario: Search Bar excepts a part of the word as input and returns correct results
    Given I am on the bestbuy main page
    When I enter text "Samsu"
    Then I can see search results "Samsung"

  @Veeva
  @BestBuy3 
  Scenario: Search Bar excepts input with mistake and returns correct results
    Given I am on the bestbuy main page
    When I enter text "amsung"
    Then I can see search results "Samsung"    

  @Veeva   
  @BestBuy4 
  Scenario: Search Bar excepts input and gives search suggestions
    Given I am on the bestbuy main page
    When I input text "Apple"
    Then I can see search suggestions for "Apple"

  @Veeva    
  @BestBuy5 
  Scenario: Search Bar excepts input and opens a relevant brand page
    Given I am on the bestbuy main page
    When I enter text "Apple"
    Then I go to the "Apple" brand page
  
    
    
  @Veeva  
 	@Iherb1
 	@Pagination
  Scenario: Verify pagination to the next page and search results on it 
    Given I am on the iHerb search results page for "magnesium"
    When I go to the next page 2
    Then I should see search results for "magnesium" on page 2
    
    
    



      
