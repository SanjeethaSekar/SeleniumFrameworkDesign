package endtoend;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Parentutility.Baseclass;
import RetryAnalyzer.Retry;
import pageObjects.Checkoutpage;
import pageObjects.Confirmationpage;
import pageObjects.Loginpage;
import pageObjects.Productpage;
import pageObjects.cartpage;
import pageObjects.orderpage;


public class SubmitOrderTest extends Baseclass {
	
	@Test(dataProvider="getdata",retryAnalyzer=Retry.class)
	public void submitorder(HashMap<String,String> input) throws InterruptedException, IOException
	{
	
		//String Productname ="IPHONE 13 PRO";
		Productpage productpage = loginpage.login(input.get("email"),input.get("password"));
		productpage.addToCart(input.get("product"));
		cartpage cartpage=productpage.goToCartPage(); 
		
		
		
		Boolean match =cartpage.verifyproductdisplay(input.get("product"));
		Assert.assertTrue(match);
		
		Checkoutpage checkoutpage =cartpage.goToCheckout();
		checkoutpage.selectcountry("india");
		Confirmationpage confirmationpage =checkoutpage.Submitorder();
		String confirmmessage=confirmationpage.getconfirmationmessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
			
	}
	
	
	@Test(dependsOnMethods="submitorder")
	public void VerifyOrderTest()
	{	
		Productpage productpage = loginpage.login("sanjeetha123@gmail.com", "Sanjeetha123*");
		orderpage orderpage=productpage.gotorderpage();
		Boolean match=orderpage.verifyorderdisplay("IPHONE 13 PRO");
		Assert.assertTrue(match);
		
	
	}
	
	@DataProvider
	public Object[][] getdata() throws IOException
	{
		 List <HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") +"//src//test//java//testdata//purchaseorder.json");
		 for(int i=0;i<data.size();i++)
		 {
			 System.out.println(data.get(i));
		 }
			
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}
	

}
