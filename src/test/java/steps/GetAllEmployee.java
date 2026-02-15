package steps;
import core.library.Base;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;

public class GetAllEmployee extends Base{

    private Response response;
    private RequestSpecification request;
    private String endpoint;
    private String url1 = Base.Urls.BASE_URL;
    
    @Given("the API base URI is")
    public void setBaseUri() {		
		  RestAssured.baseURI = url1; 
		  request = RestAssured.given();		 
		/*
		 * endpoint = url1; RestAssured.baseURI = endpoint;
		 */
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = request.get(endpoint);
    }

    @Then("the response status code is be {int}")
    public void verifyStatusCode(int code) {
        response.then().statusCode(code);
    }

    @Then("the response format should be JSON")
    public void verifyJsonFormat() {
        response.then().contentType("application/json");
    }

    @Then("the response body should contain a {string} field with value {string}")
    public void verifyStatusField(String field, String value) {
        response.then().body(field, equalTo(value));
    }

    @Then("the {string} array should not be empty")
    public void verifyArrayNotEmpty(String key) {
        response.then().body(key, not(empty()));
        
        // --- DATA PRINTING LOGIC ---
        System.out.println("------ Employee Data Report ------");
        List<Map<String, Object>> employees = response.jsonPath().getList(key);
        
        for (Map<String, Object> employee : employees) {
            System.out.println(String.format("ID: %s | Name: %-20s | Salary: %s", 
                employee.get("id"), 
                employee.get("employee_name"), 
                employee.get("employee_salary")));
        }
        System.out.println("----------------------------------");
    }

    @Then("each employee object should contain {string}, {string}, and {string}")
    public void verifyEmployeeFields(String id, String name, String salary) {
        response.then()
            .body("data", everyItem(hasKey(id)))
            .body("data", everyItem(hasKey(name)))
            .body("data", everyItem(hasKey(salary)));
    }
}