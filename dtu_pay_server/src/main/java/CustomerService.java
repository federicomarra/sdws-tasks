import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerService {
    private Map<String, Customer> customers = new ConcurrentHashMap<>();

    public String registerCustomer(String name) {
        String id = UUID.randomUUID().toString();
        Customer student = new Customer(id, name);
        customers.put(id, student);
        return id;
    }

    public boolean deleteCustomer(String id) {
        return customers.remove(id) != null;
    }

    public Customer getCustomerById(String id) {
        return customers.get(id);
    }
}
