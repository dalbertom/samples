# Introduction

https://concourse.ci/introduction.html

# Setting Up
## Installing
* Vagrant: https://concourse.ci/vagrant.html

# Using Concourse

Install fly command line on macOs
```
brew cask install fly
```

Target fly to the local VM
```
fly -t lite login -c http://192.168.100.4:8080
```

## Create hello-world
Will use the pipeline described in hello.yml and run the following command:
```
fly -t lite set-pipeline -p hello-world -c hello.yml
```

Newly created pipelines are paused by default. They can be unpaused via the GUI's hamburger button or through the command line:
```
fly -t lite unpause-pipeline -p hello-world
```

To fetch the current configuration:
```
fly -t lite get-pipeline -p hello-world
```

Since the pipeline doesn't have any resources to trigger it, we will need to start it manually by clicking the + button on the top-right

## Pipeline with a resource (timer trigger)
Configure pipeline:
```
fly -t lite set-pipeline -p hello-world -c navi-pipeline.yml
```

# Resources
List of [resources](https://concourse.ci/resource-types.html):
* git pull and push to git repositories
* time start jobs on a schedule or timestamp outputs
* s3 fetch and upload to S3 buckets
* archive fetch and extract .tar.gz archives
* semver set or bump version numbers
* github-release fetch and publish versioned GitHub resources
* docker-images fetch, build, push Docker images
* pool configure how to serialize use of an external system

# Tutorials
## Flight School
Clone repository on local machine:
```
git clone git@github.com:concourse/flight-school /tmp/flight-school
```

Check things are running:
```
cd /tmp/flight-school
which bundle || gem install bundler
bundle install
bundle exec rspec
```

Install fly
```
brew cask install fly
```

Target and log into Concourse
```
fly -t ci login -c http://192.168.100.4:8080
```

Copy build and test files
```
cp flight-school/build.yml flight-school/test.sh /tmp/flight-school
```

Execute test
```
cd /tmp/flight-school
fly -t ci execute -c build.yml
cd -
```

### Starting a Pipeline
Add pipeline
```
cp flight-school/pipeline.yml /tmp/flight-school
cd /tmp/flight-school
fly -t ci set-pipeline -p flight-school -c pipeline.yml
cd -
```
