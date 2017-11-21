package AppViews;

import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;

/** 
 * This Page Object class contains the functionality UI  
 * components of Home view of Ebay
 */
public class HomeView {
	private static AndroidDriver driver;
	
	public HomeView(AndroidDriver _driver)
	{
		this.driver = _driver;
	}
	
	/** 
     * This function searches for the product on the home page of EBay
     * @param productName 		Name of the product to be searched.
     */
	public static void SearchForProduct(String productName)
	{
		//a) Launch app with Appium on emulator. Search for a product by keyword.
		String selector1 = "com.ebay.mobile:id/search_box";
		String selector2 = "com.ebay.mobile:id/search_src_text";
		
		driver.findElement(By.id(selector1)).click(); 
		driver.findElement(By.id(selector2)).click(); 		
		driver.findElement(By.id(selector2)).sendKeys(productName+"\\n"); 
	}
}
