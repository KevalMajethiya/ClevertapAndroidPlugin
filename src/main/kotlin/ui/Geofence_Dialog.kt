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

class Geofence_Dialog(var event: AnActionEvent) : DialogWrapper(true),  NewScreenView {

    private val panelForGeofence = Geofence_InputPanel()

    private val presenter: Geofence_Presenter
    private var packageName = ""
    private var moduleName = ""

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
        presenter = Geofence_Presenter(
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

    override fun doOKAction() =

        presenter.onOkClick(
            event,

            this.packageName,
            panelForGeofence.log_values.selectedItem.toString(),
            panelForGeofence.location_accuracy_values.selectedItem.toString(),
            panelForGeofence.location_fetch_mode_values.selectedItem.toString(),
            panelForGeofence.Geofence_Monitoring_count_Textfield.text,
            panelForGeofence.interval_TextField.text,
            panelForGeofence.fastest_interval_TextField.text,
            panelForGeofence.displacement_TextField.text,
            panelForGeofence.geofence_notification_responsiveness_TextField.text,
            panelForGeofence.enable_bglocation_y.isSelected,
            panelForGeofence.enable_bglocation_n.isSelected,
            moduleName
        )

    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForGeofence
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