package PageObjects;

import Generic.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ShopPage extends UserActions {

    /************************************************************************************************************************
     * Method to add toys to cart
     * @param Scripttoyname
     * @param driver
     * @param logger
     * @throws Exception
     */
    public void AddtoCart(String Scripttoyname, WebDriver driver, ExtentTest logger)throws Exception{

        try{
            //Add toys to cart
            String Obj_Buy_Button = "//h4[text()='"+Scripttoyname+"']//ancestor::li//child::a";
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Obj_Buy_Button)));
            Click(By.xpath(Obj_Buy_Button), driver, logger);
//            WebElement Buy_Button = driver.findElement(By.xpath(Obj_Buy_Button));
//            Buy_Button.click();
            System.out.println("Successfully added "+Scripttoyname+ "to cart");
            logger.log(LogStatus.PASS,"Successfully added \"+Scripttoyname+ \"to cart");
        }catch(Exception e){
            System.out.println("Failed to add "+Scripttoyname+ "to cart");
            logger.log(LogStatus.FAIL,"Failed to add \"+Scripttoyname+ \"to cart");
            System.out.println(e);
        }

    }

}
