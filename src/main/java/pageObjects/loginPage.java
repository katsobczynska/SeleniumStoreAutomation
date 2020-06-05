package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {
	WebDriver driver;

	public loginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(xpath = "//input[@class='btn_action']")
	WebElement login;

	@FindBy(tagName = "h3")
	WebElement error;

	public WebElement Username() {
		return username;
	}

	public WebElement Password() {
		return password;
	}

	public WebElement Login() {
		return login;
	}

	public WebElement Error() {
		return error;
	}

}
