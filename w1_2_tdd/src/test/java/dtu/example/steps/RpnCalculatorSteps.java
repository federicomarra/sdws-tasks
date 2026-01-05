package dtu.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/* 
 * These are the step definitions for the RpnCalculator example.
 * Please remove when you have written your own code and the example
 * is not used anymore.
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import dtu.example.RpnCalculator;

public class RpnCalculatorSteps {
    private RpnCalculator calculator;

    @Given("the RPN Calculator is initialized")
    public void theRpnCalculatorIsInitialized() {
        calculator = new RpnCalculator();
    }

    @When("I input the number {double}")
    public void iInputTheNumber(Double number) {
        calculator.input(number);
    }

    @When("I press plus")
    public void iPressPlus() {
        calculator.plus();
    }

    @Then("the result is {double}")
    public void theResultShouldBe(Double expectedResult) {
        assertEquals(expectedResult, calculator.getResult());
    }

    @When("I request the result")
    public void iRequestTheResult() {
        calculator.getResult(); /* Attempt to get the result from the calculator */
    }
}