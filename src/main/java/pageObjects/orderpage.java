package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Parentutility.Abstractcomponent;

public class orderpage extends Abstractcomponent {

	public WebDriver driver;
	
	public orderpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//PageFactory Elements
	
	@FindBy(xpath="//tr/td[2]")
	List <WebElement> orderlist;
	
	//Action methods in CartHeader Page
	
	public Boolean verifyorderdisplay(String productname)
	{
		Boolean match= orderlist.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	
	
}
