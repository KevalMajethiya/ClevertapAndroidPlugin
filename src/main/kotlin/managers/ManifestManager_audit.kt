package managers

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.kotlin.idea.kdoc.insert
import util.Constants
import util.Methods
import java.io.FileNotFoundException


class ManifestManager_audit(private val project: Project) {


    private var read_logs_permission:Boolean=false
    private var write_external_storage_permission:Boolean=false


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

    fun addApplicationClass(repository: String) {

        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]
            sb
                .append(line)
                .append("\n")


            if (line.contains("android:allowBackup")) {
                //if (line.contains(Constants.APPLICATION)) {

                // if (line.contains(">")) {
                sb

                    .append(repository)
                    .append("\n")
                // break
                // }

            }
        }
        writeToManifest(sb)
    }

    fun launchingactname() {

        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]
            sb
                .append(line)
                .append("\n")


            //if (line.contains("activity")) {
            if (line.contains("android.intent.category.LAUNCHER"))
            {
                for(j in i downTo 1)
                {
                    var line1=documentText[j]
                    if(line1.contains("activity")) {

                        for(k in j..i) {
                            var line2 = documentText[k]
                            if (line2.contains("android:name")) {
                                var ans = line2
                                var b = ans.split(".")
                                var c = b[1]
                                var d = b[1].split("\"")
                                var e = d[0]
                                var activityname = e
                                break
                            }
                        }
                        break


                    }


                }
            }


            //}
        }
        writeToManifest(sb)
    }




    fun addMetaDataContent(repository: String) {

        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]
            if (line.contains(Constants.APPLICATION)) {

                if (line.contains("/")) {
                    sb

                        .append(repository)
                        .append("\n")
                }

            }
            sb
                .append(line)
                .append("\n")
        }
        writeToManifest(sb)
    }



    fun addPermissionData(repository: String){
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]

            if (line.contains(Constants.MANIFEST)) {

                val s=line
                //s.split(" ")
                //val a=s.substring(10)

                //val b=sb.split(a)
                //sb.append("abc" + a)
                // line.removeRange(10,15)
                //sb.append(b)
                //sb.append("\n")

                if (line.contains(">")) {
                    sb
                        .append(repository)
                        .append("\n")
                }

            }
            sb
                .append(line)
                .append("\n")
        }
        writeToManifest(sb)
    }

    fun checkbeforeinsertion()
    {
        //val opp=launchingactivityname
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()
        for (i in documentText.indices)
        {
            var line = documentText[i]

            if(line.contains("uses-permission android:name=\"android.permission.READ_LOGS\""))
            {
                read_logs_permission=true
            }
            if(line.contains("uses-permission android:name=\"android.permission.WRITE_EXTERNAL_STORAGE\""))
            {
                write_external_storage_permission=true
            }


        }
    }
    fun addData() {
        checkbeforeinsertion()

        var read_logs_permission_exist=read_logs_permission
        var write_external_storage_permission_exist=write_external_storage_permission

        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]

            if(read_logs_permission_exist==false) {
                if (line.contains(Constants.MANIFEST)) {
                    if (line.contains(">")) {
                        sb
                            .append("    <!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("    <!-- Required to read logs -->")
                            .append("\n")
                            .append("    <uses-permission android:name=\"android.permission.READ_LOGS\" />\n")
                            .append("\n")
                        read_logs_permission_exist=true
                    }
                }
            }
            if(write_external_storage_permission_exist==false) {
                if (line.contains(Constants.MANIFEST)) {
                    if (line.contains(">")) {
                        sb
                            .append("    <!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("    <!-- Required to read and write data to external storage -->\n")
                            .append("\n")
                            .append("     <uses-permission android:name=\"android.permission.WRITE_EXTERNAL_STORAGE\" />\n")
                            .append("\n")
                        write_external_storage_permission_exist=true
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
