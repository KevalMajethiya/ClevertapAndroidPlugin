package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.DialogWrapper
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import util.Constants
import javax.swing.JComponent


class Geofence_Approval_Dialog(var event: AnActionEvent,
                               private var log_value: String, private var location_accuracy_value: String,
                               private var location_fetch_mode_value: String,
                               private var Geofence_Monitoring_count: String,
                               private var interval: String,
                               private var fastest_interval: String,
                               private var displacement: String,
                               private var geofence_notification_responsiveness: String,
                               private var enable_bglocation_y: Boolean,
                               private var enable_bglocation_n: Boolean,
                               private var lang: String
) : DialogWrapper(true),  NewScreenView {
    private val panelFor_Geofence_input_aprroval = Geofence_Input_Approval()
    private val presenter: Geofence_Approval_Presenter

    private var packageName = ""
    private var moduleName = ""
    private var bglocation_value= ""

    init {

        val currentPath = event.getData(LangDataKeys.VIRTUAL_FILE)?.let {
            val module = ModuleUtil.findModuleForFile(it, event.project!!)?.name ?: Constants.DEFAULT_MODULE_NAME
            CurrentPath(it.path, it.isDirectory, module)
        }
        val projectStructure = ProjectStructureImpl(event.project!!)
        val sourceRootRepository = SourceRootRepositoryImpl(projectStructure)
        val fileCreator = FileCreatorImpl(sourceRootRepository)
        val packageExtractor = PackageExtractorImpl(currentPath, sourceRootRepository)
        val writeActionDispatcher = WriteActionDispatcherImpl()
        val moduleRepository = ModuleRepositoryImpl(projectStructure)
        presenter = Geofence_Approval_Presenter(
            this,
            // Account_id,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath

        )

        bglocation_value = if(enable_bglocation_y) {
            "true"
        } else {
            "false"
        }

        if (lang == "java") {
            panelFor_Geofence_input_aprroval.launchingact_content.text = "<html>" + "CTGeofenceSettings ctGeofenceSettings = new CTGeofenceSettings.Builder()" + "<br>" +
                    "        .enableBackgroundLocationUpdates($bglocation_value)" + "<br>" +
                    "        .setLogLevel($log_value)" + "<br>" +
                    "        .setLocationAccuracy($location_accuracy_value)" + "<br>" +
                    "        .setLocationFetchMode($location_fetch_mode_value)" + "<br>" +
                    "        .setGeofenceMonitoringCount($Geofence_Monitoring_count)" + "<br>" +
                    "        .setInterval($interval)" + "<br>" +
                    "        .setFastestInterval($fastest_interval)" + "<br>" +
                    "        .setSmallestDisplacement($displacement) " + "<br>" +
                    "        .setGeofenceNotificationResponsiveness($geofence_notification_responsiveness) " + "<br>" +
                    "        .build();" + "<br>" +

                    "<br>" +
                    "</html>"

        }
        if (lang == "kotlin") {
            panelFor_Geofence_input_aprroval.launchingact_content.text = "<html>" + "val ctGeofenceSettings = new CTGeofenceSettings.Builder()" + "<br>" +
                    "        .enableBackgroundLocationUpdates($bglocation_value)//" + "<br>" +
                    "        .setLogLevel($log_value)" + "<br>" +
                    "        .setLocationAccuracy($location_accuracy_value)" + "<br>" +
                    "        .setLocationFetchMode($location_fetch_mode_value)" + "<br>" +
                    "        .setGeofenceMonitoringCount($Geofence_Monitoring_count)" + "<br>" +
                    "        .setInterval($interval)" + "<br>" +
                    "        .setFastestInterval($fastest_interval)" + "<br>" +
                    "        .setSmallestDisplacement($displacement) " + "<br>" +
                    "        .setGeofenceNotificationResponsiveness($geofence_notification_responsiveness) " + "<br>" +
                    "        .build()" + "<br>" +
                    "<br>" +
                    "</html>"
        }




        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME
        init()
    }

    override fun doOKAction() =
        presenter.onOkClick(
            event,
            this.packageName,
            log_value,
            location_accuracy_value,
            location_fetch_mode_value,
            Geofence_Monitoring_count,
            interval,
            fastest_interval,
            displacement,
            geofence_notification_responsiveness,
            enable_bglocation_y,
            enable_bglocation_n,
            lang,
            moduleName
        )



    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelFor_Geofence_input_aprroval
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