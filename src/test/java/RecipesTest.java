import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Feature("Test Recipes")
public class RecipesTest extends BaseTestSpooner{
    String apiKey = "86dd8e817bff44ed94dd8f29874d8188";
    int id = 2;

    @Test(description = "Test Search Recipes")
    public void testSearchRecipes(){
        given().queryParam("apiKey",apiKey)
                .queryParam("query","pasta")
                .queryParam("cuisine", "italian")
                .queryParam("diet","vegetarian")
                .log().ifValidationFails()
                .when()
                .get("/recipes/complexSearch")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(description = "Test Search Recipes by Nutrients")
    public void testSearchRecipesByNutrients(){
        given().queryParam("apiKey",apiKey)
                .queryParam("maxCarbs",100)
                .queryParam("maxCalories", 450)
                .queryParam("maxCholesterol",100)
                .log().ifValidationFails()
                .when()
                .get("/recipes/findByNutrients")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(description = "Test Search Recipes by Ingredients")
    public void testSearchRecipesByIngredients(){
        given().queryParam("apiKey",apiKey)
                .queryParam("ingredients","Milk, Eggs, Other Dairy")
                .queryParam("number", 5)
                .queryParam("ranking",1)
                .log().ifValidationFails()
                .when()
                .get("/recipes/findByIngredients")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(description = "Test Get Recipe Information")
    public void testGetRecipeInformation(){
        given().queryParam("apiKey",apiKey)
                .queryParam("includeNutrition",false)
                .queryParam("addWinePairing", false)
                .queryParam("addTasteData",false)
                .log().ifValidationFails()
                .when()
                .get("/recipes/{id}/information",id)
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(description = "Test Get Recipe Information Bulk")
    public void testGetRecipeInformationBulk(){
        given().queryParam("apiKey",apiKey)
                .queryParam("ids",1,123,1123)
                .queryParam("includeNutrition", false)
                .log().ifValidationFails()
                .when()
                .get("/recipes/informationBulk")
                .then()
                .statusCode(200)
                .extract().response();
    }
}
