package AppViews;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class ProductPageView {
	private static AndroidDriver driver;
	
	public ProductPageView(AndroidDriver _driver)
	{
		this.driver = _driver;
	}
	
	/** 
     * This function gets the list of string from a text file 
     * provided in the parameter
     * 
     * @param FileName 		The full file path of the text file
     * @return				ArrayList containing the string from the file          	
     */	
	public static void ClickOnWatch()
	{
		//e) click on 'Watch'.
		driver.findElement(By.id("com.ebay.mobile:id/button_watch")).click();	 
	}	
}
