package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class individualProductPage {
	WebDriver driver;
	
	public individualProductPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Main page - product link
	@FindBy (id="item_4_title_link")
	WebElement itemLink;
	
	//Individual Product Page 
	@FindBy (className="header_label")
	WebElement headerLabel;
	
	@FindBy(className="inventory_details_back_button")
	WebElement backButton;
	
	@FindBy(className = "inventory_details_img")
	WebElement itemImage;
	
	@FindBy(className = "inventory_details_name")
	WebElement itemName;
	
	@FindBy(className = "inventory_details_desc")
	WebElement itemDescription;
	
	@FindBy(className = "inventory_details_price")
	WebElement itemPrice;
	
	@FindBy(xpath="/html/body/div/div[2]/div[2]/div/div/div/button")
	WebElement addToCart;
	

	@FindBy(xpath="/html/body/div/div[2]/div[1]/div[2]/a/span")
	WebElement cartFlag;
	
	public WebElement itemLink()
	{
		return itemLink;
	}

	public WebElement headerLabel()
	{
		return headerLabel;
	}
	
	public WebElement backButton()
	{
		return backButton;
	}
	
	public WebElement itemImage()
	{
		return itemImage;
	}
	
	public WebElement itemName()
	{
		return itemName;
	}
	
	public WebElement itemDescriprion()
	{
		return itemDescription;
	}
	
	public WebElement itemPrice()
	{
		return itemPrice;
	}
	
	public WebElement addToCart()
	{
		return addToCart;
	}
	
	public WebElement cartFlag()
	{
		return cartFlag;
	}
}

