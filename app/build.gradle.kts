import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    // Using direct ID to bypass the "None of the following candidates is applicable" error
    id("org.jetbrains.kotlin.android") 
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.playServices)
}

android {
    compileSdk = 35
    namespace = "com.venu.cdhpoc"

    defaultConfig {
        applicationId = "com.venu.cdhpoc"
        minSdk = 23
        targetSdk = 35
        versionCode = 1110100
        versionName = "1.11.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true 
        buildConfig = true
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "fcm-toolbox"
            keyPassword = "fcm-toolbox"
            storePassword = "fcm-toolbox"
            storeFile = file("debug-keystore.jks")
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    /* UI & Core */
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.google.android.material)

    /* Firebase */
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)

    /* DI & Database */
    implementation(libs.koin.android)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    /* Testing */
    testImplementation(libs.junit)
}
