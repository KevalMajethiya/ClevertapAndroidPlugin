package managers

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants

class XiomiManifestManager(private val project: Project) {

    private var androidManifest: Document? = null
    private var projectBaseDir: VirtualFile? = null
    private var xiaomi_app_id:Boolean = false
    private var xiaomi_app_key:Boolean = false

    fun initAndroidManifest(): Boolean{
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
        }
        else {
            false
        }
    }


    fun checkbeforeinsertion(){
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in documentText.indices){
            val line = documentText[i]
            if(line.contains("CLEVERTAP_XIAOMI_APP_KEY"))
            {
                xiaomi_app_key=true
            }
            if(line.contains("CLEVERTAP_XIAOMI_APP_ID"))
            {
                xiaomi_app_id=true
            }
        }
    }


    fun addMetaDataContent(repository: String, AppID: String, AppKey: String){
        checkbeforeinsertion()
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices){
            val line = documentText[i]
//            if (xiaomi_app_key==false && xiaomi_app_id==false){
//                if (line.contains(Constants.APPLICATION)) {
//                    if (line.contains("/")) {
//                        sb
//                            .append("<!-- Added by CleverTap Assistant-->")
//                            .append("\n")
//                            .append(repository)
//                            .append("\n")
//
//                        xiaomi_app_key=true
//                        xiaomi_app_id=true
//
//                    }
//                }
//            }

            if(!xiaomi_app_id){
                if (line.contains(Constants.APPLICATION)){
                    if (line.contains("/")){
                        sb
                            .append("<!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append(
                                "<meta-data\n" +
                                        "    android:name=\"CLEVERTAP_XIAOMI_APP_KEY\"\n" +
                                        "    android:value=\"@string/xiaomi_app_key\" />"
                            )
                            .append("\n")
                        xiaomi_app_id=true
                    }
                }
            }
            if(!xiaomi_app_key){
                if (line.contains(Constants.APPLICATION)){
                    if (line.contains("/")){
                        sb
                            .append("<!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append(
                                "<meta-data\n" +
                                        "    android:name=\"CLEVERTAP_XIAOMI_APP_ID\"\n" +
                                        "    android:value=\"@string/xiaomi_app_id\" />"
                            )
                            .append("\n")
                        xiaomi_app_key=true
                    }
                }
            }
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
