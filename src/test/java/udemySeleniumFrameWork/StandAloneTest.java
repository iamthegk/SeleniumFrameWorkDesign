
package udemySeleniumFrameWork;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import frameWork.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		driver.manage().window().maximize();
		LandingPage landingpage=  new LandingPage(driver);
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("pass123x@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pass@123");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));
		List<WebElement> productList = driver.findElements(By.className("mb-3"));
		// System.out.println(productList.size());

		WebElement singleProduct = productList.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		singleProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // # means id
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // to improve perfomance
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> itemsInCart=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=itemsInCart.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName)); // anymatch wiil return Boolean
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".subtotal button")).click();
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']"))).sendKeys("india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));// wait for the country list drop down
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();// selecting second option from the list
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage=driver.findElement(By.cssSelector(".hero-promary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}

}
