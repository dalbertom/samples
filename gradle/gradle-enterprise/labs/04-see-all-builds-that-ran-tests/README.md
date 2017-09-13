Lab 04: See all builds that ran tests
=====================================

In this lab you will use Gradle Enterprise's scan list page to search for build scans that are annotated with given custom values.

A build has been provided that is already configured to publish build scans for all builds.

Start a terminal session in the directory that you extracted the labs zip file into and follow the steps below.

Steps
-----

- Change to the directory of lab #4

      cd 04-see-all-builds-that-ran-tests

- Run a build that does not execute any tests

      ./gradlew assemble

- Follow the link in the console output to view your build scan.

- Verify that the build scan does not have a `RAN_TESTS` tag. The tags are listed underneath the project name and the requested
  tasks are listed at the top of the build scan.

- Visit the scan list page at https://enterprise-training.gradle.com/scans and search for your username and the
  `RAN_TESTS` tag. You should not see any results (unless multiple trainees have the same username).

- Run a build that does executes some tests

      ./gradlew clean check

- Follow the link in the console output to view your build scan.

- Verify that the build scan has a `RAN_TESTS` tag.

- Visit the scan list page at https://enterprise-training.gradle.com/scans and search for your username and the
  `RAN_TESTS` tag. You should see the build that you just executed.
