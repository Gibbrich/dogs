plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.github.gibbrich.dogs"
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
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":core"))
    implementation(project(":dogdetails"))
    implementation(project(":dogslist"))

    implementation(Deps.common.appCompat)
    implementation(Deps.common.constraint)
    implementation(Deps.app.navigationFragment)
    implementation(Deps.app.navigationUi)

    kapt(Deps.core.daggerCompiler)

    // todo - here and in other build files - clean up dependencies
    implementation("androidx.core:core-ktx:1.0.0-rc02")
}
