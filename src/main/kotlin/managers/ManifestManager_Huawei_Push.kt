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

class ManifestManager_Huawei_Push(private val project: Project) {

    private var androidManifest: Document? = null

    private var projectBaseDir: VirtualFile? = null
    private var huawei_service_name:Boolean=false
    private var firebase_messaging_auto_init_enabled:Boolean=false
    private var firebase_analytics_collection_enabled:Boolean=false
    private var fcm_sender_id:Boolean=false
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
            if(line.contains("fcm.FcmMessageListenerService"))
            {
                huawei_service_name=true
            }

        }
    }

    fun addMetaDataContent() {
        checkbeforeinsertion()
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]


            if(huawei_service_name==false) {
                if (line.contains(Constants.APPLICATION)) {
                    if (line.contains("/")) {
                        sb
                            .append("        <!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("        <service")
                            .append("\n")
                            .append("             android:name=\".MyPushService\"")
                            .append("\n")
                            .append("             android:exported=\"false\">")
                            .append("\n")
                            .append("             <intent-filter>")
                            .append("\n")
                            .append("                 <action android:name=\"com.huawei.push.action.MESSAGING_EVENT\"/>")
                            .append("\n")
                            .append("             </intent-filter>")
                            .append("\n")
                            .append("         </service>")
                            .append("\n")
                        huawei_service_name=true

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


