plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
}

android {
    namespace = ProjectConfig.appId
    compileSdk = ProjectConfig.compileSdk
    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = Build.javaCompileVersion
        targetCompatibility = Build.javaCompileVersion
    }
    kotlinOptions {
        jvmTarget = Build.javaVersion
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:${Compose.composeVersion}")
    implementation("androidx.compose.ui:ui-tooling:${Compose.composeVersion}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Compose.composeVersion}")
    implementation("androidx.compose.foundation:foundation:${Compose.composeVersion}")
    implementation("androidx.compose.material:material:${Compose.composeVersion}")
    implementation("androidx.activity:activity-compose:${Compose.composeActivity}")
}