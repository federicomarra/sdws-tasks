import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

public class HelloService {
    Client client;
    WebTarget target;
    
    HelloService() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/");
    }
    
    public String getHelloRequest(String path) {
        String response = target.path(path).request().get(String.class);
        return response;
    }
    
    public String hello() {
        return getHelloRequest("hello");
    }
    
    public String helloText() {
        return getHelloRequest("helloText");
    }
    
    public String helloJson() {
        return getHelloRequest("helloJson");
    }
    
    public String helloHtml() {
        return getHelloRequest("helloHtml");
    }
    
    public String helloXml() {
        return getHelloRequest("helloXml");
    }
}
