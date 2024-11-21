plugins {
    id("com.android.application")
}

apply(from = "classresguard.gradle")

android {
    namespace = "com.example.proguardres"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.proguardres"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions.add("type")
    flavorDimensions.add("version")

    signingConfigs {
        create("test1") {
            storeFile = file("../jks/test1.jks")
            storePassword = "123456"
            keyAlias = "key0"
            keyPassword = "123456"
        }
        create("test2") {
            storeFile = file("../jks/test2.jks");
            storePassword = "1234516"
            keyAlias = "key0"
            keyPassword = "123456"
        }
    }

    buildTypes {

        debug {
            isDebuggable = true
            isJniDebuggable = true
            isZipAlignEnabled = true
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-project.txt")
        }

        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-project.txt")
        }

    }

    productFlavors {
        create("google") {
            dimension = "type"
        }

        create("market") {
            dimension = "type"
        }

        create("ayome") {
            signingConfig = signingConfigs.getByName("test1")
            dimension = "version"
        }

        create("ayome1") {
            signingConfig = signingConfigs.getByName("test2")
            dimension = "version"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}