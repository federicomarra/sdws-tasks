import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class GreetingResource {

    @Path("/{path: hello|hellotext|helloText|hello/text|hello/Text}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @Path("/{path: hellojson|helloJson|hello/json|hello/Json}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloJson() {
        return "{\"message\":\"Hello RESTEasy\"}";
    }

    @Path("/{path: hellohtml|helloHtml|hello/html|hello/Html}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String helloHtml() {
        return "<h1>Hello RESTEasy</h1>";
    }

    @Path("/{path: helloxml|helloXml|hello/xml|hello/Xml}")
    @GET
    @Produces(MediaType.TEXT_XML)
    public String helloXml() {
        return "<greeting>Hello RESTEasy</greeting>";
    }
}
