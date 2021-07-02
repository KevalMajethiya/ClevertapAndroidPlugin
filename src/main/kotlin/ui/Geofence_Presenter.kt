package ui


import com.intellij.openapi.actionSystem.AnActionEvent
import data.file.CurrentPath
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import managers.check_language


class Geofence_Presenter(
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPath: CurrentPath?
) {

    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPath?.let { view.selectModule(currentPath.module) }
    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,
        log_value: String,
        location_accuracy_value: String,
        location_fetch_mode_value: String,
        Geofence_Monitoring_count: String,
        interval :String,
        fastest_interval: String,
        displacement:String,
        geofence_notification_responsiveness:String,
        enable_bglocation_y: Boolean,
        enable_bglocation_n: Boolean,
        moduleName: String
        //fcm1: fcm1
    ) {
        event.project?.let { project ->
            val check= check_language(project)
            val lang= check.find_language()

            Geofence_Approval_Dialog(event,log_value,location_accuracy_value,location_fetch_mode_value,Geofence_Monitoring_count,interval,fastest_interval,displacement,geofence_notification_responsiveness,enable_bglocation_y,enable_bglocation_n,lang).show()

        }
        view.close()
    }
}