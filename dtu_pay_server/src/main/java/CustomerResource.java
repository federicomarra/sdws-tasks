import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;

@Path("/customer")
public class CustomerResource {

    CustomerService service = CustomerService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(Customer customer, @Context UriInfo uriInfo) {
        // Input validation
        if (customer == null || customer.getName() == null || customer.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Name of the customer is required"))
                    .build();
        }
        // Business logic
        String id = service.registerCustomer(customer.getName().trim());

        URI location = uriInfo.getAbsolutePathBuilder().path(id).build();

        // Response 201 Created with JSON body containing the ID
        return Response.created(location)
                .entity(id)
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