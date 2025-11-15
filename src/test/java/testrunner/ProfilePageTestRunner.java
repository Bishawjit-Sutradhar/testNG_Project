package testrunner;

import config.SetUp;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class ProfilePageTestRunner extends SetUp {

    @BeforeTest
    public void setAuth() throws IOException, ParseException, InterruptedException {
        Utils.setAuth(driver);
    }
    @Test
    public void visitProfilePage() throws InterruptedException {
        driver.navigate().to("https://dailyfinance.roadtocareer.net/user/58c745b3-72dc-4245-aa09-c3b599c8625e");

    }
}
