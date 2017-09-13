Lab 05: Find potential performance killers
==========================================

In this lab you will explore a build scan to find potential performance killers in your build.

A build has been provided that is already configured to publish build scans for all builds.

Start a terminal session in the directory that you extracted the labs zip file into and follow the steps below.

Steps
-----

- Change to the directory of lab #5

      cd 05-find-potential-performance-killers

- Run a build to publish a build scan

      ./gradlew build

- Follow the link in the console output to view your build scan.

- Find the answers to the following questions

  - How much time was spent configuring the build, and how much executing tasks?
  - Which build script takes the longest to run?
  - Is a script applied to the same project multiple times? If so, what caused it to be applied?
  - Which plugin is the slowest? And where is that plugin applied?

- Investigate the slowest parts of the build identified above. Can you make the build faster by speeding those up?
