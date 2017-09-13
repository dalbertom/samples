Lab 07: You want a live dashboard of build activity
===================================================

In this lab you will use a real-time build duration chart in conjunction with the Gradle Enterprise training instance.
You will be able to monitor the frequency of builds and their durations live as they happen.

Follow the steps below.

Steps
-----

- Check out the Export API samples repository

      git clone https://github.com/gradle/gradle-enterprise-export-api-samples.git

- Navigate to the `realtime-streaming-build-duration-chart` sample

      cd gradle-enterprise-export-api-samples/samples/realtime-streaming-build-duration-chart

- Replace the value of the `gradleEnterpriseServer` constant in index.html with `https://enterprise-training.gradle.com` and save.

- The sample first listens for any builds occurring https://github.com/gradle/gradle-enterprise-export-api-samples/blob/master/samples/realtime-streaming-build-duration-chart/index.html#L22

- For each build, its `BuildStarted` and `BuildFinished` events are requested https://github.com/gradle/gradle-enterprise-export-api-samples/blob/master/samples/realtime-streaming-build-duration-chart/index.html#L24

- Now open index.html in your favorite browser.

- Finally, run some builds from the other lab directories and watch them appear on the chart in real-time.

---------------------------------------

NOTE: You may notice builds appearing on the chart that you did not run, these will be from fellow trainees.

---------------------------------------
