Lab 06: Decide how you could increase the cacheability of the given build
=========================================================================

In this lab you will explore a build scan to determine how to make your build more cacheable.

A build has been provided that is already configured to publish build scans for all builds.

Start a terminal session in the directory that you extracted the labs zip file into and follow the steps below.

Steps
-----

- Change to the directory of lab #6

      cd 06-increase-the-cacheability-of-the-build

- Run a build TWICE to publish build scans

      ./gradlew clean build
      ./gradlew clean build

---------------------------------------

NOTE: The reason that we run the build twice after each step is so that we can see if the second run was able to make
      use of cached outputs from the first run. We need to do this after each change to `build.gradle`.

---------------------------------------

NOTE: We are running `clean` with every build so that we can see if Gradle will use cached outputs from the previous
      run. If we did not, Gradle would look at the task outputs in the `build` directory and decide that the tasks are all
      `UP-TO-DATE`. Running `clean` is not necessary under normal circumstances.

---------------------------------------

- Follow the link from the second build in the console output to view your build scan.

- Can you find out why the cached output of `task1` from the first run was not used by the second run?

- Try to make `task1` cacheable.  
  HINT: Look to see if it has the `@CacheableTask` annotation

- Run the build TWICE again to publish build scans

      ./gradlew clean build
      ./gradlew clean build

- Follow the link from the second build in the console output to view your build scan.

- Was the cached output of `task1` from the previous run used by the most recent run?

- If not, can you find out why not?

- Try to fix any issues reported by the build scan to make `task1` cacheable.  
  HINT: Look to see if it has an `@OutputDirectory` annotation on its output directory

- Run the build TWICE again

      ./gradlew clean build
      ./gradlew clean build

- Verify that the output of `task1` was taken from the cache.
