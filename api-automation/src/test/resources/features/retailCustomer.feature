Feature: Retail Customer API (ES)

  Background:
    * url esUrl

  Scenario Outline: Retrieve customers

    Given path '/<customerID>'
    When method get
    Then status 200
    And match $ == {esCustomerID: <customerID>, esName: '<name>', esIsSenior: <isSenior>}

    Examples:
      | customerID | name    | isSenior |
      | 1          | Humaira | false    |
      | 2          | Pankaj  | false    |
      | 3          | John    | true     |

  Scenario: Add a new customer
    Given path ''
    And request {esName: 'karate', esIsSenior: true}
    When method post
    Then status 201
    And match $ == {esCustomerID: 4, esName: karate, esIsSenior: true}

  Scenario: Retrieve a customer which doesn't exist
    Given path '/10'
    When method get
    Then status 404
    And match $ == "Customer Not Found"