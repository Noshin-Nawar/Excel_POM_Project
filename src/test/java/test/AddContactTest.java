package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.AddContactPage;
import page.DashboardPage;
import page.LoginPage;
import page.listCustomerPage;
import util.BrowserFactory;
import util.ExcelReader;

public class AddContactTest {

	WebDriver driver;

	
	
	ExcelReader exlRead = new ExcelReader("src\\main\\java\\testData\\TF_TestData.xlsx");

	// saving the values of the required cells in separate string variables, to call
	// them later on
	String USERNAME = exlRead.getCellData("LoginInfo", "Username", 2);
	String PASSWORD = exlRead.getCellData("LoginInfo", "Password", 2);
    String DASHBOARDHEADERVERIFY = exlRead.getCellData("Dashboard", "Verify Header", 2);
 
	String FULLNAME= exlRead.getCellData("AddContactData", "Full Name", 2);
	String COMPANYNAME = exlRead.getCellData("AddContactData", "Company", 2);
	String EMAIL = exlRead.getCellData("AddContactData", "Email", 2);
	String PHONE = exlRead.getCellData("AddContactData", "Phone", 2);
	String ADDRESS = exlRead.getCellData("AddContactData", "Address", 2);
	String CITY = exlRead.getCellData("AddContactData", "City", 2);
	String STATE = exlRead.getCellData("AddContactData", "State/Region", 2);
	String ZIP = exlRead.getCellData("AddContactData", "ZIP/Postal Code", 2);
	String COUNTRY = exlRead.getCellData("AddContactData", "Country", 2);
    
	@Test 
	public void userShouldBeAbleToAddCustomer() throws InterruptedException {

		driver = BrowserFactory.init();

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		loginPage.insertUserName( USERNAME);
		loginPage.insertPassword(PASSWORD);
		loginPage.clickSignin();

		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.verifyDashboardHeader(DASHBOARDHEADERVERIFY);
		dashboardPage.clickOnCustomer();
		dashboardPage.clickOnAddCustomer();
		
		AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
	    addContactPage.validateAddContactPage("Add Contact");
		addContactPage.insertFullName(FULLNAME);
		addContactPage.selectCompany(COMPANYNAME);
		addContactPage.insertEmail( EMAIL);
        addContactPage.insertPhone(PHONE);
        addContactPage.insertAddress(ADDRESS);
        addContactPage.insertCity(CITY);
        addContactPage.insertCity(STATE);
        addContactPage.insertZip(ZIP);
        
        addContactPage.selectCountry(COUNTRY);
        addContactPage.clickSaveButton();
        dashboardPage.clickListCustomer();
       // addContactPage.varifyInsertedNameandDelete();
    
        
	}

	
	

}
