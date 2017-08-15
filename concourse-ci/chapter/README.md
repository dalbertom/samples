# Concourse CI
[Introduction](https://concourse.ci/introduction.html)

## Requirements
* docker, docker-machine, docker-compose
* fly: `brew cask install fly`

## Create VM
* Run `docker-machine create --driver virtualbox concourse`
* Docker environment `eval $(docker-machine env concourse)`
* Concourse environment `export CONCOURSE_EXTERNAL_URL=$(docker-machine ip $DOCKER_MACHINE_NAME):8080`

## Generate keys
Run `./generate-keys.sh`

## Start concourse
* Run `docker-compose up`
