package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class checkoutPage {

	WebDriver driver;

	public checkoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//Address page

	@FindBy(id = "first-name")
	WebElement firstName;

	@FindBy(id = "last-name")
	WebElement lastName;

	@FindBy(id = "postal-code")
	WebElement postalCode;

	@FindBy(xpath = "/html/body/div/div[2]/div[3]/div/form/div[2]/input")
	WebElement continueButton;

	@FindBy(xpath = "/html/body/div/div[2]/div[3]/div/form/div[2]/a")
	WebElement cancelButton;

	@FindBy(tagName = "h3")
	WebElement addressError;

//Overview page

	@FindBy(className = "summary_quantity")
	WebElement productQuantity;

	@FindBy(className = "inventory_item_name")
	WebElement itemName;

	@FindBy(className = "inventory_item_desc")
	WebElement itemDescription;

	@FindBy(className = "inventory_item_price")
	WebElement itemPrice;

	@FindBy(className = "summary_subtotal_label")
	WebElement subTotalPrice;

	@FindBy(className = "summary_tax_label")
	WebElement taxLabel;

	@FindBy(className = "summary_total_label")
	WebElement totalLabel;

	@FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div[2]/div[8]/a[1]")
	WebElement cancelButton2;

	@FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div[2]/div[8]/a[2]")
	WebElement finishButton;

//Finished order page

	@FindBy(className = "complete-header")
	WebElement completeHeader;

//Address page

	public WebElement firstName() {
		return firstName;
	}

	public WebElement lastName() {
		return lastName;
	}

	public WebElement postalCode() {
		return postalCode;
	}

	public WebElement continueButton() {
		return continueButton;
	}

	public WebElement cancelButton() {
		return cancelButton;
	}

	public WebElement addressError() {
		return addressError;
	}

//Overview page
	public WebElement productQuantity() {
		return productQuantity;
	}

	public WebElement itemName() {
		return itemName;

	}

	public WebElement itemDescription() {
		return itemDescription;
	}

	public WebElement itemPrice() {
		return itemPrice;
	}

	public WebElement subTotalPrice() {
		return subTotalPrice;
	}

	public WebElement taxLabel() {
		return taxLabel;

	}

	public WebElement totalLabel() {
		return totalLabel;
	}

	public WebElement cancelButton2() {
		return cancelButton2;
	}

	public WebElement finishButtton() {
		return finishButton;
	}

//Finished order

	public WebElement completeHeader() {
		return completeHeader;
	}
}
