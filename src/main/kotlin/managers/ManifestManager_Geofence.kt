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


class ManifestManager_Geofence(private val project: Project) {

    private var acces_fine_location_permission:Boolean=false
    private var access_background_location_permission:Boolean=false
    private var wake_lock_permission:Boolean=false
    private var receive_boot_completed_permission:Boolean=false


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

    fun checkbeforeinsertion()
    {
        //val opp=launchingactivityname
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()
        for (i in documentText.indices)
        {
            var line = documentText[i]

            if(line.contains("<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />"))
            {
                acces_fine_location_permission=true
            }
            if(line.contains("<uses-permission android:name=\"android.permission.ACCESS_BACKGROUND_LOCATION\" />"))
            {
                access_background_location_permission=true
            }
            if(line.contains("<uses-permission android:name=\"android.permission.WAKE_LOCK\"/>"))
            {
                wake_lock_permission=true
            }
            if(line.contains("<uses-permission android:name=\"android.permission.RECEIVE_BOOT_COMPLETED\"/>"))
            {
                receive_boot_completed_permission=true
            }


        }
    }
    fun addData() {
        checkbeforeinsertion()

        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]

            if(acces_fine_location_permission==false) {
                if (line.contains(Constants.MANIFEST)) {
                    if (line.contains(">")) {
                        sb
                            .append("<!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("    <uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />")
                            .append("\n")
                        acces_fine_location_permission=true
                    }
                }
            }
            if(access_background_location_permission==false) {
                if (line.contains(Constants.MANIFEST)) {
                    if (line.contains(">")) {
                        sb
                            .append("<!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("    <uses-permission android:name=\"android.permission.ACCESS_BACKGROUND_LOCATION\" />")
                            .append("\n")
                        access_background_location_permission=true
                    }
                }
            }
            if(wake_lock_permission==false) {
                if (line.contains("Constants.MANIFEST")) {
                    if (line.contains(">")) {

                        sb
                            .append("<!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("    <uses-permission android:name=\"android.permission.WAKE_LOCK\" />")
                            .append("\n")
                        wake_lock_permission=true
                    }

                }
            }
            if(receive_boot_completed_permission==false) {
                if (line.contains("Constants.MANIFEST")) {
                    if (line.contains(">")) {

                        sb
                            .append("<!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("    <uses-permission android:name=\"android.permission.RECEIVE_BOOT_COMPLETED\"/>")
                            .append("\n")
                        receive_boot_completed_permission=true
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
