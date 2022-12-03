import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("com.adarshr.test-logger") version "3.2.0"
    id("org.jetbrains.kotlin.jvm") version "1.7.21"
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