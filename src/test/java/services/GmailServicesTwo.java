package services;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GmailServicesTwo {
    Properties prop;
    public GmailServicesTwo() throws IOException {
        prop=new Properties();
        FileInputStream fs=new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
    }

    public String getGmailList() throws IOException {
        RestAssured.baseURI="https://gmail.googleapis.com";
        Response res=given().contentType("application/json")
                .header("Authorization","Bearer "+prop.getProperty("GMAIL_TOKEN"))
                .when().get("gmail/v1/users/me/messages");

        JsonPath jsonObj=res.jsonPath();
        String messageId= jsonObj.get("messages[0].id".toString());
        return messageId;

    }

    public String readLatestEmail() throws IOException {
        GmailServicesTwo gmailServicesTwo=new GmailServicesTwo();
        String messageId = gmailServicesTwo.getGmailList();
        RestAssured.baseURI="https://gmail.googleapis.com";
        Response res=given().contentType("application/json")
                .header("Authorization","Bearer "+prop.getProperty("GMAIL_TOKEN"))
                .when().get("gmail/v1/users/me/messages/"+messageId);

        JsonPath jsonObj=res.jsonPath();
        String myEmail= jsonObj.get("snippet");
        return myEmail;

    }

//    public static void main(String[] args) throws IOException {
//        GmailServicesTwo gmailServicesTwo=new GmailServicesTwo();
//        String mail=gmailServicesTwo.readLatestEmail();
//        System.out.println(mail);
//    }


}
