plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = Build.javaVersion
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation("com.squareup.sqldelight:runtime:${Square.sqlDelightVersion}")
                implementation("com.squareup.sqldelight:coroutines-extensions:${Square.sqlDelightVersion}")
                implementation(Jetbrains.datetime)
                implementation("dev.icerock.moko:mvvm-core:${Icerock.mokoVersion}")
                implementation("dev.icerock.moko:mvvm-compose:${Icerock.mokoVersion}")
                implementation("dev.icerock.moko:mvvm-flow:${Icerock.mokoVersion}")
                implementation("dev.icerock.moko:mvvm-flow-compose:${Icerock.mokoVersion}")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:${Square.sqlDelightVersion}")
                implementation(AndroidX.appCompat)
                implementation(AndroidX.activityCompose)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
        val iosMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:${Square.sqlDelightVersion}")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    namespace = ProjectConfig.appId
    compileSdk = ProjectConfig.compileSdk
    defaultConfig {
        minSdk = ProjectConfig.minSdk
    }
    compileOptions {
        sourceCompatibility = Build.javaCompileVersion
        targetCompatibility = Build.javaCompileVersion
    }
}

sqldelight {
    database("ContactDatabase") {
        packageName = "com.jskako.kmpcontacts_mvi.database"
        sourceFolders = listOf("sqldelight")
    }
 }

dependencies {
    implementation(AndroidX.core)
    commonMainApi("dev.icerock.moko:mvvm-core:${Icerock.mokoVersion}")
    commonMainApi("dev.icerock.moko:mvvm-compose:${Icerock.mokoVersion}")
    commonMainApi("dev.icerock.moko:mvvm-flow:${Icerock.mokoVersion}")
    commonMainApi("dev.icerock.moko:mvvm-flow-compose:${Icerock.mokoVersion}")
}