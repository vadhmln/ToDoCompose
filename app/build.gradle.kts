@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "ru.vdh.todocompose"
    compileSdk = 33

    defaultConfig {
        applicationId = "ru.vdh.todocompose"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core:ui"))
    implementation(project(":navigation"))
    implementation(project(":core:presentation"))
    implementation(project(":core:domain"))

    implementation(project(":feature:newfeature:ui"))
    implementation(project(":feature:newfeature:presentation"))
    implementation(project(":feature:newfeature:domain"))
    implementation(project(":feature:newfeature:data"))
    implementation(project(":feature:newfeature:datasource"))

    implementation(project(":feature:secondfeature:ui"))
    implementation(project(":feature:secondfeature:presentation"))
    implementation(project(":feature:secondfeature:domain"))
    implementation(project(":feature:secondfeature:data"))
    implementation(project(":feature:secondfeature:datasource"))

    implementation(libs.core.ktx)
    implementation(libs.android.material)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.material3.size)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Accompanist Navigation Animation
    implementation (libs.accompanist.navigation.animation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}