package ui

import com.intellij.notification.*
import com.intellij.openapi.actionSystem.AnActionEvent
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

//                NotificationGroupManager.getInstance().getNotificationGroup("Display Notification")
//                    .createNotification(Constants.NOTIFICATION_TITLE,"Huawei Push has successfully been added.", NotificationType.INFORMATION)
//                    .notify(project)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }
        view.close()
    }
}