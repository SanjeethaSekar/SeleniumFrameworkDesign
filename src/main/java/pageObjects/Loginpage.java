package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Parentutility.Abstractcomponent;

public class Loginpage extends Abstractcomponent {

	WebDriver driver;
	
	//Parameterized constructor
	
	public Loginpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory WebElements in Login Page
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginbutton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	
	//Action methods in Login Page
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public void geterrormessage()
	{
		waitforelement(errormessage);
		String error=errormessage.getText();
	}
	
	
	
	public Productpage login(String username,String Password)
	{
		email.sendKeys(username);
		password.sendKeys(Password);
		loginbutton.click();
		Productpage productpage = new Productpage(driver);
		return productpage;
	}
}
