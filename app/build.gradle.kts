plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.navigation.safeargs.kotlin)
    alias(libs.plugins.ksp.processor)
}

android {
    namespace = "com.littleapp.countries"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.littleapp.countries"
        minSdk = 24
        targetSdk = 37
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)              //Kotlin Activity
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.preference.ktx)                          //Shared Preference
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Layout
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)
    //Image
    implementation(libs.glide)                          //Glide Image
    ksp(libs.glide.ksp)                                 //Glide Compiler
    implementation(libs.glide.transformations)                            //Glide Blur
    //Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    //Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.core)    //Core
    implementation(libs.kotlinx.coroutines.android)  //Android
    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava2)
    //RxJava
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    //Other's
    implementation(libs.androidx.palette.ktx)
    implementation(libs.androidx.swiperefreshlayout)
    ksp(libs.kotlin.metadata.jvm)                       //Kotlin
}