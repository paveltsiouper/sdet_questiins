package com.weigthwatchers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//Author:Paul Tsiouper, November 7,2018
/*Question 2:
The following exercise does not require user login. Please use ID or class as selectors.
Steps:
1. Navigate to https://www.weightwatchers.com/us/
2. Verify loaded page title matches “Weight Loss Program, Recipes &amp; Help | Weight Watchers”

3. On the right corner of the page, click on “Find a Meeting”
4. Verify loaded page title contains “Get Schedules &amp; Times Near You”
5. In the search field, search for meetings for zip code: 10011
6. Print the title of the first result and the distance (located on the right of location title/name)
7. Click on the first search result and then, verify displayed location name matches with the
name of the first searched result that was clicked.
8. From this location page, print TODAY’s hours of operation (located towards the bottom of
the page)
Write an automated test for this scenario using WebDriver.*/

public class QuestionTwo {
	public static WebDriver driver;
	public static String testsiteUrl="https://www.weightwatchers.com/us/";
	public static int implicitWait=20;
	public static WebDriverWait wait;
	
	
	@BeforeTest
	public void setUp() {
		//1. Navigate to https://www.weightwatchers.com/us/
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver_v242.exe");
		driver = new ChromeDriver();
		driver.get(testsiteUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(implicitWait,TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5);
		
	}
	
	
	@Test
	public void Question2Test() {
		//2. Verify loaded page title matches “Weight Loss Program, Recipes &amp; Help | Weight Watchers”
		Assert.assertEquals(driver.getTitle(),"WW (Weight Watchers): Weight Loss & Wellness Help");
		//3. On the right corner of the page, click on “Find a Meeting”
		driver.findElement(By.cssSelector("#ela-menu-visitor-desktop-supplementa_find-a-studio")).click();
		//4. Verify loaded page title contains “Get Schedules &amp; Times Near You”
		Assert.assertEquals(driver.getTitle(),"Find a Studio & Meeting Near You | WW USA");


		
		//5. In the search field, search for meetings for zip code: 10011
		driver.findElement(By.cssSelector("[name='meetingSearch']")).sendKeys("10011");
		driver.findElement(By.cssSelector("[name='meetingSearch']")).sendKeys(Keys.ENTER);
		Assert.assertEquals(driver.getTitle(),"Weight Loss Meeting Locations; 10011 | WW USA");
		//6. Print the title of the first result and the distance (located on the right of location title/name)
		WebElement elLocation= driver.findElement(By.xpath("//span[@ng-if='!linkName']"));
	    System.out.println("Title of the first result:"+elLocation.getText());
	    WebElement elDistance= driver.findElement(By.cssSelector("[href='\\/us\\/find-a-meeting\\/1180510\\/ww-studio-flatiron-new-york-ny'] [ng-if='showDistance']"));
	    System.out.println("The distance (located on the right of location title/name)="+elDistance.getText());
	    //7. Click on the first search result and then, verify displayed location name matches with the
	    //name of the first searched result that was clicked.
	    elLocation.click();
	    //8. From this location page, print TODAY’s hours of operation (located towards the bottom of
	  //  		the page)
	    System.out.println("TODAY’s hours of operation");
	    List<WebElement> els=  driver.findElements(By.cssSelector("[uib-collapse] [ng-repeat='day in days'] [ng-class]"));
	    for ( WebElement e : els ) {
	    	System.out.println(e.getText());
	    }
	    driver.close();
	  }
	
	@AfterTest
	public void tearDownTest() {
		driver.quit();
		
	}
	

}
