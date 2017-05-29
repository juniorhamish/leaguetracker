package uk.co.dajohnston.leaguetracker.steps;

import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.dajohnston.leaguetracker.model.Association;

public class AssociationSteps {

    @Autowired
    private SpringBootSteps springBootSteps;
    private Response previousResponse;

    @Given("^I have an association with name \"([^\"]*)\"$")
    public void initialiseAssociation(String name) {
        createAssociation(name);
    }

    @Given("^I have an association with name \"([^\"]*)\" and short name \"([^\"]*)\"$")
    public void initialiseAssociation(String name, String shortName) {
        createAssociation(name, shortName);
    }

    @When("^I create association with name \"([^\"]*)\"$")
    public void createAssociation(String name) {
        Map<String, String> params = Collections.singletonMap("name", name);
        previousResponse = springBootSteps.executePost("/associations", params);
    }

    @When("^I create association with name \"([^\"]*)\" and short name \"([^\"]*)\"$")
    public void createAssociation(String name, String shortName) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("shortName", shortName);
        previousResponse = springBootSteps.executePost("/associations", params);
    }

    @Then("^association with name \"([^\"]*)\" should exist$")
    public void assertAssociationExists(String name) {
        previousResponse = springBootSteps
                .executeGet("/associations/search/findByName?name=" + name);
        previousResponse.then().statusCode(200);
        Association association = previousResponse.getBody().as(Association.class);
        assertThat(association.getName(), is(name));
    }

    @Then("^I should get a (\\d+) .* status in the response$")
    public void assertResponseHasStatus(int expectedStatus) {
        assertThat(previousResponse.statusCode(), is(expectedStatus));
    }

    @When("^I search for association by name \"([^\"]*)\"$")
    public void searchByName(String name) {
        previousResponse = springBootSteps
                .executeGet("/associations/search/findByName?name=" + name);
    }

    @And("^the response should contain json with field \"([^\"]*)\" equal to \"([^\"]*)\"$")
    public void assertResponseHasJsonField(String field, String value) {
        previousResponse.then().body(field, is(value));
    }

    @When("^I list all associations$")
    public void listAllAssociations() {
        previousResponse = springBootSteps.executeGet("/associations");
    }

    @And("^there should be (\\d+) results?$")
    public void assertResultCountInResponse(int expectedCount) {
        previousResponse.then().body("page.totalElements", is(expectedCount));
    }

    @Given("^there are no associations$")
    public void deleteAllAssociations() {
        listAllAssociations();
        List<String> associationUrls = from(previousResponse.asString())
                .get("_embedded.associations._links.self.href");
        associationUrls.forEach(associationUrl -> delete(associationUrl));
    }

    private void delete(String url) {
        springBootSteps.executeDelete(url);
    }
}
