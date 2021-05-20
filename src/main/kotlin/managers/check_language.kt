package managers

import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants
import java.io.File

class check_language(private val project: Project)
{
    private var projectBaseDir: VirtualFile? = null
    private var androidManifestfile: com.intellij.openapi.editor.Document? = null
    private var packagename:String=""
    private var launchingactivityname:String=""
    private var language=""
    private var applicationClassName:String=""

    init{
        find_language()
        find_appClass()

    }
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
            getAppClass()
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
                        val ans=line1
                        val b= ans.split(".")
                        var c=b[1]
                        val d=b[1].split("\"")
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
                    val a = line
                    val b = a.split("=")
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
        // writeToManifest(sb)
        return "com"
    }

    fun getAppClass():String{

        val documentText = androidManifestfile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices){
            val line = documentText[i]
            if (line.contains("android:name")){
                val a = line
                val b = a.split("=")
                val c = b[1]
                val d = c.split("\"")
                applicationClassName = d[1]
                break
            }
        }
        return applicationClassName

    }

    fun find_language(): String {
        AndroidManifest()
        val op = launchingactivityname
        val op1 = packagename
        // val ans=pkg
        val ans1 = op1.replace(".", "/")
        // val ans2=ans1.replace("\"","")
        print(ans1)
        val basePath = project.basePath
        //projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
        print(projectBaseDir)

        val file = File(project.basePath + "/app/src/main/java/" + ans1 + "/" + op + ".java")
        val file1 = File(project.basePath + "/app/src/main/java/" + ans1 + "/" + op + ".kt")
        val java_file_exist = file.exists()
        val kotlin_file_exist = file1.exists()
        if (java_file_exist == true) {
             language= "java"

        }
        if(kotlin_file_exist == true)
        {
            language= "kotlin"
        }

        return language
    }

    fun find_appClass() : String{

        AndroidManifest()


        return applicationClassName
    }

}