package Parentutility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.cartpage;
import pageObjects.orderpage;

public class Abstractcomponent {
	
	WebDriver driver;
	By findby;
	
	public Abstractcomponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(xpath="(//*[contains(text(),'Cart')])[1]")
	WebElement cartheader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderheader;

	//We can place all the reusable codes here. ex : Element wait and Element disapper etc.,
	
	public void waitforwebelement(By findby)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
		
		
	}
	
	public cartpage goToCartPage() throws InterruptedException
	{
		Thread.sleep(10);
		cartheader.click();
		cartpage cartpage = new cartpage(driver);
		return cartpage;
	}
	
	
	public  orderpage gotorderpage()
	{
		orderheader.click();
		orderpage orderpage = new orderpage(driver);
		return orderpage;
		
	}
	public void waitforelement(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
		
}
	public void waitforelementtodisappear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
	
	


