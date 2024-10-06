package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Parentutility.Abstractcomponent;

public class Productpage extends Abstractcomponent {
	
	WebDriver driver;
	
	//Parameterized Constructor
	
	public Productpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	//PageFactory in Product Page
	
	@FindBy(css = ".mb-3")
	List <WebElement> products;

	
	@FindBy(xpath = "//*[@id='toast-container']")
	WebElement productaddedmessage;
	
	@FindBy(css = ".ng-animating")
	WebElement animate;
	
	
	
	
	//By Locator 
	
	By prodlist = By.cssSelector(".mb-3");
	
	
	//Action methods in Product Page
	
	public List<WebElement> listofproducts() 
	{
		waitforwebelement(prodlist);
		return products;  
	}
	
	//To get the list of products and apply filter to get the desired product
	
	public WebElement getproductbyname(String Productname)
	{
	
		WebElement prod =listofproducts().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(Productname)).findFirst().orElse(null);
		return prod;
		
	}
	
	//Once the desired product is found, clicking on the add to cart button
	
	public void addToCart(String productname) throws InterruptedException
	{
		WebElement prod= getproductbyname(productname);
		prod.findElement(By.xpath(".//div[@class='card-body']/button[2]")).click();
		waitforelement(productaddedmessage);
		waitforelementtodisappear(animate);
		Thread.sleep(10);
		
	}


}
