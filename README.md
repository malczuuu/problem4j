# Problem details for Java

Simple Java package of libraries implementing [RFC7807][rfc7807].

> **Note** that this library was originally developed here as a monorepo. Right now it is separated into multiple
> repositories, each versioned individually. For old versions see other branches of this repository.

This project is not published to Maven Central Repository at the moment. Instead, it relies on [JitPack][jitpack] for
build and distribution of packages. The inspiration behind it was having unified tooling for personal projects and
seeing if and how it will evolve.

## Libraries

Each of the mentioned libraries is documented in its own `README.md` file.

### Problem4J Core [![](https://jitpack.io/v/malczuuu/problem4j-core.svg)](https://jitpack.io/#malczuuu/problem4j-core)

[`problem4j-core`](https://github.com/malczuuu/problem4j-core)

Minimal, framework-agnostic Java model of [RFC 7807][rfc7807] "Problem Details".

### Problem4J Jackson [![](https://jitpack.io/v/malczuuu/problem4j-jackson.svg)](https://jitpack.io/#malczuuu/problem4j-jackson)

[`problem4j-jackson`](https://github.com/malczuuu/problem4j-jackson)

Integration of `problem4j-core` with [Jackson's `ObjectMapper`][jackson].

### Problem4J Spring Web [![](https://jitpack.io/v/malczuuu/problem4j-spring-web.svg)](https://jitpack.io/#malczuuu/problem4j-spring-web)

[`problem4j-spring-web`](https://github.com/malczuuu/problem4j-spring-web)

Integration of `problem4j-core` (and `problem4j-jackson`) with Spring Boot REST exception handlers
(`@ControllerAdvice`).

[rfc7807]: https://tools.ietf.org/html/rfc7807

[jitpack]: https://jitpack.io/

[jackson]: https://github.com/FasterXML/jackson