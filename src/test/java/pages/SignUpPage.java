package pages;

import config.UserModel;
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

    public void doRegistration(UserModel userModel){
        txtFirstName.sendKeys(userModel.getFirstname());
        txtLastName.sendKeys(userModel.getLastname()!=null?userModel.getLastname():" ");
        txtEmail.sendKeys(userModel.getEmail());
        txtPassword.sendKeys(userModel.getPassword());
        txtPhoneNumber.sendKeys(userModel.getPhonenumber());
        txtAddress.sendKeys(userModel.getAddress()!=null?userModel.getAddress():" ");
        txtGender.get(0).click();
        txtTermsCondition.click();
        txtRegisterBtn.click();
    }




}
