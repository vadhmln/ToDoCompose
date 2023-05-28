pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "To Do Compose"
include(":app")
include(":core")
include(":feature")

include(":core:ui")
include(":core:presentation")
include(":core:domain")
include(":core:data")
include(":core:datasource")

include(":feature:todolist")
include(":feature:todolist:ui")
include(":feature:todolist:presentation")
include(":feature:todolist:domain")
include(":feature:todolist:data")
include(":feature:todolist:datasource")

include(":feature:todotask")
include(":feature:todotask:ui")
include(":feature:todotask:presentation")
include(":feature:todotask:domain")
include(":feature:todotask:data")
include(":feature:todotask:datasource")

include(":navigation")
include(":common")
include(":core:database")


