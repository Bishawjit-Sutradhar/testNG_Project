package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignUpPage {

    @FindBy(id = "firstName")
    WebElement txtFirstName;
    @FindBy(id = "lastName")
    WebElement txtLastName;
    @FindBy(id = "email")
    WebElement txtEmail;
    @FindBy(id = "password")
    WebElement txtPassword;
    @FindBy(id = "phoneNumber")
    WebElement txtPhoneNumber;
    @FindBy(id = "address")
    WebElement txtAddress;
    @FindBy(css ="[type=radio]")
    List <WebElement> txtGender;
    @FindBy(css = "[type=checkbox]")
    WebElement txtTermsCondition;
    @FindBy(css = "[type=submit]")
    public WebElement txtRegisterBtn;
    @FindBy(css = "[href='/register']")
    public WebElement btnRegister;


    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doRegistration(String firstname, String lastname, String email, String password, String phonenumber, String address){
        txtFirstName.sendKeys(firstname);
        txtLastName.sendKeys(lastname);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        txtPhoneNumber.sendKeys(phonenumber);
        txtAddress.sendKeys(address);
        txtGender.get(0).click();
        txtTermsCondition.click();
        txtRegisterBtn.click();
    }




}
