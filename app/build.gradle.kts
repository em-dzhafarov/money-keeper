import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "${AppProject.namespace}.moneykeeper"

    defaultConfig {
        applicationId = "${AppProject.namespace}.moneykeeper"
        versionCode = AppProject.versionCode
        versionName = AppProject.versionName

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        getByName("debug") {
            val props = loadProperties("${projectDir}/signing.properties")

            keyAlias = props.getProperty("keyAlias")
            keyPassword = props.getProperty("keyPassword")
            storeFile = file(props.getProperty("storeFile"))
            storePassword = props.getProperty("storePassword")
        }

        create("release") {
            val home = System.getProperty("user.home")
            val props = loadProperties("$home/signing.properties")

            keyAlias = props.getProperty("keyAlias")
            keyPassword = props.getProperty("keyPassword")
            storeFile = file("$home/${props.getProperty("storeFile")}")
            storePassword = props.getProperty("storePassword")
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("release")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementProjects(
        AppProject.Core,
        AppProject.Settings,
        AppProject.AboutApp,
        AppProject.DateTime,
        AppProject.Expense,
        AppProject.Home,
        AppProject.Filters,
        AppProject.Dashboard,
        AppProject.Search,
        AppProject.Notifications,
        AppProject.Profile
    )

    implementation(platform(libs.compose))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.navigation)
    implementation(libs.core.splash)
    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}

kapt {
    correctErrorTypes = true
}