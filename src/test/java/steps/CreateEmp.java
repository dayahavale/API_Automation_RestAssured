package steps;
import core.library.Base;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;
import java.util.Map;

public class CreateEmp extends Base{

    private String endpoint;
    private RequestSpecification request;
    private Response response;
    private String url1 = Base.Urls.BASE_URL1;
    
    @Given("the API endpoint is")
    public void setEndpoint() {
        this.endpoint = url1;
        this.request = RestAssured.given().contentType("application/json");
    }

    @Given("the request body is:")
    public void setRequestBodyis(DataTable dataTable) {
        // Converting the data table to a Map for the request body
        Map<String, String> data = dataTable.asMaps().get(0);
        request.body(data);
    }

    @When("I send a POST request to the endpoint")
    public void sendPostRequest() {
        response = request.post(endpoint);
    }

    @Then("the response status code should {int}")
    public void verifyStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response body should contain the {string}: {string}")
    public void verifyResponseBodyContent(String key, String value) {
        response.then().body("status", equalTo(value));
    }

    @Then("the response body should include a unique {string} for the newly created employee")
    public void verifyUniqueId(String key) {
        // Checking that the data.id field exists and is not null
        response.then().body("data." + key, notNullValue());
    }
}