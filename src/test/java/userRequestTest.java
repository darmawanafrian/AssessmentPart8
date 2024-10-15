import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


@Feature("user Module")
public class userRequestTest extends BaseUserTest{
    @Test(priority = 1)
    public void createUserTest(){
        String requestBody = "{\n" +
                "  \"id\": 111,\n" +
                "  \"username\": \"afriandarmawan\",\n" +
                "  \"firstName\": \"afrian\",\n" +
                "  \"lastName\": \"darmawan\",\n" +
                "  \"email\": \"aa@gmail.com\",\n" +
                "  \"password\": \"123456789\",\n" +
                "  \"phone\": \"08111111111\",\n" +
                "  \"userStatus\": 1\n" +
                "}";
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .log().ifValidationFails()
                .when()
                .post()
                .then()
                .statusCode(200);
    }

@Test(priority = 2)
    public void userLoginTest(){
        given().queryParam("afriandarmawan")
                .queryParam("123456789")
                .when()
                .get("/login")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(priority = 3)
    public void findUserByNameTest(){
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/afriandarmawan")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(priority = 4)
    public void updateUserTest(){
        String requestBody = "{\n" +
                "  \"id\": 111,\n" +
                "  \"username\": \"afriandarmawan\",\n" +
                "  \"firstName\": \"afrian\",\n" +
                "  \"lastName\": \"darmawan\",\n" +
                "  \"email\": \"bb@gmail.com\",\n" +
                "  \"password\": \"123456789\",\n" +
                "  \"phone\": \"08111111111\",\n" +
                "  \"userStatus\": 0\n" +
                "}";
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .log().ifValidationFails()
                .when()
                .put("/afriandarmawan")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(priority = 5)
    public void userLogoutTest(){
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/logout")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(priority = 6)
    public void deleteUserTest(){
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/afriandarmawan")
                .then()
                .statusCode(200)
                .extract().response();
    }
}
