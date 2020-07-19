package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	
	private WebDriver driver;
	
	private By hideButtonLocator = By.id("hide-textbox");
	private By textLocator = By.id("displayed-text");
	private By emuLocator = By.id("emu-checkbox"); 
	private By checkboxesLocator = By.cssSelector(".col-md-6.col-xs-10.col-xs-offset-1 > *");  
	private By jezevaKucicaLocator = By.cssSelector("#booksCheckboxes > [value=\"Jezeva kucica\"]"); 
	private By coursesLocator = By.xpath("//*[@id=\"Nahla Course\"]"); 
	//prompt box locators
	private By promptBoxLocator = By.id("promptBox"); 
	private By promptBoxMessageLocator = By.id("demo");
	//table locators
	private By tableLocator = By.cssSelector("#studentTable > tbody > tr"); 
	private By columnCoursesLocator = By.cssSelector("#studentTable > tbody > tr > td:nth-child(3)"); 
   //win locators
	private By windowLocator = By.id("win1"); 
	//dragandrop
	private By clickAndHoldLocator = By.id("clickAndHold"); 
	private By destinationLocator = By.cssSelector("#sortable > li:nth-child(1)"); 
	//mouse
	private By mouseHoverLocator = By.id("demo2"); 
	//frame
	private By frameLocator = By.cssSelector("[src=\"https://nahla.ba/\"]"); 
	private By volontirajLocator = By.xpath("//*[@id=\"page\"]//a[@href=\"https://nahla.ba/volontiraj/\"]"); 
	private By volonirajTextLocator = By.xpath("//*[@id=\"post-2340\"]/div/div/div/div/section[2]/div/div/div/div/div/div/div/div/p"); 
	
	//konstruktor
	public HomePage (WebDriver driver) {
		this.driver = driver; 
	}
	
	//metode 
	public void clickOnHide () {
		driver.findElement(hideButtonLocator).click(); 
	}
	
	public String checkForMessage () {
		return driver.findElement(textLocator).getText(); 
	}
	
	public boolean isEmuDisabled () {
		return driver.findElement(emuLocator).isEnabled(); 
	}
	
	public int numberOfCheckBoxes () {
		List<WebElement> boxes = driver.findElements(checkboxesLocator); 
		return boxes.size(); 
	}
	
	public boolean isJezevaKucicaSelected () {
		driver.findElement(jezevaKucicaLocator).click();
		return driver.findElement(jezevaKucicaLocator).isSelected(); 
	}
	
	public String isTurskiSelected () {
		Select coursesDropDown = new Select(driver.findElement(coursesLocator)); 
		coursesDropDown.selectByValue("Turski");	
		WebElement option = coursesDropDown.getFirstSelectedOption();
		return option.getText(); 
	}
	
	public String promptBox () {
		driver.findElement(promptBoxLocator).click();
		driver.switchTo().alert().sendKeys("Selma");
		driver.switchTo().alert().accept();
		return driver.findElement(promptBoxMessageLocator).getText();
	}
	
	public int numberOfElementsInTable () {
		List<WebElement> table = driver.findElements(tableLocator);
		return table.size()-1; 
	}
	
	
	//ovo radi al nije bas pametno napravljeno :/
	public ArrayList<String> allStudentsTakingEnglish () {
		List<WebElement> table = driver.findElements(columnCoursesLocator);
		ArrayList<String> names = new ArrayList<String>();
		int i=0; 
		for (WebElement element: table) { 
			if (element.getText().equals("English")) {
				int k = i+2; 
				String firstName = driver.findElement(By.cssSelector("#studentTable > tbody > tr:nth-child("+ k +") > td:nth-child(1)")).getText();
				String lastName =  driver.findElement(By.cssSelector("#studentTable > tbody > tr:nth-child("+ k +") > td:nth-child(2)")).getText();
				String name = firstName+" "+lastName; 
				names.add(name); 
			}
			i++; 
		} 
		if (names.isEmpty()) System.out.println("No student is taking English course");
		return names; 
	}
	
	public void openNahlaPage () {
		driver.findElement(windowLocator).click(); 
	}
	
	/*
	public void newWindow () {
		Set<String> windows = driver.getWindowHandles(); 
		for (String window: windows) {
			driver.switchTo().window(window); 
		}
	}
	
	public NahlaPage fdgf () {
		driver.findElement(windowLocator).click(); 
		return new NahlaPage(driver);
	}*/
	
	
	public String firstElementInDraggable () {
		Actions action = new Actions(driver);
		action.dragAndDrop(driver.findElement(clickAndHoldLocator), driver.findElement(destinationLocator)).perform(); 
		return driver.findElement(destinationLocator).getText();
	}

	public String mouseHoverChange () {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(mouseHoverLocator)).perform(); 
		return driver.findElement(mouseHoverLocator).getText();
	}
	
	public String clickOnVolontiraj () {
		driver.switchTo().frame(driver.findElement(frameLocator));
		driver.findElement(volontirajLocator).click(); 
		String text = driver.findElement(volonirajTextLocator).getText();
		driver.switchTo().defaultContent();
		return text; 
	}
	
	public String getPageURL () {
		return driver.getCurrentUrl(); 
	}
}
