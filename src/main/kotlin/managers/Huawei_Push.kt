package managers

//package managers
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.kotlin.idea.util.findModule

import util.Constants
import java.io.File
import java.io.FileNotFoundException


class Huawei_Push(private val project: Project)
{
    private var androidapplicationclass: Document? = null
    //private var androidapplicationclass1: Document? = null
    private var androidManifestfile: Document? = null
    private var ans:String=""
    private var pushtoken:Boolean=false
    private var import_stmt:Boolean=false
    private var packagename:String=""
    private var launchingactivityname:String=""
    private var import_stmt_hashmap:Boolean=false



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
                        return launchingactivityname
                        //initapplicationclass(activityname)
                        //sb
                        //.append(activityname)
                        // .append("\n")

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
//    fun check():String
//    {
//        //val documentText = androidManifestfile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//        ans = "com"
//        return ans
//
//    }


    @Throws(FileNotFoundException::class)
    fun initapplicationclass(): Boolean {
        AndroidManifest()
        val op=launchingactivityname
        var op1=packagename
        // val ans=pkg
        var ans1=op1.replace(".","/")
        // val ans2=ans1.replace("\"","")
        print(ans1)
        val basePath = project.basePath
        //projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
        print(projectBaseDir)

        var file = File(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
        var file1 = File(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".kt")
        var java_file_exist = file.exists()
        var kotlin_file_exist = file1.exists()
        if(java_file_exist==true)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                passtoken()
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
                androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                passtoken_kt()
                true
            } else {
                false
            }
        }

//        val manifestVirtualFile: VirtualFile? = projectBaseDir
//        return if (manifestVirtualFile != null) {
//            androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
//            //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
//
//            initiateclevertap()
//            true
//        } else {
//            false
//        }
        return true
    }

    fun checkinsertion()
    {
        //val opp=launchingactivityname
        val documentText = androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()

        for (i in documentText.indices)
        {
            var line = documentText[i]
            if(line.contains("cleverTapAPI.pushHuaweiRegistrationId"))
            {
                pushtoken=true

            }


        }
    }

    fun passtoken() {
        checkinsertion()
        //var c= codeexist
        // if(c==false) {
        // val opp=launchingactivityname
        val documentText =
            androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()

        for (i in documentText.indices) {
            var line = documentText[i]
            sb
                .append(line)
                .append("\n")
//            if(import_stmt==false) {
//                if (line.contains("package")) {
//                    sb
//                        .append("import com.clevertap.android.sdk.CleverTapAPI;")
//                        .append(" //added by CleverTap Assistant")
//                        //.append("   //Initializing the CleverTap SDK")
//                        .append("\n")
//                    import_stmt=true
//                    //.append("CleverTapAPI.createNotificationChannel(getApplicationContext(),\"3131\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true);")
//                    //.append("\n")
//                }
//            }


            if(pushtoken==false) {
                if (line.contains("void onCreate")) {
                    sb
                        .append("\t\tString appId = AGConnectServicesConfig.fromContext(MainActivity.this).getString(\"client/app_id\");")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\tString token = HmsInstanceId.getInstance(MainActivity.this).getToken(appId, \"HCM\");")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\tif(cleverTapAPI != null){")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\t\t\tcleverTapAPI.pushHuaweiRegistrationId(token,true);\n")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\t}")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\telse {")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\t\t\tLog.e(TAG,\"CleverTap is NULL\");")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\t}")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                    pushtoken=true


                }
            }
            // }

        }


        writeToManifest(sb)
        // }

    }

    fun checkinsertion_kt()
    {
        //val opp=launchingactivityname
        val documentText = androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()

        for (i in documentText.indices)
        {
            var line = documentText[i]
            if(line.contains("cleverTapAPI.pushHuaweiRegistrationId"))
            {
                pushtoken=true
            }

        }
    }


    fun passtoken_kt() {
        checkinsertion_kt()
        //var c= codeexist
        // if(c==false) {
        // val opp=launchingactivityname
        val documentText =
            androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()

        for (i in documentText.indices) {
            var line = documentText[i]
            sb
                .append(line)
                .append("\n")
//            if(import_stmt==false) {
//                if (line.contains("package")) {
//                    sb
//                        .append("import com.clevertap.android.sdk.CleverTapAPI")
//                        .append(" //added by CleverTap Assistant")
//                        //.append("   //Initializing the CleverTap SDK")
//                        .append("\n")
//                    import_stmt=true
//                    //.append("CleverTapAPI.createNotificationChannel(getApplicationContext(),\"3131\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true);")
//                    //.append("\n")
//                }
//            }


            if(pushtoken==false) {
                if (line.contains("fun onCreate")) {
                    sb
                        .append("\t\tval appId = AGConnectServicesConfig.fromContext(this@MainActivity).getString(\"client/app_id\")")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\tval token = HmsInstanceId.getInstance(this@MainActivity).getToken(appId, \"HCM\")\n")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\tif(cleverTapAPI != null){")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\t\t\tcleverTapAPI.pushHuaweiRegistrationId(token,true)")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\t}")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\telse {")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\t\t\tLog.e(TAG,\"CleverTap is NULL\")")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
                        .append("\t\t}")
                        .append("   //aaded by CleverTap Assistant")
                        .append("\n")
//
                    pushtoken=true


                }
            }
            // }

        }


        writeToManifest(sb)
        // }

    }




    private fun writeToManifest(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { androidapplicationclass!!.setText(stringBuilder) }
        }
    }
}