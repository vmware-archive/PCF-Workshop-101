# Deploying the Applications

In this exercise, we will be deploying all the applications in the project to the cloud.

### Perform the Gradle build

. Change to the PCF-Workshop-Sony/cf-spring-trader directory.

. Kick off the Gradle build

  `./gradlew clean assemble`
  
### Push the applications to CloudFoundry using the manifest-all.yml file

. cf push -f manifest-all.yml
