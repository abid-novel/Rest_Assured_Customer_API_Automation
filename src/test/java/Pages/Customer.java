package Pages;

import Utils.Utils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Customer {
    Properties properties = new Properties();
    FileInputStream file = new FileInputStream("./src/test/resources/config.properties");

    public Customer() throws FileNotFoundException {
    }

    public void callingLoginApi() throws ConfigurationException, IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("baseURL");

        Response res =
                given()
                        .contentType("application/json")
                        .body("{\n" +
                                "\"username\":\"salman\",\n" +
                                "\"password\":\"salman1234\"\n" +
                                "}")
                .when()
                        .post("/customer/api/v1/login")
                .then().assertThat().statusCode(200).extract().response();

        JsonPath resObj = res.jsonPath();
        String token = resObj.get("token");
        Utils.setEnvironment("token", token);
    }

    public void callingCustomerListApi() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("baseURL");

        Response res =
                given()
                        .contentType("application/json").header("Authorization", properties.getProperty("token"))
                .when()
                        .get("/customer/api/v1/list")
                .then().statusCode(200).extract().response();

        JsonPath resObj = res.jsonPath();
        int id = resObj.get("Customers[0].id");
        Assert.assertEquals(101, id);
    }

    public void callingSearchCustomer() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("baseURL");

        Response res =
                given()
                        .contentType("application/json").header("Authorization", properties.getProperty("token"))
                .when()
                        .get("/customer/api/v1/get/101")
                .then().statusCode(200).extract().response();
        System.out.println(res.asString());

        JsonPath resObj = res.jsonPath();
        String name = resObj.get("name");
        Assert.assertEquals("Mr. Kamal", name);
    }

    Integer ID;
    String name;
    String email;
    String address;
    String phone_number;

    public void generateCustomer() throws IOException, ConfigurationException {
        properties.load(file);
        RestAssured.baseURI = "https://randomuser.me/api";

        Response res =
                given()
                        .contentType("application/json")
                .when()
                        .get()
                .then().statusCode(200).extract().response();

        JsonPath resObj = res.jsonPath();
        ID = (int) Math.floor(Math.random() * (999999-100000) +1);
        name = resObj.get("results[0].name.first");
        email = resObj.get("results[0].email");
        address = resObj.get("results[0].location.city");
        phone_number = resObj.get("results[0].phone");

        Utils.setEnvironment("id", ID.toString());
        Utils.setEnvironment("name", name);
        Utils.setEnvironment("email", email);
        Utils.setEnvironment("address", address);
        Utils.setEnvironment("phone_number", phone_number);
    }

    public void callingCustomerCreateAPi() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("baseURL");

        Response res =
                given()
                        .contentType("application/json").header("Authorization", properties.getProperty("token"))
                        .body("" +
                                "{\"id\":" + properties.getProperty("id") + ",\n" +
                                "    \"name\":\"" + properties.getProperty("name") + "\", \n" +
                                "    \"email\":\"" + properties.getProperty("email") + "\",\n" +
                                "    \"address\":\""+properties.getProperty("address")+"\",\n" +
                                "    \"phone_number\":\"" + properties.getProperty("phone_number") + "\"}")
                .when()
                        .post("/customer/api/v1/create")
                .then().statusCode(201).extract().response();

    }

    public void callingUpdateCustomerApi() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("baseURL");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", properties.getProperty("token"))
                        .body("" +
                                "{\"id\":" + properties.getProperty("id") + ",\n" +
                                "    \"name\":\"" + properties.getProperty("name") + "\", \n" +
                                "    \"email\":\"" + properties.getProperty("email") + "\",\n" +
                                "    \"address\": \"BD\",\n" +
                                "    \"phone_number\":\"" + properties.getProperty("phone_number") + "\"}")
                .when()
                        .put("/customer/api/v1/update/"+properties.getProperty("id"))
                .then().statusCode(200).extract().response();

        JsonPath resObj = res.jsonPath();
        String address = resObj.get("Customers.address");
        Assert.assertEquals("BD", address);
        System.out.println(res.asString());
    }

    public void callingDeleteCustomerApi() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("baseURL");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", properties.getProperty("token"))
                .when()
                        .delete("/customer/api/v1/delete/"+properties.getProperty("id"))
                        .then().statusCode(200).extract().response();

    }


}
