import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class StudentTestSteps {
    StudentService service = new StudentService();
    
    // Test state
    Student inputStudent;
    Student resultStudent;
    String currentId;
    String error;

    // 1. Setup initial data
    @Given("I have a student with name {string} and city {string}")
    public void iHaveAStudent(String name, String city) {
        // Creiamo un oggetto temporaneo da inviare (l'ID è null perché lo genera il server)
        inputStudent = new Student(null, name, city);
    }

    // 2. POST call to register
    @When("I register the student")
    public void iRegisterTheStudent() {
        currentId = service.registerStudent(inputStudent);
        error = service.error; // Catch any error
    }

    // 3. Verifies received ID
    @Then("I get an ID")
    public void iGetAnID() {
        assertNotNull("ID should not be null", currentId);
        assertNull("Should not have errors", error);
    }

    // 4. GET call by ID
    @When("I search for the student by that ID")
    public void iSearchTheStudentByThatID() {
        // Clean previous results
        resultStudent = null;
        error = null;

        resultStudent = service.getStudentById(currentId);
        if (resultStudent == null) {
            error = service.error;
        }
    }

    // GET call by specific ID (to test errors)
    @When("I search for the student by id {string}")
    public void iSearchTheStudentByIdString(String id) {
        resultStudent = service.getStudentById(id);
        if (resultStudent == null) {
            error = service.error;
        }
    }

    // 5. Verifies received Student content
    @Then("I get the student answer with name {string} and city {string}")
    public void iGetTheStudentAnswer(String expectedName, String expectedCity) {
        assertNotNull("Student should be found", resultStudent);
        assertEquals(expectedName, resultStudent.getName());
        assertEquals(expectedCity, resultStudent.getCity());
    }

    // 6. PUT call to update
    @When("I update the student's city to {string}")
    public void iUpdateTheStudentCity(String newCity) {
        resultStudent = service.updateStudentCity(currentId, newCity);
        error = service.error;
    }

    // 7. DELETE call to delete the student
    @When("I delete the student")
    public void iDeleteTheStudent() {
        boolean success = service.deleteStudent(currentId);
        if (!success) {
            error = service.error;
        }
    }

    // 8. Error verification
    @Then("I get the error {string}")
    public void iGetTheError(String expectedErrorPart) {
        assertNotNull("Error should not be null", this.error);
        // Error "Not Found" or "404"
        assertTrue("Error message should contain expected text",
                this.error.contains(expectedErrorPart) || this.error.contains("400") || this.error.contains("404"));
    }
}