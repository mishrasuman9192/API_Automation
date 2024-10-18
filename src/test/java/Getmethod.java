import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Getmethod {

    @Test
    public void testGetRequest() {

        String BASE_URI = "https://reqres.in/api";

        Response response = RestAssured.given().baseUri(BASE_URI).basePath("/users").queryParams("page", 2).when().log().all().get()
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(), 200, "response is not matching");

        JsonPath jsonPath = response.jsonPath();

        int total = jsonPath.getInt("total");

        System.out.println("total: "+total);


        String supportUlr = jsonPath.getString("support.url");
        List<Object> list = jsonPath.getList("data");
        int size = jsonPath.getInt("hv");
        //System.out.println(list.size());
        List<String> fname = new ArrayList<>();
        List<String> lname= new ArrayList<>();
        for(int i=0; i<list.size();i++){
            String first = jsonPath.getString("data["+i+"].first_name");
            String last = jsonPath.getString("data["+i+"].last_name");
            fname.add(first);
            lname.add(last);
        }
        Map<List<String>, List<String>> name = new HashMap<>();
        name.put(fname,lname);
        System.out.println(name);
        System.out.println("URL: "+supportUlr);



}
}
