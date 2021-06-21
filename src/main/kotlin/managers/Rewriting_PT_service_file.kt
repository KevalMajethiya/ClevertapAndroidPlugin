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


class Rewriting_PT_service_file(private val project: Project)
{
    private var androidapplicationclass: Document? = null
    //private var androidapplicationclass1: Document? = null
    private var androidManifestfile: Document? = null
    private var ans:String=""
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
    fun initapplicationclass(): Boolean {
        AndroidManifest()
        var op1=packagename
        // val ans=pkg
        var ans1=op1.replace(".","/")
        // val ans2=ans1.replace("\"","")
        print(ans1)
        val basePath = project.basePath
        //projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
        print(projectBaseDir)

        var file = File(project.basePath +"/app/src/main/java/"+ans1+"/" + "Push_Templates/pushtemplate_service" +".java")
        var file1 = File(project.basePath +"/app/src/main/java/"+ans1+"/" + "Push_Templates/pushtemplate_service" +".kt")
        var java_file_exist = file.exists()
        var kotlin_file_exist = file1.exists()
        if(java_file_exist==true)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + "Push_Templates/pushtemplate_service" +".java")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                //rewriting_servicefile(basic_temp_title,basic_temp_message,basic_temp_msg_summary)
                true
            } else {
                false
            }
        }
        if(kotlin_file_exist==true)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + "Push_Templates/pushtemplate_service" +".kt")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

               // initiateclevertap_kt()
                true
            } else {
                false
            }
        }

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


        }
    }
    fun rewriting_servicefile(title: String, message: String, msg_summary:String, subtitle:String,
                              bg:String, big_img:String, ico:String, dl1:String, titlte_clr:String,
                              msg_clr:String, small_icon_clr:String,template_type:String) {
        //checkinsertion()
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



//                if (!line.contains("extras1.put")) {
//                    sb
//                        .append(line)
//                        .append("\n")
//
//                }
            if(line.contains("Bundle extras1 ")) {
                if (template_type == "Basic") {
                    sb
                        .append("\t\textras1.putString(\"nm\",\"<Notification Body>\");\n")
                        .append("\t\textras1.putString(\"nt\", \"<Notification Title>\");\n")
                        .append("\t\textras1.putString(\"wzrk_id\", \"1584453563_20200606\");\n")
                        .append("\t\textras1.putBoolean(\"wzrk_pn\", true);\n")
                        .append("\t\textras1.putBoolean(\"wzrk_rnv\",true);\n")
                        .append("\t\textras1.putString(\"wzrk_cid\",\"Tester\");\n")
                        .append("\t\textras1.putString(\"wzrk_ttl\", \"1593116116\");\n")
                        .append("\t\textras1.putString(\"wzrk_dt\", \"FIREBASE\");\n")
                        .append("\t\textras1.putString(\"pt_id\", \"pt_basic\");\n")
                        .append("\t\textras1.putString(\"pt_title\", \"$title\");\n")
                        .append("\t\textras1.putString(\"pt_msg\", \"$message\");\n")
                        .append("\t\textras1.putString(\"pt_msg_summary\", \"$msg_summary\");\n")
                        .append("\t\textras1.putString(\"pt_subtitle\", \"$subtitle\");\n")
                        .append("\t\textras1.putString(\"pt_big_img\",\"$big_img\");\n")
                        .append("\t\textras1.putString(\"pt_dl1\", \"$dl1\");\n")
                        .append("\t\textras1.putString(\"pt_msg_clr\", \"$msg_clr\");\n")
                        .append("\t\textras1.putString(\"pt_bg\", \"$bg\");\n")
                        //.append("\t\textras1.putString(\"pt_product_display_action_text_clr\",\"#fff\");\n")
                        .append("\t\textras1.putString(\"pt_title_clr\", \"$titlte_clr\");\n")
                        .append("\t\textras1.putString(\"pt_ico\", \"$ico\");\n")
                        .append("\t\textras1.putString(\"pt_small_icon_clr\", \"$small_icon_clr\");\n")

                }
            }


            // }

        }


        writeToManifest(sb)
        // }

    }


    fun removing_extrastuff() {
        //checkinsertion()
        var c= codeexist
        // if(c==false) {
        // val opp=launchingactivityname
        val documentText =
            androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()

        for (i in documentText.indices) {
            var line = documentText[i]

                if (!line.contains("extras1.put")) {
                    sb
                        .append(line)
                        .append("\n")

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