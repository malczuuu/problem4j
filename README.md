# Problem details for Java

[![Build Status](https://travis-ci.org/malczuuu/problem4j.svg?branch=master)](https://travis-ci.org/malczuuu/problem4j)
[![](https://jitpack.io/v/malczuuu/problem4j.svg)](https://jitpack.io/#malczuuu/problem4j)

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
        implementation "io.github.malczuuu.problem4j:problem4j-core:2.0.0"
    
        // Optionally
        implementation "io.github.malczuuu.problem4j:problem4j-jackson:2.0.0"
    }
    ```

    Feel free to use any class from `io.github.malczuuu.problem4j.core` (and 
    `io.github.malczuuu.problem4j.jackson`) packages. For integration with Spring Framework check 
    [this instruction](#how-to-setup-with-spring).

## How to setup with Spring

1.  Include dependencies.

    Add `problem4j` package.

    ```groovy
    dependencies {
        implementation "io.github.malczuuu.problem4j:problem4j-spring-web:2.0.0"
    }
    ```

2.  Create configuration.

    ```java
    import org.springframework.context.annotation.Configuration;
    import io.github.malczuuu.problem4j.spring.EnableProblem;
    
    @Configuration
    @EnableProblem
    public class ProblemConfiguration {
    }
    ```
