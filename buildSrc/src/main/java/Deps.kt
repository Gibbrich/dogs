object Deps {
    private const val kotlinVersion = "1.3.0"
    const val gradle = "com.android.tools.build:gradle:3.3.2"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

    object common {
        const val constraint = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
        const val arch = "androidx.arch.core:core-common:2.0.0"
        const val vm = "android.arch.lifecycle:extensions:1.1.1"
        const val recycler = "androidx.recyclerview:recyclerview:1.0.0"
        const val cardView = "androidx.cardview:cardview:1.0.0"
        const val appCompat = "androidx.appcompat:appcompat:1.0.0-beta01"
        
        const val glide = "com.github.bumptech.glide:glide:4.9.0"
    }

    object test {
        const val mockk = "io.mockk:mockk:1.9.3.kotlin12"
        const val junit = "junit:junit:4.12"
        const val androidTestRunner = "com.android.support.test:runner:1.0.2"
        const val androidTestRules = "com.android.support.test:rules:1.0.2"
        const val espresso = "androidx.test.espresso:espresso-core:3.1.0-alpha4"
        const val mockitoAndroid = "org.mockito:mockito-android:2.25.0"
        const val mockitoCore = "org.mockito:mockito-core:2.8.9"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"
        const val dexOpener = "com.github.tmurakami:dexopener:2.0.2"
    }

    object core {
        const val daggerVersion = "2.13"
        private const val rx2JavaVersion = "2.1.6"
        private const val rx2AndroidVersion = "2.0.1"

        const val rx2 = "io.reactivex.rxjava2:rxjava:$rx2JavaVersion"
        const val rx2Android = "io.reactivex.rxjava2:rxandroid:$rx2AndroidVersion"

        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"

        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    }


    object data {
        private const val okhttpVersion = "3.4.1"
        private const val retrofitVersion = "2.1.0"
        private const val gsonVersion = "2.7"
        private const val roomVersion = "1.1.1"

        const val retrofitRxAdapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
        const val okhttpLoggingInteceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val gson = "com.google.code.gson:gson:$gsonVersion"
        const val room = "android.arch.persistence.room:runtime:$roomVersion"
        const val roomCompiler = "android.arch.persistence.room:compiler:$roomVersion"
        const val roomRxAdapter = "android.arch.persistence.room:rxjava2:$roomVersion"
    }
}