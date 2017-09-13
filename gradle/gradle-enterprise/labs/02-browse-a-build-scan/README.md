Lab 02: Browse a build scan
===========================

In this lab you will explore a build scan to gain insights about a run Gradle build.

A build has been provided that is already configured to publish build scans for all builds.

Start a terminal session in the directory that you extracted the labs zip file into and follow the steps below.

Steps
-----

- Change to the directory of lab #2

      cd 02-browse-a-build-scan

- Run a build to publish a build scan

      ./gradlew build

- Follow the link in the console output to view your build scan.

- Find the answers to the following questions

  - What is the warning in the console output?
  - What version of `commons-lang` is used by the build?
  - Was the `publishing` plugin applied to the build?
  - What OS and JVM versions was the build run with?
  - Was the build cache enabled?
