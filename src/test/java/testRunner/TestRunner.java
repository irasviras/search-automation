/*
Author: Irina Vignanker
Task:Create an automation framework in Java using Cucumber and Selenium WebDriver 
     to test a web application's search functionality. 
     The framework should include feature files, step definitions, and page objects. 
     Write test scenarios to cover different search scenarios such as valid/invalid search terms, search suggestions, and search results pagination. 
     search suggestions, and search results pagination.  
     Provide a report of the test results in a readable format.  
 */

package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/searchBarFeatures", // Directory for the feature files
		glue = { "stepDefinition" }, // Package for step definitions
		tags = "@Veeva",
		// tags = "@Pagination",
		plugin = { "pretty", "html:target/cucumber-reports/cucumber.html" }, // Report generation in the Target folder
		monochrome = true // Make console output readable
)
public class TestRunner {

}
