package managers

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants
import java.io.FileNotFoundException


class ManifestManager(private val project: Project) {

    private var default_clevertap_class:Boolean=false
    private var internet_permission:Boolean=false
    private var network_access_permission:Boolean=false
    private var clevertap_Id:Boolean=false
    private var clevertap_token:Boolean=false
    private var clevertap_use_google_ad_id:Boolean=false
    private var clevertap_Inapp_exclude:Boolean=false
    private var region_exist:Boolean=false


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
//            sb
//                .append(line)
//                .append("\n")


            //if (line.contains("activity")) {
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

//                val s=line
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
            val line = documentText[i]
            if(line.contains("android:name=\"com.clevertap.android.sdk.Application\""))
            {
                default_clevertap_class=true
            }
            if(line.contains("uses-permission android:name=\"android.permission.INTERNET\""))
            {
                internet_permission=true
            }
            if(line.contains("uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\""))
            {
                network_access_permission=true
            }
            if(line.contains("CLEVERTAP_ACCOUNT_ID"))
            {
                clevertap_Id=true
            }
            if(line.contains("CLEVERTAP_TOKEN"))
            {
                clevertap_token=true
            }
            if(line.contains("CLEVERTAP_USE_GOOGLE_AD_ID"))
            {
                clevertap_use_google_ad_id=true
            }
            if(line.contains("CLEVERTAP_INAPP_EXCLUDE"))
            {
                clevertap_Inapp_exclude=true
            }
            if(line.contains("CLEVERTAP_REGION"))
            {
                region_exist=true
            }

        }
    }
    fun addData(repository1: String,repository2: String,repository3: String,clevertapid :String,clevertaptoken:String, usegoogle_ad_id_rb1:Boolean,usegoogle_ad_id_rb2:Boolean,region : String,clevertap_inappexclude:String) {
        checkbeforeinsertion()
        val clevertapid=clevertapid.trim()
        val clevertaptoken=clevertaptoken.trim()
        val clevertap_inappexclude=clevertap_inappexclude.trim()


        val default_clevertap_class_exist=default_clevertap_class
        var internet_permission_exist=internet_permission
        var network_access_permission_exist=network_access_permission

        var clevertap_usegoogleid_value=""
        if(usegoogle_ad_id_rb1)
        {
            clevertap_usegoogleid_value="1"
        }
        else if(usegoogle_ad_id_rb2)
        {
            clevertap_usegoogleid_value="0"
        }
        else
        {
            clevertap_usegoogleid_value=""
        }
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices) {
            val line = documentText[i]
            if(!internet_permission_exist && !network_access_permission_exist) {
                if (line.contains(Constants.MANIFEST)) {
                    if (line.contains(">")) {
                        sb
                            .append("<!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append(repository1)
                            .append("\n")
                        network_access_permission_exist=true
                        internet_permission_exist=true
                    }
                }
            }
            if(!internet_permission_exist) {
                if (line.contains(Constants.MANIFEST)) {
                    if (line.contains(">")) {
                        sb
                            .append("<!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("    <!-- Required to allow the app to send events and user profile information -->")
                            .append("\n")
                            .append("    <uses-permission android:name=\"android.permission.INTERNET\"/>")
                            .append("\n")
                    }
                }
            }
            if(!network_access_permission_exist) {
                if (line.contains(Constants.MANIFEST)) {
                    if (line.contains(">")) {
                        sb
                            .append("<!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("    <!-- Recommended so that CleverTap knows when to attempt a network call -->")
                            .append("\n")
                            .append("    <uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\"/>")
                            .append("\n")
                    }
                }
            }
            if(!default_clevertap_class_exist) {
                if (line.contains("android:allowBackup")) {
                    sb
                        .append(repository2)
                        .append("\n")

                }
            }
            if(!clevertap_Id && !clevertap_token && !clevertap_use_google_ad_id && !clevertap_Inapp_exclude && !region_exist) {
                if (line.contains(Constants.APPLICATION)) {
                    if (line.contains("/")) {
                        sb
                            .append("<!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append(repository3)
                            .append("\n")
                        clevertap_Id=true
                        clevertap_token=true
                        clevertap_use_google_ad_id=true
                        clevertap_Inapp_exclude=true
                        region_exist=true
                    }

                }
            }
            if(!clevertap_Id) {
                if (line.contains(Constants.APPLICATION)) {
                    if (line.contains("/")) {
                        sb
                            .append("         <!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("         <!-- Adding CleverTap Account_Id-->")
                            .append("\n")
                            .append("         <meta-data")
                            .append("\n")
                            .append("             android:name=\"CLEVERTAP_ACCOUNT_ID\"")
                            .append("\n")
                            .append("             android:value=\"$clevertapid\" />")
                            .append("\n")
                        clevertap_Id=true
                    }
                }
            }
            if(!clevertap_token) {
                if (line.contains(Constants.APPLICATION)) {
                    if (line.contains("/")) {
                        sb
                            .append("         <!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("         <!-- Adding CleverTap Token-->")
                            .append("\n")
                            .append("         <meta-data")
                            .append("\n")
                            .append("             android:name=\"CLEVERTAP_TOKEN\"")
                            .append("\n")
                            .append("             android:value=\"$clevertaptoken\" />")
                            .append("\n")
                        clevertap_token=true
                    }
                }
            }

            if(!clevertap_use_google_ad_id) {
                if (line.contains(Constants.APPLICATION)) {
                    if (line.contains("/")) {
                        sb
                            .append("         <!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("         <!-- IMPORTANT: To force use Google AD ID to uniquely identify  users, use the following meta tag. GDPR mandates that if you are using this tag, there is prominent disclousure to your end customer in their application. Read more about GDPR here - https://clevertap.com/blog/in-preparation-of-gdpr-compliance/ -->")
                            .append("\n")
                            .append("         <meta-data")
                            .append("\n")
                            .append("             android:name=\"CLEVERTAP_USE_GOOGLE_AD_ID\"")
                            .append("\n")
                            .append("             android:value=\"$clevertap_usegoogleid_value\" />")
                            .append("\n")
                        clevertap_use_google_ad_id=true
                    }
                }
            }
            if(!region_exist) {
                if (line.contains(Constants.APPLICATION)) {
                    if (line.contains("/")) {
                        sb
                            .append("         <!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("         <!-- Adding CleverTap Region -->")
                            .append("\n")
                            .append("         <meta-data")
                            .append("\n")
                            .append("             android:name=\"CLEVERTAP_REGION\"")
                            .append("\n")
                            .append("             android:value=\"$region\" />")
                            .append("\n")
                        region_exist=true
                    }
                }
            }
            if(!clevertap_Inapp_exclude) {
                if (line.contains(Constants.APPLICATION)) {
                    if (line.contains("/")) {
                        sb
                            .append("         <!-- Added by CleverTap Assistant-->")
                            .append("\n")
                            .append("         <!-- Activities to be excluded from in-app notifications-->")
                            .append("\n")
                            .append("         <meta-data")
                            .append("\n")
                            .append("             android:name=\"CLEVERTAP_INAPP_EXCLUDE\"")
                            .append("\n")
                            .append("             android:value=\"$clevertap_inappexclude\" />")
                            .append("\n")
                        clevertap_Inapp_exclude=true

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
