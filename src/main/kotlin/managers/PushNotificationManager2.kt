package managers

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants
import java.io.File
import java.io.FileNotFoundException

class PushNotificationManager2(private val project: Project) {
    private var androidManifestfile: Document? = null
    private var packagename:String=""
    private var launching_activity: Document? = null
    private var launchingactivityname:String=""
    private  var firebase_receiver_class_name:String=""
    private var final_ans:String=""
    private var import_clevertapAPI:Boolean=false
    private var import_util_log:Boolean=false
    private var import_non_null:Boolean=false
    private var import_os_bundle:Boolean=false
    private var import_notification_manager:Boolean=false
    private var import_notification_info:Boolean=false
    private var import_firebase_messaging_service:Boolean=false
    private var import_remote_messasge:Boolean=false
    private var import_map:Boolean=false
    private var clevertap_object:Boolean=false
    private var firebase_messaging_content:Boolean=false
    private var on_new_token:Boolean=false



    private var projectBaseDir: VirtualFile? = null
    @Throws(FileNotFoundException::class)
    fun AndroidManifest(): Boolean {
        val basePath = project.basePath
        projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)

        val manifestVirtualFile: VirtualFile? = projectBaseDir!!
            .findChild(Constants.DEFAULT_MODULE_NAME)!!
            .findChild("src")!!
            .findChild("main")!!
            .findChild("AndroidManifest.xml")
        return if (manifestVirtualFile != null) {
            androidManifestfile = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
            getpackagename()
            //launchingactivityname

            true
        } else {
            false
        }
    }

    fun getpackagename():String{


        val documentText = androidManifestfile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()

        for (i in documentText.indices) {
            val line = documentText[i]
            //final_ans= documentText[22]
            if (line.contains("android.intent.category.LAUNCHER"))
            {
                for(j in i downTo 1)
                {
                    val line1=documentText[j]
                    if(line1.contains("activity")) {
                        var ans=line1
                        var b= ans.split(".")
                        var c=b[1]
                        var d=b[1].split("\"")
                        launchingactivityname=d[0]
                        print(launchingactivityname)
                        // launchingactivityname="line"
                        //return launchingactivityname
                        //initapplicationclass(activityname)
                        //sb
                        //.append(activityname)
                        // .append("\n")

                    }
                }
            }
            if (line.contains("com.google.firebase.MESSAGING_EVENT"))
            {
                for(k in i-1 downTo 1)
                {
                    val line2=documentText[k]
                    if(line2.contains("android:name")) {

                        var ans11=line2
                        var ans12= ans11.split("\"")
                        var ans13=ans12[1]
                        firebase_receiver_class_name=ans13
                        break
//
                    }


                }
            }

            if (line.contains("package")) {
                if (line.contains("=")) {
                    var a = line
                    var b = a.split("=")
                    //var d=
                    var c = b[1]
                    var d = c.split("\"")
                    packagename = d[1]
                    //return "abc"
                    //initapplicationclass(packagename!!)
                }
            }
            sb
                .append(line)
                .append("\n")

            //  }

        }
        writeToManifest(sb)
        return "com"
    }
    @Throws(FileNotFoundException::class)
    fun initlaunchingactivity(contentTitleText:String): Boolean {
        AndroidManifest()
        var fcm_name=firebase_receiver_class_name
        var fcm_path=fcm_name.replace(".","/")
        val op=launchingactivityname
        var op1=packagename
        var fa=final_ans
        var ans1=op1.replace(".","/")
        val basePath = project.basePath
        // projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ ans1 + "/" + op + ".java")
        // projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)
        var file = File(project.basePath +"/app/src/main/java/"+ans1 + fcm_path+".java")
        var file1 = File(project.basePath +"/app/src/main/java/"+ans1 + fcm_path +".kt")
        var java_file_exist = file.exists()
        var kotlin_file_exist = file1.exists()
        if(java_file_exist==true)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1 + fcm_path +".java")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                launching_activity = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                add_content(contentTitleText)
                true
            } else {
                false
            }
        }
        if(kotlin_file_exist==true)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1 + fcm_path +".kt")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                launching_activity = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                add_content_kt(contentTitleText)
                true
            } else {
                false
            }
        }
