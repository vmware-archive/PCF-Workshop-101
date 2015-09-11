# Deploying the Applications

In this exercise, we will be deploying all the applications in the project to the cloud.

### Perform the Gradle build

. Change to the PCF-Workshop-Sony/cf-spring-trader directory.

. Kick off the Gradle build

  `./gradlew clean assemble`
  
### Push the applications to CloudFoundry using the manifest-all.yml file

. Edit the manifest-all.yml to change the `name` field to something unique for each of the applications. Otherwise you will get an error if someone else deploys an app that uses the same hostname.

----
quotes-> quotes-initials
accounts -> accounts-initials
portfolio -> portfolio-initials
webtrader -> webtrader-initials
----

. cf push -f manifest-all.yml
