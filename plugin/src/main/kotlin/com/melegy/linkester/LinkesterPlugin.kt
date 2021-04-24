package com.melegy.linkester

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.AndroidBasePlugin
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.GradleException
import org.gradle.api.DomainObjectSet
import java.util.Locale

internal class LinkesterPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        var configured = false
        project.plugins.withType(AndroidBasePlugin::class.java) {
            configured = true
            configureLinkester(project)
        }
        project.afterEvaluate {
            check(configured) {
                "The Linkester Android Gradle plugin can only be applied to an Android project."
            }
        }
    }

    private fun configureLinkester(project: Project) {
        val extension = project.extension
        val variants = extension.variants

        variants.all { variant ->
            if (variant.buildType.isDebuggable) {
                val outputDir =
                    project.file("${project.buildDir}/generated/res/linkester/${variant.dirName}")

                val task = project.tasks
                    .create(
                        "process${variant.name.capitalize(Locale.ENGLISH)}Linkester",
                        LinkesterDeepLinksCollectorTask::class.java
                    )
                task.intermediateDir = outputDir
                variant.registerGeneratedResFolders(project.files(outputDir).builtBy(task))
            }
        }
    }

    private val Project.extension: BaseExtension
        get() = project.extensions.findByType(BaseExtension::class.java)
            ?: throw error("Project $name is not an Android project.")

    private val BaseExtension.variants: DomainObjectSet<out BaseVariant>
        get() = when (this) {
            is AppExtension -> applicationVariants
            is LibraryExtension -> libraryVariants
            else -> throw GradleException("Unsupported BaseExtension type! ${javaClass::getSimpleName}")
        }
}
