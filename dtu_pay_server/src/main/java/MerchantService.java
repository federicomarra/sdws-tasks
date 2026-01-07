import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MerchantService {
    private Map<String, Merchant> merchants = new ConcurrentHashMap<>();

    public String registerMerchant(String name) {
        String id = UUID.randomUUID().toString();
        Merchant student = new Merchant(id, name);
        merchants.put(id, student);
        return id;
    }

    public boolean deleteMerchant(String id) {
        return merchants.remove(id) != null;
    }

    public Merchant getMerchantById(String id) {
        return merchants.get(id);
    }
}
