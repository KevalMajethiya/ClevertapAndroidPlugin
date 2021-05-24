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
import com.intellij.openapi.ui.SelectFromListDialog
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import org.apache.commons.lang.StringUtils
import util.Constants

import javax.swing.*
import java.io.FileNotFoundException
import java.util.stream.Stream

class Huawei_Gradle_Manager(private val project: Project) {

    private var buildGradle: Document? = null
    private var buildGradleproject: Document? = null
    private var modules = arrayOf<Any>()

    private var projectBaseDir: VirtualFile? = null

    //private var gradle_for_fcm:Boolean=false
    private var hms_sdk: Boolean = false
    private var hms_push: Boolean = false
    private var apply_plugin: Boolean = false
    private var classpath: Boolean = false
    private var buildscript_repo: Boolean = false
    private var allproject_repo: Boolean = false

    @Throws(FileNotFoundException::class)
    fun initBuildGradle(): Boolean {
        checkFilesExist()

        val toStringAspect = SelectFromListDialog.ToStringAspect { it.toString() }
        val gradleVirtualFile: VirtualFile?
        val gradleVirtualFilenew: VirtualFile?
        if (modules.size > 1) {
            val dialog = SelectFromListDialog(
                project,
                modules,
                toStringAspect,
                "Select Gradle Module",
                ListSelectionModel.SINGLE_SELECTION
            )
            val isOk = dialog.showAndGet()
            if (isOk) {
                gradleVirtualFile = projectBaseDir!!
                    .findChild(dialog.selection[0].toString())!!
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


    fun checkbeforeinsertion() {
        val documentText = buildGradle!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val documentTextnew =
            buildGradleproject!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()
        for (i in documentText.indices) {
            var line = documentText[i]

            if (line.contains("implementation 'com.clevertap.android:clevertap-hms-sdk:")) {
                hms_sdk = true
            }
            if (line.contains("apply plugin: 'com.huawei.agconnect'")) {
                apply_plugin = true
            }
            if (line.contains("implementation 'com.huawei.hms:push:")) {
                hms_push = true
            }
        }
        for (i in documentTextnew.indices) {
            var line = documentTextnew[i]
            if (line.contains("classpath 'com.huawei.agconnect:agcp:")) {
                classpath = true
            }
            if (line.contains("maven {url 'http://developer.huawei.com/repo/'}")) {
                buildscript_repo = true
            }
            if (line.contains("maven {url 'http://developer.huawei.com/repo/'}")) {
                allproject_repo = true
            }
        }

    }

    fun addDependency(actionEvent: AnActionEvent) {
        checkbeforeinsertion()
        val documentText = buildGradle!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val documentTextnew =
            buildGradleproject!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        val sb2 = StringBuilder()
        val sb3 = StringBuilder()
        //val sb4 = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]

            sb
                .append(line)
                .append("\n")
//            if(gradle_for_fcm==false && install_referrer==false) {
//                if (line.contains(Constants.DEPENDENCIES)) {
//                    if (line.contains("{")) {
//                        sb
//                            .append("\t${Constants.IMPLEMENTATION} '")
//                            .append(repository)
//                            .append("'")
//                            .append("   //added by CleverTap Assistant")
//                            .append("\n")
////                            .append("\t${Constants.IMPLEMENTATION} '")
////                            .append(Constants.PLAYSTORE)
//                            // .append("'\n")
//                            .append("\t${Constants.IMPLEMENTATION} '")
//                            .append(Constants.Intalrefrerrer)
//                            .append("'")
//                            .append("   //added by CleverTap Assistant")
//                            .append("\n")
//                        gradle_for_fcm= true
//                        playstore=true
//                        install_referrer=true
//                    }
//                }
//            }
            if (hms_sdk == false) {
                if (line.contains("dependencies")) {
                    if (line.contains("{")) {
                        sb
                            // .append("\t${Constants.IMPLEMENTATION} '")
                            .append("implementation 'com.clevertap.android:clevertap-hms-sdk:1.0.1' ")
                            .append("   //added by CleverTap Assistant")
                            .append("'\n")

                        hms_sdk = true
                    }
                }
            }
//            if(playstore==false) {
//                if (line.contains(Constants.DEPENDENCIES)) {
//                    if (line.contains("{")) {
//                        sb
//                            .append("\t${Constants.IMPLEMENTATION} '")
//                            .append(Constants.PLAYSTORE)
//                            .append("'\n")
//                    }
//                }
//            }
            if (hms_push == false) {
                if (line.contains(Constants.DEPENDENCIES)) {
                    if (line.contains("{")) {
                        sb
                            .append("implementation 'com.huawei.hms:push:5.0.0.300'")
                            .append("//added by CleverTap Assistant")
                            .append("\n")
                        hms_push = true
                    }
                }
            }
            if (apply_plugin == false) {
                if (i == documentText.lastIndex) {
                    sb
                        .append("apply plugin: 'com.huawei.agconnect'")
                        .append("   //added by CleverTap Assistant")
                    apply_plugin = true
                }
            }
        }

        for (i in documentTextnew.indices) {
            val line = documentTextnew[i]

            sb2
                .append(line)
                .append("\n")
//            if (buildscript_repo == false) {
//                if (line.contains("repositories")) {
//                    if (line.contains("{")) {
//                        sb2
//                            .append("\t\t//HUAWEI")
//                            .append("\n")
//                            .append("\t\tmaven {url 'http://developer.huawei.com/repo/'}")
//                            .append("   //added by CleverTap Assistant")
//                            .append("\n")
//                        buildscript_repo = true
//
//                    }
//                }
//            }
            if (classpath == false) {
                if (line.contains(Constants.DEPENDENCIES)) {
                    if (line.contains("{")) {
                        sb2
                            .append("\t\t//HUAWEI")
                            .append("\n")
                            .append("\t\tclasspath 'com.huawei.agconnect:agcp:1.3.1.300'")
                            .append("   //added by CleverTap Assistant")
                            .append("\n")
                        classpath = true

                    }
                }
            }
            //break


            if (allproject_repo == false) {
                if (line.contains("allprojects ")) {

                    for (j in i + 1..documentTextnew.size - 1) {

                        var line1 = documentTextnew[j]
                        sb3
                            .append(line1)
                            .append("\n")
                        if (line1.contains("repositories {")) {

                            sb3
//                                .append(line1)
//                                .append("\n")
                                .append("//HUAWEI")
                                .append("\n")
                                .append("\t\tmaven {url 'http://developer.huawei.com/repo/'}")
                                .append("   //added by CleverTap Assistant")
                                .append("\n")
                            //allproject_repo = true

                        }
                    }
                    break
                }
            }
            //break
        }


        writeToProjectGradle(sb2,sb3,actionEvent)
        writeToGradle(sb, actionEvent)
    }

    private fun writeToGradle(stringBuilder: StringBuilder, actionEvent: AnActionEvent) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { buildGradle!!.setText(stringBuilder) }
            syncProject(actionEvent)
        }
    }
    private fun writeToProjectGradle(stringBuilder: StringBuilder,stringBuilder2: StringBuilder, actionEvent: AnActionEvent) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { buildGradleproject!!.setText(stringBuilder.append(stringBuilder2)) }
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
