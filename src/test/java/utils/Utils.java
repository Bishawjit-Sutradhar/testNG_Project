package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Utils {

    public static void scroll(WebDriver driver,int px){
        JavascriptExecutor javascriptExecutor=(JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0,"+px+")");
    }

    public static int randomId(int max, int min){
       double randomId=Math.random()*(max-min)+min;
       return (int) randomId;
    }

    public static void saveUserData(String firstName, String lastName, String email, String password, String phoneNumber, String address) throws IOException, ParseException {
        String url="./src/test/resources/users.json";
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader(url));

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("firstName",firstName);
        jsonObject.put("lastName",lastName);
        jsonObject.put("email",email);
        jsonObject.put("password",password);
        jsonObject.put("phoneNumber",phoneNumber);
        jsonObject.put("address",address);

        jsonArray.add(jsonObject);

        FileWriter fileWriter=new FileWriter(url);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();


    }

}
