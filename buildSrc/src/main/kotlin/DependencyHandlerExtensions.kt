import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.implementProject(project: AppProject) {
    implementProjects(project)
}

fun DependencyHandler.implementProjects(vararg projects: AppProject) {
    for (project in projects) {
        impl(project(project.name))
    }
}

fun DependencyHandler.apiProject(project: AppProject) {
    apiProjects(project)
}

fun DependencyHandler.apiProjects(vararg projects: AppProject) {
    for (project in projects) {
        api(project(project.name))
    }
}

internal fun DependencyHandler.impl(what: Any) = with("implementation", what)
internal fun DependencyHandler.api(what: Any) = with("api", what)

private fun DependencyHandler.with(type: String, name: Any) = add(type, name)