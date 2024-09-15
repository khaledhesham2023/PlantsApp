import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.khaledamin.plantsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.khaledamin.plantsapp"
        minSdk = 21
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        val keystoreFile = project.rootProject.file("apikeys.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())
        val apiKey = properties.getProperty("API_KEY") ?: ""
        buildConfigField(type = "String", name = "API_KEY", value = apiKey)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.glide)
    implementation(libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.kotlinx.coroutines.android.v152)
    implementation(libs.hilt.android)
    annotationProcessor (libs.hilt.compiler)

    // For instrumentation tests
    androidTestImplementation(libs.hilt.android.testing)
    androidTestAnnotationProcessor(libs.hilt.compiler)

    // For local unit tests
    testImplementation(libs.hilt.android.testing)
    testAnnotationProcessor(libs.hilt.compiler)
}

kapt {
    correctErrorTypes = true
}