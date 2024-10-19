import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Feature("Test MealPlan")
public class mealPlanTest extends BaseTestSpooner{
    String apiKey = "86dd8e817bff44ed94dd8f29874d8188";
    String username = "afriandarmawan0";
    String hash = "5b58321d340e95e4551d7071b0c25e72ad6f4917";

    @Test(description = "Test Meal Planner Generate")
    public void testMealPlannerGenerate(){
        given().queryParam("apiKey",apiKey)
                .log().ifValidationFails()
                .when()
                .get("/mealplanner/generate")
                .then()
                .statusCode(200)
                .body("week.monday.meals.size()", equalTo(3))
                .extract().response();
    }

    @Test(description = "Test Meal Planner Generate With Parameter")
    public void testMealPlannerParameterGenerate(){
        given().queryParam("apiKey",apiKey)
                .queryParam("targetCalories",1000)
                .queryParam("diet", "vegetarian")
                .queryParam("timeframe","day")
                .log().ifValidationFails()
                .when()
                .get("/mealplanner/generate")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(description = "add Mealplan")
    public void testAddMealPlan(){
        String requestBody="{\n" +
                "    \"date\": 1589500800,\n" +
                "    \"slot\": 1,\n" +
                "    \"position\": 0,\n" +
                "    \"type\": \"INGREDIENTS\",\n" +
                "    \"value\": {\n" +
                "        \"ingredients\": [\n" +
                "            {\n" +
                "                \"name\": \"1 banana\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("apiKey",apiKey)
                .queryParam("hash",hash)
                .body(requestBody)
                .when()
                .post("/mealplanner/{username}/items", username)
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(description = "Test Image Classification")
    public void testImageClassification(){
        given().queryParam("apiKey",apiKey)
                .queryParam("imageUrl","https://img.freepik.com/premium-photo/ivory-macaron-pastry-hd-8k-photographic-image_973183-6192.jpg")
                .log().ifValidationFails()
                .when()
                .get("/food/images/classify")
                .then()
                .statusCode(200)
                .extract().response();
    }
}
