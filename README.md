## Задание

##### Да се направи уеб-приложение, което да надгражда приложението направено по задание „ЗАДАНИЕ ПРАКТИКУМ МАГИСТРИ 2019/2020“. Новата версия на приложението трябва да изпълнява следните задачи:


1. Приложението да използва релационна база от данни, която притежава три релационно свързани таблици (при ваше желание може и повече). Вашата ДБ трябва да спазва добрите практики и да е в трета бормална форма;

2. Трябва да изградите REST end point(s), който (които) да обслужват базови заявки към вашето приложение, реализиращи CRUD операции с базата данни;

3. Трябва да разширите вашето приложение с допълнителни страници, които да имат подходящ потребителски интерфейс, подпомагащ CRUD операциите;

4. Нека вашето приложение да има визуализация на данни от ДБ, чрез използване на филтър (някакъв вид търсене) по един и по два критерия;



*Темата на приложението може да бъде директно продължение на проекта от Практикум или да бъде ново приложение, но задължително трябва да изпълнява и условията от предходното задание (Задание Практикум Магистри 2019/2020).*

*За изпълнение на проекта използвайте технологиите преподавани през триместъра по Практикум и трите базови дисциплини.*

*Проектът е предвиден за самостоятелна работа, съответно всички предаващи проекти ще бъдат изпитвани относно функционалностите и работата на проекта. Проектът се защитава на посочените официални изпитни дати.*


==================================================================================






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

to start the project find src/main/java -> WeatherApplication.java -> right click and Run As -> Java Application




