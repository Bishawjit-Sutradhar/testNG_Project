package testrunner;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import config.SetUp;
import utils.Utils;

public class AdminLoginTestRunner extends SetUp {


    @Test(priority = 1, description = "admin can login valid emain and password")
    public void doLogin(){
        LoginPage loginPage=new LoginPage(driver);

        Utils.scroll(driver,500);

        String email="admin@test.com";
        String password="admin123";
        loginPage.doLogin(email,password);
        DashboardPage dashboardPage=new DashboardPage(driver);
        String textHeaderActual= dashboardPage.textAdmin.getText();
        String textHeaderExpected="Admin Dashboard";
        boolean isVisible= dashboardPage.btnIcon.get(0).isDisplayed();

        Assert.assertEquals(textHeaderActual,textHeaderExpected);
        Assert.assertEquals(isVisible,true);

    }

    @Test(priority = 2,description = "Verify profile user name")
    public void viewProfile() throws InterruptedException {
        Thread.sleep(5000);
       driver.findElements(By.tagName("button")).get(1).click();
       String name=driver.findElement(By.name("firstName")).getAttribute("Value");// get disable value from table

        System.out.println(name);
    }



    @Test(priority = 3,description = "Admin can logout")
    public void logOut(){
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.doLogOut();

        String txtLoginPageActual= driver.findElement(By.tagName("h1")).getText();
        String txtLoginPageExpected="Login";

        Assert.assertEquals(txtLoginPageActual,txtLoginPageExpected);

    }


}
