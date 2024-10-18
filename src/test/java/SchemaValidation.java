import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SchemaValidation {

    @Test
    public void validate_Schema(){

        String BASE_URI = "https://reqres.in/api";
        Response response = given().baseUri(BASE_URI).basePath("/users").queryParams("page", 2).when().get()
                .then().extract().response();
        //when we need to validate something from response we use then().body() method
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath
                            ("schemas/userSchema.json"));

    }
}
