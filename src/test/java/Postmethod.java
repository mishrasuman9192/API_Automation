import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Postmethod {
    String BASE_URI = "https://reqres.in/api/users";
    @Test
    public void testPostRequestUsingString() {


        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        Response response = RestAssured.given().header("Content-Type", "application/json").baseUri(BASE_URI).body(body).log().all().when().post()
                .then().extract().response();

        System.out.println("status code: " + response.statusCode());
    }

        @Test
        public void testPostRequestUsingJsonFile () {
            String BASE_URI = "https://reqres.in/api/users";

            File file = new File("src/main/resources/test.json");

            Response response = RestAssured.given().body(file).header("Content-Type", "application/json").baseUri(BASE_URI).log().all().when().post()
                    .then().log().all().extract().response();

            System.out.println("status code: " + response.statusCode());
        }
        public void testPostRequestUsingMap () {
            String BASE_URI = "https://reqres.in/api/users";

            Map<String, String> map = new HashMap<>();
            map.put("name", "morpheus");
            map.put("job", "leader");

            Response response = RestAssured.given().header("Content-Type", "application/json").body(map).baseUri(BASE_URI).log().all().when().post()
                    .then().log().all().extract().response();

            System.out.println("status code: " + response.statusCode());
        }
    }
