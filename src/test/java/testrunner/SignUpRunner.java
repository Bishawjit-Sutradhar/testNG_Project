package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.SignUpPage;
import config.SetUp;
import utils.Utils;

import java.io.IOException;

public class SignUpRunner extends SetUp {

   @Test(priority = 1, description = "User can registration successfully")
   public void signUp() throws IOException, ParseException {
       SignUpPage signUpPage=new SignUpPage(driver);

       Utils.scroll(driver,500);
       signUpPage.btnRegister.click();

       Faker faker=new Faker();

       String firstName=faker.name().firstName();
       String lastName=faker.name().lastName();
       String email="sqa.engineer"+ Utils.randomId(1000,9999)+"@gmail.com";
       String password="1234";
       String phonenumber="0175"+ Utils.randomId(1000000,9999999);
       String address=faker.address().fullAddress();

       signUpPage.doRegistration(firstName,lastName,email,password,phonenumber,address);

       Utils.saveUserData(firstName,lastName,email,password,phonenumber,address);


   }

}
