package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BaseClass;

public class LoginPage extends BaseClass{
	
	@FindBy(xpath = "//span[contains(text(),'Hello, Sign in')]")
	List<WebElement> signInLink;
	
	@FindBy(id = "nav-link-accountList")
	WebElement signInLabelBtn;
	
	@FindBy(xpath = "//div[@id='nav-flyout-ya-signin']//span[contains(text(),'Sign in')]")
	WebElement signInBtn;
	
	@FindBy(id = "ap_email")
	WebElement userName;
	
	@FindBy(id = "continue")
	WebElement continueBtn;
	
	@FindBy(id = "ap_password")
	WebElement password;
	
	@FindBy(id = "signInSubmit")
	WebElement credentialSigninBtn;
	
	@FindBy(xpath = "//span[contains(text(),'Sign Out')]")
	WebElement signOutBtn;
	
	
	// Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	//Hello  sign in button
	public boolean validateHelloSignInLabel(){
		if(signInLink.size()>0) {
			return true;
		}else {
			return false;
		}	
	}
	
	//User name label
	public String userNameLabel(String user) {
		WebElement userAccLabel = driver.findElement(By.xpath("//span[contains(text(),'Hello, "+user+ "')]"));
		return userAccLabel.getText();
	}
	
	//sign In
	public void clickOnSignIn(){
		js.executeScript("arguments[0].scrollIntoView(true);", signInLabelBtn);
	  	wait.until(ExpectedConditions.visibilityOf(signInLabelBtn));
		Actions action = new Actions(driver);
		action.moveToElement(signInLabelBtn).build().perform();
	}
	
	
	//Log in to Amazon 
	public void signIn(String un, String pwd){
    	js.executeScript("arguments[0].click();", signInBtn); 
		userName.sendKeys(un);
		continueBtn.click();
		password.sendKeys(pwd);
		credentialSigninBtn.click();  	
	}
	
	//sign out
	public void signOut(){		
    	js.executeScript("arguments[0].click();", signOutBtn);	    	
	}
	
	//signout verification
	public boolean verifySignOut() {
		return userName.isDisplayed();
	}
}
