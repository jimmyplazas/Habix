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
        maven {
            url = uri("https://androidx.dev/snapshots/builds/13540061/artifacts/repository")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://androidx.dev/snapshots/builds/13540061/artifacts/repository")
        }
    }
}

rootProject.name = "Habix"
include(":app")
include(":core")
include(":core:core_data")
include(":core:core_presentation")
include(":authentication")
include(":authentication:authentication_data")
include(":authentication:authentication_presentation")
include(":authentication:authentication_domain")
include(":habits")
include(":habits:habits_data")
include(":habits:habits_presentation")
include(":habits:habits_domain")
include(":onboarding")
include(":onboarding:onboarding_data")
include(":onboarding:onboarding_presentation")
include(":onboarding:onboarding_domain")
include(":settings")
include(":settings:settings_presentation")
include(":settings:settings_domain")
include(":core:core_ui")
include(":core:core_domain")
