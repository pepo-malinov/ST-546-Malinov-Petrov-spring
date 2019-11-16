### Creating project with Spring Initializr

https://start.spring.io/

Единствена промяна в предефинираната конфигурация е в

Options -> Packaging -> War

В Dependencies -> Search dependencies to add, добавяме:

    Spring web

    Spring Boot DevTools

    Spring Data JPA

    Spring Boot Actuator

    H2 Database


Generate -> Save and extract the zip file

Close existing projects in eclipse

Go to File -> Import -> Maven -> Existing Maven Projects ->

select the extracted directory and click Finish

Right click on project -> Run as -> Maven Install

a project-name.war file should be created within target directory

Right click on the file -> properties -> Location and use the icon to open the directory in Explorer

Write CMD in the bar to open the command prompt

to start the war file write

java -jar project-name.war

press enter

The server should run by default on localhost:8080


===================================================

Checking out the project for first time from github

open git perspective in eclipse and clone the project

find weather-app-spring directory in the cloned project -> right click import project -> finish

the project should appear within your project explorer


