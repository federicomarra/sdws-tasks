import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals; // JUnit 5

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.SNIPPET_TYPE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectDirectories;
import org.junit.platform.suite.api.Suite;
import org.junit.runners.model.InvalidTestClassError;

@Suite()
@IncludeEngines("cucumber")
@SelectDirectories("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = SNIPPET_TYPE_PROPERTY_NAME, value = "camelcase")

public class StringCalculatorSteps {
    StringCalculator calculator;
    String[] input;
    int result;
    int invalidResult = -1234321;

    @Given("the input is empty")
    public void theInputIsEmpty() {
        calculator = new StringCalculator();
        input = new String[] {""};
    }

    @Given("the input is {string}")
    public void theInputIs(String args) {
        calculator = new StringCalculator();
        input = args.replace("\\n", "\n").split("[//,;\n]");
    }

    @When("I calculate the addition")
    public void iCalculateTheAddition() {
        if (!isValidInput(input)) {
            result = invalidResult;
        } else {
            result = calculator.sum(input);
        }
    }

    @When("I calculate the product")
    public void iCalculateTheProduct() {
        if (!isValidInput(input)) {
            result = invalidResult;
        } else {
            result = calculator.mult(input);
        }
    }

    @Then("the result should be {int}")
    public void theResultShouldBe(int expected) {
        assertEquals(expected, result);
    }
    
    @Then("an error should be raised indicating invalid input")
    public void anErrorShouldBeRaised() {
        assertEquals(invalidResult, result);
    }

    private boolean isValidInput(String[] input) {
        boolean valid = input != null && input.length > 0;
        int invalidError = 0;
        String invalidInput = "";
        // Validates each input string is a parsable integer
        for (String s : input) {
            if (s.equals("")) continue;
            valid = valid && s != null && !s.trim().isEmpty();
            if (valid || invalidError > 0) {
                try {
                    int parsed = Integer.parseInt(s.trim());
                    if (parsed < 0) {
                        valid = false;
                        invalidError = 1;
                        invalidInput = s;
                    }
                } catch (NumberFormatException e) {
                    valid = false;
                    invalidError = 2;
                    invalidInput = s;
                }
            }
                
            
        }
        switch (invalidError) {
            case 1: System.out.println("ERROR Invalid input, negative number: " + invalidInput); break;
            case 2: System.out.println("ERROR Invalid input, not parsable string: " + invalidInput); break;
        }
        
        
        return valid;
    }

}