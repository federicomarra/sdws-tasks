import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;

@Path("/customers")
public class CustomerResource {

    CustomerService service = new CustomerService();

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(String name, @Context UriInfo uriInfo) {
        // Input validation
        if (name == null || name.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Il nome del customer non può essere vuoto"))
                    .build();
        }

        // Business logic
        String id = service.registerCustomer(name.trim());

        // Generate something similar to: http://host:port/api/customer/123
        URI location = uriInfo.getAbsolutePathBuilder().path(id).build();

        // Response 201 Created with JSON body containing the ID
        return Response.created(location)
                .entity(Map.of("id", id)) // Ritorna {"id": "..."}
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deregisterCustomer(@PathParam("id") String id) {
        boolean deleted = service.deleteCustomer(id);

        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}