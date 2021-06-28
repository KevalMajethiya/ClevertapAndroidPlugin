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

class XiomiDialog(var event: AnActionEvent) : DialogWrapper(true), NewScreenView {

    private val panelForXiomi = XiomiInputPanel()

    private val presenter: NewXiomiPresenter
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
        presenter = NewXiomiPresenter(
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

    override fun doOKAction() = presenter.onOkClick(
        event,
        this.packageName,
        moduleName,
        panelForXiomi.appId.text,
        panelForXiomi.appKey.text
    )

    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForXiomi
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