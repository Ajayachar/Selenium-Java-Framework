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

public class TC001_Validate_Mandatoryfield_Contact {

    private CartPage CP = new CartPage();
    private ContactsPage contactp = new ContactsPage();
    private HomePage hp = new HomePage();
    private ShopPage sp = new ShopPage();
    private ExtentReports reporter = ExtentReportManager.getReporter("HTMLOutput\\TC001_Validate_Mandatoryfield_Contact.html");
    WebDriver driver;ExtentTest logger;

    @Parameters({"URL"})
    @Test

    public void validateMandatoryErrorMessageandCreateContact(String URL) throws Exception {
        String Tname = "TC001_Validate_Mandatoryfield_Contact";
        logger = reporter.startTest(Tname);
        try{
            //Login and navigate to URL
            driver = hp.NavigatetoURL(URL,logger);

            //Click on contact page
            hp.NavigateTab("Contact", driver, logger);

            //Validate mandatory fields in contact page
            contactp.VerifyMandatoryFields_Contact(driver,logger);

            hp.CloseBrowser(driver);
        }catch (Exception e){
            System.out.println("Failed to validate mandatory fields in contact page");
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

