package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class basketPage {
	WebDriver driver;

	public basketPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "subheader")
	WebElement subheader;

	@FindBy(className = "cart_quantity_label")
	WebElement quantityLabel;

	@FindBy(css = "body.main-body:nth-child(2) div.page_wrapper:nth-child(2) div.cart_contents_container div:nth-child(1) div.cart_list div.cart_item:nth-child(3) > div.cart_quantity")
	WebElement cartQuantity;

	@FindBy(className = "cart_desc_label")
	WebElement cartDescriptionLabel;

	@FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div[1]/div[3]/div[2]/div[2]/button")
	WebElement removeButton;

	@FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div[2]/a[1]")
	WebElement continueShopping;

	@FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div[2]/a[2]")
	WebElement checkout;

	public WebElement subheader() {
		return subheader;
	}

	public WebElement quantityLabel() {
		return quantityLabel;
	}

	public WebElement cartQuantity() {
		return cartQuantity;
	}

	public WebElement cartDescriptionLabel() {
		return cartDescriptionLabel;
	}

	public WebElement removeButton() {
		return removeButton;
	}

	public WebElement continueShopping() {
		return continueShopping;
	}

	public WebElement checkout() {
		return checkout;
	}

}