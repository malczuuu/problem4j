# How to setup with Spring

1.  Include dependencies.

    Add [JitPack](https://jitpack.io/) repository.

    ```groovy
    repositories {
        maven { url "https://jitpack.io/" }
    }
    ```

    Add `problem4j` package.

    ```groovy
    dependencies {
        implementation "com.github.malczuuu.problem4j:problem4j-spring:{version}"
    }
    ```

    This package depends on `problem4j-core` and `problem4j-jackson` in appropriate versions, so no need to include them implicitly.

2.  Create configuration.

    ```java
    import org.springframework.context.annotation.Configuration;
    import EnableProblem;
    
    @Configuration
    @EnableProblem
    public class ProblemConfiguration {
    }
    ```

    You can change default responses on predefined exception by declaring custom bean of type `ProblemSupplier`.

3.  Throw `ProblemException` or it's subclasses from anywhere in your REST methods. All errors will be automatically handled and status will be returned to user.
