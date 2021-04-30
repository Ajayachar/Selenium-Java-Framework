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

public class TC004_Add_to_Cart_and_Verify {

    private CartPage CP = new CartPage();
    private ContactsPage contactp = new ContactsPage();
    private HomePage hp = new HomePage();
    private ShopPage sp = new ShopPage();
    private ExtentReports reporter = ExtentReportManager.getReporter("HTMLOutput\\TC004_Add_to_Cart_and_Verify.html");
    WebDriver driver;ExtentTest logger;

    @Parameters({"URL"})
    @Test

    public void validateMandatoryErrorMessageandCreateContact(String URL) throws Exception {
        String Tname = "TC003_Validate_InvalidData_Contact";
        logger = reporter.startTest("TC003_Validate_InvalidData_Contact");

        try{

            //login to jupiter toys
            driver = hp.NavigatetoURL(URL,logger);
            hp.NavigateTab("Shop", driver,logger);

            //Add 2 funny cow and 1 fluffy bunny
            sp.AddtoCart("Funny Cow", driver,logger);
            sp.AddtoCart("Funny Cow", driver,logger);
            sp.AddtoCart("Fluffy Bunny", driver,logger);

            //Go to cart and very
            CP.ClickonCartandVerify("Funny Cow", "2", driver,logger);
            CP.ClickonCartandVerify("Fluffy Bunny", "1", driver,logger);

            hp.CloseBrowser(driver);
        }catch (Exception e){
            System.out.println("Failed to add toys to cart and very it in add cart");
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
