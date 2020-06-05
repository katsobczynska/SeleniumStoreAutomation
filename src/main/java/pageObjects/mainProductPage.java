package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class mainProductPage {

	WebDriver driver;
	public mainProductPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Main menu
	@FindBy (xpath="/html/body/div/div[1]/div/div[3]/div/button")
	WebElement mainMenu;
	
	//Main menu - links
	@FindBy (id="inventory_sidebar_link")
	WebElement inventory;
	
	@FindBy(id="about_sidebar_link")
	WebElement about;
	
	@FindBy(id="logout_sidebar_link")
	WebElement logout;
	
	@FindBy(id="reset_sidebar_link")
	WebElement reset;

	// Close main menu
	@FindBy (xpath="//*[@id=\"menu_button_container\"]/div/div[2]/div[2]/div/button")
	WebElement closeMainMenu;
	
	//Cart
	@FindBy(id="shopping_cart_container")
	WebElement cart;
	
	@FindBy(xpath="/html/body/div/div[2]/div[1]/div[2]/a/span")
	WebElement flag;
	
	//Sort container
	@FindBy(css=".product_sort_container")
	WebElement sortContainer;
	

	@FindBy(css=".inventory_item_img")
	WebElement productPicture;
	
	@FindBy(id="item_0_img_link")
	WebElement productLink;
	
	@FindBy(css=".inventory_item_name")
	WebElement productName;
	
	@FindBy(css=".inventory_item_desc")
	WebElement productDescription;
	
	@FindBy(css=".pricebar")
	WebElement productPrice;
	
	@FindBy(xpath="/html/body/div/div[2]/div[2]/div/div[2]/div/div[1]/div[3]/button")
	WebElement addToCartButton;
	
	@FindBy(xpath="/html/body/div/div[2]/div[2]/div/div[2]/div/div[4]/div[3]/button")
	WebElement addToCartButton2;
	
	
	//Main menu
	public WebElement mainMenu()
	{
		return mainMenu;
	}
	
	//Main menu - links
	public WebElement inventory()
	{
		return inventory;
	}
	
	public WebElement about()
	{
		return about;
	}
	
	public WebElement logout()
	{
		return logout;
	}
	
	public WebElement reset()
	{
		return reset;
	}

	//Close MainMenu
	public WebElement closeMainMenu()
	{
		return closeMainMenu;
	}
	
	//Cart
	public WebElement cart()
	{
		return cart;
	}
	
	
	public WebElement sortContainer()
	{
		return sortContainer;
	}
	
	//Elements of each displayed product
	
	public WebElement productPicture()
	{
		return productPicture;
	}
	
	public WebElement productName()
	{
		return productName;
	}
	
	public WebElement productDescription()
	{
		return productDescription;
	}
	
	public WebElement productPrice()
	{
		return productPrice;
	}
	
	public WebElement addToCartButton()
	{
		return addToCartButton;
	}
	
	public WebElement addToCartButton2()
	{
		return addToCartButton2;
	}
	
	public WebElement productLink()
	{
		return productLink;
	}
	
	public WebElement flag()
	{
		return flag;
	}
	

	
	
	
	
	
	
}