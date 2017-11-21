package AppViews;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.Dimension;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import net.bytebuddy.asm.Advice.This;

public class SearchResultView {
	private static AndroidDriver driver;	
	
	public SearchResultView(AndroidDriver _driver)
	{
		this.driver = _driver;				
	}
	
	/** 
     * This function will sort the search result in ascending order          	
     */
	public static void SortByLowestPrice(){
		//b) From search results, sort by the given option on price..
		driver.findElement(By.id("com.ebay.mobile:id/button_sort")).click();		
		WebElement searchView = driver.findElement(By.id("com.ebay.mobile:id/search_refine_list"));
		List<WebElement> searchView1 = searchView.findElements(By.className("android.widget.LinearLayout"));
		searchView1.get(1).click();
	}
	
	/** 
     * This function will verify if the price is sorted for N items
     * 
     * @param itemCount 	The item count to verify for sort is completed.
     */
	public static Boolean Verify_PriceSorted(int itemCount)	{
		LinkedHashMap<String,BigDecimal> ProductNamePrice = new LinkedHashMap<String,BigDecimal>();		
		ProductNamePrice = getProductNamePrice(itemCount);
		
		//verify ascending below
		Boolean isSorted = isSortedCurrency(ProductNamePrice.values());
		
		return isSorted;		 
	}
	
	private static Boolean isSortedCurrency(Collection<BigDecimal> items)
	{
		BigDecimal min = new BigDecimal(0.00);		
		for(BigDecimal item : items)
		 {
	 
			 if(item.compareTo(min)  >= 0)
			 {
				 min = item;
				 continue;
			 }
			 else			 
				 return false;			 		
		 }				
		return true;
	}
			
	/** 
     * This function will provide a list containing item name & price.
     * provided in the parameter
     * 
     * @param num			value for items to be searched 
     * @return				Key value pair containing name & price          	
     */	
	//c)  Write a utility method that will return the first ‘N’ item prices and the item names as a list, based on the above search.
	public  static LinkedHashMap<String, BigDecimal> getProductNamePrice(int num) {		
	
		LinkedHashMap<String,BigDecimal> products = new LinkedHashMap<String,BigDecimal>();			
		products = scrollDownTillProductItem(num);			   			   
	   	return products;
	}
			
	/** 
     * This function will fill all the product information scrolling down
     * till N product
     * 
     * @param N 			Number of Items which needs to be scroll down
     * @return				List of product information          	
     */
	public static LinkedHashMap<String, BigDecimal> scrollDownTillProductItem(int N){
		
	    int ctr = 0;	    
	    LinkedHashMap<String,BigDecimal> products = new LinkedHashMap<String,BigDecimal>();	   	    
			    	    	   
	    for(int i = 0; i < 2000 ; i++){
	    	
	    	if(ctr == N) 
	    		break;
	    	
	    	List<WebElement> items = driver.findElement(By.id("com.ebay.mobile:id/search_result_fragment")).findElements(By.id("com.ebay.mobile:id/cell_collection_item"));	
	    	
	    	for(WebElement item : items)
		    {
	    		String productName = item.findElement(By.id("com.ebay.mobile:id/textview_item_title")).getText();			   
	    		String productPrice = item.findElement(By.id("com.ebay.mobile:id/textview_item_price")).getText();
	    		
	    		if (productName == null || productPrice == null || productName == "" || productPrice == "")
	    			continue;
	    		
	    		BigDecimal dProductPrice = new BigDecimal(0);
				try {
					dProductPrice = parse(productPrice,Locale.US);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		if(products.containsKey(productName))
	    			products.put(productName+"_duplicate", dProductPrice);
	    		else
	    			products.put(productName, dProductPrice);
	    		
				System.out.println(productName + " - " + productPrice);				
				ctr++;
				
				if(ctr == N) 
					break;
		    }	    	
	    	scrollDOwn();
	    }	    
	    return products;
	}
	
	
	public static BigDecimal parse(final String amount, final Locale locale) throws ParseException {
	    final NumberFormat format = NumberFormat.getNumberInstance(locale);
	    if (format instanceof DecimalFormat) {
	        ((DecimalFormat) format).setParseBigDecimal(true);
	    }
	    return (BigDecimal) format.parse(amount.replaceAll("[^\\d.,]",""));
	}
	
	/** 
     * This function will scroll down the view
     */
	private static void scrollDOwn(){
		Dimension size = driver.manage().window().getSize();
	    int anchor = (int) (size.width * 0.5);
	    int startPoint = (int) (size.height * 0.9);
	    int endPoint = (int) (size.height * 0.1);	
		
	    new TouchAction(driver).press(anchor, startPoint).waitAction(Duration.ofSeconds(3)).moveTo(anchor, endPoint).release().perform();
	}
	
	/** 
     * This function will select first product.
     */
	public static void SelectFirstProduct(){
		//d) Click on the product link for the first product in search result.
	    for(int i = 0; i < 20; i++)
	    {
		scrollUp();
		}
	    
		driver.findElement(By.id("com.ebay.mobile:id/cell_collection_item")).click();
	}

	/** 
     * This function will scroll up the view
     */	
	public static void scrollUp(){
		Dimension size = driver.manage().window().getSize();
	    int anchor = (int) (size.width * 0.5);
	    int startPoint = (int) (size.height * 0.4);
	    int endPoint = (int) (size.height * 0.6);	    
	    	    	
		new TouchAction(driver).press(anchor, startPoint).waitAction(Duration.ofSeconds(3)).moveTo(anchor, endPoint).release().perform();
	    }	   
}
