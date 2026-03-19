
Feature: Application Login Functionality
Background:
  Given Setup the entries in the db
  When launch the browser using env variables
  And  hit the url to landup on login page



@SmokeTest @UserAcceptanceTest @SanityTest
Scenario: Admin Page login
Given User is on landing page
When User is login to the application
  |name|mohit|
  |lastname|patel|
  |Email   |mohit@gmail.com|
  |contact |89249834893    |
Then Home page is displaying
And Cards should be display



Scenario Outline: Login Page Function
  Given User is on landing page
  When User is login to the application with "<username>" and password "<password>"
  Then Home page is displaying
  And Cards should be display
  Examples:
  | username  | password |
  | testUser  | pass12   |
  | newest    | pass33   |
  | 4         | 322      |

Scenario Outline:
  Given User is on landing page
  When User is login with <username> and password "<password>"
  Then Home page should display
  And Card details should display
  Examples:
  | username | password |
  | 4232     | 322      |
  | 32       | 36       |