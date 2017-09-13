Lab 01: Using the Gradle Enterprise build cache
===============================================

This lab demonstrates using Gradle Enterprise as a remote build cache.

A build has been provided that is already configured with the remote build cache configuration.

The local cache has been disabled for demonstration purposes. You can see the configuration in `settings.gradle`.

Start a terminal session in the directory that you extracted the labs zip file into and follow the steps below.

Steps
-----

- Change to the directory of lab #1

      cd 01-using-the-gradle-enterprise-build-cache

- Run a build. This build will execute without the cache being populated

      ./gradlew clean check --build-cache

---------------------------------------

NOTE: We are running `clean` with every build so that the incremental build feature in Gradle will not label some tasks as `UP-TO-DATE`.
This is only for demonstration purposes.

---------------------------------------

- Look at the console output for this build and note how long it took and how many tasks were executed.

- Run the same build again. This time the remote build cache has been populated from the last run

      ./gradlew clean check --build-cache

- Again, look at the console output for this build and note how long it took and how many tasks were executed.  
  You should notice that fewer tasks were executed and the build completed much faster.

- View the build cache usage summary page to see the global hit rate at  
  https://enterprise-training.gradle.com/cache-admin
  
- Finally, run the same build again, refresh the build cache usage summary page and see how the hits increased.  

---------------------------------------

NOTE: You will learn later in the training how to gain deep insights into the caching behavior of your build through build scans.

---------------------------------------
