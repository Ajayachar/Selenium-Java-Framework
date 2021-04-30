package PageObjects;

import Generic.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jdi.Value;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends UserActions {

    //Object properties
    private By Obj_Cart_Link = org.openqa.selenium.By.xpath("//a[@href='#/cart']");
    private By Obj_Checkout_Button = org.openqa.selenium.By.xpath("//a[text()='Check Out']");

    /***************************************************************************************************************************
     * Method to click on cart and verify the toy and quantity
     * @param ToyName
     * @param Quantity
     * @param driver
     * @param logger
     * @throws Exception
     */
    public void ClickonCartandVerify(String ToyName, String Quantity, WebDriver driver, ExtentTest logger)throws Exception{

        try{
            //navigate to cart page
            Click(Obj_Cart_Link, driver, logger);
            waitUntillVisibility(Obj_Checkout_Button, 20, driver);
            System.out.println("Clicked on cart successfully");
            logger.log(LogStatus.PASS,"Clicked on cart successfully");
            //verify the table values
            VerifyTable(ToyName, Quantity, driver, logger);
        }catch(Exception e){
            logger.log(LogStatus.FAIL,"Failed to click on cart and verify cart");
            System.out.println(e);
        }

    }

    /***************************************************************************************************************************
     * Method to verify the table by passing toy name and quantity
     * @param ToyName
     * @param Quantity
     * @param driver
     * @param logger
     */
    public void VerifyTable(String ToyName, String Quantity, WebDriver driver, ExtentTest logger) {

        try {
            //find all the rows in the table
            WebElement webtable = driver.findElement(By.xpath("//*[@class ='table table-striped cart-items']//tbody"));
            List<WebElement> allrows = webtable.findElements(By.tagName("tr"));
            List<WebElement> cells;

            Outloop:for (WebElement allrow : allrows) {
                //get all the columns in the row
                cells = allrow.findElements(By.tagName("td"));
                for (int i = 0; i < cells.size(); i++) {

                    //verify cell value with toyname if it matches verify the quatity
                    if (cells.get(i).getText().toLowerCase().trim().contains(ToyName.toLowerCase())) {
                        WebElement in = allrow.findElement(By.tagName("input"));

                        if (in.getAttribute("value").contains(Quantity)) {
                            System.out.println("Toyname and quantity matched in cart");
                            logger.log(LogStatus.PASS,"Toyname and quantity matched in cart");
                            break Outloop;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Toyname and quantity are not matching cart");
            logger.log(LogStatus.FAIL,"Toyname and quantity are not matching cart");
            System.out.println(e);
        }
    }

}
