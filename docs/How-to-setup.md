# How to setup

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
