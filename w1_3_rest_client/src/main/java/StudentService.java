import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

public class StudentService {
    Client client;
    WebTarget target;
    String error;

    public StudentService() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/");
    }

    // 0. GET /students
    // Restituisce un array di tutti gli studenti (poiché il server restituisce una lista/collezione)
    public Student[] getAllStudents() {
        WebTarget t = target.path("students");
        
        Response response = t.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(Student[].class);
        } else {
            printError(response);
        }
        return null;
    }

    // 1. GET /students?name={name}
    // Restituisce un array di studenti (poiché il server restituisce una lista/collezione)
    public Student[] getStudents(String name) {
        WebTarget t = target.path("students");
        if (name != null && !name.isBlank()) {
            t = t.queryParam("name", name);
        }

        Response response = t.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(Student[].class);
        } else {
            printError(response);
        }
        return null;
    }

    // 2. POST /students
    // Invia un oggetto Student e restituisce l'ID generato (String)
    public String registerStudent(Student newStudent) {
        Response response = target.path("students")
                .request()
                .post(Entity.json(newStudent));

        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            printError(response);
        }
        return null;
    }

    // 3. GET /students/{id}
    // Restituisce un singolo oggetto Student
    public Student getStudentById(String id) {
        Response response = target.path("students")
                .path(id)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {
            return response.readEntity(Student.class);
        } else {
            printError(response); // Gestisce il 404
        }
        return null;
    }

    // 4. PUT /students/{id}
    // Aggiorna la città. Nota: Ho usato queryParam per newCity.
    public Student updateStudentCity(String id, String newCity) {
        System.out.println("Updating student " + id + " to city " + newCity);
        Student newStudent = new Student(id, null, newCity);
        Response response = target.path("students")
                .path(id)
                .request()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .put(Entity.json(newStudent));
        if (response.getStatus() == 200) {
            return response.readEntity(Student.class);
        } else {
            printError(response);
        }
        return null;
    }

    // 5. DELETE /students/{id}
    public boolean deleteStudent(String id) {
        Response response = target.path("students")
                .path(id)
                .request()
                .delete();

        if (response.getStatus() == 204) { // 204 No Content è il successo per DELETE nel tuo Resource
            return true;
        } else {
            printError(response);
            return false;
        }
    }

    // 6. DELETE /students/ALLDB
    public boolean clearDatabase() {
        Response response = target.path("students")
                .path("ALLDB")
                .request()
                .delete();

        if (response.getStatus() == 204) {
            return true;
        } else {
            printError(response);
            return false;
        }
    }

    // Metodo helper per stampare gli errori
    private void printError(Response response) {
        // Legge l'errore se c'è un corpo, altrimenti stampa lo status code
        if (response.hasEntity()) {
            error = response.readEntity(String.class);
            System.out.println("Error " + response.getStatus() + ": " + error);
        } else {
            System.out.println("Error status: " + response.getStatus());
        }
    }
}