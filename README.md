# Custom Metrics for Jenkins Pipelines 
Enlarge your metrics 😎

### Contents of the repo

Configuration files and code that we used to measure our software delivery KPIs.

#### Collectd

After having `collectd` installed and configured on your Jenkins master host, install the collectd generic jxm plugin and place the [config file](./collectd/jmx-jenkins.conf) in your included config files, then restart collectd.

#### Jenkins

* `src`: contains the code that needs to be imported as shared libraries (usually `$JENKINS_HOME/workflow-libs`)
* `test`: test to ensure code does what it should do
* `vars`: contain an example function that can be used to orchestrate a pipeline that builds docker images

Testing the custom metrics: `./gradlew build` will run the tests after getting the dependencies. 

