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
import managers.Huawei_Gradle_Manager
import managers.Huawei_Push
import managers.ManifestManager_Huawei_Push
import util.Constants
import java.io.FileNotFoundException

class Huawei_Approval_Presenter(
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

    private var Huawei_Push:Huawei_Push?=null
    private var Huawei_Gradle_Manager:Huawei_Gradle_Manager?=null
    private var ManifestManager_Huawei_Push:ManifestManager_Huawei_Push?=null

    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPathfcm?.let { view.selectModule(currentPathfcm.module) }
    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,
        moduleName: String
        //fcm1: fcm1
    ) {
        event.project?.let { project ->

            Huawei_Push = Huawei_Push(project)
            Huawei_Gradle_Manager = Huawei_Gradle_Manager(project)
            ManifestManager_Huawei_Push= ManifestManager_Huawei_Push(project)

            try {
                Huawei_Gradle_Manager?.let{
                    if(it.initBuildGradle())
                    {
                        it.addDependency(event)

                    }


                }

//                Huawei_Push?.let{
//                    it.initapplicationclass()
//                }
//                ManifestManager_Huawei_Push?.let{
//                    if (it.initAndroidManifest()) {
//                        it.addMetaDataContent()
//                    }
//                }









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