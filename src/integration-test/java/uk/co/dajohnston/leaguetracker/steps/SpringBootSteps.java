package uk.co.dajohnston.leaguetracker.steps;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import uk.co.dajohnston.leaguetracker.Application;

@ContextConfiguration(classes = Application.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootSteps {

    @LocalServerPort
    int port;

    @Before
    public void setPort() {
        RestAssured.port = port;
    }

    public Response executePost(String url, Map<String, String> params) {
        String body = new Gson().toJson(params);
        return given().contentType("application/json").body(body).post(url).thenReturn();
    }

    public Response executeGet(String url) {
        return given().get(url).thenReturn();
    }
}
