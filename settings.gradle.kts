<<<<<<< HEAD
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
        // ADD THIS EXACT LINE HERE
        maven { url = uri("https://jitpack.io") }
    }
}
=======
rootProject.name = "flatbooking-backend"
>>>>>>> 2c0e7e79584e2f7d5a36a82b32a9a5f7e651e177

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
