import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.android.lib) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.hilt.kapt) apply false
    alias(libs.plugins.ksp) apply false
}

fun BaseExtension.baseConfig() {

    compileSdkVersion(AppProject.compileSdk)

    defaultConfig.apply {
        namespace = AppProject.namespace
        minSdk = AppProject.minSdk
        targetSdk = AppProject.targetSdk
        testInstrumentationRunner = AppProject.testRunner
    }

    compileOptions.apply {
        sourceCompatibility = AppProject.java
        targetCompatibility = AppProject.java
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = AppProject.java.toString()
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