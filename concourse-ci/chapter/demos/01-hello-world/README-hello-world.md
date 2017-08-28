# Hello World
* Run `fly -t lite login -c http://$CONCOURSE_EXTERNAL_URL`
  * CONCOURSE_EXTERNAL_URL=$(docker-machine ip $DOCKER_MACHINE_NAME):8080
* username: concourse
* password: changeme
* Run `fly -t lite sync`

## Basic pipeline
The pipeline is described in hello.yml
* Create pipeline with `fly -t lite set-pipeline -p hello-world -c hello.yml`
* Unpause pipeline with `fly -t lite unpause-pipeline -p hello-world`
* Fetch configuration with `fly -t lite get-pipeline -p hello-world`

## Pipeline with resource (timed trigger)
The pipeline is described in navi-pipeline.yml
* Reconfigure the pipeline with `fly -t lite set-pipeline -p hello-world -c navi-pipeline.yml`