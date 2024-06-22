package selenium.resuablePackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frameWork.pageobjects.CartPage;
import frameWork.pageobjects.ProductCatalogue;

public class AbstractClass {
	WebDriver driver;

	public AbstractClass(WebDriver driver) {
		// constructor
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;
	

	public void waitForElementToVisible(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToDisappear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.invisibilityOf(findBy));

	}
	public CartPage goToCart() {
		cartButton.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
}
