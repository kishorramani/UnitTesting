plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.kishorramani.unittesting"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kishorramani.unittesting"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }

    sourceSets{
        /*androidTest{
            java.src
        }*/
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Room testing
    testImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.androidx.room.testing)

    //Kotlin coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    //Coroutine testing
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlinx.coroutines.test)

    //Android Architecture component
    testImplementation(libs.androidx.core.testing)

    //Android Architecture component testing
    androidTestImplementation(libs.androidx.core.testing)

    androidTestImplementation(libs.turbine)

    //Mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)
    implementation(libs.gson)

    //glide
    implementation(libs.glide)

    //mock web server
    testImplementation (libs.mockwebserver)


    // Lifecycle components
    /*
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.8.7")*/

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.core.ktx)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.intents)


}