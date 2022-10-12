@tag
Feature: Purchase the product from Ecommerce website
  I want to purchase a product from eccommerce site
  
  Background: 
  Given I landed on Ecommerce page

  @tag2
  Scenario Outline: Positive test of submitting a order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANK YOU FOR THE ORDER." message is displayed on the confirmation page

    Examples: 
      | name                | password      | productName |
      | cjasraj98@gmail.com | Myacademy@007 | ZARA COAT   |

      
      
      