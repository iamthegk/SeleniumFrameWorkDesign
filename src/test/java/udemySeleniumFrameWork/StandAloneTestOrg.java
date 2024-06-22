
package udemySeleniumFrameWork;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import frameWork.pageobjects.CartPage;
import frameWork.pageobjects.CheckOutPage;
import frameWork.pageobjects.ConfirmationPage;
import frameWork.pageobjects.LandingPage;
import frameWork.pageobjects.ProductCatalogue;

public class StandAloneTestOrg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		driver.manage().window().maximize();
		LandingPage landingpage = new LandingPage(driver);

		landingpage.hitUrl();
		landingpage.login("pass123x@gmail.com", "Pass@123");

		ProductCatalogue productcatalogue = new ProductCatalogue(driver);

		List<WebElement> productList = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage = productcatalogue.goToCart(); // IMP STEP ->
		Boolean match = cartpage.verifyTheMatchingProduct(productName);
		Assert.assertTrue(match);

		CheckOutPage checkoutpage = cartpage.clickOnCheckOut(); // IMP STEP ->
		checkoutpage.selectCountry("india");

		ConfirmationPage confirmationpage = checkoutpage.submit(); // IMP STEP
		String confirmationMessage = confirmationpage.getConfirmationMessage();

		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}

}
