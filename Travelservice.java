package Testingframework;




import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.ExecuteScript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Travelservice {

	static Properties prop = new Properties();
	static WebDriver driver = new FirefoxDriver();
	 static Logger logger=Logger.getLogger("Travelservice");
	@Test
	public void launchbrowser() throws InterruptedException {

		/*
		 * Launch the Browser 1. Read the file of xpath from config/OR
		 * properties file
		 */
		File file = new File(System.getProperty("user.dir") + "//src//Library//OR.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver.get(prop.getProperty("TravelserviceURL"));// Main URL for the application
		//Thread.sleep(2000);
		
		WebElement Gridster = driver.findElement(By.className("gridster"));
		WebElement ULGridster= Gridster.findElement(By.tagName("ul"));
		List<WebElement> listofmenu = ULGridster.findElements(By.tagName("li"));
		System.out.println("Tags"+listofmenu);
		for (int i = 0; i < listofmenu.size(); i++) {
			WebElement onemenu = listofmenu.get(i);
			WebElement menuspan= onemenu.findElement(By.tagName("span"));
			String nameofmenu= menuspan.getText();
			System.out.println(""+nameofmenu);

			if(nameofmenu.matches("Hudba") || nameofmenu.matches("Audio")) {
				menuspan.click();			
				System.out.println("found!!!");
			}
		}
		//driver.findElement(By.xpath("html/body/div[1]/div/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[3]/img")).click();
		//driver.findElement(By.xpath(prop.getProperty("TravelserviceAudio"))).click();// Main URL for the application
		
		//driver.findElement(By.xpath("//img[@src='http://ifd.aero/resource/images/menus/t-audio.png?1518018576609']")).click();
		//Thread.sleep(2000);
		
/*		driver.findElement(By.xpath(prop.getProperty("Entertainment"))).click(); // Navigate
																					// to
																					// Entertainment
																					// section
		System.out.println("Main landing Everhub Page Section");
		Thread.sleep(2000);
		System.out.println("Entertainment Section");*/
		//driver.findElement(By.xpath(prop.getProperty("TravelserviceAudio"))).click(); // Navigate
																			// to
																			// Audio
																			// section
	//driver.get("http://www.msftconnecttest.com/#/entertainment/audio");
		System.out.println("Audio Section");
		Thread.sleep(2000);
	}

	@Test
	public void playaudio() throws InterruptedException {
		

		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		 
		/* CSS selector selection for Thumb nails of albums */
		List<WebElement> links = driver.findElements(By.cssSelector(".media-box__poster"));

		/* Total number of albums on the page */
		System.out.println("Total number of Albums " + links.size());
		// System.out.println(links);

		/* Logic for getting into all album on the page */
		for (int i = 0; i <= links.size(); i++) {

			/* Scroll into the page and making albums visible to click */
		//	WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(links.get(i)));
			Thread.sleep(1000);
			
		     //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//System.out.println("yes");
			
			//WebElement ele = links.get(i);
			WebElement albumelement=links.get(i);
			// albumelement = wait.until(ExpectedConditions.elementToBeClickable(links.get(i)));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", albumelement);
	
			//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(links.get(i)));
			albumelement.click();
			String url = albumelement.getAttribute("href");

			int counter = 0;
			// System.out.println(" sucess " + driver.getTitle());

			/* List for playing each track one by one */
			List<WebElement> playNow1 = driver.findElements(By.cssSelector(".track-status"));

			/* List for printing title of each song */
			List<WebElement> titlesOfSongs = driver.findElements(By.cssSelector(".track-title>span"));

			/* List for printing Track number of each song */
			List<WebElement> tracknumber = driver.findElements(By.cssSelector(".track-number"));

			/* List for printing each album name */
			List<WebElement> Albumname = driver.findElements(By.cssSelector(".media-detail__metadata__title"));

			/* List for printing Artist for each album */
			List<WebElement> Artistname = driver.findElements(By.cssSelector(".media-detail__metadata__subtitle"));

			// System.out.println("Songs "+titlesOfSongs);
			//String url1 = ele.getAttribute("href");

			//verifyLinkActive(url1);
			// zoomIn();
			// set100();
			// System.out.println("Counter is " + counter);
			//Thread.sleep(1500);

			/* Printing Album name */
			System.out.println("Album number" + i);
			System.out.println("Album name " + Albumname.get(0).getText());
			logger.info("Album number" + i + "Album name " + Albumname.get(0).getText());
			

			/* Printing Album Artist name */
			System.out.println("Artist name " + Artistname.get(0).getText());
			System.out.println("Number of songs in an album are  " + playNow1.size());
			
			/* Logic for playing all songs of album */
			for (int j = 0; j < playNow1.size(); j++) {

			//	Thread.sleep(1000);

				/* Play each song of the album */
				WebElement songselement = wait.until(ExpectedConditions.elementToBeClickable(playNow1.get(j)));

				playNow1.get(j).click();
				//System.out.println(+playNow1.get(j).getAttribute("data-url"));
				/*
				 * WebElement element=(WebElement)
				 * driver.findElements(By.cssSelector(".track-title>span"));
				 * 
				 * String val=playNow1.get(j).getAttribute("innerText");
				 */

				/* Print Track number followed by track name */
				System.out.println("" + tracknumber.get(j).getText() + " " + titlesOfSongs.get(j).getText());
				// System.out.println("Song "+titlesOfSongs.get(j));

				String songurl=playNow1.get(j).getAttribute("data-url");
				verifyLinkActive(songurl);
				//Thread.sleep(1500);
				//counter++;
			}

			/* Number of songs in an album */
			
			// System.out.println("Album number"+playNow1);

			/* Go back to list of albums */
			//driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/modal-header/div/div[3]/div")).click();
			driver.findElement(By.xpath(prop.getProperty("TravelserviceAudiobackbutton"))).click(); 
			//System.out.println("" + i);
			//Thread.sleep(1000);
			//System.out.println("Album number" + i);
			// zoomOut();
		}

		logger.info(" album Tested ");
	}
	


	/* Response to check whether each album is being played */
	public static void verifyLinkActive(String linkUrl) {
		try {
			URL url = new URL(linkUrl);

			URL url1 = new URL(linkUrl);
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
				//logger.info(linkUrl + " - " + httpURLConnect.getResponseMessage());
			} else {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
				 logger.info(linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
				logger.info(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
				
			}
			
			
			
		} catch (Exception e) {
			System.out.println(linkUrl + " - " + e);
		}
	}

	
	public static void main(String[] args) throws InterruptedException {

	    // Here we need to create logger instance so we need to pass Class name for 
	    //which  we want to create log file in my case Google is classname
	   Logger logger=Logger.getLogger("Travelservice");

	      // configure log4j properties file
	       PropertyConfigurator.configure("log4j.properties");
	         // Open browser
	        //WebDriver driver = new FirefoxDriver();
	        logger.info("Browser Opened");

	        // Set implicit wait
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        logger.info("Implicit wait given");

	        // Load application
	    // driver.get("https://www.google.co.in/");
	    // logger.info("Url opened");
	        
	       Travelservice ad= new  Travelservice();
	        ad.launchbrowser();
	        ad.playaudio();

	}
}
