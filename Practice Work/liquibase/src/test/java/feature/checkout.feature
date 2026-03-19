Feature: Place the order for products

@Checkout
  Scenario Outline: Search Experience for product search in both home page and offers page
    Given User is on the GreenKart Landing page
    When Search the product with name <Name> and extract actual name of the product
    And Added "3" items of selected item product to cart
    Then User proceeds to checkout and validate the <Name> items in checkout page
    And User has ability to enter promo code and place the order
    Examples:
      | Name |
      | Tom  |

