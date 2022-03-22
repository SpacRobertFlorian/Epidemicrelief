# epidemic-relief

## Abstract

Sample Spring Boot application using JSP as template engine and MySql as data source.
Application will be further developed during the Accesa internship.

## Requirements

* Java 11
* Gradle
* MySql database

### Java and Gradle installation

To install Java and Gradle, on Windows OS you can use Chocolatey package manager to install: https://chocolatey.org

    choco install adoptopenjdk11

    choco install gradle

## IDE

We strongly recommend IntelliJ IDEA Ultimate Edition as your IDE.
Open the project in IntelliJ using File -> Open -> select dir of this project

## Database Setup

The application will not start without a working connection to the database.

* In src/main/resources/application.yml file, change your DB settings (username, password) to reflect those on your local environment
* Create a database schema named "edpidemicrelief" using UTF-8 charset and default collation. We recommend using MySQL Workbench to accomplish this.
* Once the server starts, it will automatically generate the required tables in your schema.

## Running the application

2 Options:

* Form terminal: `gradle bootRun`
* From IDE: Run main run configuration
* Open the following url in your browser: http://localhost:8081/
