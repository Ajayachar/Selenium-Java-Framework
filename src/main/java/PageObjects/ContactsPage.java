package PageObjects;

import Generic.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactsPage extends UserActions {

    //page object properties

    private final By Obj_Submit_Button =org.openqa.selenium.By.xpath("//a[text()='Submit']");
    private final By Obj_Alert_Button =org.openqa.selenium.By.xpath("//div[@class='alert alert-error ng-scope']");
    private final By Obj_SuccessMessage_Button =org.openqa.selenium.By.xpath("//div[@class='alert alert-success']");
    private final By Obj_Forename_Textbox =org.openqa.selenium.By.id("forename");
    private final By Obj_Surname_Textbox =org.openqa.selenium.By.id("surname");
    private final By Obj_Email_Textbox =org.openqa.selenium.By.id("email");
    private final By Obj_Telephone_Textbox =org.openqa.selenium.By.id("telephone");
    private final By Obj_TelephoneInvalid_Textbox =org.openqa.selenium.By.xpath("//*[contains(text(),'Please enter a valid telephone number') and @id='telephone-err']");
    private final By Obj_Message_Textbox =org.openqa.selenium.By.id("message");
    private final By Obj_ForenameError_Textbox =org.openqa.selenium.By.xpath("//*[contains(text(),'Forename is required') and @id='forename-err']");
    private final By Obj_EmailError_Textbox =org.openqa.selenium.By.xpath("//*[contains(text(),'Email is required') and @id='email-err']");
    private final By Obj_EmailInvlaid_Textbox =org.openqa.selenium.By.xpath("//*[contains(text(),'Please enter a valid email') and @id='email-err']");
    private final By Obj_MessageError_Textbox =org.openqa.selenium.By.xpath("//*[contains(text(),'Message is required') and @id='message-err']");
    private final By Obj_Back_Button = org.openqa.selenium.By.xpath("//*[contains(text(),'Back') and @class='btn']");


    /*********************************************************************************************************************************
     * Method to create contact and verify
     * @param driver
     * @param logger
     * @throws Exception
     */
    public void CreateContact(WebDriver driver, ExtentTest logger) throws Exception{

        try{
            //waiting for page to load
            waitUntillVisibility(Obj_Forename_Textbox, 30, driver);

            //validating contact page is displayed
            VerifyPageContent("We welcome your feedback", driver, logger);

            //Entering values and verify its successfully saved
            String Firstname = GetRandomString("First", 4);
            Enter(Obj_Forename_Textbox, driver, Firstname, logger);
            Enter(Obj_Surname_Textbox, driver, GetRandomString("Sur", 4), logger);
            Enter(Obj_Email_Textbox, driver, GetRandomString("email_", 4)+"@test.com", logger);
            Enter(Obj_Telephone_Textbox, driver, GetRandomNumber(40000000, 99999999),logger);
            Enter(Obj_Message_Textbox, driver, GetRandomString("Message ", 7),logger);

            waitUntillClickable(Obj_Submit_Button, 30, driver);
            Click(Obj_Submit_Button, driver,logger);

            waitUntillVisibility(Obj_Back_Button, 30, driver);
            waitUntillClickable(Obj_Back_Button, 30, driver);
            VerifyPageContent("Thanks "+Firstname, driver,logger);
            System.out.println("Contact created successfully");
            logger.log(LogStatus.PASS,"Contact created successfully");
        } catch (Exception e){
            System.out.println("Failed to create contact");
            logger.log(LogStatus.FAIL,"Failed to create contact");
            System.out.println(e);
        }

    }
    /*********************************************************************************************************************************
     * Method to verify mandatory fields
     * @param driver
     * @param logger
     * @throws Exception
     */

    public void VerifyMandatoryFields_Contact(WebDriver driver, ExtentTest logger)throws Exception{

        try{
            //waiting for page to load
            waitUntillVisibility(Obj_Forename_Textbox, 30, driver);

            //Validating mandatory fields error message are displayed
            Click(Obj_Submit_Button, driver, logger);
            waitUntillVisibility(Obj_ForenameError_Textbox, 30, driver);
            waitUntillVisibility(Obj_EmailError_Textbox, 30, driver);
            waitUntillVisibility(Obj_MessageError_Textbox, 30, driver);
            VerifyPageContent("We welcome your feedback", driver, logger);

            //Entering valid values and saving it
            String Firstname = GetRandomString("First", 4);
            Enter(Obj_Forename_Textbox, driver, Firstname, logger);
            Enter(Obj_Surname_Textbox, driver, GetRandomString("Sur", 4),logger);
            Enter(Obj_Email_Textbox, driver, GetRandomString("email_", 4)+"@test.com", logger);
            Enter(Obj_Telephone_Textbox, driver, GetRandomNumber(40000000, 99999999), logger);
            Enter(Obj_Message_Textbox, driver, GetRandomString("Message ", 7), logger);
            Click(Obj_Submit_Button, driver,logger);
            waitUntillVisibility(Obj_Back_Button, 30, driver);
            waitUntillClickable(Obj_Back_Button, 30, driver);
            VerifyPageContent("Thanks "+Firstname, driver,logger);
            System.out.println("Successfully validated contact mandatory fields");
            logger.log(LogStatus.PASS,"Successfully validated contact mandatory fields");
        }catch (Exception e){
            System.out.println("Failed to validate mandatory fields under contact");
            logger.log(LogStatus.FAIL,"Failed to validate mandatory fields under contact");
            System.out.println(e);
        }

    }

    /*********************************************************************************************************************************
     * Method to verfiy invalid data
     * @param driver
     * @param logger
     * @throws Exception
     */
    public void InvalidData_Contact(WebDriver driver, ExtentTest logger) throws Exception{

        try{
            //waiting for page to load
            waitUntillVisibility(Obj_Forename_Textbox, 30, driver);
            VerifyPageContent("We welcome your feedback", driver, logger);

            //Entering invalid data
            String Firstname = GetRandomNumber(10, 999999);
            Enter(Obj_Forename_Textbox, driver, Firstname,logger);
            Enter(Obj_Surname_Textbox, driver, GetRandomNumber(10, 999999),logger);
            Enter(Obj_Email_Textbox, driver, GetRandomString("email_", 4),logger);
            Enter(Obj_Telephone_Textbox, driver, GetRandomString("tele", 4),logger);
            Enter(Obj_Message_Textbox, driver, GetRandomString("Message ", 7),logger);
            Click(Obj_Submit_Button, driver,logger);

            //Validating invalid error message are displayed
            waitUntillVisibility(Obj_TelephoneInvalid_Textbox, 30, driver);
            waitUntillVisibility(Obj_EmailInvlaid_Textbox, 30, driver);
            System.out.println("Successfully validated invalid data in contact page");
            logger.log(LogStatus.PASS,"Successfully validated invalid data in contact page");
        } catch (Exception e){
            System.out.println("Invalid data validation failed");
            logger.log(LogStatus.FAIL,"Invalid data validation failed");
            System.out.println(e);
        }

    }
}
