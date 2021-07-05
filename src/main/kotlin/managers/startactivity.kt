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


class startactivity(private val project: Project) {
    private var androidapplicationclass: Document? = null

    //private var androidapplicationclass1: Document? = null
    private var androidManifestfile: Document? = null
    private var ans: String = ""
    private var codeexist: Boolean = false
    private var import_stmt: Boolean = false
    private var packagename: String = ""
    private var launchingactivityname: String = ""
    private var import_stmt_hashmap: Boolean = false


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

    fun getpackagename(): String {


        val documentText =
            androidManifestfile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()

        for (i in documentText.indices) {
            val line = documentText[i]

            if (line.contains("android.intent.category.LAUNCHER")) {
                for (j in i downTo 1) {
                    var line1 = documentText[j]
                    if (line1.contains("activity")) {

                        for (k in j..i) {
                            var line2 = documentText[k]
                            if (line2.contains("android:name")) {
                                var ans = line2
                                var b = ans.split(".")
                                var c = b[1]
                                var d = b[1].split("\"")
                                var e = d[0]
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
//            sb
//                .append(line)
//                .append("\n")

            //  }

        }
        // writeToManifest(sb)
        return "com"
    }
//    fun check():String
//    {
//        //val documentText = androidManifestfile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//        ans = "com"
//        return ans
//
//    }


    fun startActivity(): String {
        AndroidManifest()
        //val debugFlag = if (attachDebugger) "-D " else ""
        var db = true
        var op = launchingactivityname
        var op1 = packagename

        var ans1 = op1.replace(".", "/")
        var ans = "./adb shell am start -n" + " " + ans1 + "/." + op
        //var process = Runtime.getRuntime().exec(ans)
        //var process = Runtime.getRuntime().exec("cd /Users/kevalmajethiya/Library/Android/sdk/platform-tools/ans")
        //var process1 = Runtime.getRuntime().exec(ans)
        return ans


    }

}