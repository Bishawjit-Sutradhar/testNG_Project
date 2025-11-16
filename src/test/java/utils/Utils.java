package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;


public class Utils {

    public static void scroll(WebDriver driver,int px){
        JavascriptExecutor javascriptExecutor=(JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0,"+px+")");
    }

    public static int randomId(int max, int min){
       double randomId=Math.random()*(max-min)+min;
       return (int) randomId;
    }

    public static void saveUserData(JSONObject jsonObject, String filePath) throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader(filePath));

        jsonArray.add(jsonObject);

        FileWriter fileWriter=new FileWriter(filePath);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();

    }

    //Get Login authToken and authTokenData
    public static void getToken(WebDriver driver) throws IOException {
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) driver;
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until((ExpectedCondition<Boolean>) wd->javascriptExecutor.executeScript("return window.localStorage.getItem('authToken')")!=null);

        //get the authToken form the local Storage
        String authToken= javascriptExecutor.executeScript("return window.localStorage.getItem('authToken');").toString();
        String authTokenData=javascriptExecutor.executeScript("return window.localStorage.getItem('authTokenData');").toString();

        System.out.println("Auth Token Retrieved"+authToken);
        System.out.println("Auth Token Retrieved"+authTokenData);

        //Save the auth token to a localStorage.json file
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("authToken",authToken);
        jsonObject.put("authTokenData",authTokenData);

        FileWriter fileWriter=new FileWriter("./src/test/resources/localStorage.json");
        fileWriter.write(jsonObject.toJSONString());
        fileWriter.flush();
        fileWriter.close();

    }
    //Set Login authToken and authTokenData
    public static void setAuth(WebDriver driver) throws IOException, ParseException, InterruptedException {

        JSONParser jsonParser=new JSONParser();
        JSONObject jsonObject= (JSONObject) jsonParser.parse(new FileReader("./src/test/resources/localStorage.json"));
        String authToken=jsonObject.get("authToken").toString();
        String authTokenData=jsonObject.get("authTokenData").toString();

        System.out.println(authToken);

        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.localStorage.setItem('authToken',arguments[0]);",authToken);
        javascriptExecutor.executeScript("window.localStorage.setItem('authTokenData',arguments[0]);",authTokenData);
        Thread.sleep(2000);

    }
    //Gmail API Integration
    public static void setEnv(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config=new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key,value);
        config.save();

    }
}
