package apiauto;
import com.sun.security.jgss.AuthorizationDataEntry;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import netscape.javascript.JSObject;
import org.apache.http.auth.AUTH;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class TestApi {
    @Test
    public void testGetListUsers(){
        RestAssured.given()
                .header("Authorization","Bearer 46395dfc187905e8727b9c4698a8dd04a30370ba002f50a9e0938a3afaf56e14")
                .when()
                .get("https://gorest.co.in/public/v2/users")
                .then().log().all()
                .assertThat().statusCode(200);

    }

    @Test  //Negative test with wrong url API
    public void negativeTestGetListUsers(){
        RestAssured.given()
                .header("Authorization","Bearer 46395dfc187905e8727b9c4698a8dd04a30370ba002f50a9e0938a3afaf56e14")
                .when()
                .get("https://gorest.co.in/public/v2")
                .then().log().all()
                .assertThat().statusCode(404);

    }

    @Test
    public void testPostUser(){

        String valueName = "Ridwan";
        String valueGender = "Male";
        String valueEmail = "ridwan_26@gmail.com";
        String valueStatus = "active";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", valueName);
        bodyObj.put("gender", valueGender);
        bodyObj.put("email", valueEmail);
        bodyObj.put("status", valueStatus);

        RestAssured.given()
                .header("Authorization","Bearer 46395dfc187905e8727b9c4698a8dd04a30370ba002f50a9e0938a3afaf56e14")
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .body(bodyObj.toString())
                .when()
                .post("https://gorest.co.in/public/v2/users").then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("name", Matchers.equalTo(valueName));

    }

    @Test //Negative test with no auth
    public void negativeTestPostUser(){

        String valueName = "Ridwan";
        String valueGender = "Male";
        String valueEmail = "ridwan_26@gmail.com";
        String valueStatus = "active";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", valueName);
        bodyObj.put("gender", valueGender);
        bodyObj.put("email", valueEmail);
        bodyObj.put("status", valueStatus);

        RestAssured.given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .body(bodyObj.toString())
                .when()
                .post("https://gorest.co.in/public/v2/users").then().log().all()
                .assertThat().statusCode(401);

    }

    @Test
    public void getSpecificUser(){

        RestAssured.given()
                .header("Authorization","Bearer 46395dfc187905e8727b9c4698a8dd04a30370ba002f50a9e0938a3afaf56e14")
                .when()
                .get("https://gorest.co.in/public/v2/users/5969773")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test // Negative test with user id not registered
    public void negativeGetSpecificUser(){

        RestAssured.given()
                .header("Authorization","Bearer 46395dfc187905e8727b9c4698a8dd04a30370ba002f50a9e0938a3afaf56e14")
                .when()
                .get("https://gorest.co.in/public/v2/users/5971179")
                .then().log().all()
                .assertThat().statusCode(404);
    }

    @Test
    public void testPutUser(){

        RestAssured.baseURI = "https://gorest.co.in/public/v2/users";

        int userId = 5971178;
        String newName = "Roy";

        String valueName = given().header("Authorization","Bearer 46395dfc187905e8727b9c4698a8dd04a30370ba002f50a9e0938a3afaf56e14").when().get("https://gorest.co.in/public/v2/users/"+userId).getBody().jsonPath().get("name");
        String valueEmail = given().when().get("https://gorest.co.in/public/v2/users/"+userId).getBody().jsonPath().get("email");
        String valueGender = given().when().get("https://gorest.co.in/public/v2/users/"+userId).getBody().jsonPath().get("gender");
        String valueStatus = given().when().get("https://gorest.co.in/public/v2/users/"+userId).getBody().jsonPath().get("status");
        System.out.println("name before = "+valueName);

        HashMap<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("id", userId);
        bodyMap.put("name", newName);
        bodyMap.put("email", valueEmail);
        bodyMap.put("gender", valueGender);
        bodyMap.put("status", valueStatus);
        JSONObject jsonObject = new JSONObject(bodyMap);

        given().log().all()
                .header("Authorization","Bearer 46395dfc187905e8727b9c4698a8dd04a30370ba002f50a9e0938a3afaf56e14")
                .header("Content-Type","application/json")
                .body(jsonObject.toString())
                .put("https://gorest.co.in/public/v2/users/"+userId)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("name", Matchers.equalTo(newName));
    }

    @Test // Negative test edit user with empty data
    public void negativeTestPutUser(){

        RestAssured.baseURI = "https://gorest.co.in/public/v2/users";

        int userId = 5969773;
        String newName = "";

        String valueName = given().header("Authorization","Bearer 46395dfc187905e8727b9c4698a8dd04a30370ba002f50a9e0938a3afaf56e14").when().get("https://gorest.co.in/public/v2/users/"+userId).getBody().jsonPath().get("name");
        String valueEmail = given().when().get("https://gorest.co.in/public/v2/users/"+userId).getBody().jsonPath().get("email");
        String valueGender = given().when().get("https://gorest.co.in/public/v2/users/"+userId).getBody().jsonPath().get("gender");
        String valueStatus = given().when().get("https://gorest.co.in/public/v2/users/"+userId).getBody().jsonPath().get("status");
        System.out.println("name before = "+valueName);

        HashMap<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("id", userId);
        bodyMap.put("name", newName);
        bodyMap.put("email", valueEmail);
        bodyMap.put("gender", valueGender);
        bodyMap.put("status", valueStatus);
        JSONObject jsonObject = new JSONObject(bodyMap);

        given().log().all()
                .header("Authorization","Bearer 46395dfc187905e8727b9c4698a8dd04a30370ba002f50a9e0938a3afaf56e14")
                .header("Content-Type","application/json")
                .body(jsonObject.toString())
                .put("https://gorest.co.in/public/v2/users/"+userId)
                .then().log().all()
                .assertThat().statusCode(422);
                //.assertThat().body("name", Matchers.equalTo(newName));
    }

    @Test
    public void testDeleteUser(){
        RestAssured.baseURI = "https://gorest.co.in/public/v2/users/";
        int idUser = 5971178;

        given().log().all()
                .header("Authorization","Bearer 46395dfc187905e8727b9c4698a8dd04a30370ba002f50a9e0938a3afaf56e14")
                .when().delete("https://gorest.co.in/public/v2/users/"+idUser)
                .then().log().all()
                .assertThat().statusCode(204);

    }

    @Test // Negative test validate data alredy deleted
    public void negativeTestDeleteUser(){
        RestAssured.baseURI = "https://gorest.co.in/public/v2/users/";
        int idUser = 5971178;

        given().log().all()
                .header("Authorization","Bearer 46395dfc187905e8727b9c4698a8dd04a30370ba002f50a9e0938a3afaf56e14")
                .when().delete("https://gorest.co.in/public/v2/users/"+idUser)
                .then().log().all()
                .assertThat().statusCode(404);

    }

}
