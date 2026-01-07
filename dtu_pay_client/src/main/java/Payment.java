public class Payment {
    private String id;
    private int amount;
    private String customerId;
    private String customerName;
    private String merchantId;
    private String merchantName;

    public Payment() {}

    public Payment(int amount, String customerId, String merchantId) {
        this.amount = amount;
        this.customerId = customerId;
        this.merchantId = merchantId;
    }

    public Payment(int amount, String customerId, String customerName, String merchantId, String merchantName) {
        this.amount = amount;
        this.customerId = customerId;
        this.customerName = customerName;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCustomerName() { return customerName;}

    public void setCustomerName(String customerName) { this.customerName = customerName;}

    public String getMerchantName() { return merchantName; }

    public void setMerchantName(String merchantName) { this.merchantName = merchantName; }

    @Override
    public String toString() {
        return "Payment{amount=" + amount + ", customerName='" + customerName + "', merchantName='" + merchantName + "'}";
    }
}