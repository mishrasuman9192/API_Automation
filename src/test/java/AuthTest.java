import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {


    String BASE_URI = "https://httpbin.org/basic-auth/user/password";

    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(BASE_URI)
                                                .build();


    @Test
    public void testWithoutBaseAuth(){

        Response response = given().spec(requestSpecification).when().get().then().extract()
                            .response();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(),200,"Statuscode not matching");
    }

    @Test
    public void testWithBaseAuth(){

        Response response = given().spec(requestSpecification).auth().basic("user","password").when().get().then().extract()
                .response();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(),200,"Statuscode not matching");
    }
}
