Bibernate demo poject
===================

Summary
-------------

This project contains demos showcasing how to use Bibernate.
The project is designed to help developers understand how to work with Bibernate,including how to save, find, update, and remove entities, 
how to configure Bibernate using different approaches (Java, XML, and Properties),
how to optimize database queries, and more.

> :warning: **This project is not Bibernate and  not a comprehensive demonstration of Bibernate's capabilities.**

Prerequisites
-------------

-   Java 17 or higher
-   Docker compose

Setup
-----

1.  Clone the repository: `git clone https://github.com/example/bibernate-demo-project.git`
2.  Navigate to the project directory: `cd bibernate-demo-project`
3.  Start the PostgreSQL database using Docker Compose: `docker-compose up -d`
4.  Connect to the database using your preferred database client with the following details:
    -   Host: `localhost`
    -   Port: `5435`
    -   Username: `postgres`
    -   Password: `root`
    -   Database: `postgres`

Running Demos
-------------

All demos can be found in the `com.bobocode.svydovets` package.
Simply navigate to the desired demo and run the `main` method

Clean up
--------

1.  Stop the Docker Compose containers: `docker-compose down`