//        val manifestVirtualFile: VirtualFile? = projectBaseDir
//        return if (manifestVirtualFile != null) {
//            launching_activity = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
//            addnotificationchannel(contentTitleText)
//            true
//
//        } else {
//            false
//        }
        return true
    }

    fun checkbeforeinsertion()
    {
        //val opp=launchingactivityname
        val documentText = launching_activity!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()
        for (i in documentText.indices)
        {
            var line = documentText[i]
            if(line.contains("import com.clevertap.android.sdk.CleverTapAPI;"))
            {
                import_clevertapAPI=true

            }
            if(line.contains("import android.util.Log;"))
            {
                import_util_log=true

            }
            if(line.contains("import androidx.annotation.NonNull;"))
            {
                import_non_null=true

            }
            if(line.contains("import android.os.Bundle;"))
            {
                import_os_bundle=true

            }
            if(line.contains("import android.app.NotificationManager;"))
            {
                import_notification_manager=true

            }
            if(line.contains("import com.clevertap.android.sdk.pushnotification.NotificationInfo;"))
            {
                import_notification_info=true

            }
            if(line.contains("import com.google.firebase.messaging.FirebaseMessagingService;"))
            {
                import_firebase_messaging_service=true

            }
            if(line.contains("import com.google.firebase.messaging.RemoteMessage;"))
            {
                import_remote_messasge=true

            }
            if(line.contains("import java.util.Map;"))
            {
                import_map=true

            }
            if(line.contains("RemoteMessage.Notification notification = remoteMessage.getNotification();"))
            {
                firebase_messaging_content=true

            }
            if(line.contains("CleverTapAPI clevertapDefaultInstance;"))
            {
                clevertap_object=true

            }
            if(line.contains("void onNewToken"))
            {
                on_new_token=true

            }


        }
    }

    fun add_content(contentTitleText:String) {
        checkbeforeinsertion()
        //if(notification_channel_exist==false) {
        val documentText =launching_activity!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]
            sb
                .append(line)
                .append("\n")
            if(import_clevertapAPI==false) {
                if (line.contains("package")) {
                    // if (line.contains("/")) {
                    sb
                        .append("import com.clevertap.android.sdk.CleverTapAPI;")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_clevertapAPI=true
                    // }
                }
            }
            if(import_notification_manager==false) {
                if (line.contains("package")) {
                    // if (line.contains("/")) {
                    sb
                        .append("import android.app.NotificationManager;")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_notification_manager=true
                    // }
                }
            }
            if(import_util_log==false) {
                    if (line.contains("package")) {
                        sb
                            .append("import android.util.Log;")
                            .append("        //added by CleverTap Assistant")
                            .append("\n")
                        import_util_log=true
                    }
                }
            if(import_non_null==false) {
                if (line.contains("package")) {
                    sb
                        .append("import androidx.annotation.NonNull;")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_non_null=true
                }
            }

            if(import_os_bundle==false) {
                if (line.contains("package")) {
                    sb
                        .append("import android.os.Bundle;")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_os_bundle=true
                }
            }

            if(import_notification_info==false) {
                if (line.contains("package")) {
                    sb
                        .append("import com.clevertap.android.sdk.pushnotification.NotificationInfo;")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_notification_info=true
                }
            }


            if(import_firebase_messaging_service==false) {
                if (line.contains("package")) {
                    sb
                        .append("import com.google.firebase.messaging.FirebaseMessagingService;")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_firebase_messaging_service=true
                }
            }

            if(import_remote_messasge==false) {
                if (line.contains("package")) {
                    sb
                        .append("import com.google.firebase.messaging.RemoteMessage;")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_remote_messasge=true
                }
            }

            if(import_map==false) {
                if (line.contains("package")) {
                    sb
                        .append("import java.util.Map;\n")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_map=true
                }
            }





            if(clevertap_object==false) {
                if (line.contains("class")) {
                    // if (line.contains("/")) {
                    sb
                        .append("\t\tCleverTapAPI clevertapDefaultInstance;")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    clevertap_object=true
                    // }
                }
            }

            if(firebase_messaging_content==false) {
                if (line.contains("super.onMessageReceived")) {
                    sb
                        .append("\t\tRemoteMessage.Notification notification = remoteMessage.getNotification();")
                        .append("\n")
                        .append("\t\ttry ")
                        .append("\n")
                        .append("\t\t{")
                        .append("\n")
                        .append("\t\t\tif (remoteMessage.getData().size() > 0)")
                        .append("\n")
                        .append("\t\t\t{")
                        .append("\n")
                        .append("\t\t\t\tBundle extras = new Bundle();")
                        .append("\n")
                        .append("\t\t\t\tfor (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) ")
                        .append("\n")
                        .append("\t\t\t\t{")
                        .append("\n")
                        .append("\t\t\t\t\textras.putString(entry.getKey(), entry.getValue());")
                        .append("\n")
                        .append("\t\t\t\t\tLog.d(\"key,value\", entry.getKey()+\" and \"+entry.getValue());")
                        .append("\n")
                        .append("\t\t\t\t}")
                        .append("\n")
                        .append("\t\t\t\tNotificationInfo info = CleverTapAPI.getNotificationInfo(extras);")
                        .append("\n")
                        .append("\t\t\t\tif (info.fromCleverTap)")
                        .append("\n")
                        .append("\t\t\t\t{")
                        .append("\n")
                        .append("\t\t\t\t\tCleverTapAPI.createNotification(getApplicationContext(), extras);")
                        .append("\n")
                        .append("\t\t\t\t}")
                        .append("\n")
                        .append("\t\t\t\telse")
                        .append("\n")
                        .append("\t\t\t\t{")
                        .append("\n")
                        .append("\t\t\t\t\tMap<String, String> data = remoteMessage.getData();")
                        .append("\n")
                        .append("\t\t\t\t\tLog.d(\"FROM\", remoteMessage.getFrom());")
                        .append("\n")
                        .append("\t\t\t\t}")
                        .append("\n")
                        .append("\t\t\t}")
                        .append("\n")
                        .append("\t\t}")
                        .append("\t\tcatch (Throwable t)")
                        .append("\n")
                        .append("\t\t{")
                        .append("\n")
                        .append("\t\t\tLog.d(\"MYFCMLIST\", \"Error parsing FCM message\", t);")
                        .append("\n")
                        .append("\t\t}")
                        .append("\n")
                    firebase_messaging_content=true

                }
            }
            if(on_new_token==false) {
                if (i == documentText.lastIndex-1) {
                    sb
                        .append("\t@Override")
                        .append("\n")
                        .append("\tpublic void onNewToken(@NonNull String s)")
                        .append("\n")
                        .append("\t{")
                        .append("\n")
                        .append("\t\tsuper.onNewToken(s);")
                        .append("\n")
                        .append("\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());")
                        .append("\n")
                        .append("\t\tclevertapDefaultInstance.pushFcmRegistrationId(s,true);")
                        .append("\n")
                        .append("\t\tCleverTapAPI.createNotificationChannel(this,\"$contentTitleText\",\"$contentTitleText\",\"Channel for Push in App\", NotificationManager.IMPORTANCE_HIGH,true);")
                        .append("\n")
                        .append("\t}")
                        .append("\n")
                    on_new_token=true
                }
            }

        }
        writeToManifest(sb)
        // }
    }

    fun checkbeforeinsertion_kt()
    {
        //val opp=launchingactivityname
        val documentText = launching_activity!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()
        for (i in documentText.indices)
        {
            var line = documentText[i]
            if(line.contains("import com.clevertap.android.sdk.CleverTapAPI"))
            {
                import_clevertapAPI=true

            }
            if(line.contains("import android.util.Log"))
            {
                import_util_log=true

            }
            if(line.contains("import androidx.annotation.NonNull"))
            {
                import_non_null=true

            }
            if(line.contains("import android.os.Bundle"))
            {
                import_os_bundle=true

            }
            if(line.contains("import android.app.NotificationManager"))
            {
                import_notification_manager=true

            }
            if(line.contains("import com.clevertap.android.sdk.pushnotification.NotificationInfo"))
            {
                import_notification_info=true

            }
            if(line.contains("import com.google.firebase.messaging.FirebaseMessagingService"))
            {
                import_firebase_messaging_service=true

            }
            if(line.contains("import com.google.firebase.messaging.RemoteMessage"))
            {
                import_remote_messasge=true

            }
            if(line.contains("import java.util.Map"))
            {
                import_map=true

            }
            if(line.contains("RemoteMessage.Notification notification = remoteMessage.getNotification()"))
            {
                firebase_messaging_content=true

            }
            if(line.contains("var clevertapDefaultInstance"))
            {
                clevertap_object=true

            }
            if(line.contains("fun onNewToken"))
            {
                on_new_token=true

            }


        }
    }

    fun add_content_kt(contentTitleText:String) {
        checkbeforeinsertion_kt()
        //if(notification_channel_exist==false) {
        val documentText =launching_activity!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]
            sb
                .append(line)
                .append("\n")
            if(import_clevertapAPI==false) {
                if (line.contains("package")) {
                    // if (line.contains("/")) {
                    sb
                        .append("import com.clevertap.android.sdk.CleverTapAPI")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_clevertapAPI=true
                    // }
                }
            }
            if(import_notification_manager==false) {
                if (line.contains("package")) {
                    // if (line.contains("/")) {
                    sb
                        .append("import android.app.NotificationManager")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_notification_manager=true
                    // }
                }
            }
            if(import_util_log==false) {
                if (line.contains("package")) {
                    sb
                        .append("import android.util.Log")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_util_log=true
                }
            }
            if(import_non_null==false) {
                if (line.contains("package")) {
                    sb
                        .append("import androidx.annotation.NonNull")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_non_null=true
                }
            }

            if(import_os_bundle==false) {
                if (line.contains("package")) {
                    sb
                        .append("import android.os.Bundle;")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_os_bundle=true
                }
            }

            if(import_notification_info==false) {
                if (line.contains("package")) {
                    sb
                        .append("import com.clevertap.android.sdk.pushnotification.NotificationInfo")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_notification_info=true
                }
            }

            if(import_firebase_messaging_service==false) {
                if (line.contains("package")) {
                    sb
                        .append("import com.google.firebase.messaging.FirebaseMessagingService")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_firebase_messaging_service=true
                }
            }

            if(import_remote_messasge==false) {
                if (line.contains("package")) {
                    sb
                        .append("import com.google.firebase.messaging.RemoteMessage")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_remote_messasge=true
                }
            }

            if(import_map==false) {
                if (line.contains("package")) {
                    sb
                        .append("import java.util.Map")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_map=true
                }
            }





            if(clevertap_object==false) {
                if (line.contains("class")) {
                    // if (line.contains("/")) {
                    sb
                        .append("\t\tvar clevertapDefaultInstance : CleverTapAPI? = null")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    clevertap_object=true
                    // }
                }
            }

            if(firebase_messaging_content==false) {
                if (line.contains("super.onMessageReceived")) {
                    sb
                        .append("\t\tval notification: RemoteMessage.Notification?  = remoteMessage.getNotification();")
                        .append("\n")
                        .append("\tremoteMessage.data.apply {")
                        .append("\n")
                        .append("\t\ttry ")
                        .append("\n")
                        .append("\t\t{")
                        .append("\n")
                        .append("\t\t\tif (remoteMessage.getData().size > 0)")
                        .append("\n")
                        .append("\t\t\t{")
                        .append("\n")
                        .append("\t\t\t\tval extras = Bundle()")
                        .append("\n")
                        .append("\t\t\t\tfor ((key, value) in this) ")
                        .append("\n")
                        .append("\t\t\t\t{")
                        .append("\n")
                        .append("\t\t\t\t\textras.putString(key, value)")
                        .append("\n")
                        .append("\t\t\t\t\tLog.d(\"key,value\", key.toString() + \" and \" + value)")
                        .append("\n")
                        .append("\t\t\t\t}")
                        .append("\n")
                        .append("\t\t\t\tval info = CleverTapAPI.getNotificationInfo(extras)")
                        .append("\n")
                        .append("\t\t\t\tif (info.fromCleverTap)")
                        .append("\n")
                        .append("\t\t\t\t{")
                        .append("\n")
                        .append("\t\t\t\t\tCleverTapAPI.createNotification(getApplicationContext(), extras)")
                        .append("\n")
                        .append("\t\t\t\t}")
                        .append("\n")
                        .append("\t\t\t\telse")
                        .append("\n")
                        .append("\t\t\t\t{")
                        .append("\n")
                        .append("\t\t\t\t\t//val data: kotlin.collections.Map<String, String> = remoteMessage.getData()")
                        .append("\n")
                        .append("\t\t\t\t\tLog.d(\"FROM\", remoteMessage.getFrom()!!)")
                        .append("\n")
                        .append("\t\t\t\t}")
                        .append("\n")
                        .append("\t\t\t}")
                        .append("\n")
                        .append("\t\t}")
                        .append("\n")
                        .append("\t\tcatch (t: Throwable)")
                        .append("\n")
                        .append("\t\t{")
                        .append("\n")
                        .append("\t\t\tLog.d(\"MYFCMLIST\", \"Error parsing FCM message\", t);")
                        .append("\n")
                        .append("\t\t}")
                        .append("\n")
                        .append("\t}")
                        .append("\n")
                    firebase_messaging_content=true

                }
            }
            if(on_new_token==false) {
                if (i == documentText.lastIndex-1) {
                    sb
                        .append("\toverride")
                        .append("\n")
                        .append("\tfun onNewToken(@NonNull s: String)")
                        .append("\n")
                        .append("\t{")
                        .append("\n")
                        .append("\t\tsuper.onNewToken(s)")
                        .append("\n")
                        .append("\t\tCleverTapAPI.getDefaultInstance(getApplicationContext())")
                        .append("\n")
                        .append("\t\tclevertapDefaultInstance?.pushFcmRegistrationId(s,true);")
                        .append("\n")
                        .append("\t\tCleverTapAPI.createNotificationChannel(this,\"$contentTitleText\",\"$contentTitleText\",\"Channel for Push in App\", NotificationManager.IMPORTANCE_HIGH,true);")
                        .append("\n")
                        .append("\t}")

                        .append("\n")
                    on_new_token=true
                }
            }

        }
        writeToManifest(sb)
        // }
    }



    private fun writeToManifest(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction {launching_activity!!.setText(stringBuilder) }
        }
    }
}
