Feature: Verify Search TV feature

  @Parallel
  Scenario Outline: Verify “About this item” section for second highest priced item
    Given User open amazon web app.
    When User click on the hamburger menu in the top left corner.
    And User scroll own and then Click on the "<department>" link under Shop by Department section.
    And User click on "<productType>" under Tv, Audio & Cameras sub section.
    And User scroll down and filter the results by Brand ‘Samsung’.
    And User sort the "<brand>" results with "<sorting>".
    And User click on the "<itemIndex> highest priced item.
    Then Verify that “About this item” section is present and log this section text to console or report.
    Examples:
      | department                    | productType   | brand     | sorting              | itemIndex |
      | "TV, Appliances, Electronics" | "Televisions" | "Samsung" | "Price: High to Low" | "2"       |