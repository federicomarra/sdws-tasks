import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class PersonService {
    Client client;
    WebTarget target;
    String error;

    PersonService() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/");
    }

    public Person getPersonRequest() {
        Person person = target.path("person").request().get(Person.class);
        return person;
    }

    public Person putPersonRequest(Person newPerson) {
        Response response = target.path("person").request().put(Entity.json(newPerson));
        if (response.getStatus() == 200) {
            return response.readEntity(Person.class);
        } else {
            error = response.readEntity(String.class);
            System.out.println(error);
        }
        return null;
    }
}
