package managers

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.EmptyAction
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import org.apache.commons.lang.StringUtils
import util.Constants
import java.io.FileNotFoundException
import java.util.stream.Stream
import javax.swing.SwingUtilities

class GradleManager_Geofence(private val project: Project) {

    private var buildGradle: Document? = null

    private var modules = arrayOf<Any>()

    private var projectBaseDir: VirtualFile? = null
    private var geofence_sdk_exist:Boolean=false
    private var clevertap_sdk_exist:Boolean=false
    private var work_runtime_exist:Boolean=false
    private var androidx_concurrent_exist:Boolean=false
    private var play_services_location_permission:Boolean=false


    @Throws(FileNotFoundException::class)
    fun initBuildGradle(): Boolean {
        checkFilesExist()
        val gradleVirtualFile: VirtualFile?
        val gradleVirtualFilenew: VirtualFile?
        if (modules.size > 1) {
            val isHaveAppModule: String? = modules.find { it == Constants.DEFAULT_MODULE_NAME } as String
            if (isHaveAppModule != null && isHaveAppModule != "") {
                gradleVirtualFile = projectBaseDir!!
                    .findChild(isHaveAppModule)!!
                    .findChild("build.gradle")
            } else {
                return false
            }
        } else if (modules.size == 1) {
            gradleVirtualFile = projectBaseDir!!
                .findChild(modules[0] as String)!!
                .findChild("build.gradle")
        } else {
            gradleVirtualFile = projectBaseDir!!
                .findChild("build.gradle")
        }
        gradleVirtualFilenew = projectBaseDir!!.findChild("build.gradle")
        if (gradleVirtualFile != null) {

            buildGradle = FileDocumentManager.getInstance().getDocument(gradleVirtualFile)
        }
        return true
    }

    @Throws(FileNotFoundException::class)
    private fun checkFilesExist() {

        val basePath = project.basePath
        if (StringUtils.isEmpty(basePath)) {
            throw FileNotFoundException(Constants.ERROR_BASE_PATH_NOT_FOUND)
        }

        projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)
        if (projectBaseDir == null) {
            throw FileNotFoundException(Constants.ERROR_PROJECT_BASE_DIR_NOT_FOUND)
        }

        val virtualSettingsGradle = projectBaseDir!!.findChild("settings.gradle")
        if (virtualSettingsGradle != null) {
            val settingsGradle = FileDocumentManager.getInstance().getDocument(virtualSettingsGradle)
            if (settingsGradle != null) {
                modules = readSettingsGradle(settingsGradle)
            }
        } else if (projectBaseDir!!.findChild("build.gradle") == null) {
            throw FileNotFoundException(Constants.ERROR_BUILD_GRADLE_NOT_FOUND)
        }
    }

    fun checkbeforeinsertion()
    {
        //val opp=launchingactivityname
        val documentText = buildGradle!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()
        for (i in documentText.indices)
        {
            val line = documentText[i]
            if(line.contains("implementation 'com.clevertap.android:clevertap-geofence-sdk"))
            {
                geofence_sdk_exist=true
            }
            if(line.contains("implementation 'com.clevertap.android:clevertap-android-sdk"))
            {
                clevertap_sdk_exist=true
            }
            if(line.contains("implementation 'androidx.work:work-runtime"))
            {
                work_runtime_exist=true
            }
            if(line.contains("implementation 'androidx.concurrent:concurrent-futures"))
            {
                androidx_concurrent_exist=true
            }
            if(line.contains("implementation 'com.google.android.gms:play-services-location"))
            {
                play_services_location_permission=true
            }
        }
    }
    fun addDependency( actionEvent: AnActionEvent) {
        checkbeforeinsertion()
        val documentText = buildGradle!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]
            sb
                .append(line)
                .append("\n")
            if(!geofence_sdk_exist) {
                if (line.contains("dependencies {")) {
                    sb
                        .append("\timplementation 'com.clevertap.android:clevertap-geofence-sdk:1.0.2'")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    geofence_sdk_exist= true
                }
            }
            if(!clevertap_sdk_exist) {
                if (line.contains("dependencies {")) {
                    sb
                        .append("\timplementation 'com.clevertap.android:clevertap-android-sdk:4.1.0'")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    clevertap_sdk_exist= true
                }
            }
            if(!work_runtime_exist) {
                if (line.contains("dependencies {")) {
                    sb
                        .append("\timplementation 'androidx.work:work-runtime:2.3.4'")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    work_runtime_exist= true
                }
            }
            if(!androidx_concurrent_exist) {
                if (line.contains("dependencies {")) {
                    sb
                        .append("\timplementation 'androidx.concurrent:concurrent-futures:1.0.0'")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    androidx_concurrent_exist= true
                }
            }
            if(!play_services_location_permission) {
                if (line.contains("dependencies {")) {
                    sb
                        .append("\timplementation 'com.google.android.gms:play-services-location:17.0.0'")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    play_services_location_permission= true
                }
            }
        }


        // writeToProjectGradle(sb2,actionEvent)
        writeToGradle(sb, actionEvent)

}

private fun writeToGradle(stringBuilder: StringBuilder, actionEvent: AnActionEvent) {
    val application = ApplicationManager.getApplication()
    application.invokeLater {
        application.runWriteAction { buildGradle!!.setText(stringBuilder) }
        syncProject(actionEvent)
    }
}


// TODO do not allow this method called without invokeLater()
private fun syncProject(actionEvent: AnActionEvent) {
    val androidSyncAction = getAction("Android.SyncProject")
    val refreshAllProjectAction = getAction("ExternalSystem.RefreshAllProjects")

    if (androidSyncAction != null && androidSyncAction !is EmptyAction) {
        androidSyncAction.actionPerformed(actionEvent)
    } else if (refreshAllProjectAction != null && refreshAllProjectAction !is EmptyAction) {
        refreshAllProjectAction.actionPerformed(actionEvent)
    } else {
        SwingUtilities.invokeLater {
            Messages.showInfoMessage(
                Constants.ERROR_SYNC_FAILED,
                Constants.ERROR_TITLE_SYNC_FAILED
            )
        }
    }
}

private fun getAction(actionId: String): AnAction? {
    return ActionManager.getInstance().getAction(actionId)
}

private fun readSettingsGradle(settingsGradle: Document): Array<Any> {
    return Stream.of(*settingsGradle.text.split("'".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        .filter { s -> s.startsWith(":") }
        .map { s -> s.replace(":", "") }
        .toArray()
}
}
