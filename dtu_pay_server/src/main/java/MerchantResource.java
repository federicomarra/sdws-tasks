import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;

@Path("/merchant")
public class MerchantResource {

    MerchantService service = MerchantService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerMerchant(String name, @Context UriInfo uriInfo) {
        // Input validation
        if (name == null || name.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Name of the merchant is required"))
                    .build();
        }

        // Business logic
        String id = service.registerMerchant(name.trim());

        URI location = uriInfo.getAbsolutePathBuilder().path(id).build();

        // Response 201 Created with JSON body containing the ID
        return Response.created(location)
                .entity(id)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deregisterMerchant(@PathParam("id") String id) {
        boolean deleted = service.deleteMerchant(id);

        if (deleted) {
            return Response.noContent().build(); // 204 No Content
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // 404 Not Found
        }
    }
}