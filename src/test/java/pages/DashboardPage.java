package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {

    @FindBy(tagName = "h2")
    public WebElement textAdmin;
    @FindBy(css = "[type=button]")
    public List <WebElement> btnIcon;
    @FindBy(css = "li")
    List<WebElement>comboBox;

    public DashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    public void doLogOut(){
       btnIcon.get(0).click();
       comboBox.get(1).click();
    }

}
