package ui


import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import util.Constants
import util.Constants.CONTENT_TEXT
import util.Constants.CONTENT_TITLE
import util.Constants.ChannelID_Name_FCM
import util.Constants.DEPENDENCY_VERSION
import util.Constants.DEPENDENCY_VERSION_VALUE
import util.Constants.DEPENDENCY_VERSION_VALUE_FCM
import util.Constants.FCM_PANEL
import util.Constants.FCM_SERVICE_NAME
import util.Constants.FCM_SERVICE_NAME_FCM
import util.Constants.MY_FIREBASE_MESSAGING_SERVICE
import util.Constants.MY_FIREBASE_MESSAGING_SERVICE_FCM
import util.Constants.NEED_INSTRUCTION
import util.Constants.NOTES_INSTRUCTION
import util.Constants.PENDINGINTENT_ACTIVITY_NAME
import util.Constants.PENDINGINTENT_ACTIVITY_NAME_FCM
import javax.swing.*
import javax.swing.BorderFactory
import java.awt.GridBagConstraints
import java.awt.Insets
import java.awt.GridBagLayout
import java.io.File
import javax.swing.JPanel
import javax.swing.JTextField
import java.io.IOException
import java.nio.file.*


class fcm1(var event: AnActionEvent,private val project: Project) : DialogWrapper(true), NewScreenView{
    private val panelForFCM = fcm()

    private val presenter: NewScreenPresenter
    private var packageName = ""
    private var moduleName = ""
    private var projectBaseDir: VirtualFile? = null
    private var androidManifestfile: com.intellij.openapi.editor.Document? = null
    private var pkgname:String=""
    private var launchingactivityname:String=""
    private var language=""

