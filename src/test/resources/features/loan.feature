Feature: Loan request

  Scenario: request a car loan
    Given I have opened the loan request page
    And I select loan type 'Car-loan'
    And the amount I want to borrow is '1000'
#    And I continue to explanation