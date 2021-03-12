# CitiTaskFramework
Test after interview

Tools/technology used for the project:
- Java 8, 
- Selenium webdriver, 
- Maven, 
- TestNG , (As instructed I was initially using Junit-5 but was facing challenges such as, execution was not happening at all and code run was failing as soon as I execute my code, debugging was also getting stuck at some point of the code... I will retry junit to resolve these challenges)
- Used Page object model design pattern with Page factory
- Extent reports
- Read project configuration data from properties file, 
- Read test data from Excel files
- Failure of any testcase will capture screenshot (after execution you can verify the screenshot in root directory of the project) 


• To run the code, you can run through testng.XML as a test suite. 
I have 2 classes implemented, 1. LoginPageTest class with 3 tests and 2. HomePageTest class with 1 test case. 
I have commented loginPageTest in textNG.xml so when you execute the testng.xml it will only run 1 testcase from homepageTest class
I have intentionally used test data(from excel) that will fail the test case, just to see failure test case screenshot as well as failure report. 
but in case you want to execute all of the implemented test cases,  you can just uncomment the loginPageTest line in the testng.xml
• Extent Report you can find in project root directory in Reports folder 


Please note : I have observed that if we run login test multiple times, then Amazon will start sending "sign-in attempt approval email and that will required to actually access the email in order to approve the Sign-in attempt, I have not handled this in my frame work.
So request you if needed please use below test email credentials to login to the gmail to approve the login attempt: 
gmail id:  	mp1092985@gmail.com
Password:	Test!@#$
  
