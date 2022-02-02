# Rest_Assured_Customer_API_Automation_Allure_Report

## Prerequisite
1. Need to install jdk 8 or 11 or any LTS version
2. Set up Java Home & Gradle Home
3. Download Allure latest version & configure allure to the environment path

## How to Run the Project
1. Clone the repository
2. Run cmd in the root folder
3. Then give the following command in cmd

```
gradle clean test
```
```
allure generate allure-results --clean -o allure-report
```
```
allure serve allure-results
```


## Screenshots of the Allure Report
![Rest_Assured_API_Automation_Allure_Report_1](https://user-images.githubusercontent.com/96170694/152241485-985b6b53-3da6-4ae8-b731-22fe1e4c6197.JPG)
![Rest_Assured_API_Automation_Allure_Report_2](https://user-images.githubusercontent.com/96170694/152241495-dc4888d5-97f8-4abe-8d98-ea705f283150.JPG)
