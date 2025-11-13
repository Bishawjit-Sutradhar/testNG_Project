package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class Utils {

    public static void scroll(WebDriver driver,int px){
        JavascriptExecutor javascriptExecutor=(JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0,"+px+")");
    }

    public static int randomId(int max, int min){
       double randomId=Math.random()*(max-min)+min;
       return (int) randomId;
    }

}
