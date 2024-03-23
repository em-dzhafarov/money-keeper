import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.hilt() {
    impl(Dependency.hilt)
    kapt(Dependency.hiltKapt)
}

fun DependencyHandler.room() {
    impl(Dependency.roomKtx)
    impl(Dependency.roomRuntime)
    ksp(Dependency.roomKsp)
}

fun DependencyHandler.navigation() {
    impl(Dependency.navKtx)
    impl(Dependency.navHilt)
}

fun DependencyHandler.compose() {
    impl(platform(Dependency.compose))
    impl(Dependency.composeUi)
    impl(Dependency.composeGraphics)
    impl(Dependency.composeTooling)
    impl(Dependency.composeMaterial3)
}

fun DependencyHandler.accompanistNavigation() {
    impl(Dependency.accompanistNavigation)
}

fun DependencyHandler.accompanistSystemUiController() {
    impl(Dependency.accompanistSystemUiController)
}

fun DependencyHandler.lifecycle() {
    impl(Dependency.lifecycle)
}

fun DependencyHandler.splash() {
    impl(Dependency.splash)
}

fun DependencyHandler.dataStore() {
    impl(Dependency.dataStore)
}

fun DependencyHandler.gson() {
    impl(Dependency.gson)
}

fun DependencyHandler.viewModel() {
    impl(Dependency.viewModel)
}

fun DependencyHandler.coroutines() {
    impl(Dependency.coroutines)
}
