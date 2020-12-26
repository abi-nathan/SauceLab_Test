package SauceLabTest;

import java.net.MalformedURLException;
import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestCase1 {
	
	public WebDriver driver=null;
	
	public static final String USERNAME = "abicnathan";
	public static final String ACCESS_KEY = "17a4c060-692e-4098-b217-d658d7154ef2";
	public static final String URL = "https://"+USERNAME +":"+ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	
	
	@Parameters({"platform","browser","version"})
	@BeforeClass
	
	//public void setup(@Optional("browser") String br, @Optional("version") String vr, @Optional("platform") String pf) throws MalformedURLException {
   
	//public void setup(@Optional("Chrome") String br, @Optional("81.0") String vr, @Optional("Windows 10") String pf) throws MalformedURLException {
	
	public void setup(String pf,String br,String vr) throws MalformedURLException {
		
		System.out.println("The browser is "+br);
		
		DesiredCapabilities caps=new DesiredCapabilities() ;
		
		//caps.setCapability("deviceName", dv);
		caps.setCapability("platformName", pf);
		caps.setCapability("browserName",br );
		caps.setCapability("browserVersion", vr);
		
		driver=new RemoteWebDriver(new java.net.URL(URL), caps);
		
	}
	
	@Test
	public void verifyLogin()
	{
		driver.get("https://opensource-demo.orangehrmlive.com/");
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
		
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
		
	}
	
	@AfterClass
	public void tearDown() 
	{
		driver.quit();
	}

}
