import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
    id("com.adarshr.test-logger") version "4.0.0"

    kotlin("jvm") version "2.1.0"
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "com.adarshr.test-logger")

    repositories {
        mavenCentral()
    }

    testing {
        suites {
            // Configure the built-in test suite
            val test by getting(JvmTestSuite::class) {
                // Use Kotlin Test test framework
                useKotlinTest()
            }
        }
    }
}

testlogger {
    theme = ThemeType.MOCHA
}
