import org.gradle.internal.impldep.junit.runner.Version.id

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "quest"
include(":app")
include(":config")
include(":analytics")
include(":analytics:api")
include(":config:api")
include("logger")
include("logger:api")
include("logger:impl")
include("network")
include("lifecycle")
include("lifecycle:api")
include("share")
include("share:api")
include("serialization")
include(":theme")
include("time")
include("time:api")
include("time:impl")
include("share:impl")
include(":post")
include(":post:ui")
include(":post:domain")
include(":post:data")