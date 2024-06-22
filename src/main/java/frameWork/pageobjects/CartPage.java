package frameWork.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import selenium.resuablePackage.AbstractClass;

public class CartPage extends AbstractClass {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> itemsInCartPage;
	@FindBy(css=".subtotal button")
	WebElement checkOutButton;
	

	public boolean verifyTheMatchingProduct(String productName) {

		Boolean match = itemsInCartPage.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName)); // anymatch
		return match;

	}
	public void clickOnCheckOut() {
		checkOutButton.click();
	}

}
