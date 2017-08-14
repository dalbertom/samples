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
