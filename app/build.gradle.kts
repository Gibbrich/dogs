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
    implementation(project(":data"))
    implementation(project(":dogdetails"))

    implementation(Deps.common.appCompat)
    implementation(Deps.common.constraint)

    implementation(Deps.common.swipeRefresh)
    implementation(Deps.common.arch)
    implementation(Deps.common.vm)
    implementation(Deps.common.recycler)
    implementation(Deps.common.cardView)
    implementation(Deps.common.glide) {
        exclude("com.android.support")
    }

    kapt(Deps.core.daggerCompiler)

    testImplementation(Deps.test.junit)
    androidTestImplementation(Deps.test.androidTestRunner)
    androidTestImplementation(Deps.test.androidTestRules)
    androidTestImplementation(Deps.test.espresso)
}
