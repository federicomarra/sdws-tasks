import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.ws.rs.WebApplicationException;

import static org.junit.Assert.assertEquals;

public class PersonTestSteps {
    Person result;
    String error;
    Person newPerson;
    PersonService service = new PersonService();
    
    @Given("I have a person called {string} from {string}")
    public void iHaveAPerson(String name, String address) {
        newPerson = new Person(address, name);
    }

    @When("I call the GET person service")
    public void iCallTheGETPersonService() {
        result = service.getPersonRequest();
    } 

    @When("I call the PUT person service")
    public void iCallThePUTPersonService() {
        try {
            result = service.putPersonRequest(newPerson);
            error = service.error;
        } catch (WebApplicationException e) {
            error = e.getResponse().readEntity(String.class);
            throw new WebApplicationException(error, e);
        }
    }

    @Then("I get the person answer {string}")
    public void iGetThePersonAnswer(String expected) {
        assertEquals(expected, result.toString());
    }
    
    @Then("I get the person error {string}")
    public void iGetTheError(String error) {
        assertEquals(error, this.error);
    }
}