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

include(":feature:newfeature")
include(":feature:newfeature:ui")
include(":feature:newfeature:presentation")
include(":feature:newfeature:domain")
include(":feature:newfeature:data")
include(":feature:newfeature:datasource")

include(":feature:secondfeature")
include(":feature:secondfeature:ui")
include(":feature:secondfeature:presentation")
include(":feature:secondfeature:domain")
include(":feature:secondfeature:data")
include(":feature:secondfeature:datasource")

include(":navigation")
