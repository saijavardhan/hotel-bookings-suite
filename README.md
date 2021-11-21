# hotel-bookings-suite

    This suite contains automated and manual tests.
    This is built on serenity-bdd frawemork. It generates a sophisticated report with screenshots.
    This frawemork has both manual and automated tests. 

    # Pre requisites to run the tests
        1. Make sure Java (>1.8) and maven is installed globally. you can test with the below command
          ## java --version
          ## maven --version
        2. Better to have latest browsers installed. Serenity will try best to get a matching browser webdriver while running tests. 
           
    # Running Tests and check the report 
    
        1) Once you have cloned the repository, Please be on the main folder
        2) a) Run the below command to run the tests in chrome browser. By default chrome is configured
                ## mvn clean verify serenity:aggregate
            b) If you want to run in firefox browser then use the below command
                ## mvn clean verify serenity:aggregate -Dwebdriver.driver=firefox
        3) you can check the report in below folder
           ## target/site/serenity/index.html    
           
           