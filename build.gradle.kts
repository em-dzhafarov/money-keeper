import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.androidApp) version Versions.androidAppPlugin apply false
    id(Plugins.androidLibrary) version Versions.androidLibraryPlugin apply false
    id(Plugins.kotlinAndroid) version Versions.androidKotlinPlugin apply false
    id(Plugins.hilt) version Versions.hiltKapt apply false
    id(Plugins.ksp) version Versions.ksp apply false
}

fun BaseExtension.baseConfig() {

    compileSdkVersion(Config.compileSdk)

    defaultConfig.apply {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        testInstrumentationRunner = Config.testRunner
    }

    compileOptions.apply {
        sourceCompatibility = Versions.java
        targetCompatibility = Versions.java
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = Versions.java.toString()
        }
    }
}

fun PluginContainer.applyBaseConfig(project: Project) {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> {
                project.extensions
                    .getByType<AppExtension>()
                    .apply {
                        baseConfig()
                    }
            }
            is LibraryPlugin -> {
                project.extensions
                    .getByType<LibraryExtension>()
                    .apply {
                        baseConfig()
                    }
            }
        }
    }
}

subprojects {
    project.plugins.applyBaseConfig(project)
}