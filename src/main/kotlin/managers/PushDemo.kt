package managers

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants
import java.io.FileNotFoundException


class PushDemo(private val project: Project) {


    private var final_ans:String=""


    private var androidManifest: Document? = null

    private var projectBaseDir: VirtualFile? = null

    @Throws(FileNotFoundException::class)
    fun initAndroidManifest(): Boolean {
        val basePath = project.basePath
        projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)
        val manifestVirtualFile: VirtualFile? = projectBaseDir!!
            .findChild(Constants.DEFAULT_MODULE_NAME)!!
            .findChild("src")!!
            .findChild("main")!!
            .findChild("AndroidManifest.xml")
        return if (manifestVirtualFile != null) {
            androidManifest = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

            true
        } else {
            false
        }
    }






    fun addMetaDataContent() {
        initAndroidManifest()
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]

//                if (line.contains("com.google.firebase.MESSAGING_EVENT")) {
//                    sb
//
//                        .append(line)
//                        .append("\n")
//                }
            if (line.contains("com.google.firebase.MESSAGING_EVENT"))
            {
                for(k in i-1 downTo 1)
                {
                    val line2=documentText[k]
                    if(line2.contains("android:name")) {

                        val ans11=line2
                        val ans12= ans11.split("\"")
                        val ans13=ans12[1]
                        //var ans14=ans12[1].split("\"")
                        final_ans=ans13
                        //print(firebase_receiver_class_name)
                        // launchingactivityname="line"
                        //return firebase_receiver_class_name
                        //initapplicationclass(activityname)
                        //sb
                        //.append(activityname)
                        // .append("\n")
                        sb
                            .append(ans13)
                            .append("\n")
                        break

                    }
                    //break
                }
            }

            final_ans
            sb
                .append(line)
                .append("\n")
        }
        writeToManifest(sb)
    }





    private fun writeToManifest(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { androidManifest!!.setText(stringBuilder) }
        }
    }
}
