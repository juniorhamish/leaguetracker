package uk.co.dajohnston.leaguetracker.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MySteps {

    @Given("^I have (\\d+) cukes in my belly$")
    public void cukesInBelly(int number) {

    }

    @When("^I wait (\\d+) hour$")
    public void wait(int hours) {

    }

    @Then("^my belly should growl$")
    public void checkBellyGrowls() {

    }
}
