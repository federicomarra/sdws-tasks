import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/payment")
public class PaymentResource {
    PaymentService service = new PaymentService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response pay(Payment payment) {
        boolean success = service.pay(payment.getAmount(), payment.getCustomerId(), payment.getMerchantId());
        return success
                ? Response.status(Response.Status.CREATED).build()
                : Response.status(Response.Status.BAD_REQUEST).build();
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Payment> getAllPayments() {
        return service.getPayments();
    }
}
