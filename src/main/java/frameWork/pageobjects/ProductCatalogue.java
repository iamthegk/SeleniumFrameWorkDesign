package frameWork.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.resuablePackage.AbstractClass;

public class ProductCatalogue extends AbstractClass {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		// constructor
		super(driver); // every child class has to give super
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement emailElement = driver.findElement(By.id("userEmail"));
	// pagefactory - only support driver.find constructions

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement animationElement;

	// Page factory only for driver.findElement construction,
	// It will not support By element, so

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By waitForToast = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToVisible(productsBy);
		return products;

	}

	public WebElement getProductByName(String productName) {

		WebElement singleProduct = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return singleProduct;
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToVisible(waitForToast);
		waitForElementToDisappear(animationElement);

	}

}
