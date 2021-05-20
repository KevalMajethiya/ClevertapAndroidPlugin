package ui

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.util.NotNullLazyValue
import data.file.*
import data.repository.ModuleRepository
import managers.GradleManager_for_fcm
import managers.ManifestManager_forFCM
import managers.PushNotificationManager
import managers.check_language
import util.Constants
import util.Methods
import java.io.FileNotFoundException

class Geofence_Presenter(
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPath: CurrentPath?
) {

    companion object {
        private val NOTIFICATION_GROUP = object :
            NotNullLazyValue<NotificationGroup>() {
            override fun compute(): NotificationGroup {
                return NotificationGroup(
                    Constants.DISPLAY_ID,
                    NotificationDisplayType.BALLOON,
                    true
                )
            }
        }
    }

    private var gradleManagerForfcm: GradleManager_for_fcm? = null
    private var manifestManagerForFCM: ManifestManager_forFCM? = null
    private var pushnotificationmanager: PushNotificationManager? = null

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
            var check= check_language(project)
            var lang= check.find_language()

            Geofence_Approval_Dialog(event,log_value,location_accuracy_value,location_fetch_mode_value,Geofence_Monitoring_count,interval,fastest_interval,displacement,geofence_notification_responsiveness,enable_bglocation_y,enable_bglocation_n,lang).show()
            // FCMApprovalDialog(event,serviceNameText,contentTitleText,fcm_sender_id,dependencyVersionText,IsRadiobuttonrb1Selected,IsRadiobuttonrb2Selected,lang).show()

        }
        view.close()
    }
}