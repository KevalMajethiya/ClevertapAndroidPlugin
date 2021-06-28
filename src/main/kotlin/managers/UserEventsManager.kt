package managers

//package managers

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants
import java.io.FileNotFoundException


class UserEventsManager(private val project: Project)
{
    private var androidapplicationclass: Document? = null
    private var androidManifestfile: Document? = null
    private var eventproperties_exsit:Boolean=false
    private var pushevent_exist:Boolean=false
    private var packagename:String=""
    private var launchingactivityname:String=""
    private var import_stmt_hashmap:Boolean=false
    private var hashmap_obj:Boolean=false
    private var clevertap_instance:Boolean=false



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

                        for(k in j..i) {
                            val line2 = documentText[k]
                            if (line2.contains("android:name")) {

                                val b = line2.split(".")
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

                    val b = line.split("=")
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
        writeToManifest(sb)
        return "com"
    }
//    fun check():String
//    {
//        //val documentText = androidManifestfile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//        ans = "com"
//        return ans
//
//    }


    @Throws(FileNotFoundException::class)
    fun initapplicationclass(): Boolean {
        AndroidManifest()
        val op=launchingactivityname
        val op1=packagename
        // val ans=pkg
        val ans1=op1.replace(".","/")
        // val ans2=ans1.replace("\"","")
        print(ans1)
        projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
        print(projectBaseDir)

        val manifestVirtualFile: VirtualFile? = projectBaseDir
        return if (manifestVirtualFile != null) {
            androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
            initiate_UserEvents()
            true
        } else {
            false
        }
    }

    fun checkinsertion()
    {
        //val opp=launchingactivityname
        val documentText = androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()

        for (i in documentText.indices)
        {
            val line = documentText[i]

            if(line.contains("CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());"))
            {
                clevertap_instance=true

            }
            if(line.contains("import java.util.HashMap;"))
            {
                import_stmt_hashmap=true

            }
            if(line.contains("eventProperties.put(\"Event Property_name \", \"value\");"))
            {
                eventproperties_exsit=true

            }
            if(line.contains("clevertapDefaultInstance.pushEvent(\"ProductViewed\", eventProperties);"))
            {
                pushevent_exist=true

            }
            if(line.contains(" HashMap<String, Object> eventProperties = new HashMap<String, Object>();"))
            {
                hashmap_obj=true

            }

        }
    }

    fun initiate_UserEvents() {
        checkinsertion()
        //var c= context_exsit
        // if(c==false) {
        // val opp=launchingactivityname
        val documentText =
            androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()

        for (i in documentText.indices) {
            val line = documentText[i]
            sb
                .append(line)
                .append("\n")


            if(!import_stmt_hashmap) {
                if (line.contains("package")) {
                    // if (line.contains("/")) {
                    sb
                        .append("import java.util.HashMap;")
                        .append("                      //added by CleverTap plug-in")
                        .append("\n")
                    import_stmt_hashmap=true
                    // }
                }
            }

            if(!hashmap_obj) {
                if (line.contains("class")) {

                    sb
                        .append("HashMap<String, Object> eventProperties = new HashMap<String, Object>();")
                        .append("  //added by CleverTap Assistant")
                        .append("\n")
                    import_stmt_hashmap=true

                }
            }

            if(clevertap_instance) {
                if (!eventproperties_exsit) {
                    if (line.contains("void onCreate")) {
                        sb
                            .append("        eventProperties.put(\"Event Property_name \", \"value\");")
                            .append("   //added by CleverTap Assistant")
                            .append("\n")

                        eventproperties_exsit = true


                    }
                }

                if (!pushevent_exist) {
                    if (line.contains("CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());")) {
                        sb
                            // .append("\n")
                            .append("        clevertapDefaultInstance.pushEvent(\"event_name\", eventProperties);")
                            .append("     //added by CleverTap Assistant")
                            .append("\n")
                        pushevent_exist = true


                    }
                }

            }
            if(!clevertap_instance)
            {
                if (line.contains("void onCreate")) {
                    sb
                        .append("\n")
                        .append("        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());")
                        .append("   //Initializing the CleverTap SDK")
                        .append("\n")
                    //clevertap_instance=true
                }
                if (!eventproperties_exsit) {
                    if (line.contains("void onCreate")) {
                        sb
                            .append("        eventProperties.put(\"Event Property_name \", \"value\");")
                            .append("   //added by CleverTap Assistant")
                            .append("\n")

                        eventproperties_exsit = true


                    }
                }

                if (!pushevent_exist) {
                    if (line.contains("setContentView")) {
                        sb
                            // .append("\n")
                            .append("        clevertapDefaultInstance.pushEvent(\"event_name\", eventProperties);")
                            .append("     //added by CleverTap Assistant")
                            .append("\n")
                        pushevent_exist = true


                    }
                }

            }



            // }

        }


        writeToManifest(sb)
        // }

    }


    private fun writeToManifest(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { androidapplicationclass!!.setText(stringBuilder) }
        }
    }
}