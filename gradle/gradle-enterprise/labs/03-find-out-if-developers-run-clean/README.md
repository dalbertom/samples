Lab 03: Find out if the developers in your company run the `clean` task
=======================================================================

In this lab you will use Gradle Enterprise's scan list page to search for build scans that match a particular criteria.

Follow the steps below.

Steps
-----

- Browse to the scan list page at
  https://enterprise-training.gradle.com

- In the _Requested tasks_ search criteria field, enter `clean`.

- Click the _Search_ button.  
  The search results contain all builds where the command-line invocation matched the task `clean`,
  even if the requested tasks were abbreviated on the command-line.

- Try filtering further by applying additional search criteria.

- View a build scan from the search results and determine whether running `clean` was necessary.
