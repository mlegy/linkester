package com.melegy.linkester

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.AppExtension
import com.android.build.api.dsl.AndroidSourceSet
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.BaseVariant
import com.melegy.linkester.internal.constants.PluginConstants.TARGET_DIRECTORY
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.GradleException
import org.gradle.api.Task
import org.gradle.api.DomainObjectCollection
import org.gradle.api.DomainObjectSet
import java.io.File

internal class LinkesterPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extension
        val mainSourceSet = extension.sourceSets.getByName(MAIN_SOURCE_SET)
        val variants = extension.variants
        with(project) {
            registerTask(createTask(LinkesterDeepLinksCollectorTask::class.java), variants)
            addTargetDirectoryToSourceSet(mainSourceSet)
        }
    }

    private val Project.extension: BaseExtension
        get() {
            val android = project.extensions.findByType(com.android.build.gradle.BaseExtension::class.java)
            if (android != null) return android
            else throw GradleException("Project $name is not an Android project")
        }

    private fun <V : BaseVariant> Project.registerTask(
        taskToBeRegistered: Task,
        variants: DomainObjectCollection<V>
    ) {
        afterEvaluate {
            variants.all { variant ->
                tasks.all { task ->
                    if (task.isCompileKotlinTask(variant)) task.dependsOn(taskToBeRegistered)
                }
            }
        }
    }

    private val BaseExtension.variants: DomainObjectSet<out BaseVariant>
        get() = when (this) {
            is AppExtension -> applicationVariants
            is LibraryExtension -> libraryVariants
            else -> throw GradleException("Unsupported BaseExtension type! ${javaClass::getSimpleName}")
        }


    private fun Project.addTargetDirectoryToSourceSet(sourceSet: AndroidSourceSet) {
        sourceSet.res.srcDir("$buildDir${File.separator}$TARGET_DIRECTORY")
    }

    private fun <T : Task> Project.createTask(taskType: Class<T>): Task {
        val taskName = taskType.simpleName.decapitalize()
        return tasks.create(taskName, taskType)
    }

    private fun <T : BaseVariant> Task.isCompileKotlinTask(variant: T) =
        name == "compile${variant.name.capitalize()}Kotlin"

    companion object {
        private const val MAIN_SOURCE_SET = "main"
    }
}
