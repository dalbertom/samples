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
