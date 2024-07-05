import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id(Plugins.androidApp)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
    id(Plugins.ksp)
}

android {
    namespace = "${Config.namespace}.moneykeeper"

    defaultConfig {
        applicationId = "${Config.namespace}.moneykeeper"
        versionCode = 1
        versionName = "1.0"

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
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
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
        AppProject.AboutApp,
        AppProject.DateTime,
        AppProject.Settings,
        AppProject.Expense,
        AppProject.Home,
        AppProject.Filters,
        AppProject.Dashboard,
        AppProject.Search,
        AppProject.Notifications,
        AppProject.Profile
    )

    splash()
    compose()
    navigation()
    accompanistNavigation()
    hilt()
}

kapt {
    correctErrorTypes = true
}