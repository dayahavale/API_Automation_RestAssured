Feature: Employee Search API

  @AC1
  Scenario: Successfully retrieve and log all employee data
    Given the API base URI is
    When I send a GET request to "/employees"
    Then the response status code is be 200
    And the response format should be JSON
    And the response body should contain a "status" field with value "success"
    And the "data" array should not be empty
    And each employee object should contain "id", "employee_name", and "employee_salary"
    
   @AC2
   Scenario: Successfully create a new employee
    Given the API endpoint is
    And the request body is:
      | name   | salary | age |
      | Gemini | 5000   | 30  |
    When I send a POST request to the endpoint
    Then the response status code should 200
    And the response body should contain the "status": "success"
    And the response body should include a unique "id" for the newly created employee
    
   @AC3
   Scenario: Successfully update employee details
    Given the API endpoint
    And the request body :
      | name   | salary | age |
      | test   | 123    | 23  |
    When I send a PUT request to the endpoint
    Then the response status code should bee 200
    And the response body should contain "status": "success"
    And the response body should contain "message": "Successfully! Record has been updated."
    And the response body should contain the updated employee details
    
    @AC4
    Scenario: Successfully delete an employee
    Given the API endpoint URL is
    When I send a DELETE request to the endpoint
    Then the response status code is 200
    And the response body is contain "status": "success"
    And the response body is contain "message": "Successfully! Record has been deleted"