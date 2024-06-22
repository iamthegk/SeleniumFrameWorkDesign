package frameWork.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.resuablePackage.AbstractClass;

public class LandingPage extends AbstractClass{
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// constructor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement emailElement = driver.findElement(By.id("userEmail"));
	// pagefactory
	@FindBy(id = "userEmail")
	WebElement usermail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	
	public void hitUrl() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public void login(String email, String password) {
		
		usermail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
	}

}
