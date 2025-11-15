### Test Report
<img width="1365" height="646" alt="image" src="https://github.com/user-attachments/assets/89a4f067-3b1a-4074-98dc-f2b9efc30a6a" />

### Suite Command
1. gradle clean test -Psuite="SmokeSuite.xml" (For smoke test Run)
2. gradle clean test -Psuite="RegressionSuite.xml" (For Regression test Run)
3. gradle clean test -Pemail="admin@test.com" -Ppassword="admin123" (Test Run from CLI passing email and password)
### Allure Report Generated
1.  allure generate allure-results
2.  allure serve allure-results --clean -output
   
### Testcase
<img width="1364" height="649" alt="image" src="https://github.com/user-attachments/assets/6977aa21-ee29-460b-b5dc-dd2c4a5b4be4" />
