package Test;

import Generic.ExtentReportManager;
import PageObjects.CartPage;
import PageObjects.ContactsPage;
import PageObjects.HomePage;
import PageObjects.ShopPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC002_Validate_Contact_Creation {

    private CartPage CP = new CartPage();
    private ContactsPage contactp = new ContactsPage();
    private HomePage hp = new HomePage();
    private ShopPage sp = new ShopPage();
    private ExtentReports reporter = ExtentReportManager.getReporter("HTMLOutput\\TC002_Validate_Contact_Creation.html");
    WebDriver driver;
    ExtentTest logger;

    @Parameters({"URL"})
    @Test

    public void validateMandatoryErrorMessageandCreateContact(String URL) throws Exception {
        String Tname = "TC002_Validate_Contact_Creation";
        logger = reporter.startTest("TC001_Validate_Mandatoryfield_Contact");

        try{
            //Login and navigate to URL
            driver = hp.NavigatetoURL(URL,logger);

            //Click on contact page
            hp.NavigateTab("Contact", driver,logger);

            //create contact and verify success message
            contactp.CreateContact(driver, logger);

            hp.CloseBrowser(driver);
        }catch (Exception e){
            System.out.println("Failed to create a contact");
            System.out.println(e);
            logger.log(LogStatus.FAIL,Tname);
            hp.CloseBrowser(driver);
        }
    }
    @AfterTest
    public void endReport(){
        reporter.endTest(logger);
        reporter.close();
    }
}
