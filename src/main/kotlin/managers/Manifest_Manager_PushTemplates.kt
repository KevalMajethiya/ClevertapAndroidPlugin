package managers

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants
import java.io.FileNotFoundException

class Manifest_Manager_PushTemplates(private val project: Project) {

    private var androidManifest: Document? = null

    private var projectBaseDir: VirtualFile? = null
    private var PTNotificationIntentService:Boolean=false
    private var PTPushNotificationReceiver:Boolean=false
    private var PushTemplateReceiver:Boolean=false
    private var PTPushNotificationReceiver_service:Boolean=false


    private var receiver:Boolean=false


    @Throws(FileNotFoundException::class)
    fun initAndroidManifest(): Boolean {
        val basePath = project.basePath
        projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)
        val manifestVirtualFile: VirtualFile? = projectBaseDir!!
            .findChild(Constants.DEFAULT_MODULE_NAME)!!
            .findChild("src")!!
            .findChild("main")!!
            .findChild("AndroidManifest.xml")
        return if (manifestVirtualFile != null) {
            androidManifest = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
            true
        } else {
            false
        }
    }

    fun checkbeforeinsertion()
    {
        //val opp=launchingactivityname
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()
        for (i in documentText.indices)
        {
            var line = documentText[i]

            if(line.contains("    android:name=\"com.clevertap.pushtemplates.PTNotificationIntentService\""))
            {
                PTNotificationIntentService=true
            }
            if(line.contains("android:name=\"com.clevertap.pushtemplates.PTPushNotificationReceiver\""))
            {
                PTPushNotificationReceiver=true
            }
            if(line.contains("android:name=\"com.clevertap.pushtemplates.PushTemplateReceiver\""))
            {
                PushTemplateReceiver=true
            }

        }
    }

    fun addMetaDataContent() {
        checkbeforeinsertion()
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]


//                if (line.contains(Constants.APPLICATION)) {
//                    if (line.contains("/")) {
//                    sb.append("\t\t<service android:name=\".Push_Templates.pushtemplate_service\" />")
//                    }
//                }
                if (PTNotificationIntentService == false) {
                    if (line.contains(Constants.APPLICATION)) {
                        if (line.contains("/")) {
                            sb
                                .append("        <!-- Added by CleverTap Assistant-->")
                                .append("\n")
                                .append("        <service")
                                .append("\n")
                                .append("                 android:name=\"com.clevertap.pushtemplates.PTNotificationIntentService\"")
                                .append("\n")
                                .append("                 android:exported=\"false\">")
                                .append("\n")
                                .append("                 <intent-filter>")
                                .append("\n")
                                .append("                     <action android:name=\"com.clevertap.PT_PUSH_EVENT\"/>")
                                .append("\n")
                                .append("                 </intent-filter>")
                                .append("\n")
                                .append("         </service>")
                                .append("\n")
                            PTNotificationIntentService = true

                        }
                    }
                }
//            if (PTPushNotificationReceiver_service == false) {
//                if (line.contains(Constants.APPLICATION)) {
//                    if (line.contains("/")) {
//                        sb
//                            .append("        <!-- Added by CleverTap Assistant-->")
//                            .append("\n")
//                            .append("        <service")
//                            .append("\n")
//                            .append("                 android:name=\"com.clevertap.pushtemplates.PTPushNotificationReceiver\"")
//                            .append("\n")
//                            .append("                 android:exported=\"false\">")
//                            .append("\n")
//                            .append("                 <intent-filter>")
//                            .append("\n")
//                            .append("                      <action android:name=\"com.google.firebase.MESSAGING_EVENT\"/>")
//                            .append("\n")
//                            .append("                 </intent-filter>")
//                            .append("\n")
//                            .append("         </service>")
//                            .append("\n")
//                        PTPushNotificationReceiver_service = true
//
//                    }
//                }
//            }
            if (PTPushNotificationReceiver == false) {
                if (line.contains(Constants.APPLICATION)) {
                    if (line.contains("/")) {
                        sb
                            .append("        <!-- Added by CleverTap Assistant-->")
                            .append("\n")
//                            .append(repository)
//                            .append("\n")
                            .append("         <receiver")
                            .append("\n")
                            .append("                 android:name=\"com.clevertap.pushtemplates.PTPushNotificationReceiver\"")
                            .append("\n")
                            .append("                 android:exported=\"false\"")
                            .append("\n")
                            .append("                 android:enabled=\"true\">")
                            .append("\n")
                            .append("         </receiver>")
                            .append("\n")
                        PTPushNotificationReceiver =true


                    }
                }
            }
            if (PushTemplateReceiver == false) {
                if (line.contains(Constants.APPLICATION)) {
                    if (line.contains("/")) {
                        sb
                            .append("        <!-- Added by CleverTap Assistant-->")
                            .append("\n")
//                            .append(repository)
//                            .append("\n")
                            .append("         <receiver")
                            .append("\n")
                            .append("                 android:name=\"com.clevertap.pushtemplates.PushTemplateReceiver\"")
                            .append("\n")
                            .append("                 android:exported=\"false\"")
                            .append("\n")
                            .append("                 android:enabled=\"true\">")
                            .append("\n")
                            .append("         </receiver>")
                            .append("\n")
                        PushTemplateReceiver =true


                    }
                }
            }




            sb
                .append(line)
                .append("\n")
        }
        writeToManifest(sb)
    }

    private fun writeToManifest(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { androidManifest!!.setText(stringBuilder) }
        }
    }
}
