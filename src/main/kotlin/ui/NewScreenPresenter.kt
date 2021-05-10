package ui

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.util.NotNullLazyValue
import data.file.CurrentPathfcm
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import managers.GradleManager_for_fcm
import managers.ManifestManager_forFCM
import managers.PushNotificationManager
import managers.check_language
import util.Constants
import util.Methods
import java.io.FileNotFoundException

class NewScreenPresenter(
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPathfcm: CurrentPathfcm?
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
        currentPathfcm?.let { view.selectModule(currentPathfcm.module) }
    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,
        serviceNameText: String,
        pendingIntentText: String,
        contentTitleText: String,
        contentTextText: String,
        fcm_sender_id :String,
        dependencyVersionText: String,
        IsRadiobuttonrb1Selected: Boolean,
        IsRadiobuttonrb2Selected: Boolean,
        isNeedReadMeForInstructions: Boolean,
        moduleName: String
        //fcm1: fcm1
    ) {
        event.project?.let { project ->
            var check= check_language(project)
            var lang= check.find_language()

            FCMApprovalDialog(event,serviceNameText,contentTitleText,fcm_sender_id,dependencyVersionText,IsRadiobuttonrb1Selected,IsRadiobuttonrb2Selected,lang).show()

//            gradleManagerForfcm = GradleManager_for_fcm(project)
//            manifestManagerForFCM = ManifestManager_forFCM(project)
//            pushnotificationmanager= PushNotificationManager(project)
            try {




//                gradleManagerForfcm?.let {
//                    if (it.initBuildGradle()) {
//                        it.addDependency("${Constants.GRADLE_FOR_FCM}$dependencyVersionText", event)
//
//                    }
//
//                }
//
//                manifestManagerForFCM?.let {
//                    if (it.initAndroidManifest()) {
//
//                        it.addMetaDataContent(
//                            Methods.getAndroidManifestContent(
//                                packageName,
//                                serviceNameText,fcm_sender_id, IsRadiobuttonrb1Selected, IsRadiobuttonrb2Selected
//
//
//                            ),serviceNameText,fcm_sender_id,IsRadiobuttonrb1Selected
//
//
//
//                        )
//
//
//                    }
//                }
//
//                pushnotificationmanager?.let {
//                    if (it.initlaunchingactivity()) {
//                        it.addnotificationchannel(Methods.createnotificationchannel(contentTitleText))
//
//                    }
//
//                }
                //if(IsRadiobuttonrb1Selected==true) {
//                    writeActionDispatcher.dispatch {
//                        fileCreator.createScreenFiles(
//                            packageName,
//                            serviceNameText,
//                            pendingIntentText,
//                            contentTitleText,
//                            contentTextText,
//                            isNeedReadMeForInstructions,
//                            moduleName,
//                            Methods.checkPrimaryColorInColorsFile(project)
//                        )
//                    }
               // }
//                ApplicationManager.getApplication()
//                    .invokeLater({
//                        Notifications.Bus.notify(
//                            NOTIFICATION_GROUP.value
//                                .createNotification(
//                                    Constants.NOTIFICATION_TITLE,
//                                    Constants.NOTIFICATION_CONTENT,
//                                    NotificationType.INFORMATION,
//                                    null
//                                )
//                        )
//                    }
//                        , ModalityState.NON_MODAL)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }
        view.close()
    }
}