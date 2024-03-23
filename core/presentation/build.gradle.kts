plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.core.presentation"
}

dependencies {
    coroutines()
    viewModel()
}