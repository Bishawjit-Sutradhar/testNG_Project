package testrunner;

import config.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class AdminLoginTestRunner extends SetUp {


    @Test(priority = 1,description = "Login with wrong credential")
    public void loginWithWrongCredential(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("admin@test.com","wrongPassword");
        String warningMessageActual=driver.findElement(By.cssSelector("p")).getText();
        String warningMessageExpected="Invalid email or password";
        Assert.assertEquals(warningMessageActual,warningMessageExpected);

        clearData();

    }

    //This method is used to clear the email and password textfield after try to login wrong credential
    public void clearData(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.txtEmail.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        loginPage.txtPassword.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
    }

    @Test(priority = 2, description = "admin can login valid emain and password",groups = "smoke")
    public void doLogin() throws IOException {
        LoginPage loginPage=new LoginPage(driver);
        Utils.scroll(driver,500);

        if(System.getProperty("email")!=null && System.getProperty("password")!=null){
            String email=System.getProperty("email");
            String password=System.getProperty("password");
            loginPage.doLogin(email,password);
        }else {
            loginPage.doLogin("admin@test.com","admin123");
        }
        //Get the autToken
        Utils.getToken(driver);

        DashboardPage dashboardPage=new DashboardPage(driver);
        String textHeaderActual= dashboardPage.textAdmin.getText();
        String textHeaderExpected="Admin Dashboard";
        boolean isVisible= dashboardPage.btnIcon.get(0).isDisplayed();



        Assert.assertEquals(textHeaderActual,textHeaderExpected);
        Assert.assertEquals(isVisible,true);

    }


    @Test(priority = 3,description = "Verify profile user name",groups = "smoke")
    public void viewProfile() throws InterruptedException {
        Thread.sleep(7000);
       driver.findElements(By.tagName("button")).get(1).click();
       String name=driver.findElement(By.name("firstName")).getAttribute("Value");// get disable value from table

        System.out.println(name);

        SoftAssert softAssert=new SoftAssert();

        String textActual=driver.findElement(By.tagName("h4")).getText();
        String textExpected="User Details";
        softAssert.assertTrue(textActual.contains(textExpected));

        List <WebElement> button=driver.findElements(By.tagName("button"));
        softAssert.assertTrue(button.get(2).isDisplayed());


    }



    @Test(priority = 4,description = "Admin can logout", groups = "smoke")
    public void logOut(){
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.doLogOut();

        String txtLoginPageActual= driver.findElement(By.tagName("h1")).getText();
        String txtLoginPageExpected="Login";

        Assert.assertEquals(txtLoginPageActual,txtLoginPageExpected);

    }


}
