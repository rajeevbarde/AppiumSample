package AppViews;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class SignInView {
	private static AndroidDriver driver;
	
	public SignInView(AndroidDriver _driver)
	{
		this.driver = _driver;
	}
	
	public static void VerifySign()
	{
		//f) Now, verify if the SignIn page is showing up.
		driver.findElement(By.id("com.ebay.mobile:id/login_src_text")).sendKeys("test_username1");
		driver.findElement(By.id("com.ebay.mobile:id/button_close")).click();
	}
}
