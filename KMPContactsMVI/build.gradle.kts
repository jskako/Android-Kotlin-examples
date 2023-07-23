buildscript {
    dependencies {
        classpath(Square.sqlDelight)
        classpath("com.android.tools.build:gradle:8.0.2")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(Build.androidApplicationVersion).apply(false)
    id("com.android.library").version(Build.androidLibraryVersion).apply(false)
    kotlin("multiplatform").version(Build.kotlinMultiplatformVersion).apply(false)
    id("org.jetbrains.compose").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
