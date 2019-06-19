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
        testInstrumentationRunner = "com.github.gibbrich.dogs.MockTestRunner"
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

repositories {
    maven("https://jitpack.io")
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

    androidTestImplementation("com.android.support.test.espresso:espresso-contrib:3.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-intents:3.0.2")
    androidTestImplementation("org.mockito:mockito-android:2.25.0")
    androidTestImplementation("com.github.tmurakami:dexopener:2.0.2")
    androidTestImplementation("org.mockito:mockito-core:2.8.9")
    androidTestImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    kaptAndroidTest("com.google.dagger:dagger-compiler:${Deps.core.daggerVersion}")
}
