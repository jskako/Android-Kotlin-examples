import org.gradle.api.JavaVersion

object Build {
    const val javaVersion = 11
    val javaCompileVersion = JavaVersion.VERSION_11
    private const val androidBuildToolsVersion = "8.0.0"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.kotlinVersion}"

    private const val hiltAndroidGradlePluginVersion = "2.45"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"
}