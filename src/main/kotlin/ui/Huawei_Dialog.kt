package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.DialogWrapper
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import util.Constants
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.JComponent
import javax.swing.JFileChooser


class Huawei_Dialog(var event: AnActionEvent) : DialogWrapper(true), NewScreenView {

    private val panelForHuawei = HuaweInputPanel()
    //private val panelFord=d()

    private val presenter: HuaweiPresenter
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
        presenter = HuaweiPresenter(
            this,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath
        )
        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME





        panelForHuawei.buttontoadd_Agconnect_ervicefile.addActionListener()
        {
            // d().f.isVisible=false
            val fileChooser = JFileChooser()
            val option = fileChooser.showSaveDialog(null)
            if (option == JFileChooser.APPROVE_OPTION) {
                val file = fileChooser.selectedFile
                val fname: String = file.name
                val fn = "agconnect-services.json"
                // String fnn="google-services.json";
                // String fnn="google-services.json";

                //label.setText("File Saved as: " + file.getName());
                val project = event.project

                val sourcePath = project?.basePath
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
                    panelForHuawei.label_file_status.text = "Agconnect Service file added"
                } else {
                    panelForHuawei.label_file_status.text = "You have selected wrong file"
                }


            } else {
                panelForHuawei.label_file_status.text = "Save command canceled"
            }
        }





        init()

    }


    override fun doOKAction() =
        presenter.onOkClick(
            event
//            this.packageName,
//            panelForFCM.serviceNameTextField.text,
//            panelForFCM.pendingIntentTextField.text,
//            panelForFCM.contentTitleTextField.text,
//            panelForFCM.contentTextTextField.text,
//            panelForFCM.fcm_sender_id_TextField.text,
//            //panelForFCM.Exclude_filesTextField.text,
//            panelForFCM.dependencyVersionTextField.text,
//            panelForFCM.rb1_fcm.isSelected,
//            panelForFCM.rb2_fcm.isSelected,
//            panelForFCM.isNeedReadMeForInstructions.isSelected,

            //Methods.createnotificationchannel(panelForFCM.contentTitleTextField.text),
//            moduleName
            // fcm1(event)


        )
    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForHuawei
        //return panelForFCM
    }

    override fun close() = close(OK_EXIT_CODE)

    override fun showPackage(packageName: String) {
        this.packageName = packageName
    }

    override fun showModules(modules: List<String>) {}

    override fun selectModule(module: String) {
        moduleName = module
    }
}