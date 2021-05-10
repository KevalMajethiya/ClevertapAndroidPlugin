package managers
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.kotlin.idea.util.findModule
import ui.NewScreenDialog_Clevertap

import util.Constants
import java.io.File
import java.io.FileNotFoundException
import java.util.logging.Logger


class ApplicationClassManager(private val project: Project)
{
    private var androidapplicationclass: Document? = null
    private var androidManifestfile: Document? = null
    private var ans:String=""
    var radiobuttonvalue : Boolean=false
    private var packagename:String=""
    private var codeexist:Boolean=false
   private var import_stmt:Boolean=false

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

    fun getpackagename():String {


        val documentText = androidManifestfile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()

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
                    //initapplicationclass("d",packagename!!)

                    //print(packagename)

                    //ans="com"
                    //return "abc"
                }
            }

                sb
                    .append(line)
                    .append("\n")

            //}
        }
        writeToManifest(sb)
        return "com"
    }

    @Throws(FileNotFoundException::class)
    fun initapplicationclass(demo :String,IsRadiobuttonrb1Selected:Boolean): Boolean {
        AndroidManifest()
        var op1=packagename
        radiobuttonvalue=IsRadiobuttonrb1Selected
        var ans1 =op1.replace(".","/")
        val basePath = project.basePath
        //print(demo)

        val applicationfilename=demo
        //print(applicationfilename)
        //projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ ans1 +"/" + applicationfilename +".java")
        var file = File(project.basePath +"/app/src/main/java/"+ans1+"/" + applicationfilename +".java")
        var file1 = File(project.basePath +"/app/src/main/java/"+ans1+"/" + applicationfilename +".kt")
        var java_file_exist = file.exists()
        var kotlin_file_exist = file1.exists()
        if(java_file_exist==true)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + applicationfilename +".java")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                addapplicationclassdetails()
                true
            } else {
                false
            }
        }
        if(kotlin_file_exist==true)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + applicationfilename +".kt")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                addapplicationclassdetails_kt()
                true
            } else {
                false
            }
        }

        val manifestVirtualFile: VirtualFile? = projectBaseDir
