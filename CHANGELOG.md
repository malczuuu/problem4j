# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- **[problem4j-core]** Add `Problem` class with builder.
- **[problem4j-core]** Add common `Problem`-based exceptions.
- **[problem4j-jackson]** Add Jackson's `ProblemModule` for (de)serialization of `Problem` instances.
- **[problem4j-spring]** Default exception handler which resolves common errors as `Problem` instances.
- **[problem4j-spring]** `ProblemSupplier` for converting common Spring exceptions to `Problem` instances.
- **[problem4j-spring]** Activating library via `@EnableProblem` annotation.

<!--
### Changed
### Deprecated
### Removed
### Fixed
### Security
-->