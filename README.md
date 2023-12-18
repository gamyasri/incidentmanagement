# Build and deploy

The manifest.yml needs to be in the direction from which the `cf push` command is executed

* `mvn package` -> build a new jar file in the target folder
* `cf push -p target/aiops-0.0.1-SNAPSHOT.jar -m 1G` -> deploys the jar file 
