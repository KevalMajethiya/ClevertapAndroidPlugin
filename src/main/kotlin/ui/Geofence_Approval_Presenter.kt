package ui

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.util.NotNullLazyValue
import data.file.CurrentPath
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import managers.Geofence_Manager
import managers.GradleManager_Geofence
import managers.ManifestManager_Geofence
import util.Constants
import java.io.FileNotFoundException

class Geofence_Approval_Presenter(
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPathfcm: CurrentPath?
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

    private var Geofence_Manager:Geofence_Manager?=null
    private var GradleManager_Geofence:GradleManager_Geofence?=null
    private var ManifestManager_Geofence:ManifestManager_Geofence?=null

    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPathfcm?.let { view.selectModule(currentPathfcm.module) }
    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,
        log_value: String,
        location_accuracy_value: String,
        location_fetch_mode_value: String,
        Geofence_Monitoring_count: String,
        interval: String,
        fastest_interval: String,
        displacement: String,
        geofence_notification_responsiveness: String,
        enable_bglocation_y:Boolean,
        enable_bglocation_n:Boolean,
        lang: String,
        moduleName: String
        //fcm1: fcm1

    ) {
        event.project?.let { project ->

            Geofence_Manager = Geofence_Manager(project)
            GradleManager_Geofence = GradleManager_Geofence(project)
            ManifestManager_Geofence= ManifestManager_Geofence(project)

            try {
                GradleManager_Geofence?.let{
                    if(it.initBuildGradle())
                    {
                        it.addDependency(event)
                    }
                }

                Geofence_Manager?.let{
                    it.initapplicationclass(log_value,location_accuracy_value,location_fetch_mode_value,Geofence_Monitoring_count,interval,fastest_interval,displacement,
                        geofence_notification_responsiveness,enable_bglocation_y,enable_bglocation_n,lang)
                }
                ManifestManager_Geofence?.let{
                    if (it.initAndroidManifest()) {
                        it.addData()
                    }
                }




                ApplicationManager.getApplication()
                    .invokeLater({
                        Notifications.Bus.notify(
                            NOTIFICATION_GROUP.value
                                .createNotification(
                                    Constants.NOTIFICATION_TITLE,
                                    Constants.NOTIFICATION_CONTENT,
                                    NotificationType.INFORMATION,
                                    null
                                )
                        )
                    }, ModalityState.NON_MODAL)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }
        view.close()
    }
}