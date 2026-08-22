/*
 * Copyright 2026 Vasyl Sabov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")

            if (path == ":") {
                configureSpotlessForRootProject()
            } else {
                configureSpotlessForModule()
            }
        }
    }

    private fun Project.configureSpotlessForModule() {
        extensions.configure<SpotlessExtension> {
            kotlin {
                target("src/**/*.kt")
                // General style comes from the root .editorconfig (ktlint_official code style).
                ktlint(libs.findVersion("ktlint").get().requiredVersion)
                licenseHeaderFile(rootDir.resolve("spotless/copyright.kt"))
            }
            format("kts") {
                target("*.kts")
                // Look for the first line that doesn't have a block comment (assumed to be the license)
                licenseHeaderFile(rootDir.resolve("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
                endWithNewline()
            }
        }
    }

    private fun Project.configureSpotlessForRootProject() {
        extensions.configure<SpotlessExtension> {
            kotlin {
                target("build-logic/convention/src/**/*.kt")
                ktlint(libs.findVersion("ktlint").get().requiredVersion)
                licenseHeaderFile(rootDir.resolve("spotless/copyright.kt"))
            }
            format("kts") {
                target("*.kts")
                target("build-logic/*.kts")
                target("build-logic/convention/*.kts")
                // Look for the first line that doesn't have a block comment (assumed to be the license)
                licenseHeaderFile(rootDir.resolve("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
                endWithNewline()
            }
        }
    }
}
