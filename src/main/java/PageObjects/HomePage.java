package PageObjects;

import Generic.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends UserActions {

    private final By Obj_Forename_Textbox =org.openqa.selenium.By.id("forename");

    /*********************************************************************************************************************************************
     * Method: Method to navigate to URL
     * @param URL
     */
    public WebDriver NavigatetoURL(String URL, ExtentTest logger) {

        //set chrome driver path
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //navigate to URL
        driver.get(URL);
        WaitforPagetoLoad(driver);
        driver.manage().window().maximize();

        //javascript tp wait until page is loaded
        String pageLoadStatus;
        do {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            pageLoadStatus = (String) js.executeScript("return document.readyState");
        } while (!pageLoadStatus.equals("complete"));

        System.out.println("Successfully Navigated to URL");
        logger.log(LogStatus.PASS,"Successfully Navigated to URL");
        return driver;
    }

    /*********************************************************************************************************************
     * Method to click on tab
     * @param tabname
     * @param driver
     * @throws Exception
     */

    public void NavigateTab(String tabname, WebDriver driver, ExtentTest logger) throws Exception{

        //tab object property
        By Obj_Tab_Link =org.openqa.selenium.By.xpath("//a[text()='"+tabname+"']");
        Click(Obj_Tab_Link, driver, logger);
        WaitforPagetoLoad(driver);

        //Verify if it has navigated to correct tab
        if(tabname == "Shop"){
            //wait untill object is clickable
            String Obj_Buy_Button = "//h4[text()='Teddy Bear']//ancestor::li//child::a";
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Obj_Buy_Button)));

            VerifyPageContent("Teddy Bear", driver,logger);
            System.out.println("Succeessfully clicked Shop tab");
            logger.log(LogStatus.PASS,"Succeessfully clicked Shop tab");
        }else if(tabname == "Contact"){
            //Wait until visibility of object
            waitUntillVisibility(Obj_Forename_Textbox, 30, driver);

            VerifyPageContent("We welcome your feedback", driver,logger);
            System.out.println("Succeessfully clicked Contact tab");
            logger.log(LogStatus.PASS,"Succeessfully clicked Contact tab");
        }else{
            VerifyPageContent("Start Shopping", driver,logger);
            System.out.println("Succeessfully clicked Home tab");
            logger.log(LogStatus.PASS,"Succeessfully clicked Home tab");
        }
    }

    /***********************************************************************************************************************
     * Method to close the broswer
     * @param driver
     */
    public void CloseBrowser(WebDriver driver){
        driver.quit();
    }
}
