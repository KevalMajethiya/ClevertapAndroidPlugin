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


class createFCMfile(private val project: Project)
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


    @Throws(FileNotFoundException::class)

    fun initapplicationclass(serviceNameText:String,channel_id:String){
        AndroidManifest()
        val op=launchingactivityname
        var op1=packagename
        // val ans=pkg
        var ans1=op1.replace(".","/")
        // val ans2=ans1.replace("\"","")
        print(ans1)
        val basePath = project.basePath
        //projectBaseDir = basePath +"/app/src/main/java/"+ans1
        print(projectBaseDir)
        var dirpath = basePath +"/app/src/main/java/"+ans1+"/"+"fcm"
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
            var file1 = File(dirpath + "/" + serviceNameText + ".java")
            file1.createNewFile()
            var fw = FileWriter(dirpath + "/" + serviceNameText + ".java")
            fw.write(
                "package $op1.${Constants.FCM_DIRECTORY};\n\n" +
                        "\n" +
                        "import android.app.NotificationManager;\n" +
                        "\n" +
                        "import android.os.Bundle;\n" +
                        "\n" +
                        "import androidx.annotation.NonNull;\n" +
                        "\n" +
                        "import android.util.Log;\n" +
                        "\n" +
                        "import com.clevertap.android.sdk.CleverTapAPI;\n" +
                        "\n" +
                        "\n" +
                        "import com.clevertap.android.sdk.pushnotification.NotificationInfo;\n" +
                        "import com.google.firebase.messaging.FirebaseMessagingService;\n" +
                        "import com.google.firebase.messaging.RemoteMessage;\n" +
                        "\n" +
                        "import java.util.Map;\n" +
                        "\n" +
                        "//******************* PLEASE MAKE SURE TO ADD THE GOOGLE_SERVICES.JSON FILE IN TO THE PROJECT DIRECTORY***************//\n" +
                        "public class $serviceNameText extends FirebaseMessagingService {\n" +
                        "\n" +
                        "\tCleverTapAPI clevertapDefaultInstance;\n" +
                        "\t@Override\n" +
                        "\tpublic void onMessageReceived(RemoteMessage remoteMessage) {\n" +
                        "\t\tsuper.onMessageReceived(remoteMessage);\n" +
                        "\n" +
                        "\t\tRemoteMessage.Notification notification = remoteMessage.getNotification();\n" +
                        "\t\ttry {\n" +
                        "\t\t\tif (remoteMessage.getData().size() > 0) {\n" +
                        "\t\t\t\tBundle extras = new Bundle();\n" +
                        "\t\t\t\tfor (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {\n" +
                        "\t\t\t\t\textras.putString(entry.getKey(), entry.getValue());\n" +
                        "\t\t\t\t\tLog.d(\"key,value\", entry.getKey()+\" and \"+entry.getValue());\n" +
                        "\t\t\t\t}\n" +
                        "\n" +
                        "\t\t\t\tNotificationInfo info = CleverTapAPI.getNotificationInfo(extras);\n" +
                        "\n" +
                        "\n" +
                        "\t\t\t\tif (info.fromCleverTap) {\n" +
                        "\n" +
                        "\t\t\t\t\t\tCleverTapAPI.createNotification(getApplicationContext(), extras);\n" +
                        "\n" +
                        "\t\t\t\t} else {\n" +
                        "\t\t\t\t\tMap<String, String> data = remoteMessage.getData();\n" +
                        "\t\t\t\t\tLog.d(\"FROM\", remoteMessage.getFrom());\n" +
                        "\t\t\t\t}\n" +
                        "\t\t\t}\n" +
                        "\t\t} catch (Throwable t) {\n" +
                        "\t\t\tLog.d(\"MYFCMLIST\", \"Error parsing FCM message\", t);\n" +
                        "\t\t}\n" +
                        "\n" +
                        "\n" +
                        "\t}\n" +
                        "\n" +
                        "\t@Override\n" +
                        "\tpublic void onNewToken(@NonNull String s) {\n" +
                        "\t\tsuper.onNewToken(s);\n" +
                        "\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());\n" +
                        "\t\tclevertapDefaultInstance.pushFcmRegistrationId(s,true);\n" +
                        "\t\tCleverTapAPI.createNotificationChannel(this,\"$channel_id\",\"$channel_id\",\"Channel for Push in App\", NotificationManager.IMPORTANCE_HIGH,true);\n" +
                        "\t}\n" +
                        "\n" +
                        "\n" +
                        "}"
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