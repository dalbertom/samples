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

can use export TERM=dumb instead of --console=plain

## Lab 6 querying the task execution graph
```
gradle.taskGraph.whenReady { graph ->
  if (graph.hasTask(':release')) {
    ...
  }
}

task someTask {
  doLast { // during task execution
    if (gradle.taskGraph.hasTask(':otherTask')) {
      ...
    }
  }
}

println gradle.taskGraph.allTasks // would throw an exception because this runs during configuration and task graph hasn't been built yet
```

## Task Rules
```
/* The 'Pattern: ping<ID>' string does not affect the execution
 * it is only used for documentation (e.g. when running gradle tasks)
 */
tasks.addRule('Pattern: ping<ID>') { String taskName -> // this runs as part of the Configuration Phase
  if (taskName.startsWith("ping")) {
    task(taskName) {
      doLast {
        println "Pinging: " + (taskName - "ping")
      }
    }
  }
}
task groupPing {
  dependsOn pingServer1, pingServer2
}
```

## Logging
* levels: error, quiet, warning, lifecycle, info, debug
```
gradle -i hello
gradle hello -d
gradle -q hello
```

```
println "A message logged at QUIET level"
logger.quiet "A message that is always logged."
logger.error "An error log message."
logger.warn "A warning log message."
logger.lifecycle "A lifecycle log message."
logger.info "An info log message."
logger.debug "A debug log message."
```

### Logging from classes
```
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

Logger logger = Logging.getLogger("some-logger")
logger.info("An info log message")
```

## Plugins
### Script Plugin
`apply from: file.gradle`
`apply from: "http://my.org/path/to/features-1.0.gradle"` // these are not cached, so if url is not accessible the build will fail
* There is currently a pull request open to allow caching of plugins
* should be used with care, only useful when you really need that push functionality

### Binary Plugins
`apply plugin: 'plugin'`
They are classes that implement `Plugin<Project>` and have an `apply` method.
#### Two ways to apply them
* Using class instance
** apply plugin: org.foo.plugins.MyPlugin
* Using plugin ID
** apply plugin: "org.foo.my-plugin"
The plugin ID is set in META-INF/gradle-plugins/<plugin id>.properties
And the properties file has implementation-class=org.foo.plugins.MyPlugin
Allows the plugin author to change the class or package without affecting the users because they will just use the id

More flexible
```
buildscript {
   classpath 'com.foo:bar:1.2'
}
apply plugin: 'com.foo.bar'
```

Instead of doing `apply plugin:` it is now preferred to use the `plugins` block, which also goes to the Gradle Portal
```
plugins {
  id "com.foo.bar" version "1.2"
}
```

In settings.gradle you can add other plugin repositories
```
pluginManagement {
  repositories {
    // add other repositories
    gradlePluginPortal()
  }
}
```

## Gradle File Types
* FileCollection: flattened set of files (e.g. classpath)
* FileTree (extends FileCollection): hierarchy of files (e.g. directory)

Project methods:
* files() -> FileCollection
* fileTree() -> FileTree
* zipTree() -> ZipFileTree
* tarTree() -> TarFileTree

Features:
* path representations
* ant integrations
* relative paths are resolved against the root project
* additive (you can add/subtract them)
* lazily evaluated
* Buildable - you can tie the creation of the file object to a task `builtBy`

## Misc File Stuff
* Delete
** Delete task (type: Delete)
** delete method
* Copy
** Copy task (type: Copy)
** copy method
*** no up-to-date check, prefer Copy task whenever possible
* mkdir method
* there is no move task/method
* no jar/zip/tar methods

## Ant integration
myFileTree.addToAntBuilder(ant, "fileset")
