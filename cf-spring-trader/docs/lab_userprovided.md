# Creating Services
In the previous lab, you deployed a single microservice that has no dependencies. However, it is very rare that a microservice has no dependencies - *no microservice is an island!*

**Cloud Foundry** provides a marketplace where administrators can enable certain services to be available to developers/operators. These services are called [*Managed services*](http://docs.pivotal.io/pivotalcf/devguide/services/#managed-services), in contrast to [*User-provided services*](http://docs.pivotal.io/pivotalcf/devguide/services/#user-provided-services). 

In order for our microservices to be able to connect to the Registry service, we will have to create a [*User-provided service*](http://docs.pivotal.io/pivotalcf/devguide/services/user-provided.html). This tells our microservices where to find the registry service.

### Exercise
1. Create the *marketplace services* using the CLI.

  `./create-services.sh`

This simple script creates 4 services:

----

  #!/usr/bin/env bash<br>
  cf create-service p-mysql 100mb-dev trader-db<br>
  cf create-service p-service-registry standard trader-registry<br>
  cf create-service p-config-server standard config-server<br>
  cf create-service p-circuit-breaker-dashboard standard trader-dashboard<br>
  
----

# Summary
You have now created marketplace services via the CLI! You can now log in and see the services in your space at `https://console.pcfsony.com`, or see the services from the CLI.

  `> cf services`
