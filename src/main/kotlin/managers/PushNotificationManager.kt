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

class PushNotificationManager(private val project: Project) {
    private var androidManifestfile: Document? = null
    private var packagename:String=""
    private var launching_activity: Document? = null
    private var launchingactivityname:String=""
    private  var firebase_receiver_class_name:String=""
    private var notification_channel_exist:Boolean=false
    private var import_stmt:Boolean=false
    private var import_stmt_hashmap:Boolean=false
    private var final_ans:String=""



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
        val op=launchingactivityname
        var op1=packagename
        var fa=final_ans
        var ans1=op1.replace(".","/")
        val basePath = project.basePath
       // projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ ans1 + "/" + op + ".java")
       // projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)
        var file = File(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
        var file1 = File(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".kt")
        var java_file_exist = file.exists()
        var kotlin_file_exist = file1.exists()
        if(java_file_exist==true)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                launching_activity = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                addnotificationchannel(contentTitleText)
                true
            } else {
                false
            }
        }
        if(kotlin_file_exist==true)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".kt")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                launching_activity = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                addnotificationchannel_kt(contentTitleText)
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
            if(line.contains("CleverTapAPI.createNotificationChannel"))
            {
                notification_channel_exist=true

            }
            if(line.contains("import android.app.NotificationManager"))
            {
                import_stmt=true

            }
//            if(line.contains("import java.util.HashMap;"))
//            {
//                import_stmt_hashmap=true
//
//            }
        }
    }

    fun addnotificationchannel(contentTitleText:String) {
        checkbeforeinsertion()
        //if(notification_channel_exist==false) {
            val documentText =launching_activity!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val sb = StringBuilder()
            for (i in documentText.indices) {
                val line = documentText[i]
                sb
                    .append(line)
                    .append("\n")
                if(import_stmt==false) {
                    if (line.contains("package")) {
                        // if (line.contains("/")) {
                        sb
                            .append("import android.app.NotificationManager;")
                            .append("        //added by CleverTap Assistant")
                            .append("\n")
                        import_stmt=true
                        // }
                    }
                }
//                if(import_stmt_hashmap==false) {
//                    if (line.contains("package")) {
//                        // if (line.contains("/")) {
//                        sb
//                            .append("import java.util.HashMap;")
//                            .append("\n")
//                        // }
//                    }
//                }
                if(notification_channel_exist==false) {
                    if (line.contains("setContentView")) {
                        // if (line.contains("/")) {
                        sb
                            .append("\t\tCleverTapAPI.createNotificationChannel(getApplicationContext(),\"$contentTitleText\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true);")
                            .append("        //added by CleverTap Assistant")
                            .append("\n")
                            notification_channel_exist=true
                        // }
                    }
                }

            }
            writeToManifest(sb)
       // }
    }

    fun addnotificationchannel_kt(contentTitleText:String) {
        checkbeforeinsertion()
        //if(notification_channel_exist==false) {
        val documentText =launching_activity!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]
            sb
                .append(line)
                .append("\n")
            if(import_stmt==false) {
                if (line.contains("package")) {
                    // if (line.contains("/")) {
                    sb
                        .append("import android.app.NotificationManager")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    import_stmt=true
                    // }
                }
            }
//                if(import_stmt_hashmap==false) {
//                    if (line.contains("package")) {
//                        // if (line.contains("/")) {
//                        sb
//                            .append("import java.util.HashMap;")
//                            .append("\n")
//                        // }
//                    }
//                }
            if(notification_channel_exist==false) {
                if (line.contains("setContentView")) {
                    // if (line.contains("/")) {
                    sb
                        .append("\t\tCleverTapAPI.createNotificationChannel(getApplicationContext(),\"$contentTitleText\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true)")
                        .append("        //added by CleverTap Assistant")
                        .append("\n")
                    notification_channel_exist=true
                    // }
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
