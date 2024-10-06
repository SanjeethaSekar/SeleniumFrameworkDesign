package endtoend;

import org.testng.Assert;
import org.testng.annotations.Test;

import Parentutility.Baseclass;
import pageObjects.Loginpage;
import pageObjects.Productpage;
import pageObjects.cartpage;

public class ErrorValidations extends Baseclass {
	
@Test
public void Loginerrorvalidations()
{
	String Productname ="IPHONE 13 PRO";
	Productpage productpage = loginpage.login("sanjeetha123@gmail.com", "Sanjeea123*");
	
	
}

public void producterrorvalidations() throws InterruptedException
{
	String Productname ="IPHONE 13 PRO";
	Productpage productpage = loginpage.login("sanjeetha123@gmail.com", "Sanjeetha123*");
	productpage.addToCart(Productname);
	cartpage cartpage=productpage.goToCartPage();
	Boolean match =cartpage.verifyproductdisplay("IPHON E 34");
	Assert.assertTrue(match);
}
	
}
