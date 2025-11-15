package testrunner;

import config.LoginDataSet;
import config.SetUp;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTestRunnerCSV extends SetUp {

    @Test(dataProvider = "LoginDataSet",dataProviderClass = LoginDataSet.class)
    public void loginWithCSVData(String email,String password){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin(email,password);

        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.doLogOut();
    }

}
