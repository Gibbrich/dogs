# dogs

## General information
App allows user to download dogs pictures. On picture click user can check detailed picture and breed. App caches dowloaded data, so user can use it offline.

## Architecture:
- App divided into several modules, so each feature independent on another features. Thus, developers can experiment with different technologies in different modules not affecting entire codebase.
- Basic architecture pattern is MVVM, implemented by using Architecture components.
- Dependencies and build logic are written in Kotlin script and should be placed in buildSrc module, which improves readability/comprehensibility of custom build logic.

## What can be improved/added from technical perspective:
- Transition animation from breeds list to breed detail, using shared element transition for better UX
- More UI and integration tests

## How to build and install app
Installs the Debug build

    ./gradlew installDebug

## How to test
- Connect Android device or launch emulator

- Install and run instrumentation tests on connected devices

      ./gradlew connectedAndroidTest

- Run unit tests

      ./gradlew test
