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

## Dependency Management
* Configuration is a FileCollection
* Has rich API

### Transitive Dependencies
* Default version conflict resolution is newest
* Option to fail conflict resolution, e.g. configurations.myconf.resolutionStrategy.failOnVersionConflict()

### Forcing Versions
* Can be done in dependencies block
** explicitly adding hibernate in the configuration, and forcing a version
* Can be done in configurations.compile.resolutionStrategy.force
** not adding hibernate, but if it happens to be added, force it to a version

### Dependency Resolution Strategies
```
configurations.all {
  resolutionStrategy.eachDependency { DependencyResolveDetails details ->
    if (details.requested.group == 'org.gradle') {
      details.useVersion "1.4"
    }
    // details.requested.group, details.requested.name, details.requested.version
    // details.useVersion "1.4"
  }
}
```

### Dependency Reports
* `gradle dependencies [--configuration <name>]`
* `gradle dependencyInsight --dependency <name> --configuration <name>`


## Ivy Uploading
* uploading to ivy is built-in
* to upload to maven you need to apply plugin: 'maven'

## Uploading to Maven repository
### Install to Local Maven
* gradle install

## Extending Gradle
* extra properties
* extension objects
```
class MyExtension {
  String someProperty
}
extensions.create("myDSL", MyExtension)
extensions.maybeCreate // will only create if it doesn't exist yet
myDSL {
  someProperty = "someValue"
}
```

## Domain Object Container
* plugins
* configurations
* tasks
```
tasks.withType(Jar) // built-in filter
tasks.matching { task -> // custom filter
  task.name.startsWith("web")
}
tasks.withType(Jar).matching // filter chaining

tasks.all { // applies to all tasks
  doFirst { ... }
}
tasks.whenTaskAdded { task -> ... } // applies to tasks created after this rule was defined
```

## Tasks Inputs/Outputs
you can describe
* input/output files
* input/output directories
* input properties

```
class MyTask extends DefaultTask {
  @InputFile File text
  @InputFiles FileCollection path
  @InputDirectory File templates
  @Input String mode
  @OutputFile File result
  @OutputDirectory File transformedTemplates
  boolean verbose // ignored

  @TaskAction
  generate() { ... }
}
```

Input/Output API
```
ant.import "build.xml"
someAntTarget {
  inputs.files
  inputs.dir
  outputs.files
  outputs.dir
  outputs.upToDateWhen { task ->
  }
}
```

* Input files/dirs are verified to exist
** Disable with @Optional
* Output dirs are created before execution

## Inferred Task Dependencies

## Java Plugin
compileClasspath = sourceSets.main.output + configurations.testCompile
### Querying Source Sets
// They all return FileTree
* sourceSets.main.allJava
* sourceSets.main.resources
* sourceSets.main.allResource.matching { include ... }
// Returns a buildable FileCollection
* sourceSets.main.output

### Classes Tasks
* classes, testClasses
* They are lifecycle tasks used to aggregate other tasks like compile, processResources, etc.

### Test Task
* `forkEvery`: Custom fork frequency is available but it typically means there is a code smell (memory leak, etc)
* `scanForTestClasses`: can be set to false to disable automated test finding and then use `include` and `exclude` to specify a custom test path (`**/test/special**/*Test.class`)

#### Test Task Listeners
```
test {
  beforeTest { desc ->
  }
  afterTest { desc, result ->
  }
  afterSuite { desc, result ->
  }
}
```

## Lifecycle Tasks
Standard Java lifecycle tasks:
* clean
* classes
* test
* assemble
* check
* build

## Multi-Project Builds
gradle buildNeeded
gradle buildDependents

## Organizing our Build Logic

## Hooking into Gradle
init scripts
* gradle -I ci-init.gradle
* or put file in GRADLE_USER_HOME/init.gradle or ~/.gradle/init.d/*.gradle

sample init script
```
initscript {
  repositories { ... }
  dependencies {
    classpath ...
  }
}
gradle.startParameter...
gradle.addBuildListener...
```

```
gradle.taskGraph.whenReady { taskGraph -> ... }
gradle.taskGraph.beforeTask { task -> ... }
gradle.taskGraph.afterTask { task -> ... }
gradle.beforeProject { project -> ... }
gradle.afterProject { project -> ... }
gradle.addBuildListener(BuildListener listener)

public interface BuildListener { // or extends BuildAdapter if only needs to implement one of the methods
    void buildStarted(Gradle gradle);
    void settingsEvaluated(Settings settings);
    void projectsLoaded(Gradle gradle);
    void projectsEvaluated(Gradle gradle);
    void buildFinished(BuildResult result);
}
```
