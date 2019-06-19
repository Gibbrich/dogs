plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":core"))

    implementation(Deps.data.okhttp) {
        exclude(module = "okio")
    }

    implementation(Deps.data.okhttpLoggingInteceptor)
    implementation(Deps.data.retrofit)
    implementation(Deps.data.retrofitRxAdapter)
    implementation(Deps.data.retrofitConverter)
    implementation(Deps.data.gson)

    implementation(Deps.data.room)
    implementation(Deps.data.roomRxAdapter)
    kapt(Deps.data.roomCompiler)

    kapt(Deps.core.daggerCompiler)

    testImplementation(Deps.test.junit)
    testImplementation(Deps.test.mockk)

    androidTestImplementation(Deps.test.androidTestRunner)
    kaptAndroidTest(Deps.data.roomCompiler)
}
