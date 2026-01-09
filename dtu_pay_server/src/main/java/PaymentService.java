import java.util.ArrayList;

public class PaymentService {
    private final ArrayList<Payment> payments = new ArrayList<>();
    private final CustomerService customerService = CustomerService.getInstance();
    private final MerchantService merchantService = MerchantService.getInstance();

    public boolean pay(Integer amount, String customerId, String merchantId) {
        Customer customer = customerService.getCustomerById(customerId);
        Merchant merchant = merchantService.getMerchantById(merchantId);
        if (customer == null) {
            throw new RuntimeException("customer with id \"" + customerId + "\" is unknown");
        }
        if (merchant == null) {
            throw new RuntimeException("merchant with id \"" + merchantId + "\" is unknown");
        }

        Payment payment = new Payment(amount, customerId, customer.getName(), merchantId, merchant.getName());
        payments.add(payment);
        return true;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }
}
