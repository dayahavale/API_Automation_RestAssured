package steps;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import core.library.Base;
public class UpdateEmp extends Base{

    private Response response;
    private Map<String, Object> requestBody = new HashMap<>();
    private String endpoint;
    private String url1 = Base.Urls.BASE_URL2;

    @Given("the API endpoint")
    public void the_api_endpoint_url_is() {
        endpoint = url1;
        RestAssured.baseURI = endpoint;
    }

    @And("the request body :")
    public void the_request_body_is(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);

        requestBody.put("name", data.get("name"));
        requestBody.put("salary", data.get("salary"));
        requestBody.put("age", data.get("age"));
    }

    @When("I send a PUT request to the endpoint")
    public void i_send_a_put_request_to_the_endpoint() {
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put(endpoint);
    }

    @Then("the response status code should bee {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the response body should contain {string}: {string}")
    public void the_response_body_should_contain(String key, String value) {
        response.then().body(key, equalTo(value));
    }

    @And("the response body should contain the updated employee details")
    public void the_response_body_should_contain_the_updated_employee_details() {
        response.then()
                .body("data.name", equalTo(requestBody.get("name")))
                .body("data.salary", equalTo(requestBody.get("salary")))
                .body("data.age", equalTo(requestBody.get("age")));
    }
}
