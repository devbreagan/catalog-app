plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.gbreagan.catalog"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gbreagan.catalog"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // Load value from .properties file instead
            buildConfigField("String", "API_URL", "\"https://www.freetogame.com/api/\"")
        }
        debug {
            buildConfigField("String", "API_URL", "\"https://www.freetogame.com/api/\"")
//            buildConfigField("String", "API_URL", "\"http://localhost:3000/api\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.lifecycle.runtime.compose.android)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.jetbrains.kotlin.serialization)
    implementation(libs.androidx.navigation.compose)

    //retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.converter.gson)
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.google.code.gson)

    // hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    ksp(libs.hilt.android.compiler)

    // coil
    implementation(libs.coil.compose)

    // room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)

    // paging
    implementation(libs.androidx.paging.compose)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation (libs.mockk)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.androidx.paging.common)

}