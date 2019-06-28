/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gmail.fetch;

/**
 *
 * @author Abela
 */
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
public class GmailFetch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
       System.setProperty("webdriver.chrome.driver", "C:\\Users\\Abela\\Documents\\NetBeansProjects\\Selenium\\src\\chromedriver.exe");
        WebDriver driver;
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        //Launch the some site
         String gmailUrl = "https://www.gmail.com";
        driver.get(gmailUrl);

        
       
        
        
        //Log in
        
        driver.findElement(By.id("identifierId")).sendKeys("abeltsg32@gmail.com",Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        
       driver.findElement(By.name("password")).sendKeys("###############",Keys.ENTER);
      
       //Wait for captcha
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       
        // Print number of new emails and Subjects of new email
        List<WebElement> NewEmail =  driver.findElement(By.className("Cp")).findElement(By.tagName("table")).findElements(By.className("zE"));
        System.out.println("New Emails :" + NewEmail.size());
        
        for (int x = 0; x < NewEmail.size(); x++){
            System.out.println("* Subject " + (x + 1) + ": " + NewEmail.get(x).findElement(By.className("y6")).getText());
        }

        String CurrentUrl = driver.getCurrentUrl();
        String PageSource = driver.getPageSource();

        System.out.println("URL: "+CurrentUrl);
        System.out.println("Source Code: "+PageSource);

      
        System.out.println();
        Verify(driver.getCurrentUrl(), gmailUrl, driver);
          //Wait
         Thread.sleep(360000000);

        // Close the driver
        driver.quit();
    }
     public static void Verify(String actualUrl, String url, WebDriver driver){
        if (actualUrl.equals(url)){
            System.out.println("Verification Successful - The correct Url is opened.");
        } else {
            System.out.println("Verification Failed - An incorrect Url is opened.");
            System.out.println("Actual URL is : " + actualUrl);
            System.out.println("Expected URL is : " + url);
        }

        String pageSource = driver.getPageSource();
        int pageSourceLength = pageSource.length();
        System.out.println("Total length of the Page Source is : " + pageSourceLength);
    }
    
}
