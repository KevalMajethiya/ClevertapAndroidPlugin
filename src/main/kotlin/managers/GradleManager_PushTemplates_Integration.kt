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
import util.Constants.FCM_NOTIFICATION

import javax.swing.*
import java.io.FileNotFoundException
import java.util.stream.Stream

class GradleManager_PushTemplates_Integration(private val project: Project) {

    private var buildGradle: Document? = null
    private var buildGradleproject: Document? = null
    private var modules = arrayOf<Any>()

    private var projectBaseDir: VirtualFile? = null
    private var PT_sdk:Boolean=false
    private var maven_exist:Boolean=false
    private var PT_dependency:Boolean=false

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
            buildGradleproject = FileDocumentManager.getInstance().getDocument(gradleVirtualFilenew!!)
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
            var line = documentText[i]
            if(line.contains("implementation 'com.clevertap.android:push-templates"))
            {
                PT_sdk=true

            }
            if(line.contains("implementation 'com.github.KevalMajethiya:PushTemplateLibrary"))
            {
                PT_dependency=true

            }
        }
        val documentTextnew=buildGradleproject!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in documentTextnew.indices)
        {
            val line = documentTextnew[i]

            if(line.contains("maven { url 'https://jitpack.io' }"))
            {
                maven_exist=true

            }
        }
    }
    fun addDependency( actionEvent: AnActionEvent) {
        checkbeforeinsertion()
       // if(codeexist==false) {
            val documentText = buildGradle!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val documentTextnew=buildGradleproject!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            val sb = StringBuilder()
            val sb2= StringBuilder()
            val sb3= StringBuilder()

            for (i in documentText.indices) {
                val line = documentText[i]

                sb
                    .append(line)
                    .append("\n")
                if(PT_sdk==false) {
                    if (line.contains(Constants.DEPENDENCIES)) {
                        if (line.contains("{")) {
                            sb
                                .append("\timplementation 'com.clevertap.android:push-templates:0.0.8'")
                                .append("   //added by CleverTap Assistant")
                                .append("\n")
                            PT_sdk = true
                        }
                    }
                }
                if (PT_dependency == false){
                    if (line.contains(Constants.DEPENDENCIES)) {
                        if (line.contains("{")) {
                            sb
                                .append("\timplementation 'com.github.KevalMajethiya:PushTemplateLibrary:0.1.0'")
                                .append("   //added by CleverTap Assistant")
                                .append("\n")
                            PT_dependency = true
                        }
                    }
                }
            }
            for (i in documentTextnew.indices)
            {
                val line = documentTextnew[i]

                sb2
                    .append(line)
                    .append("\n")
                if(!maven_exist) {
                    if (line.contains("allprojects {")) {
                        for (j in i + 1..documentTextnew.size - 1) {

                            val line1 = documentTextnew[j]
                            sb3
                                .append(line1)
                                .append("\n")

                            if (line1.contains("repositories {")) {
                                sb3
//                                .append(line1)
//                                .append("\n")
                                    .append("        maven { url 'https://jitpack.io' }")
                                    .append("   //added by CleverTap Assistant")
                                    .append("\n")
                                maven_exist=true


                            }


                        }
                        break

                    }
                }

                //break

            }



            writeToProjectGradle(sb2,sb3,actionEvent)
            writeToGradle(sb, actionEvent)
      //  }
    }

    private fun writeToGradle(stringBuilder: StringBuilder, actionEvent: AnActionEvent) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { buildGradle!!.setText(stringBuilder) }
            syncProject(actionEvent)
        }
    }
    private fun writeToProjectGradle(stringBuilder1: StringBuilder,stringBuilder2: StringBuilder, actionEvent: AnActionEvent) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { buildGradleproject!!.setText(stringBuilder1.append(stringBuilder2)) }
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
