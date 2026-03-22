// Top-level build file
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
//buildscript {
//    dependencies {
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
//    }
//}
buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}

//allprojects {
//    repositories {
//        google()
//        mavenCentral()
//    }
//}