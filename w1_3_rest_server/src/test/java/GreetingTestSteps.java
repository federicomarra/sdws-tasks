import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class GreetingTestSteps {
    String result;
    GreetingResource service = new GreetingResource();
    @When("I call the hello text service")
    public void iCallTheHelloTextService() {
        result = service.hello();
    }

    @When("I call the hello html service")
    public void iCallTheHelloHTMLService() {
        result = service.helloHtml();
    }

    @When("I call the hello json service")
    public void iCallTheHelloJSONService() {
        result = service.helloJson();
    }

    @When("I call the hello xml service")
    public void iCallTheHelloXMLService() {
        result = service.helloXml();
    }
    
    @Then("I get the answer {string}")
    public void iGetTheAnswer(String string) {
        assertEquals(string, result);
    }
}