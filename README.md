This is a project that reproduces a compilation error in Kotlin 1.1.4

# Environment

* Android Studio 3.0 Beta 2
* Kotlin 1.1.4

# Bug

When a `java-library` depends on another `java-library`, the kotlin sources are not added to the classpath.
E.g. if libA depends on libB, `libB/build/classes/java/main/` will be added to the classpath of `libA` but `libB/build/classes/kotlin/main/` will not

The difference seems to be that, in kotlin `1.1.3`, both java and kotlin classes were under the `build/classes/java/main` folder but now they are separated in `kotlin` and `java` folders

# How to Reproduce

Either run
```
./repro.sh
```

or
```
./gradlew clean :libA:assemble
```

This demo projects fails to compile on kotlin `1.1.4` but it does work if you change the version to `1.1.3-2` in the root `build.gradle`

the error should look something like this:

```
[...]/libA/src/main/java/com/developerphil/liba/HelloWorld.java:3: error: cannot find symbol
import com.developerphil.libb.Greeter;
                             ^
  symbol:   class Greeter
  location: package com.developerphil.libb
[...]/libA/src/main/java/com/developerphil/liba/HelloWorld.java:8: error: cannot find symbol
        return "Hello " + new Greeter().getWho();
                              ^
  symbol:   class Greeter
  location: class HelloWorld
2 errors
1 warning
```