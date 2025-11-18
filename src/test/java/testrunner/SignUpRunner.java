package testrunner;

import com.github.javafaker.Faker;
import config.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.SignUpPage;
import config.SetUp;
import services.GmailServices;
import utils.Utils;

import java.io.IOException;

public class SignUpRunner extends SetUp {

    String filePath="./src/test/resources/users.json";

   @Test(priority = 1, description = "User can registration successfully",groups = "smoke")
   public void signUp() throws IOException, ParseException, ConfigurationException, javax.naming.ConfigurationException, InterruptedException {



       SignUpPage signUpPage=new SignUpPage(driver);

       Utils.scroll(driver,500);
       signUpPage.btnRegister.click();

       Faker faker=new Faker();

       String firstName=faker.name().firstName();
       String lastName=faker.name().lastName();
       String email="sqa.engineer1997+"+Utils.randomId(1000,9999)+"@gmail.com";
       String password="1234";
       String phonenumber="0175"+ Utils.randomId(1000000,9999999);
       String address=faker.address().fullAddress();

       UserModel userModel=new UserModel();
       userModel.setFirstname(firstName);
       userModel.setLastname(lastName);
       userModel.setEmail(email);
       userModel.setPassword(password);
       userModel.setPhonenumber(phonenumber);
       userModel.setAddress(address);
       signUpPage.doRegistration(userModel);


       JSONObject jsonObject=new JSONObject();
       jsonObject.put("firstName",firstName);
       jsonObject.put("lastName",lastName);
       jsonObject.put("email",email);
       jsonObject.put("password",password);
       jsonObject.put("phoneNumber",phonenumber);
       jsonObject.put("address",address);

       Utils.saveUserData(jsonObject,filePath);

       Thread.sleep(5000);

       GmailServices gmailServices=new GmailServices();
       String myEmail= gmailServices.readLatestEmail();
       System.out.println(myEmail);

   }

   //Save user Data
   @Test(priority = 2,description = "User can registration without Optional credentials")
   public void signUpWithOutOption() throws IOException, ParseException {
       SignUpPage signUpPage=new SignUpPage(driver);
//       Utils.scroll(driver,500);
//       signUpPage.btnRegister.click();

       Faker faker=new Faker();
       String firstName=faker.name().firstName();
       String email="sqa.engineer+"+Utils.randomId(1000,9999)+"@gmail.com";
       String password="1234";
       String phoneNumber="0175"+Utils.randomId(1000000,9999999);

       UserModel userModel=new UserModel();
       userModel.setFirstname(firstName);
       userModel.setEmail(email);
       userModel.setPassword(password);
       userModel.setPhonenumber(phoneNumber);

       signUpPage.doRegistration(userModel);

       JSONObject jsonObject=new JSONObject();
       jsonObject.put("firstName",firstName);
       jsonObject.put("email",email);
       jsonObject.put("password",password);
       jsonObject.put("phoneNumber",phoneNumber);

       Utils.saveUserData(jsonObject,filePath);

   }
   @AfterMethod
   public void navigateToSignUpPage(){
       driver.navigate().to("https://dailyfinance.roadtocareer.net/register");
   }


}