//            .findChild(Constants.DEFAULT_MODULE_NAME)!!
//            .findChild("src")!!
//            .findChild("main")!!
//            .findChild("java")!!
//
//            .findChild("com")!!
//            //.findChild(a)!!
//            .findChild("example")!!
//            .findChild("demoapp1")!!
//            .findChild("MyApplicationClass.java")
        return if (manifestVirtualFile != null) {
            androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
            addapplicationclassdetails()
            true
        } else {
            false
        }
    }

    fun checkbeforeinsertion()
    {
        //val opp=launchingactivityname
        val documentText = androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()
        for (i in documentText.indices)
        {
            var line = documentText[i]
            if(line.contains("ActivityLifecycleCallback.register"))
            {
                codeexist=true

            }
            if(line.contains("import com.clevertap.android.sdk.ActivityLifecycleCallback"))
            {
                import_stmt=true

            }
        }
    }
    fun addapplicationclassdetails() {
        checkbeforeinsertion()
        var c= codeexist
       // if(c==false) {
            var rnabs = radiobuttonvalue
            val documentText =
                androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val sb = StringBuilder()
            if (radiobuttonvalue == true) {
                for (i in documentText.indices) {
                    var line = documentText[i]
                    if(import_stmt==false) {
                        if (line.contains("package")) {
                            sb.append("import com.clevertap.android.sdk.ActivityLifecycleCallback;")
                            sb.append("\n")
                        }
                    }
                    if(c==false) {
                        // var line1= documentText[i]
                        if (line.contains("public void onCreate() { super.onCreate();")) {
                            val ans = line
                            val ans1 = ans.split("{")
                            val ans2 = ans1[0]
                            val ans3 = ans1[1]
                            line.split("{")
                            //line=line[0].toString()
                            line = line[0].toString()

                            sb
                                //.append("ActivityLifecycleCallback.register(this);")
                                .append(ans2)
                                .append("\n")
                                .append("    {")
                                .append("\n")
                                .append("       " + ans3)
                                .append("\n")
                                //.append(repository)
                                .append("// Must be called before super.onCreate()")
                                .append("\n")
                                .append("        " + "ActivityLifecycleCallback.register(this);")
                                .append("   //added by CleverTap Assistant")
                                .append("\n")
                            c=true


                        }
                        if (line.contains("{ super.onCreate();")) {
                            val ans = line
                            val ans1 = ans.split("{")
                            val ans2 = ans1[1]
                            //val ans3=ans1[1]
                            line.split("{")
                            //line=line[0].toString()
                            line = line[0].toString()

                            sb
                                //.append("ActivityLifecycleCallback.register(this);")
                                .append("{")
                                .append("\n")
                                .append(ans2)
                                .append("\n")


                                // .append("       "+ans3)
                                // .append("\n")
                                //.append(repository)
                                .append("// Must be called before super.onCreate()")
                                .append("\n")
                                .append("        " + "ActivityLifecycleCallback.register(this);")
                                .append("   //added by CleverTap Assistant")
                                .append("\n")
                            c=true


                        }
                        if (line.contains(" super.onCreate()")) {

                            //  if (line.contains("{"))
                            //  {
                            sb
                                // .append(repository)
                                .append("// Must be called before super.onCreate()")
                                .append("\n")
                                .append("    " + "ActivityLifecycleCallback.register(this);")
                                .append("   //added by CleverTap Assistant")
                                .append("\n")
                            c=true
                            //   }

                        }
                    }

                        sb
                            .append(line)
                            .append("\n")
                        line = documentText[i]

                }
            }
            writeToManifest(sb)
        //}

    }
    fun addapplicationclassdetails_kt() {
        checkbeforeinsertion()
        var c= codeexist
        // if(c==false) {
        var rnabs = radiobuttonvalue
        val documentText =
            androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        if (radiobuttonvalue == true) {
            for (i in documentText.indices) {
                var line = documentText[i]
                if(import_stmt==false) {
                    if (line.contains("class")) {
                        sb.append("import com.clevertap.android.sdk.ActivityLifecycleCallback")
                        sb.append("\n")
                    }
                }
                if(c==false) {
                    // var line1= documentText[i]
                    if (line.contains(" override fun onCreate() { super.onCreate()")) {
                        val ans = line
                        val ans1 = ans.split("{")
                        val ans2 = ans1[0]
                        val ans3 = ans1[1]
                        line.split("{")
                        //line=line[0].toString()
                        line = line[0].toString()

                        sb
                            //.append("ActivityLifecycleCallback.register(this);")
                            .append(ans2)
                            .append("\n")
                            .append("    {")
                            .append("\n")
                            .append("       " + ans3)
                            .append("\n")
                            //.append(repository)
                            .append("// Must be called before super.onCreate()")
                            .append("\n")
                            .append("\t\tActivityLifecycleCallback.register(this)")
                            .append("   //added by CleverTap Assistant")
                            .append("\n")
                        c=true


                    }
                    if (line.contains("{ super.onCreate()")) {
                        val ans = line
                        val ans1 = ans.split("{")
                        val ans2 = ans1[1]
                        //val ans3=ans1[1]
                        line.split("{")
                        //line=line[0].toString()
                        line = line[0].toString()

                        sb
                            //.append("ActivityLifecycleCallback.register(this);")
                            .append("{")
                            .append("\n")
                            .append(ans2)
                            .append("\n")


                            // .append("       "+ans3)
                            // .append("\n")
                            //.append(repository)
                            .append("// Must be called before super.onCreate()")
                            .append("\n")
                            .append("\t\tActivityLifecycleCallback.register(this)")
                            .append("   //added by CleverTap Assistant")
                            .append("\n")
                        c=true


                    }
                    if (line.contains(" super.onCreate()")) {

                        //  if (line.contains("{"))
                        //  {
                        sb
                            // .append(repository)
                            .append("// Must be called before super.onCreate()")
                            .append("\n")
                            .append("\t\tActivityLifecycleCallback.register(this)")
                            .append("   //added by CleverTap Assistant")
                            .append("\n")
                        c=true
                        //   }

                    }
                }

                sb
                    .append(line)
                    .append("\n")
                line = documentText[i]

            }
        }
        writeToManifest(sb)
        //}

    }


    private fun writeToManifest(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {

                application.runWriteAction { androidapplicationclass!!.setText(stringBuilder) }

        }
    }
}