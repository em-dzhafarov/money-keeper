internal object Dependency {
    internal const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    internal const val hiltKapt = "com.google.dagger:hilt-compiler:${Versions.hiltKapt}"

    internal const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    internal const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    internal const val roomKsp = "androidx.room:room-compiler:${Versions.room}"

    internal const val splash = "androidx.core:core-splashscreen:${Versions.splash}"

    internal const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"

    internal const val gson = "com.google.code.gson:gson:${Versions.gson}"

    internal const val navKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navKtx}"
    internal const val navHilt = "androidx.hilt:hilt-navigation-compose:${Versions.navHilt}"

    internal const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
    internal const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    internal const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    internal const val compose = "androidx.compose:compose-bom:${Versions.compose}"
    internal const val composeUi = "androidx.compose.ui:ui"
    internal const val composeGraphics = "androidx.compose.ui:ui-graphics"
    internal const val composeTooling = "androidx.compose.ui:ui-tooling-preview"
    internal const val composeMaterial3 = "androidx.compose.material3:material3"
    internal const val composeFonts = "androidx.compose.ui:ui-text-google-fonts:${Versions.fonts}"

    internal const val accompanistSystemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    internal const val accompanistNavigation = "com.google.accompanist:accompanist-navigation-material:${Versions.accompanist}"
}