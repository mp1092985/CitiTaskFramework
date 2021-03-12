package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BaseClass;

public class HomePage extends BaseClass{
	@FindBy(id = "twotabsearchtextbox")
	WebElement searchBox;
	
	@FindBy(id = "nav-search-submit-button")
	WebElement searchBtn;
	
	@FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
	WebElement searchResult;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	//search item
	public void searchItem(String item) {
		searchBox.sendKeys(item);
		searchBtn.click();
	}
	
	//Verify Search result
	public String verifySearchResult() {
		wait.until(ExpectedConditions.visibilityOf(searchResult));
		return searchResult.getText();
	}
}
