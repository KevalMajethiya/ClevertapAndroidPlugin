package ui


import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.DialogWrapper
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
import javax.swing.JPanel
import javax.swing.JTextField
import java.io.IOException
import java.nio.file.*


class fcm1(var event: AnActionEvent) : DialogWrapper(true), NewScreenView{
    private val panelForFCM = fcm()

    private val presenter: NewScreenPresenter
    private var packageName = ""
    private var moduleName = ""

    init {
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

        panelForFCM.b.addActionListener()
        {

            fun setStringToClipboard( stringContent : String)
            {
                val stringSelection = StringSelection(stringContent);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            }
            setStringToClipboard("        try {\n" +
                    "            if (message.getData().size() > 0) {\n" +
                    "                Bundle extras = new Bundle();\n" +
                    "                for (Map.Entry<String, String> entry : message.getData().entrySet()) {\n" +
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
                    "\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());\n" +
                    "\t\tclevertapDefaultInstance.pushFcmRegistrationId(s,true);\n" +

                    "\t}\n");
            // If a string is on the system clipboard, this method returns it; otherwise it returns null.

            // This method writes a string to the clipboard.




        }
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