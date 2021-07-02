package ui

import com.intellij.notification.*
import com.intellij.openapi.actionSystem.AnActionEvent
import data.file.*
import data.repository.ModuleRepository
import managers.*
import util.Constants
import util.Methods
import java.io.FileNotFoundException

class FCMApprovaPresenter(
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPathfcm: CurrentPath?
) {

    private var gradleManagerForfcm: GradleManager_for_fcm? = null
    private var manifestManagerForFCM: ManifestManager_forFCM? = null
    private var pushnotificationmanager: PushNotificationManager? = null
    private var pushnotificationmanager2: PushNotificationManager2? = null
    private  var createFCMfile: createFCMfile?=null

    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPathfcm?.let { view.selectModule(currentPathfcm.module) }
    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,
        serviceNameText: String,
        //pendingIntentText: String,
        contentTitleText: String,
        //contentTextText: String,
        fcm_sender_id :String,
        dependencyVersionText: String,
        IsRadiobuttonrb1Selected: Boolean,
        IsRadiobuttonrb2Selected: Boolean,
        isNeedReadMeForInstructions: Boolean,
        moduleName: String
        //fcm1: fcm1
    ) {
        event.project?.let { project ->

            gradleManagerForfcm = GradleManager_for_fcm(project)
            manifestManagerForFCM = ManifestManager_forFCM(project)
            pushnotificationmanager= PushNotificationManager(project)
            pushnotificationmanager2= PushNotificationManager2(project)
            createFCMfile= createFCMfile(project)
            try {
                gradleManagerForfcm?.let {
                    if(IsRadiobuttonrb2Selected) {
                        if (it.initBuildGradle()) {
                            it.addDependency("${Constants.GRADLE_FOR_FCM}$dependencyVersionText", event)

                        }
                    }

                }

                manifestManagerForFCM?.let {
                    if (it.initAndroidManifest()) {

                        it.addMetaDataContent(
                            Methods.getAndroidManifestContent(
                                packageName,
                                serviceNameText,fcm_sender_id, IsRadiobuttonrb1Selected, IsRadiobuttonrb2Selected


                            ),serviceNameText,fcm_sender_id,IsRadiobuttonrb1Selected,IsRadiobuttonrb2Selected



                        )


                    }
                }

//                pushnotificationmanager?.let {
//                    if (it.initlaunchingactivity(contentTitleText)) {
//                        it.addnotificationchannel(contentTitleText)
//
//                    }
//
//                }
                pushnotificationmanager?.initlaunchingactivity(contentTitleText)

                if(IsRadiobuttonrb1Selected){
                    pushnotificationmanager2?.initlaunchingactivity(contentTitleText)
                }
                if(IsRadiobuttonrb2Selected) {
                    createFCMfile?.initapplicationclass( serviceNameText,contentTitleText)
                }

                NotificationGroupManager.getInstance().getNotificationGroup("Display Notification")
                    .createNotification(Constants.NOTIFICATION_TITLE,"Push Notification has been successfully added.", NotificationType.INFORMATION)
                    .notify(project)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }
        view.close()
    }
}