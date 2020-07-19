package testPackage;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;


import pages.HomePage;


public class HomePageTest {
	
	private WebDriver driver; 
	
	@BeforeClass 
	public void setUp () {
		String dir = System.getProperty("user.dir"); 
		System.setProperty("webdriver.chrome.driver", dir + "\\executable\\chromedriver.exe");//useful for github 
		//allows everyone to access to their webdriver path 
		driver = new ChromeDriver(); 
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //waiting in seconds 
		driver.manage().window().maximize(); //maximizing the screen 
		driver.get("https://amina-pez.github.io/automation/"); //openes a specific webpage
	}
	
	@Test (priority = 1)
	public void testMessage () {
		HomePage home = new HomePage(driver);
		Assert.assertEquals(home.checkForMessage(), "Tekst koji treba biti sakriven", "Expected message is not there.");
		home.clickOnHide();
		Assert.assertEquals(home.checkForMessage(), "", "There should be no message.");
	}
	
	@Test (priority = 2)
	public void checkIfEmuIsDisabled () {
		HomePage home = new HomePage(driver); 
		Assert.assertFalse(home.isEmuDisabled(), "Emu box is not disabled.");
	}
	
	@Test (priority = 3)
	public void checkNumberOfBoxes () {
		HomePage home = new HomePage(driver); 
		Assert.assertEquals(home.numberOfCheckBoxes(), 6, "Number of checkboxes not as expected.");
	}
	
	@Test (priority = 4)
	public void checkIfJezevaKucicaIsSelected () {
		HomePage home = new HomePage(driver); 
		Assert.assertTrue(home.isJezevaKucicaSelected(), "Jezeva kucica is not selected.");
	}
	
	@Test (priority = 5)
	public void checkIfTurskiIsSelected () {
		HomePage home = new HomePage(driver); 
		Assert.assertEquals(home.isTurskiSelected(), "Turski", "Turski is not selected from drop down menu.");
	}
	
	@Test (priority = 6)
	public void examinePromptBox () {
		HomePage home = new HomePage(driver); 
		Assert.assertEquals(home.promptBox(), "Hello Selma", "Something's wrong with prompt box.");
	}
	
	@Test (priority = 7)
	public void examineNumberOfElementsInTable () {
		HomePage home = new HomePage(driver); 
		Assert.assertEquals(home.numberOfElementsInTable(), 6, "Number of elements in table not as expected.");
	}
	
	@Test (priority = 8)
	public void checkStudentsNames (){
		HomePage home = new HomePage(driver); 
		ArrayList<String> studentNames = home.allStudentsTakingEnglish();
		Assert.assertEquals(studentNames.get(0), "William Bell", "First student should be William Bell.");
		Assert.assertEquals(studentNames.get(1), "Majda Husic", "Second student should be Majda	Husic.");
	}
	
	
	@Test (priority = 9)
	public void verifyThatObjectIsMovedCorrectly () {
		HomePage home = new HomePage(driver);
		Assert.assertEquals(home.firstElementInDraggable(), "clickAndHold()", "Object was not moved correctly.");
	}
	
	@Test (priority = 10)
	public void verifyThatMouseIsMovedCorrectly () {
		HomePage home = new HomePage(driver);
		Assert.assertEquals(home.mouseHoverChange(), "Mouse over me", "Mouse was not moved correctly.");
	}
	
	@Test (priority = 11)
	public void examineFrame() {
		HomePage home = new HomePage(driver);
		Assert.assertEquals(home.clickOnVolontiraj(), "Volontiraj", "Something's wrong with the frame"); 
	}
	
	@Test (priority = 12)
	public void verifyURL() {
		HomePage home = new HomePage(driver);
		Assert.assertEquals(home.getPageURL(), "https://amina-pez.github.io/automation/", "User is not on the correct page.");
	}
}
