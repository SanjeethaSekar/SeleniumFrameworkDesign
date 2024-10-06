package pageObjects;

import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Parentutility.Abstractcomponent;

public class cartpage extends Abstractcomponent{
	
	WebDriver driver;
	
	public cartpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(css= ".cartSection h3")
	List <WebElement> productlist;
	
	@FindBy(css = ".totalRow button")
	WebElement checkout;
	
	//Action methods in CartHeader Page
	
	public Boolean verifyproductdisplay(String productname)
	{
		Boolean match= productlist.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	
	
	public Checkoutpage goToCheckout()
	{
		checkout.click();
		Checkoutpage checkoutpage = new Checkoutpage(driver);
		return checkoutpage;
	}
}
