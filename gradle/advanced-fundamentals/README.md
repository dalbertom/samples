# Advanced Gradle Fundamentals for Java/JVM

## Lab 1 create build scan
* Can add plugins via a script in ~/.gradle/init.d/. Thit might be useful to enable things like build scan for certain people.
* Build scan tags could be used to add branch names or a particular project that is being built.

## Lab 2 custom tasks
* ad-hoc vs typed tasks: ad-hoc use doFirst/doLast. Typed use type: (e.g. Copy)

### Implement task types
```groovy
class FtpTask extends DefaultTask {
  String host = "docs.mycompany.com"

  @TaskAction
  void ftp() {
    // do something complicated
  }
}
```

Gradle TestKit to write tests for the build. Chapter 45 - https://docs.gradle.org/current/userguide/test_kit.html

## Lab 3 task graph
Dependencies on tasks that haven't been defined yet can be setup in different ways:
* dependsOn "setup"
* dependsOn { setup }
* or add a task configuration that calls dependsOn right after the task it depends on has been defined.

## Lab 4 task ordering
* dependsOn (e.g task b depends on task a) means that to run task b it must run task a first
* Ordering dependency e.g. integrationTests.mustRunAfter(unitTests) to force ordering on tasks that are independent
** mustRunAfter doesn't imply the other task has to run
** shouldRunAfter is much less strict than mustRunAfter
** yields dependsOn if there would be a cycle otherwise
*** If there's a cycle due to dependsOn, shouldRunAfter will not fail, whereas mustRunAfter will break
* finalizedBy to do a cleanup e.g.
```
task integrationTests {
  dependsOn startWebServer
  finalizedBy stopWebServer // it's often used for releasing resources, like Java's try-finally block
}
```

## Lab 5 excluding tasks
* gradle taskA -x taskB // exclude at invocation time. Will exclude taskB and its dependencies
* gradle.startParameter.excludedTaskNames.add "jar" // not very common
* task.onlyIf { condition } // exclude at runtime
* exclude them at configuration time:
```
if (isReleaseManagerUser()) {
  task ftpDistribution {
    doLast {
      // do something
    }
  }
}
```
