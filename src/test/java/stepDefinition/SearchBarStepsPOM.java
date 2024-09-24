/*
Author: Irina Vignanker
Task:Create an automation framework in Java using Cucumber and Selenium WebDriver 
     to test a web application's search functionality. 
     The framework should include feature files, step definitions, and page objects. 
     Write test scenarios to cover different search scenarios such as valid/invalid search terms, search suggestions, and search results pagination. 
     search suggestions, and search results pagination.  
     Provide a report of the test results in a readable format.  
 */

package stepDefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.SearchBar;

import java.time.Duration;

public class SearchBarStepsPOM {

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	WebDriver driver = null;
	SearchBar search;

	// Search for BestBuy
	@Given("I am on the bestbuy main page")
	public void i_am_on_the_bestbuy_main_page() {

		System.setProperty("webdriver.chrome.driver", "C://WebDriver//chromedriver-win64//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(SearchBar.BASE_URL);
	}

	@When("I enter text {string}")
	public void i_enter_text(String userInput) {
		search = new SearchBar(driver);
		search.enterText(userInput);
	}

	@When("I input text {string}")
	public void i_input_text(String userInput) {
		search = new SearchBar(driver);
		search.inputText(userInput);
	}

	@Then("I go to the {string} brand page")
	public void i_go_to_the_brand_page(String userInputBrandName) {
		String expectedBrandUrl = SearchBar.BASE_URL + "/brand/" + userInputBrandName.toLowerCase();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlToBe(expectedBrandUrl));
		String actualUrl = driver.getCurrentUrl();
		assertEquals("The actual URL does not match the expected URL. Expected: " + expectedBrandUrl + ", actual: "
				+ actualUrl, expectedBrandUrl, actualUrl);
	}

	@Then("I can see search results {string}")
	public void i_can_see_search_results(String expectedSearchResult) {
		search = new SearchBar(driver);
		String actualSearchResult = search.getSearchResults();
		assertTrue("Search result doesn't contain the text input by user. Actual result: " + actualSearchResult,
				actualSearchResult.contains(expectedSearchResult));
	}

	@Then("I can see search suggestions for {string}")
	public void i_can_see_search_suggestions(String expectedSearchSuggestion) {
		search = new SearchBar(driver);
		String actualSuggestion = search.getSearchSuggestions();
		assertTrue("Search result doesn't contain the text input by user. Actual result: " + actualSuggestion,
				actualSuggestion.contains(expectedSearchSuggestion));
	}

	// Pagination on Iherb
	@Given("I am on the iHerb search results page for {string}")
	public void i_am_on_the_i_herb_search_results_page_for(String userInputProduct) {

		System.setProperty("webdriver.chrome.driver", "C://WebDriver//chromedriver-win64//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(SearchBar.BASE_URL_IHERB);

		search = new SearchBar(driver);
		search.searchIherb(userInputProduct);
	}

	@When("I go to the next page {int}")
	public void i_go_to_the_next_page(int pageNumber) {
		search = new SearchBar(driver);
		search.changePageIherb(pageNumber);
	}

	@Then("I should see search results for {string} on page {int}")
	public void i_should_see_search_results_on_page(String expectedSearchResult, int pageNumber) {

		search = new SearchBar(driver);
		String actualSearchResult = search.getSearchResultsIherb();
		assertTrue(
				"Page number " + pageNumber + " does not contain the expected text: " + expectedSearchResult
						+ ". Actual result is: " + actualSearchResult,
				actualSearchResult.toLowerCase().contains(expectedSearchResult.toLowerCase()));
	}

}
