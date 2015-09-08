Pivotal CF Workshop - Spring MVC
================================

Introduction
------------

This is the Spring MVC sample application for the Pivotal CF Workshop.
It is intended to demonstrate some of the basic functionality of Pivotal
CF:

 * Pivotal CF target, login, and push
 * Pivotal CF environment variables
 * Pivotal CF service variables
 * Scaling, router and load balancing
 * Health manager and application restart
 * RDBMS services and application auto-configuration

Building, Packaging, and Deploying
--------------------------------

###To get the source code and build the WAR file


    git clone https://github.com/jtgammon/PCF-Bootcamp-Sony

    mvn clean package

###To run the application

The application is set to use an embedded H2 database in non-PaaS environments,
to take advantage of Pivotal CF's auto-configuration for services.  No
additional configuration is necessary when running locally or in Pivotal CF.

In Pivotal CF, it is assumed that a MySQL Dev service will be used.

