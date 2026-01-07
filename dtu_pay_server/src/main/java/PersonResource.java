import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/person")
public class PersonResource {
    private Person person = new Person("USA", "Susan");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson() {
        return person;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Person updatePerson(Person newPerson) {
        String newAddress = newPerson.getAddress();
        if (newAddress == null || newAddress.isEmpty() || newAddress.equals("-none-") || newPerson.getName() == null || newPerson.getName().isEmpty()){
            throw new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST)
                    .entity("ERROR: Address cannot be \"-none-\"")
                    .type(MediaType.TEXT_PLAIN)
                    .build()
            );
        }
        this.person = new Person(newPerson.getAddress(), newPerson.getName());
        return this.person;
    }
}

