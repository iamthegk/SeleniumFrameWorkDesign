package frameWork.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import selenium.resuablePackage.AbstractClass;

public class CheckOutPage extends AbstractClass {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submitButton;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountryInd;
	
	@FindBy (css=".action__submit")
	WebElement submit;
	public void selectCountry(String countryName) {
		Actions action = new Actions(driver);
		action.sendKeys(selectCountry, countryName).build().perform();
		selectCountryInd.click();
	}
	
	public ConfirmationPage submit() {
		submit.click();
		return new ConfirmationPage(driver);
		
	}

}
