package ui

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


class google_service_file(var event: AnActionEvent) : DialogWrapper(true), NewScreenView{
    private val panelForFCM = fileselection()

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

        panelForFCM.button.addActionListener()
        {
            val fileChooser = JFileChooser()
            val option = fileChooser.showSaveDialog(null)
            if (option == JFileChooser.APPROVE_OPTION)
            {
                val file = fileChooser.selectedFile
                val fname: String
                fname = file.name
                val fn = "google-services.json"
                // String fnn="google-services.json";
                // String fnn="google-services.json";

                //label.setText("File Saved as: " + file.getName());
                val project = event.getProject()
                val sourcePath = project?.getBasePath()
               // panelForFCM.label.setText(sourcePath)
                //label.text = "File Saved as: " + file.path
                val toFile = sourcePath +"/app/"+ file.name
                val source = Paths.get(file.path)
                val target = Paths.get(toFile)
               // val p = System.getProperty("user.dir")
                //
                // val ans= event.project

                if (fname.equals(fn,ignoreCase = true)) {
                    try
                    {
                        Files.move(source, target)
                    } catch (e1: IOException)
                    {
                        // e.printStackTrace();
                    }
                    panelForFCM.label.setText("Google Service file added")
                } else {
                    panelForFCM.label.setText("You have selected wrong file")
                }



            } else
            {
                panelForFCM.label.text = "Save command canceled"
            }
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