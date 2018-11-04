# Problem details for Java

[![Build Status](https://travis-ci.org/malczuuu/problem4j.svg?branch=master)](https://travis-ci.org/malczuuu/problem4j)

Simple Java library implementing [RFC7807](https://tools.ietf.org/html/rfc7807).

## How to setup

1.  Include dependencies.

    Add [JitPack](https://jitpack.io/) dependency repository.

    ```groovy
    repositories {
        maven { url "https://jitpack.io/" }
    }
    ```

    Add `problem4j` package.

    ```groovy
    dependencies {
        implementation "io.github.malczuuu.problem4j:problem4j-core:{10-digits-of-commit-hash}"
        implementation "io.github.malczuuu.problem4j:problem4j-jackson:{10-digits-of-commit-hash}"
    }
    ```

    Feel free to use any class from `io.github.malczuuu.problem4j.core` and `io.github.malczuuu.problem4j.jackson` packages. For integration with Spring Framework check [this instruction](How-to-setup-with-Spring.md).

## How to setup with Spring

1.  Include dependencies.

    Add `problem4j` package.

    ```groovy
    dependencies {
        implementation "io.github.malczuuu.problem4j:problem4j-spring:{10-digits-of-commit-hash}"
    }
    ```

    This package depends on `problem4j-core` and `problem4j-jackson` in appropriate versions, so no need to include them implicitly.

2.  Create configuration.

    ```java
    import org.springframework.context.annotation.Configuration;
    import io.github.malczuuu.problem4j.spring.EnableProblem;
    
    @Configuration
    @EnableProblem
    public class ProblemConfiguration {
    }
    ```

    You can change default responses on predefined exception by declaring custom bean of type `ProblemSupplier`.

3.  Throw `ProblemException` or it's subclasses from anywhere in your REST methods. All errors will be automatically handled and status will be returned to user.
