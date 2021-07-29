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
import java.io.FileWriter


class Create_PushTemplate_service_file(private val project: Project)
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
    //var dirpath =""



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
        //val sb = StringBuilder()

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

        }

        return "com"
    }


    @Throws(FileNotFoundException::class)

    fun initapplicationclass(){
        AndroidManifest()
        //val op=launchingactivityname
        var op1=packagename
        // val ans=pkg
        var ans1=op1.replace(".","/")
        // val ans2=ans1.replace("\"","")
        print(ans1)
        val basePath = project.basePath
        //projectBaseDir = basePath +"/app/src/main/java/"+ans1
        print(projectBaseDir)
        var dirpath = basePath +"/app/src/main/java/"+ans1+"/"+"Push_Templates"
        // var ans = dirpath
        //path
        var dir = File(dirpath)
        //var file = File("/Users/kevalmajethiya/AndroidStudioProjects/MyApp/app/src/main/java/com/example/myapp/fcm")
        //if(dir.exists()==false)
        //{
        dir.mkdir()

        // }
        var count = dir.list().size
        if(count<1) {
            var file1 = File(dirpath + "/" + "pushtemplate_service" + ".java")
            file1.createNewFile()
            var fw = FileWriter(dirpath + "/" + "pushtemplate_service" + ".java")
            fw.write(
                "package $op1.Push_Templates;\n\n" +
                        "\n" +
                        "import android.app.Service;\n" +
                        "import android.app.Service;\n" +
                        "import android.content.Context;\n" +
                        "import android.content.Intent;\n" +
                        "import android.util.Log;\n" +
                        "import android.os.Bundle;\n" +
                        "import android.os.IBinder;\n"+
                        "import android.widget.Toast;\n"+
                        "import androidx.annotation.Nullable;\n" +
                        "import com.clevertap.android.sdk.CleverTapAPI;\n" +
                        "import com.clevertap.pushtemplates.TemplateRenderer;\n" +
                        "import com.clevertap.pushtemplates.Utils;\n" +
                        "\n" +
                        "\n"+
                        "public class pushtemplate_service extends Service {\n" +
                        "\n" +
                        "\t@Nullable\n" +
                        "\t@Override\n" +
                        "\tpublic IBinder onBind(Intent intent) {\n" +
                        "\t\treturn null;\n" +
                        "\t}\n"+
                        "\n" +
                        "\t@Override\n" +
                        "\tpublic int onStartCommand(Intent intent, int flags, int startId) {\n" +
                        "\t\tTemplateRenderer.setDebugLevel(3);\n" +
                        "\t\tContext context = getApplicationContext();\n" +
                        "\t\tBundle extras1 = new Bundle();\n" +
                        "\t\tif (Utils.isForPushTemplates(extras1)) {\n" +
                        "\t\t\tTemplateRenderer.createNotification(context, extras1);\n" +
                        "\t\t}\n" +
                        "\n" +
                        "\t\telse {\n" +
                        "\t\t\tCleverTapAPI.createNotification(context, extras1);\n" +
                        "\t\t}" +
                        "\t\treturn START_STICKY;\n" +

                        "\t}\n" +
                        "\n"+
                        "\t@Override\n" +
                        "\tpublic void onDestroy() {\n" +
                        "\t\tsuper.onDestroy();\n" +
                        "\t\tToast.makeText(this, \"Service Destroyed\", Toast.LENGTH_LONG).show();\n" +
                        "\t}\n" +
                        "\n" +
                        "}\n"

            )
            fw.close()
        }


    }


    fun initiateclevertap()
    {

//        path
//        var file = File("/Users/kevalmajethiya/AndroidStudioProjects/MyApp/app/src/main/java/com/example/myapp/fcm")
//        file.mkdir()


    }


    private fun writeToManifest(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { androidapplicationclass!!.setText(stringBuilder) }
        }
    }
}