package managers

//package managers

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants
import java.io.File
import java.io.FileNotFoundException


class abc(private val project: Project)
{
    private var androidapplicationclass: Document? = null
    //private var androidapplicationclass1: Document? = null
    private var androidManifestfile: Document? = null
//    private var ans:String=""
    private var codeexist:Boolean=false
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

                            for(k in j..i) {
                                val line2 = documentText[k]
                                if (line2.contains("android:name")) {
                                    val b = line2.split(".")
                                    var c = b[1]
                                    val d = b[1].split("\"")
                                    val e = d[0]
                                    launchingactivityname = e
                                    break
                                }
                            }
                        break


                    }


                }
            }


            if (line.contains("package")) {
                if (line.contains("=")) {
                    val b = line.split("=")
                    //var d=
                    val c = b[1]
                    val d = c.split("\"")
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
        val op1=packagename
       // val ans=pkg
        val ans1=op1.replace(".","/")
       // val ans2=ans1.replace("\"","")
        print(ans1)
        val basePath = project.basePath
        //projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
        print(projectBaseDir)

        val file = File(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
        val file1 = File(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".kt")
        val java_file_exist = file.exists()
        val kotlin_file_exist = file1.exists()
        if(java_file_exist)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                initiateclevertap()
                true
            } else {
                false
            }
        }
        if(kotlin_file_exist)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".kt")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                initiateclevertap_kt()
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
            val line = documentText[i]
            if(line.contains("CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());"))
            {
                codeexist=true

            }
            if(line.contains("import com.clevertap.android.sdk.CleverTapAPI;"))
            {
                import_stmt=true

            }
            if(line.contains("import java.util.HashMap;"))
            {
                import_stmt_hashmap=true

            }

        }
    }

    fun initiateclevertap() {
        checkinsertion()
        var c= codeexist
       // if(c==false) {
            // val opp=launchingactivityname
            val documentText =
                androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val sb = StringBuilder()

            for (i in documentText.indices) {
                val line = documentText[i]
                sb
                    .append(line)
                    .append("\n")
                if(!import_stmt) {
                    if (line.contains("package")) {
                        sb
                            .append("import com.clevertap.android.sdk.CleverTapAPI;")
                            .append(" //added by CleverTap Assistant")
                            //.append("   //Initializing the CleverTap SDK")
                            .append("\n")
                        import_stmt=true
                        //.append("CleverTapAPI.createNotificationChannel(getApplicationContext(),\"3131\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true);")
                        //.append("\n")
                    }
                }
//                if(import_stmt_hashmap==false) {
//                    if (line.contains("package")) {
//                        // if (line.contains("/")) {
//                        sb
//                            .append("import java.util.HashMap;")
//                            .append("                      //added by CleverTap plug-in")
//                            .append("\n")
//                        import_stmt_hashmap=true
//                        // }
//                    }
//                }


                if(!c) {
                    if (line.contains("void onCreate")) {
                        sb
//                            .append("        Context context = getApplicationContext();")
//                            .append("   //added by CleverTap plug-in")
                            .append("\n")
                            .append("        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());")
                            .append("   //Initializing the CleverTap SDK")
                            .append("\n")
                        c=true


                    }
                }
                // }

            }


            writeToManifest(sb)
       // }

    }

    private fun checkinsertion_kt()
    {
        //val opp=launchingactivityname
        val documentText = androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()

        for (i in documentText.indices)
        {
            val line = documentText[i]
            if(line.contains("CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext())"))
            {
                codeexist=true

            }
            if(line.contains("import com.clevertap.android.sdk.CleverTapAPI"))
            {
                import_stmt=true

            }
            if(line.contains("import java.util.HashMap"))
            {
                import_stmt_hashmap=true

            }

        }
    }


    private fun initiateclevertap_kt() {
        checkinsertion_kt()
        var c= codeexist
        // if(c==false) {
        // val opp=launchingactivityname
        val documentText =
            androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()

        for (i in documentText.indices) {
            val line = documentText[i]
            sb
                .append(line)
                .append("\n")
            if(!import_stmt) {
                if (line.contains("package")) {
                    sb
                        .append("import com.clevertap.android.sdk.CleverTapAPI")
                        .append(" //added by CleverTap Assistant")
                        //.append("   //Initializing the CleverTap SDK")
                        .append("\n")
                    import_stmt=true
                    //.append("CleverTapAPI.createNotificationChannel(getApplicationContext(),\"3131\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true);")
                    //.append("\n")
                }
            }
//                if(import_stmt_hashmap==false) {
//                    if (line.contains("package")) {
//                        // if (line.contains("/")) {
//                        sb
//                            .append("import java.util.HashMap;")
//                            .append("                      //added by CleverTap plug-in")
//                            .append("\n")
//                        import_stmt_hashmap=true
//                        // }
//                    }
//                }


            if(!c) {
                if (line.contains("fun onCreate")) {
                    sb
//                            .append("        Context context = getApplicationContext();")
//                            .append("   //added by CleverTap plug-in")
                        .append("\n")
                        .append("        var clevertapDefaultInstance: CleverTapAPI? = null")
                        .append("\n")
                        .append("\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)")
                        .append("   //Initializing the CleverTap SDK")
                        .append("\n")
                    c=true


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