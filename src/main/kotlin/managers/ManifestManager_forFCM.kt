package managers

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants
import java.io.FileNotFoundException

class ManifestManager_forFCM(private val project: Project) {

    private var androidManifest: Document? = null

    private var projectBaseDir: VirtualFile? = null
    private var fcm_service_name:Boolean=false
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
            val line = documentText[i]
            if(line.contains("fcm.FcmMessageListenerService"))
            {
                fcm_service_name=true
            }
            if(line.contains("firebase_messaging_auto_init_enabled"))
            {
                firebase_messaging_auto_init_enabled=true
            }
            if(line.contains("firebase_analytics_collection_enabled"))
            {
                firebase_analytics_collection_enabled=true
            }
            if(line.contains("FCM_SENDER_ID"))
            {
                fcm_sender_id=true
            }
            if(line.contains("android:name=\"com.clevertap.android.sdk.pushnotification.CTPushNotificationReceiver\""))
            {
                receiver=true
            }
        }
    }

    fun addMetaDataContent(repository: String,fcmservice_name:String,fcmsenderid:String,IsRadiobuttonrb1Selected:Boolean,IsRadiobuttonrb2Selected:Boolean) {
        checkbeforeinsertion()
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]
            if(!fcm_service_name && !firebase_messaging_auto_init_enabled && !firebase_analytics_collection_enabled && !fcm_sender_id && !receiver) {
                if (line.contains(Constants.APPLICATION)) {
                    if (line.contains("/")) {
                        sb
                            .append("<!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append(repository)
                            .append("\n")
                        fcm_service_name=true
                        firebase_messaging_auto_init_enabled=true
                        firebase_analytics_collection_enabled=true
                        fcm_sender_id=true
                        receiver=true
                    }
                }
            }
            if(IsRadiobuttonrb1Selected) {
                if (!receiver) {
                    if (line.contains(Constants.APPLICATION)) {
                        if (line.contains("/")) {
                            sb
                                .append("        <!-- Added by CleverTap Assistant-->")
                                .append("\n")
//                            .append(repository)
//                            .append("\n")
                                .append("         <receiver")
                                .append("\n")
                                .append("             android:name=\"com.clevertap.android.sdk.pushnotification.CTPushNotificationReceiver\"")
                                .append("\n")
                                .append("             android:exported=\"false\"")
                                .append("\n")
                                .append("             android:enabled=\"true\">")
                                .append("\n")
                                .append("         </receiver>")
                                .append("\n")
                            receiver =true


                        }
                    }
                }
            }
            if(IsRadiobuttonrb2Selected) {
                if (!fcm_service_name) {
                    if (line.contains(Constants.APPLICATION)) {
                        if (line.contains("/")) {
                            sb
                                .append("        <!-- Added by CleverTap Assistant-->")
                                .append("\n")
                                .append("        <service")
                                .append("\n")
                                .append("             android:name=\".fcm.$fcmservice_name\"")
                                .append("\n")
                                .append("             android:exported=\"false\">")
                                .append("\n")
                                .append("             <intent-filter>")
                                .append("\n")
                                .append("                 <action android:name=\"com.google.firebase.MESSAGING_EVENT\" />")
                                .append("\n")
                                .append("             </intent-filter>")
                                .append("\n")
                                .append("         </service>")
                                .append("\n")
                            fcm_service_name = true

                        }
                    }
                }
                if (!firebase_messaging_auto_init_enabled) {
                    if (line.contains(Constants.APPLICATION)) {
                        if (line.contains("/")) {
                            sb
                                .append("         <!-- Added by CleverTap Assistant-->")
                                .append("\n")
                                .append("         <meta-data")
                                .append("\n")
                                .append("             android:name=\"firebase_messaging_auto_init_enabled\"")
                                .append("\n")
                                .append("             android:value=\"false\" />")
                                .append("\n")
                            firebase_messaging_auto_init_enabled = true

                        }
                    }
                }
                if (!firebase_analytics_collection_enabled) {
                    if (line.contains(Constants.APPLICATION)) {
                        if (line.contains("/")) {
                            sb
                                .append("         <!-- Added by CleverTap Assistant-->")
                                .append("\n")
                                .append("         <meta-data")
                                .append("\n")
                                .append("             android:name=\"firebase_analytics_collection_enabled\"")
                                .append("\n")
                                .append("             android:value=\"false\"/>")
                                .append("\n")
                            firebase_analytics_collection_enabled = true

                        }
                    }
                }
                if (!fcm_sender_id) {
                    if (line.contains(Constants.APPLICATION)) {
                        if (line.contains("/")) {
                            sb
                                .append("        <!-- Added by CleverTap Assistant-->")
                                .append("\n")
                                .append("         <meta-data")
                                .append("\n")
                                .append("             android:name=\"FCM_SENDER_ID\"")
                                .append("\n")
                                .append("             android:value=$fcmsenderid />")
                                .append("\n")
                            fcm_sender_id = true

                        }
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
