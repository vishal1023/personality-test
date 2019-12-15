# personality-test
NOTE -- I am a backend developer so build this project more focused on backend part of it. 
Experimented ReactJS as front-end technology (ReactJS is now to new.)  

#PRE-REQUISITES

1. JAVA 8
2. NODE JS   INSTALLED
3. MAVEN 3.3 OR HIGHER INSTALLED
4. MongoDB

#Build
Run below command to build the project
mvn clean install package

#Running application
TO RUNNING THIS APPLICATION FOLLOW BELOW STEPS

1. Run below command to start backend service
java -jar <location of personality-test-1.0.jar file>

2. Run below command to start react front end service
cd to source location  
cd src/main/webapp
npm start

open a browser and hit url http://localhost:3000

Initial set of questions get loaded automatically when you start application using JSON file.
JSON file is present in resources folder.
There is a json file (personality_test_initial_questions.json) in resource folder which holds initial questions.

#Further steps or improvements 
1. Make application URL's configurable
2. Add validation on Front End (with tests)
3. Remove hardcoding from Front end
4. Add functionality to add delete questions on demand  