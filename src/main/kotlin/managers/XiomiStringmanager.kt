package managers

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants

class XiomiStringmanager(private val project: Project) {

    private var stringXML: Document? = null
    private var projectBaseDir: VirtualFile? = null
    private var xiaomi_app_id:Boolean = false
    private var xiaomi_app_key:Boolean = false

    fun initStringXml(): Boolean {
        val basePath = project.basePath
        projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)
        val stringVirtualFile: VirtualFile? = projectBaseDir!!
            .findChild(Constants.DEFAULT_MODULE_NAME)!!
            .findChild("src")!!
            .findChild("main")!!
            .findChild("res")!!
            .findChild("values")!!
            .findChild("strings.xml")
        return if (stringVirtualFile != null) {
            stringXML = FileDocumentManager.getInstance().getDocument(stringVirtualFile)
            true
        }
        else {
            false
        }
    }

    fun checkbeforeinsertion(){
        val documentText = stringXML!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in documentText.indices){
            val line = documentText[i]
            if(line.contains("xiaomi_app_key"))
            {
                xiaomi_app_key=true
            }
            if(line.contains("xiaomi_app_id"))
            {
                xiaomi_app_id=true
            }
        }
    }

    fun addStringXmlContent(AppID:String,AppKey:String){
        checkbeforeinsertion()
        val documentText = stringXML!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices){
            val line = documentText[i]
            if(!xiaomi_app_id){
                if (line.contains(Constants.RESOURCES)){
                    if (line.contains("/")){
                        sb
                            .append("    <!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("    <string name=\"xiaomi_app_id\">$AppID</string>")
                            .append("\n")
                        xiaomi_app_id=true
                    }
                }
            }

            if(!xiaomi_app_key){
                if (line.contains(Constants.RESOURCES)){
                    if (line.contains("/")){
                        sb
                            .append("    <!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("    <string name=\"xiaomi_app_key\">$AppKey</string>")
                            .append("\n")
                        xiaomi_app_key=true
                    }
                }
            }
            sb
                .append(line)
                .append("\n")
        }
        writeToStrings(sb)
    }

    private fun writeToStrings(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { stringXML!!.setText(stringBuilder) }
        }
    }
}