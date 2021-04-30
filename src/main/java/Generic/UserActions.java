package Generic;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Random;

public class UserActions{

    /**************************************************************************************************************************
     * Method: Method to click based on the object passed
     * @param by
     * @param driver
     * @throws Exception
     */
    public void Click(By by, WebDriver driver, ExtentTest logger) throws Exception {

        //Wait until object is displayed
        waitUntillVisibility(by, 20, driver);
        WebElement element = driver.findElement(by);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        } catch (WebDriverException e) {
            System.out.println("Selenium not able to click");
            logger.log(LogStatus.FAIL,"Selenium not able to click");
        }
        System.out.println("Element located by " + by + " was clicked successfully");
        logger.log(LogStatus.PASS,"Element located by " + by + " was clicked successfully");
    }

    /**************************************************************************************************************************
     * Method: Method to enter value based on the object passed
     * @param by
     * @param driver
     * @throws Exception
     */
    public void Enter(By by, WebDriver driver, String text, ExtentTest logger) throws Exception {

        //Wait until object is displayed
        waitUntillVisibility(by, 20, driver);
        WebElement element = driver.findElement(by);
        try {
            element.sendKeys(text);
        } catch (WebDriverException e) {
            System.out.println("Selenium not able to enter text");
            logger.log(LogStatus.FAIL,"Selenium not able to enter text");
        }
        System.out.println("Element located by " + by + " and text entered successfully");
        logger.log(LogStatus.PASS,"Element located by" + by+ " and text entered successfully");
    }

    /*****************************************************************************************************************************
     * Method: generateRandomString based on the length
     * @param: t_name
     * @param: t_length
     * @return
     ****************************************************************************************************************************/

    public String GetRandomString(String t_name, int t_length) {

        char[] p_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Random p_random = new Random();
        StringBuilder r_sb = new StringBuilder();

        r_sb.append(t_name);

        for (int i = 0; i < t_length; i++) {
            char c = p_chars[p_random.nextInt(p_chars.length)];
            r_sb.append(c);
        }
        System.out.println("Generated random string and returning the values");
        return r_sb.toString();
    }

    /*******************************************************************************************************************************
     * Method: Method to wait unitll element is visible
     * @param locator
     * @param sec
     * @param driver
     * @return
     *******************************************************************************************************************************/

    public WebElement waitUntillVisibility(By locator, Integer sec, WebDriver driver) {
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(sec)).pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /*******************************************************************************************************************************
     * Method: Method to wait unitll element is clickable
     * @param locator
     * @param sec
     * @param driver
     * @return
     *******************************************************************************************************************************/

    public WebElement waitUntillClickable(By locator, Integer sec, WebDriver driver) {
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(sec)).pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /*****************************************************************************************************************************
     * method to verify string in page source
     * @param VerifyPageContent
     * @param driver
     */
    public void VerifyPageContent(String VerifyPageContent, WebDriver driver, ExtentTest logger) throws InterruptedException {
        WaitforPagetoLoad(driver);
        String GetPageSource = driver.getPageSource();
        if (GetPageSource.contains(VerifyPageContent)) {
            System.out.println("Page source contains expected values " + VerifyPageContent);
            logger.log(LogStatus.PASS,"Page source contains expected values "+VerifyPageContent);
        } else {
            System.out.println("Page source does not contain expected values " + VerifyPageContent);
            logger.log(LogStatus.FAIL,"Page source does not contain expected values " + VerifyPageContent);
        }
    }

    /******************************************************************************************************************************
     * Method: get random integer between the min and max value
     * @param t_min
     * @param t_max
     * @return
     */
    public String GetRandomNumber(int t_min, int t_max) {
        int a = (int) Math.floor(Math.random() * ((t_max - t_min) + 1)) + t_min;
        return String.valueOf(a);
    }

    /******************************************************************************************************************************
     * Method to wait untill page is loaded
     * @param driver
     */
    public void WaitforPagetoLoad(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").toString().equals("complete");
    }

}
