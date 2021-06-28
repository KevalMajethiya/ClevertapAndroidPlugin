package managers

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
    private var firebase_receiver_class_name:String=""

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
            get_receiver_Class()
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
                                val ans = line2
                                val b = ans.split(".")
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
        for (i in documentText.indices) {
            val line = documentText[i]
            if (line.contains("application")){
                for(j in i..documentText.lastIndex) {
                    val line1=documentText[j]
                    if (line1.contains("android:name")) {
                        val a = line1
                        val b = a.split("=")
                        val c = b[1]
                        val d = c.split("\"")
                        val e = d[1].split(".")

                        applicationClassName = e[1]
                        break
                    }
                }
                break
        }
        }
        return applicationClassName

    }

    fun get_receiver_Class():String{

        val documentText = androidManifestfile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]
            if (line.contains("com.google.firebase.MESSAGING_EVENT"))
            {
                for(k in i-1 downTo 1)
                {
                    val line2=documentText[k]
                    if(line2.contains("android:name")) {

                        val ans11=line2
                        val ans12= ans11.split("\"")
                        val ans13=ans12[1]
                        firebase_receiver_class_name=ans13
                        break
//
                    }


                }
            }

        }
        return firebase_receiver_class_name

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
        if (java_file_exist) {
             language= "java"

        }
        if(kotlin_file_exist)
        {
            language= "kotlin"
        }

        return language
    }

    fun find_appClass() : String{

        AndroidManifest()


        return applicationClassName
    }

    fun find_receiver_class() : String{

        AndroidManifest()


        return firebase_receiver_class_name
    }

}