public class Person {
    private String name;
    private String address;

    public Person() {}
    
    public Person(String address, String name) {
        this.address = address;
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
        return "{\"address\":\"" + address + "\",\"name\":\"" + name + "\"}";
    }
    
}
