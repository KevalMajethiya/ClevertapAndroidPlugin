package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.DialogWrapper
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import managers.PushNotificationManager
import util.Constants
import util.Methods
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.JComponent
import javax.swing.JFileChooser
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;



class NewScreenDialog(var event: AnActionEvent) : DialogWrapper(true), NewScreenView {

    private val panelForFCM = FCMInputPanel()

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


        panelForFCM.buttontoaddgservicefile.addActionListener()
        {
            val fileChooser = JFileChooser()
            val option = fileChooser.showSaveDialog(null)
            if (option == JFileChooser.APPROVE_OPTION) {
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
                val toFile = sourcePath + "/app/" + file.name
                val source = Paths.get(file.path)
                val target = Paths.get(toFile)
                // val p = System.getProperty("user.dir")
                //
                // val ans= event.project

                if (fname.equals(fn, ignoreCase = true)) {
                    try {
                        Files.move(source, target)
                    } catch (e1: IOException) {
                        // e.printStackTrace();
                    }
                    panelForFCM.label_file_status.setText("Google Service file added")
                } else {
                    panelForFCM.label_file_status.setText("You have selected wrong file")
                }


            } else {
                panelForFCM.label_file_status.text = "Save command canceled"
            }
        }


        panelForFCM.button.addActionListener { JOptionPane.showOptionDialog(null, "Add the below code In the oncreate method of your launching activity\n CleverTapAPI.createNotificationChannel(getApplicationContext(),\"3131\",\"mychannel\",\"Description\",NotificationManager.IMPORTANCE_MAX,true);", "Info",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,null,null) }



        init()

    }


        override fun doOKAction() =
            presenter.onOkClick(
                    event,
                    this.packageName,
                    panelForFCM.serviceNameTextField.text,
                    panelForFCM.pendingIntentTextField.text,
                    panelForFCM.contentTitleTextField.text,
                    panelForFCM.contentTextTextField.text,
                    //panelForFCM.Exclude_filesTextField.text,
                     panelForFCM.dependencyVersionTextField.text,
                    panelForFCM.isNeedReadMeForInstructions.isSelected,
                    //Methods.createnotificationchannel(panelForFCM.contentTitleTextField.text),
                    moduleName

            )
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