    init {
        find_language()
        val currentPath = event.getData(DataKeys.VIRTUAL_FILE)?.let {
            val module = ModuleUtil.findModuleForFile(it, event.project!!)?.name ?: Constants.DEFAULT_MODULE_NAME
            CurrentPathfcm(it.path, it.isDirectory, module)
        }
        val projectStructure = ProjectStructureImpl(event.project!!)
        val sourceRootRepository = SourceRootRepositoryImpl(projectStructure)
        val fileCreator = FileCreatorImpl(sourceRootRepository)
        val packageExtractor = PackageExtractorImpl_FCM(currentPath, sourceRootRepository)
        val writeActionDispatcher = WriteActionDispatcherImpl()
        val moduleRepository = ModuleRepositoryImpl(projectStructure)
        presenter = NewScreenPresenter(
            this,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath
        )
        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME
        init()


    }
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
                    pkgname = d[1]
                    //return "abc"
                    //initapplicationclass(packagename!!)
                }
            }
            sb
                .append(line)
                .append("\n")

            //  }

        }
        // writeToManifest(sb)
        return "com"
    }

    fun find_language(): Boolean {
        AndroidManifest()
        val op = launchingactivityname
        var op1 = pkgname
        // val ans=pkg
        var ans1 = op1.replace(".", "/")
        // val ans2=ans1.replace("\"","")
        print(ans1)
        val basePath = project.basePath
        //projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
        print(projectBaseDir)

        var file = File(project.basePath + "/app/src/main/java/" + ans1 + "/" + op + ".java")
        var file1 = File(project.basePath + "/app/src/main/java/" + ans1 + "/" + op + ".kt")
        var java_file_exist = file.exists()
        var kotlin_file_exist = file1.exists()
        if (java_file_exist == true) {
            //language= "java"
                panelForFCM. l3.setText("<html>"+"try {"+"<br>"+
                        "if (remotemessage.getData().size() > 0) {"+"<br>"+
                        "                Bundle extras = new Bundle();"+"<br>"+
                        "                for (Map.Entry<String, String> entry : remotemessage.getData().entrySet()) {"+"<br>"+
                        "                    extras.putString(entry.getKey(), entry.getValue());"+"<br>"+
                        "}"+"<br>"+
                        "                NotificationInfo info = CleverTapAPI.getNotificationInfo(extras);"+"<br>"+
                        "                if (info.fromCleverTap) {"+"<br>"+
                        "                    CleverTapAPI.createNotification(getApplicationContext(), extras);"+"<br>"+
                        "                } else {"+"<br>"+
                        "                    // not from CleverTap handle yourself or pass to another provider"+"<br>"+
                        "}"+"<br>"+
                        "}"+"<br>"+
                        "        } catch (Throwable t) {"+"<br>"+
                        "           Log.d(\"MYFCMLIST\", \"Error parsing FCM message\", t);"+"<br>"+
                        "}"+
                        "</html>")

            panelForFCM. l6.setText("<html>"+"@Override"+"<br>"+"public void onNewToken(@NonNull String s) {"+"<br>"+"    super.onNewToken(s);"+"<br>"+"\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());"+"<br>"+"clevertapDefaultInstance.pushFcmRegistrationId(s,true);"+"<br>"+"}"+"<br>"+"</html>")
            panelForFCM.b.addActionListener()
            {

                fun setStringToClipboard( stringContent : String)
                {
                    val stringSelection = StringSelection(stringContent);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                }
                setStringToClipboard("\t\ttry {\n" +
                        "            if (remotemessage.getData().size() > 0) {\n" +
                        "                Bundle extras = new Bundle();\n" +
                        "                for (Map.Entry<String, String> entry : remotemessage.getData().entrySet()) {\n" +
                        "                    extras.putString(entry.getKey(), entry.getValue());\n" +
                        "                }\n" +
                        "\n" +
                        "                NotificationInfo info = CleverTapAPI.getNotificationInfo(extras);\n" +
                        "\n" +
                        "                if (info.fromCleverTap) {\n" +
                        "                    CleverTapAPI.createNotification(getApplicationContext(), extras);\n" +
                        "                } else {\n" +
                        "                    // not from CleverTap handle yourself or pass to another provider\n" +
                        "                }\n" +
                        "            }\n" +
                        "        } catch (Throwable t) {\n" +
                        "           Log.d(\"MYFCMLIST\", \"Error parsing FCM message\", t);\n" +
                        "        }");
                // If a string is on the system clipboard, this method returns it; otherwise it returns null.

                // This method writes a string to the clipboard.




            }
            panelForFCM.b1.addActionListener()
            {

                fun setStringToClipboard( stringContent : String)
                {
                    val stringSelection = StringSelection(stringContent);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                }
                setStringToClipboard("@Override\n" +
                        "\tpublic void onNewToken(@NonNull String s) {\n" +
                        "\t\tsuper.onNewToken(s);\n" +
                        "\t\tCleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());\n" +
                        "\t\tclevertapDefaultInstance.pushFcmRegistrationId(s,true);\n" +

                        "\t}\n");
                // If a string is on the system clipboard, this method returns it; otherwise it returns null.

                // This method writes a string to the clipboard.




            }

        }
        if(kotlin_file_exist == true)
        {
            //language= "kotlin"
                panelForFCM.l3.setText("<html>"+"remotemessage.data.apply {"+"<br>"+
                        "try {"+"<br>"+
                        "if (size() > 0) {"+"<br>"+
                        "                val extras =Bundle()"+"<br>"+
                        "                for ((key,value) in this ) {"+"<br>"+
                        "                    extras.putString(key, value)"+"<br>"+
                        "}"+"<br>"+
                        "                val info = CleverTapAPI.getNotificationInfo(extras)"+"<br>"+
                        "                if (info.fromCleverTap) {"+"<br>"+
                        "                    CleverTapAPI.createNotification(ApplicationContext(), extras)"+"<br>"+
                        "                } else {"+"<br>"+
                        "                    // not from CleverTap handle yourself or pass to another provider"+"<br>"+
                        "}"+"<br>"+
                        "}"+"<br>"+
                        "        } catch (t: Throwable) {"+"<br>"+
                        "           Log.d(\"MYFCMLIST\", \"Error parsing FCM message\", t)"+"<br>"+
                        "}"+
                        "</html>")

            panelForFCM. l6.setText("<html>"+"@Override"+"<br>"+"fun onNewToken(@NonNull String s) {"+"<br>"+"    super.onNewToken(s)"+"<br>"+"\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext())"+"<br>"+"clevertapDefaultInstance.pushFcmRegistrationId(s,true)"+"<br>"+"}"+"<br>"+"</html>")
            panelForFCM.b.addActionListener()
            {

                fun setStringToClipboard( stringContent : String)
                {
                    val stringSelection = StringSelection(stringContent);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                }
                setStringToClipboard("\t\t message.data.apply {\n" +
                        "\t\ttry {\n"+
                        "            if (size() > 0) {\n" +
                        "                val extras = Bundle()\n" +
                        "                for (key, value) {\n" +
                        "                    extras.putString(key, value)\n" +
                        "                }\n" +
                        "\n" +
                        "                val info = CleverTapAPI.getNotificationInfo(extras)\n" +
                        "\n" +
                        "                if (info.fromCleverTap) {\n" +
                        "                    CleverTapAPI.createNotification(applicationContext(), extras)\n" +
                        "                } else {\n" +
                        "                    // not from CleverTap handle yourself or pass to another provider\n" +
                        "                }\n" +
                        "            }\n" +
                        "        } catch (t: Throwable) {\n" +
                        "           Log.d(\"MYFCMLIST\", \"Error parsing FCM message\", t);\n" +
                        "        }");
                // If a string is on the system clipboard, this method returns it; otherwise it returns null.

                // This method writes a string to the clipboard.




            }
            panelForFCM.b1.addActionListener()
            {

                fun setStringToClipboard( stringContent : String)
                {
                    val stringSelection = StringSelection(stringContent);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                }
                setStringToClipboard("@Override\n" +
                        "\tfun onNewToken(@NonNull String s) {\n" +
                        "\t\tsuper.onNewToken(s)\n" +
                        "\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext())\n" +
                        "\t\tclevertapDefaultInstance.pushFcmRegistrationId(s,true)\n" +

                        "\t}\n");
                // If a string is on the system clipboard, this method returns it; otherwise it returns null.

                // This method writes a string to the clipboard.




            }
        }

        return true
    }




    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForFCM
    }

    override fun close() = close(DialogWrapper.OK_EXIT_CODE)

    override fun showPackage(packageName: String) {
        this.packageName = packageName
    }

    override fun showModules(modules: List<String>) {}

    override fun selectModule(module: String) {
        moduleName = module
    }
}