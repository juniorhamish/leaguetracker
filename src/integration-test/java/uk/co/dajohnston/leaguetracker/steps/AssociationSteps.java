package uk.co.dajohnston.leaguetracker.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class AssociationSteps {

    @Autowired
    private SpringBootSteps springBootSteps;

    @When("^I create association with name \"([^\"]*)\"$")
    public void createAssociation(String name) {
        Map<String, String> params = Collections.singletonMap("name", name);
        Response response = springBootSteps.executePost("/associations", params);
        response.then().statusCode(201);
    }

    @Then("^association with name \"([^\"]*)\" should exist$")
    public void assertAssociationExists(String name) {
        Response response = springBootSteps.executeGet("/associations/search/findByName?name=" + name);
        response.then().statusCode(200);
    }
}
