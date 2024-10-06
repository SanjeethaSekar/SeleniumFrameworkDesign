package Parentutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.Loginpage;

public class Baseclass   {
	
	
	
	public WebDriver driver;
	public Loginpage loginpage;
	
	public WebDriver intializedriver() throws IOException
	{
		//Properties class in Java 
		Properties prop = new Properties();
		
		//To make it compatible, to covert the file into fileinputstream
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/globalproperty/runconfig.properties");
		
		//Using the property object reference,loading the file in the class
		prop.load(fis);
		
		String browsername = prop.getProperty("browser");
		System.out.println(browsername);
	
		if(browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Home\\Documents\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else 
		{
			System.out.println("edge");
		}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
		
			return driver;
		}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filepath) throws IOException
	{
		//read the json to String 
		String jsoncontent=FileUtils.readFileToString(new File(filepath),
				StandardCharsets.UTF_8);
		
		
		//convert the string to Hashmap
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>() {
			
		});
		return data;
		
	}
	

	public String getScreenshot(String testcasename, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//" + testcasename +"index.html");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" + testcasename +".png";
		
		
	}
	
	
	
		
		
		@BeforeMethod(alwaysRun=true)
		public Loginpage launchApplication() throws IOException
		{
			driver = intializedriver();
			loginpage = new Loginpage(driver);
			loginpage.goTo();
			return loginpage;
			
		}
		
		@AfterMethod(alwaysRun=true)
		public void tearDown()
		{
			driver.close();
		}
		
	
	}
