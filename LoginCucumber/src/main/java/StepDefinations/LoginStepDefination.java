package StepDefinations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefination {
	
	WebDriver driver;
	String borrowAmount;
		
	@Given("^user on the given URL page$")
	public void user_on_the_given_URL_page()  {
		System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
		driver = new ChromeDriver(); driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");   
	}

	@When("^user submits the form the following details:$")
	public void user_enters_the_following_details(DataTable details)  {
		
		List<List<String>> data = details.raw();
		
		String applicationType = data.get(1).get(0);
		String numberOfDependents = data.get(1).get(1);
		String income = data.get(1).get(2);
		String otherIncome = data.get(1).get(3);
		String livingExpenses = data.get(1).get(4);
		String otherLoanRepayments = data.get(1).get(5);
		String totalCreditCardLimits = data.get(1).get(6);
		
		driver.findElement(By.xpath("//label[contains(text(),'"+applicationType+"')]")).click();
		driver.findElement(By.xpath("//select[@title='Number of dependants']//option[1]")).click();
		driver.findElement(By.xpath("//span[@id='q2q1i1']//following-sibling::input")).sendKeys(income);
		driver.findElement(By.xpath("//span[@id='q2q2i1']//following-sibling::input")).sendKeys(otherIncome);
		driver.findElement(By.xpath("//input[@id='expenses']")).sendKeys(livingExpenses);
		driver.findElement(By.xpath("//input[@id='otherloans']")).sendKeys(otherLoanRepayments);
		driver.findElement(By.xpath("//input[@id='credit']")).sendKeys(totalCreditCardLimits);
		driver.findElement(By.xpath("//button[@id='btnBorrowCalculater']")).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		borrowAmount= driver.findElement(By.xpath("//span[@id='borrowResultTextAmount']")).getText();
		  
	}
	
	@When("^user clicks on borrow button$")
	public void user_clicks_on_borrow_button() {
	    // Write code here that turns the phrase above into concrete actions
		
	}

	@Then("^borrowing estimate should be \"([^\"]*)\"$")
	public void borrowing_estimate_should_be_$(String borrowingEstimate)  {
		System.out.println("Expected-"+borrowingEstimate);
	    Assert.assertEquals(borrowingEstimate,borrowAmount,"Amounts did not match");
	   
	}

	@When("^user clicks on start over button$")
	public void user_clicks_on_start_over_button()  {
	    // Write code here that turns the phrase above into concrete actions
	   
	}

	@Then("^whole form should be cleared$")
	public void whole_form_should_be_cleared()  {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@When("^user enters \"([^\"]*)\" with \"([^\"]*)\"$")
	public void user_enters_with(String livingExpenses, String amount)  {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("^error message should be displayed$")
	public void error_message_should_be_displayed()  {
	    // Write code here that turns the phrase above into concrete actions
	    
	}


}
