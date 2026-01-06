import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Optional;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    // In a real app, use Dependency Injection (@Inject), but here simple instantiation is fine
    StudentService service = new StudentService();

    @GET
    public Response listStudentsByName(@QueryParam("name") String name) {
        if (name != null && !name.isBlank()) {
            return Response.ok(service.findByName(name)).build();
        }
        return Response.ok(service.findAll()).build();
    }

    @POST
    public String register(Student s) {
        // We accept a Student object but ignore the ID provided by a client (if any)
        return service.register(s.getName(), s.getCity());
    }

    @GET
    @Path("/{id}")
    public Response getStudentById(@PathParam("id") String id) {
        Optional<Student> student = service.findById(id);
        if (student.isPresent()) {
            return Response.ok(student.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Not Found")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCity(@PathParam("id") String id, Student updatedStudent) {
        Optional<Student> updated = service.changeCity(id, updatedStudent.getCity());
        if (updated.isPresent()) {
            return Response.ok(updated.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Path("/ALLDB")
    public Response deleteAll() {
        service.clearDatabase();
        return Response.noContent().build();
    }
}