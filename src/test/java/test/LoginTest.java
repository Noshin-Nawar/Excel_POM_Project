package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;
import util.ExcelReader;

public class LoginTest {

	WebDriver driver;

	// Object creation and using the excel path to link the excel with the project
	ExcelReader exlRead = new ExcelReader("src\\main\\java\\testData\\TF_TestData.xlsx");

	// saving the values of the required cells in separate string variables, to call
	// them later on
	String USERNAME = exlRead.getCellData("LoginInfo", "Username", 2);
	String PASSWORD = exlRead.getCellData("LoginInfo", "Password", 2);
	String DASHBOARDHEADERVERIFY = exlRead.getCellData("Dashboard", "Verify Header", 2);

	@Test
	public void validUserShouldBeAbleToLogin() {

		// connecting two drivers (driver of BrowserFactory and driver of LoginTest) and
		// calling the init() method
		driver = BrowserFactory.init();

		// using Pagefactory method to create object of the LoginPage
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(USERNAME);
		loginPage.insertPassword(PASSWORD);
		loginPage.clickSignin();

		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.verifyDashboardHeader(DASHBOARDHEADERVERIFY);

		// calling the tearDown() method
		// BrowserFactory.tearDown();

	}

}
