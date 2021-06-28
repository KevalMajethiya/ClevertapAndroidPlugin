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

class XiomiApprovalDialog(var event: AnActionEvent,appID : String,appKey : String): DialogWrapper(true),  NewScreenView {

    private val panelForXiomi = XiomiInputPanel()
    private val panelForXiomi1 = XiomiApproval()
    private val presenter: XiomiApprovalPresenter

    private var packageName = ""
    private var moduleName = ""
    private var AppId = appID
    private var AppKey = appKey

    init {
        val currentPath = event.getData(DataKeys.VIRTUAL_FILE)?.let {
            val module = ModuleUtil.findModuleForFile(it, event.project!!)?.name ?: Constants.DEFAULT_MODULE_NAME
            CurrentPath(it.path, it.isDirectory, module)
        }
        val projectStructure = ProjectStructureImpl(event.project!!)
        val sourceRootRepository = SourceRootRepositoryImpl(projectStructure)
        val fileCreator = FileCreatorImpl(sourceRootRepository)
        val packageExtractor = PackageExtractorImpl(currentPath, sourceRootRepository)
        val writeActionDispatcher = WriteActionDispatcherImpl()
        val moduleRepository = ModuleRepositoryImpl(projectStructure)
        presenter = XiomiApprovalPresenter(
            this,
            // Account_id,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath

        )

        panelForXiomi1.manifest_content.text = "<html>"+ "&lt meta-data" + "<br>" +
                "  \t      android:name=\"CLEVERTAP_XIAOMI_APP_ID\"" + "<br>" +
                "  \t      android:value=\"@string/xiaomi_app_id \" / &gt" + "<br>" +
                "&lt meta-data" + "<br>" +
                "  \t      android:name=\"CLEVERTAP_XIAOMI_APP_KEY\"" + "<br>" +
                "  \t      android:value=\"@string/xiaomi_app_key \" / &gt" + "<br>" +
                "</html>"

        panelForXiomi1.strings_xml_content.text = ("<html>"+ "&lt string name = " + "\"xiaomi_app_id\" &gt " +AppId+"&lt/string&gt" +"<br>"
                + "&lt string name = " + "\"xiaomi_app_key\" &gt " +AppKey+"&lt/string&gt")

        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME
        init()

    }

    override fun doOKAction() =
        presenter.onOkClick(
            event,
            this.packageName,
            AppId,
            AppKey
        )


    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForXiomi1
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