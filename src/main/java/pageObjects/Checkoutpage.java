package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Parentutility.Abstractcomponent;

public class Checkoutpage extends Abstractcomponent {
	
	WebDriver driver;
	
	
	//Parameterized constructor
	
	public Checkoutpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


    //PageFactory WebElements
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(css = ".action__submit")
	WebElement Submit;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectIndia;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectcountry(String countryname) throws InterruptedException
	{
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryname).build().perform();
		waitforwebelement(results);
		SelectIndia.click();
		Thread.sleep(5);
		waitforelement(Submit);
	
	}
	
	
	public Confirmationpage Submitorder() throws InterruptedException
	{
		waitforelement(Submit);
		Submit.click();
		Confirmationpage confirmationpage = new Confirmationpage(driver);
		return confirmationpage;
	}
	
	
	
	
	
	
}
	
