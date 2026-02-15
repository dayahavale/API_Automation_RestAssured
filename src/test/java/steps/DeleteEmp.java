package steps;
import core.library.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class DeleteEmp extends Base {

    private String endpoint;
    private Response response;
    private String url1 = Base.Urls.BASE_URL3;

    @Given("the API endpoint URL is")
    public void the_api_endpoint_is() {
        this.endpoint = url1;
    }

    @When("I send a DELETE request to the endpoint")
    public void i_send_a_delete_request_to_the_endpoint() {
        response = RestAssured.when().delete(endpoint);
    }

    @Then("the response status code is {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response body is contain {string}: {string}")
    public void the_response_body_should_contain(String key, String value) {
        response.then().body(key, equalTo(value));
    }
    
}