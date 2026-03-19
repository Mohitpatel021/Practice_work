Feature: Search and Match the filtered Result

@Offers
Scenario Outline: Search Experience for product search in both home page and offers page
  Given User is on the GreenKart Landing page
  When Search the product with name <Name> and extract actual name of the product
  Then search with the same product name <Name> in offers page
  And Validate both the product name
  Examples:
    | Name |
    | Tom  |
    | Beet |
    | On   |

