# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- [PathFiles](src/main/java/io/github/mjaroslav/sharedjava/io/PathFiles.java) utility class.
- [Colors](src/main/java/io/github/mjaroslav/sharedjava/format/Colors.java) utility class.
- [Bits](src/main/java/io/github/mjaroslav/sharedjava/format/Bits.java) utility class.
- [LazySupplier](src/main/java/io/github/mjaroslav/sharedjava/function/LazySupplier.java) realization
  for `java.util.function.Supplier`.
- Tuples — interfaces for _modifiable_ groups of objects also have number primitive and common realizations:
    - [Unit](src/main/java/io/github/mjaroslav/sharedjava/tuple/Unit.java) for one object.
    - [Pair](src/main/java/io/github/mjaroslav/sharedjava/tuple/Pair.java) for two objects.
    - [Triplet](src/main/java/io/github/mjaroslav/sharedjava/tuple/Triplet.java) for three objects.
    - `Simple` prefix for common realization
      (e.g. [SimpleTriplet](src/main/java/io/github/mjaroslav/sharedjava/tuple/triplet/SimpleTriplet.java)) and first
      character type name for number primitives
      (e.g. [DPair](src/main/java/io/github/mjaroslav/sharedjava/tuple/pair/DPair.java)
      for `Pair<Double, Double>` realization). In additional, use `Delegating` prefix for realizations that provide
      changing
      `toString`, `hashCode` and `equals` methods by functions
      (e.g. [DelegatingUnit](src/main/java/io/github/mjaroslav/sharedjava/tuple/unit/DelegatingUnit.java)).
- [ReflectionHelper](src/main/java/io/github/mjaroslav/sharedjava/reflect/ReflectionHelper.java) with some hacky
  reflection utils.
- [DelegatingSet](src/main/java/io/github/mjaroslav/sharedjava/util/DelegatingSet.java) for delegating equals and
  hashCode.
- [DelegatingMap](src/main/java/io/github/mjaroslav/sharedjava/util/DelegatingMap.java) for delegating equals and
  hashCode.
- [Property](src/main/java/io/github/mjaroslav/sharedjava/util/Property.java) JFX-like Property.

[unreleased]: https://github.com/MJaroslav/Shared-Java/
