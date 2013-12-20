[Chapter 50. Dependency Management](http://www.gradle.org/docs/current/userguide/dependency_management.html)

* 50.3 Dependency configurations
  * [Definition of a configuration](example50.1.gradle)
  * [Accessing a configuration](example50.2.gradle)
  * [Configuration of a configuration](example50.3.gradle)
* 50.4 How to declare your dependencies
  * 50.4.1 External module dependencies
    * [Module dependencies](example50.4.gradle)
    * 50.4.1.2 Artifact only notation
      * [Artifact only notation](example50.5.gradle)
    * 50.4.1.3 Classifiers
      * [Dependency with classifier](example50.6.gradle)
      * [Iterating over a configuration](example50.7.gradle)
  * 50.4.2 Client module dependencies
    * [Client module dependencies](example50.8.gradle)
  * 50.4.3 Project dependencies
    * [Project dependencies](example50.9.gradle)
  * 50.4.4 File dependencies
    * [File dependencies](example50.10.gradle)
    * [Generated file dependencies](example50.11.gradle)
  * 50.4.5 Gradle API Dependency
    * [Gradle API dependencies](example50.12.gradle)
  * 50.4.6 Local Groovy Dependency
    * [Gradle's groovy dependencies](example50.13.gradle)
  * 50.4.7 Excluding transitive dependencies
    * [Excluding transitive dependencies](example50.14.gradle)
  * 50.4.8 Optional attributes
    * [Optional attributes of dependencies](example50.15.gradle)
    * [Collections and arrays of dependencies](example50.16.gradle)
  * 50.4.9 Dependency configurations
    * [Dependency configurations](example50.17.gradle)
    * [Dependency configurations for project](example50.18.gradle)
* 50.5 Working with dependencies
  * [Configuration.copy](example50.19.gradle)
  * [Accessing declared dependencies](example50.20.gradle)
  * [Configuration files](example50.21.gradle)
  * [Configuration files with spec](example50.22.gradle)
  * [Configuration copy](example50.23.gradle)
  * [Configuration copy vs Configuration files](example50.24.gradle)
* 50.6 Repositories
  * 50.6.1 Maven central repository
    * [Maven central repository](example50.25.gradle)
  * 50.6.2 Maven JCenter repository
    * [Maven JCenter repository](example50.26.gradle)
  * 50.6.3 Local Maven repository
    * [Local Maven repository](example50.27.gradle)
  * 50.6.4 Maven repositories
    * [Maven repositories](example50.28.gradle)
    * [Adding additional Maven repositories for JAR files](example50.29.gradle)
    * 50.6.4.1 Accessing password protected Maven repositories
      * [Accessing password protected Maven repository](example50.30.gradle)
  * 50.6.5 Flat directory repository
    * [Flat repository resolver](example50.31.gradle)
  * 50.6.6 Ivy repositories
    * [Ivy repository](example50.32.gradle)
    * 50.6.6.1 Defining custom patterns for an Ivy repository
      * [Ivy repository with pattern layout](example50.33.gradle)
    * 50.6.6.2 Ivy repository with Maven compatible layout
      * [Ivy repository with Maven compatible layout](example50.34.gradle)
    * 50.6.6.3 Defining different artifact and Ivy file locations for an Ivy repository
      * [Ivy repository with custom patterns](example50.35.gradle)
    * 50.6.6.4 Accessing password protected Ivy repositories
      * [Ivy repository](example50.36.gradle)
  * 50.6.7 Working with repositories
    * [Accessing a repository](example50.37.gradle)
    * [Configuration of a repository](example50.38.gradle)
  * 50.6.8 More about Ivy reoslvers
    * [Definition of a custom repository](example50.39.gradle)
  * 50.8.2 Using dependency resolve rules
    * 50.8.2.1 Modelling releaseable units
      * [Forcing consistent version for a group of libraries](example50.40.gradle)
    * 50.8.2.2 Implement a custom versioning scheme
      * [Using a custom versioning scheme](example50.41.gradle)
    * 50.8.2.3 Blacklisting a particular version with a replacement
      * [Blacklisting a version with a replacement](example50.42.gradle)
    * 50.8.2.4 Substituting a dependency module with a compatible replacement
      * [Changing dependency gruop and/or name at the resolution](example50.43.gradle)
  * 50.8.3 Enabling Ivy dynamic resolve mode
    * [Enabling dynamic resolve mode](example50.44.gradle)
  * 50.8.4 Component metadata rules
    * ['Latest' version selector](example50.45.gradle)
    * [Custom status scheme](example50.46.gradle)
* 50.9 The dependency cache
  * 50.9.3 Fine-tuned control over dependency caching
    * [Dynamic version cache control](example50.47.gradle)
    * [Changing module cache control](example50.48.gradle)
