Feature: User is able to filter test items

Lifecycle:
Before:
Scope: SCENARIO
Given Launch Page is opened

Scenario: Sorting test items by name
Given Launch with id 3 is opened
When Search phrase '<FilterBy>' is entered
Then Test Items number after filter is <ResultsCount>
Examples:
|FilterBy   |ResultsCount |
|test       |3            |
|launch     |2            |
|suite with |2            |
