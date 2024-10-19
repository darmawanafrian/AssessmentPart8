import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Feature("Connect User")
public class ConnectUserTest extends BaseTestSpooner{
    @Test(description = "Get Username and Hash")
    public void testConnectUser(){
        String apiKey = "86dd8e817bff44ed94dd8f29874d8188";
        String requestBody="{\n" +
                "    \"username\": \"afriandarmawan\",\n" +
                "    \"firstName\": \"afrian\",\n" +
                "    \"lastName\": \"darmawan\",\n" +
                "    \"email\": \"darmawanafrian@gmail.com\"\n" +
                "}";
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("apiKey",apiKey)
                .body(requestBody)
                .post("users/connect")
                .then()
                .statusCode(200)
                .extract().response();
    }
}
