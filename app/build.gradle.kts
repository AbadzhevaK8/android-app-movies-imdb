plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.abadzheva.movies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.abadzheva.movies"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // RxJava3 support for Retrofit
    implementation(libs.adapter.rxjava3)

    // RXJava
    implementation(libs.rxandroid)
    implementation(libs.rxjava)

    // Glide
    implementation(libs.glide)

    // Room
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    // RxJava3 support for Room
    implementation(libs.room.rxjava3)
}