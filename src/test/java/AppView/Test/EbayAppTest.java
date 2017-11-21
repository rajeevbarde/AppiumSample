package AppView.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import AppViews.*;
import io.appium.java_client.android.AndroidDriver;

public class EbayAppTest {

	private static AndroidDriver driver;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		//App details
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/Apps/EBay/");
		File app = new File(appDir, "ebay.com.apk");
		
		//Desired capabilties
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "9b4dcc3f");
		//capabilities.setCapability("deviceName", "U8QC7SONOVM7CM9H");
		capabilities.setCapability("platformVersion", "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		//apabilities.setCapability("appActivity","com.ninegag.android.app.ui.HomeActivity");
		//capabilities.setCapability("appWaitActivity","com.ninegag.android.app.ui.SplashScreenActivity");
		
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HomeView view_Home = new HomeView(driver);
		SearchResultView view_SearchResult = new SearchResultView(driver);
		ProductPageView view_ProductPage = new ProductPageView(driver);
		SignInView view_SignIn = new SignInView(driver);		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {		
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestCase1() {
		HomeView.SearchForProduct("Mango");  // Search for Mango
		SearchResultView.SortByLowestPrice(); // Perform sort by lowest price
		Boolean isSorted = SearchResultView.Verify_PriceSorted(7); // Verify the price is sorted for 7 items 				
		assertTrue(isSorted);					
	}

	@Test
	public void TestCase2(){
		SearchResultView.SelectFirstProduct(); //Select first product		
		ProductPageView.ClickOnWatch(); //Click on watch
		SignInView.VerifySign(); //Verify Sign-in
	}
}
