/*
Author: Irina Vignanker
Task:Create an automation framework in Java using Cucumber and Selenium WebDriver 
     to test a web application's search functionality. 
     The framework should include feature files, step definitions, and page objects. 
     Write test scenarios to cover different search scenarios such as valid/invalid search terms, search suggestions, and search results pagination. 
     search suggestions, and search results pagination.  
     Provide a report of the test results in a readable format.  
 */

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

public class SearchBar {

	WebDriver driver;

	public static final String BASE_URL = "https://www.bestbuy.ca/en-ca";
	public static final String BASE_URL_IHERB = "https://ca.iherb.com/";

	// locators
	By searchBar = By.name("search");

	By searchResults = By
			.xpath("//div[contains(@class, 'productsRow_DcaXn') and contains(@class, 'style-module_row__Q0c-x')]");

	By searchSuggestions = By.xpath(
			"//div[@class='style-module_searchTerm__Lj24V' and @data-automation='x-product-preview-popular-searches']");

	By searchBarIherb = By.className("iherb-header-search-input");

	By searchResultsIherb = By.xpath("//div[contains(@class, 'panel-stack') and contains(@class, 'defer-block')]");

	// Constructor
	public SearchBar(WebDriver driver) {
		this.driver = driver;
	}

	// methods for search on BestBuy
	public void inputText(String userInput) {
		driver.findElement(searchBar).sendKeys(userInput);
	}

	public void enterText(String userInput) {
		driver.findElement(searchBar).sendKeys(userInput + Keys.RETURN);
	}

	public String getSearchResults() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement actualResult = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
		return actualResult.getText();
	}

	public String getSearchSuggestions() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement actualSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestions));
		return actualSuggestion.getText();
	}

	// methods for search on iherb
	public void searchIherb(String userInput) {
		driver.findElement(searchBarIherb).sendKeys(userInput + Keys.RETURN);
	}

	public void changePageIherb(int pageNumber) {
		// change URL
		String currentPageUrl = driver.getCurrentUrl();
		driver.get(currentPageUrl + "&p=" + pageNumber);

		// make sure the URL has been changed correctly
		String expectedUrl = SearchBar.BASE_URL_IHERB + "search?kw=magnesium&p=" + pageNumber;
		String actualUrl = driver.getCurrentUrl();
		assertEquals(
				"The actual URL does not match the expected URL. Expected: " + expectedUrl + ", actual: " + actualUrl,
				expectedUrl, actualUrl);
	}

	public String getSearchResultsIherb() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement actualResult = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsIherb));
		return actualResult.getText();
	}

}
