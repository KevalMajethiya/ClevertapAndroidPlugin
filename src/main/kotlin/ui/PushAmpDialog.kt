package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.DialogWrapper
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import util.Constants
import javax.swing.JComponent

class PushAmpDialog(var event: AnActionEvent) : DialogWrapper(true), NewScreenView {

    private val panelForPushAmp = PushAmpInputPanel()
    private val presenter: PushAmpPresenter
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

        presenter = PushAmpPresenter(
            this,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath
        )

        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME
        panelForPushAmp.rb1.addActionListener(){
            if (panelForPushAmp.rb1.isSelected()){

//                panelForPushAmp.receiverClass.isVisible = true
//                panelForPushAmp.receiverLabel.isVisible = true
            }
        }

        panelForPushAmp.rb2.addActionListener(){
            if (panelForPushAmp.rb2.isSelected()){

                panelForPushAmp.receiverClass.isVisible = false
                panelForPushAmp.receiverLabel.isVisible = false
            }
        }

        init()

    }

    override fun doOKAction() =
        presenter.onOkClick(
            event,
            this.packageName,
            panelForPushAmp.rb1.isSelected,
            panelForPushAmp.rb2.isSelected,
            panelForPushAmp.receiverClass.text,
            moduleName
        )

    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForPushAmp
        //return panelForFCM
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