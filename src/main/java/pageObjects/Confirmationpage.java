package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Parentutility.Abstractcomponent;

public class Confirmationpage extends Abstractcomponent {
	
	
	WebDriver driver;
	//parameterized constructor
	 public Confirmationpage(WebDriver driver)
	 {
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
		 
	 }
	 
	 
	 //PageFactory WebElements
	 
	 @FindBy(css =".hero-primary")
	 WebElement confirmationmessage;
	 

	 public String getconfirmationmessage()
	 {
		 return confirmationmessage.getText();
		 
	 }
}
