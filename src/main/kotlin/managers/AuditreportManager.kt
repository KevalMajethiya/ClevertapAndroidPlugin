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


class AuditreportManager(private val project: Project)
{
    private var androidapplicationclass: Document? = null
    private var androidManifestfile: Document? = null
    private var ans:String=""
    private var codeexist:Boolean=false
    private var import_stmt_context:Boolean=false
    private var import_stmt_report_generate:Boolean=false
    private var packagename:String=""
    private var launchingactivityname:String=""
    private var context_exist:Boolean=false
    private var debuglevel_exist:Boolean=false
    private var reportgenerate_run_exist:Boolean=false
    private var remaining_code_exist:Boolean=false


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
                    var line1=documentText[j]
                    if(line1.contains("activity")) {
                        if(line1.contains("android:name")){
                            var ans=line1
                            var b= ans.split(".")
                            var c=b[1]
                            var d=b[1].split("\"")
                            var e=d[0]
                            launchingactivityname=e


                        }
                        else
                        {
                            var line1=documentText[j+1]
                            if(line1.contains("android:name")){
                                var ans=line1
                                var b= ans.split(".")
                                var c=b[1]
                                var d=b[1].split("\"")
                                var e=d[0]
                                launchingactivityname=e


                            }

                        }
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
        //print(projectBaseDir)
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

                initiateauditreport()
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

                initiateauditreport_kt()
                true
            } else {
                false
            }
        }

        // projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)
        //projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java")

        // projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!) //       val manifestVirtualFile: VirtualFile? = projectBaseDir
//            .findChild(Constants.DEFAULT_MODULE_NAME)!!
//            .findChild("src")!!
//            .findChild("main")!!
//            .findChild("java")!!
//
//            //.findChild("com")!!
//            .findChild(pkg)!!
//            .findChild("example")!!
//            .findChild("demoapp1")!!
//            .findChild("MainActivity.java")
//        return if (manifestVirtualFile != null) {
//            androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
//            initiateauditreport()
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
            if(line.contains("import android.content.Context"))
            {
                import_stmt_context=true

            }
            if(line.contains("import com.example.getauditreport.ReportGenerate"))
            {
                import_stmt_report_generate=true

            }
            if(line.contains("context = getApplicationContext()"))
            {
                context_exist=true

            }
            if(line.contains("clevertapDefaultInstance.setDebugLevel(CleverTapAPI.LogLevel.DEBUG)"))
            {
                debuglevel_exist=true

            }
            if(line.contains("ReportGenerate.run(context)"))
            {
                reportgenerate_run_exist=true

            }
            if(line.contains("}"))
            {
                remaining_code_exist=true

            }
        }
    }

    fun initiateauditreport() {
        checkinsertion()
        var c= codeexist
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
            if( import_stmt_context==false) {
                if (line.contains("package")) {
                    sb
                        .append("import android.content.Context;")
                        .append(" //added by CleverTap Assistant")
                        //.append("   //Initializing the CleverTap SDK")
                        .append("\n")
                    import_stmt_context=true
                    //.append("CleverTapAPI.createNotificationChannel(getApplicationContext(),\"3131\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true);")
                    //.append("\n")
                }
            }
            if(import_stmt_report_generate==false) {
                if (line.contains("package")) {
                    // if (line.contains("/")) {
                    sb
                        .append("import com.example.getauditreport.ReportGenerate;")
                        .append("                      //added by CleverTap Assistant")
                        .append("\n")
                    import_stmt_report_generate=true
                    // }
                }
            }


            if(context_exist==false) {
                if (line.contains("void onCreate")) {
                    sb
                            .append("        Context context = getApplicationContext();")
                            .append("   //added by CleverTap Assistant")
                        .append("\n")
                    context_exist=true


                }
            }


            if(reportgenerate_run_exist==false) {
                if (line.contains("setContentView")) {
                    sb
                        .append("        ReportGenerate.run(context);")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    reportgenerate_run_exist=true


                }
            }
            if(debuglevel_exist==false) {
                if (line.contains("setContentView")) {
                    sb
                        .append("\t\tclevertapDefaultInstance.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    debuglevel_exist=true


                }
            }



            // }

        }


        writeToManifest(sb)
        // }

    }

    fun initiateauditreport_kt() {
        checkinsertion()
        var c= codeexist
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
            if( import_stmt_context==false) {
                if (line.contains("package")) {
                    sb
                        .append("import android.content.Context")
                        .append(" //added by CleverTap Assistant")
                        //.append("   //Initializing the CleverTap SDK")
                        .append("\n")
                    import_stmt_context=true
                    //.append("CleverTapAPI.createNotificationChannel(getApplicationContext(),\"3131\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true);")
                    //.append("\n")
                }
            }
            if(import_stmt_report_generate==false) {
                if (line.contains("package")) {
                    // if (line.contains("/")) {
                    sb
                        .append("import com.example.getauditreport.ReportGenerate")
                        .append("                      //added by CleverTap Assistant")
                        .append("\n")
                    import_stmt_report_generate=true
                    // }
                }
            }


            if(context_exist==false) {
                if (line.contains("fun onCreate")) {
                    sb
                        .append("\t\tvar context = getApplicationContext()")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    context_exist=true


                }
            }


            if(reportgenerate_run_exist==false) {
                if (line.contains("setContentView")) {
                    sb
                        .append("        ReportGenerate.run(context)")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    reportgenerate_run_exist=true


                }
            }
            if(debuglevel_exist==false) {
                if (line.contains("setContentView")) {
                    sb
                        .append("\t\tCleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG)")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    debuglevel_exist=true


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