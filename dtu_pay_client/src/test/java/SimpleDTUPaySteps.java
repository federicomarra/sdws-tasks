import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleDTUPaySteps {
    private Customer customer;
    private Merchant merchant;
    private String customerId, merchantId;
    private final SimpleDtuPayService dtuPay = new SimpleDtuPayService();
    private boolean successful = false;
    private ArrayList<Payment> payments;

    // Successful payment scenario
    @Given("a customer with name {string}")
    public void aCustomerWithName(String name) {
        customer = new Customer(name);
    }

    @Given("a merchant with name {string}")
    public void aMerchantWithName(String name) {
        merchant = new Merchant(name);
    }

    @When("the merchant initiates a payment for {int} kr by the customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(Integer amount) {
        successful = dtuPay.pay(amount,customerId,merchantId);}

    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
        assertTrue(successful);
    }

    @Given("a merchant with name {string}, who is registered with Simple DTU Pay")
    public void aMerchantWithNameWhoIsRegisteredWithSimpleDTUPay(String name) {
        merchant = new Merchant(name);
        merchantId = dtuPay.register(merchant);
    }

    @When("the merchant initiates a payment for {string} kr using customer id {string}")
    public void theMerchantInitiatesAPaymentForKrUsingCustomerId(String amount, String invalidCustomerId) {
        successful = dtuPay.pay(Integer.parseInt(amount), invalidCustomerId, merchantId);
    }

    @Then("the payment is not successful")
    public void thePaymentIsNotSuccessful() {
        assertFalse(successful);
    }

    @And("an error message is returned saying {string}")
    public void anErrorMessageIsReturnedSayingNonExistentId(String arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("a customer with name {string}, who is registered with Simple DTU Pay")
    public void aCustomerWithNameWhoIsRegisteredWithSimpleDTUPay(String name) {
        customer = new Customer(name);
        customerId = dtuPay.register(customer);
    }

    @When("the customer initiates a payment for {string} kr using merchant id {string}")
    public void theCustomerInitiatesAPaymentForKrUsingMerchantId(String amount, String invalidMerchantId) {
        successful = dtuPay.pay(Integer.parseInt(amount), customerId, invalidMerchantId);
    }

    @When("the customer registers with Simple DTU Pay")
    public void theCustomerRegistersWithSimpleDTUPay() {
        customerId = dtuPay.register(customer);
    }

    @Then("the registration is successful")
    public void theRegistrationIsSuccessful() {
        assertTrue(customerId != null && !customerId.isEmpty() && merchantId != null && !merchantId.isEmpty());
    }

    @When("the merchant registers with Simple DTU Pay")
    public void theMerchantRegistersWithSimpleDTUPay() {
        merchantId = dtuPay.register(merchant);
    }

    @And("a successful payment of {string} kr from the customer to the merchant")
    public void aSuccessfulPaymentOfKrFromTheCustomerToTheMerchant(String amount) {
        successful = dtuPay.pay(Integer.parseInt(amount), customerId, merchantId);
        assertTrue(successful);
    }

    @When("the manager asks for a list of payments")
    public void theManagerAsksForAListOfPayments() {
        payments = dtuPay.getPayments();
    }

    @Then("the list contains a payments where customer {string} paid {string} kr to merchant {string}")
    public void theListContainsAPaymentsWhereCustomerPaidKrToMerchant(String customerName, String amountStr, String merchantName) {
        int expectedAmount = Integer.parseInt(amountStr);

        boolean found = false;
        for (Payment payment : payments) {
            if (payment.getCustomerName().equals(customerName) &&
                    payment.getAmount() == expectedAmount &&
                    payment.getMerchantName().equals(merchantName)) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @After
    public void tearDown() {
        if (customerId != null) {
            dtuPay.deregisterCustomer(customerId);
        }
        if (merchantId != null) {
            dtuPay.deregisterMerchant(merchantId);
        }
    }
}