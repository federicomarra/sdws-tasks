import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

public class SimpleDtuPayService {
    Client client;
    WebTarget target;
    String error;

    public SimpleDtuPayService() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/");
    }

    // POST /customer
    public String register(Customer customer) {
        Response response = target.path("customer")
                .request()
                .post(Entity.json(customer));

        if (response.getStatus() == 201) {
            return response.readEntity(String.class);
        } else {
            printError(response);
        }
        return null;
    }

    // POST /merchant
    public String register(Merchant merchant) {
        Response response = target.path("merchant")
                .request()
                .post(Entity.json(merchant));

        if (response.getStatus() == 201) {
            return response.readEntity(String.class);
        } else {
            printError(response);
        }
        return null;
    }

    // DELETE /customer/{id}
    public void deregisterCustomer(String customerId) {
        target.path("customer").path(customerId)
                .request()
                .delete();
    }

    // DELETE /merchant/{id}
    public void deregisterMerchant(String merchantId) {
        target.path("merchant").path(merchantId)
                .request()
                .delete();
    }

    // POST /payment
    public boolean pay(Integer amount, String customerId, String merchantId) {
        Payment payment = new Payment(amount, customerId, merchantId);
        try (Response response = target.path("payment")
                .request()
                .post(Entity.json(payment))) {

            return response.getStatus() == 200;
        }
    }

    // GET /payment/all
    public ArrayList<Payment> getPayments() {
        Response response = target.path("payment/all")
                .request()
                .get();

        if (response.getStatus() == 200) {
            return response.readEntity(ArrayList.class);
        } else {
            printError(response);
        }
        return null;
    }

    // Helper method to print error messages
    private void printError(Response response) {
        if (response.hasEntity()) {
            error = response.readEntity(String.class);
            System.out.println("Error " + response.getStatus() + ": " + error);
        } else {
            System.out.println("Error status: " + response.getStatus());
        }
    }
}
