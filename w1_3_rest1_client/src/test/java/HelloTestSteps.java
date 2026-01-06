import static org.junit.Assert.assertEquals;

//import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HelloTestSteps {
    String result;
    HelloService service = new HelloService();

    @When("I call the hello service")
    public void iCallTheHelloService() {
        result = service.hello();
    }
    @When("I call the hello text service")
    public void iCallTheHelloTextService() {
        result = service.helloText();
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

    @Then("I get the hello answer {string}")
    public void iGetTheHelloAnswer(String string) {
        assertEquals(string, result);
    }
}