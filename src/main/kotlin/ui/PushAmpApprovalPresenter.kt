package ui

//import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import data.file.CurrentPath
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import managers.Manager_PushAmp
import managers.check_language
import util.Constants
import java.io.FileNotFoundException

class PushAmpApprovalPresenter (
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPathfcm: CurrentPath?
) {

    private var managerForPushAmp : Manager_PushAmp? = null


    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPathfcm?.let { view.selectModule(currentPathfcm.module) }
    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,
        IsRadiobuttonrb1Selected: Boolean,
        IsRadiobuttonrb2Selected: Boolean,
        receivcerClass: String,
        appclassname:String,
        moduleName: String
    ){
        event.project?.let { project ->


            val check= check_language(project)
            val lang= check.find_language()
            check.find_receiver_class()

            managerForPushAmp = Manager_PushAmp(project)


            try {
                managerForPushAmp?.let {
                    if (IsRadiobuttonrb2Selected){
                        if (it.initAndroidManifest()){
                            it.addMetaDataContent(IsRadiobuttonrb2Selected)
                        }
                    }
                    if (IsRadiobuttonrb1Selected){
                        if (it.initAppClass(appclassname) && it.initReceiverClass(receivcerClass)){
                            it.addApplicationData(IsRadiobuttonrb1Selected,appclassname,lang)
                            it.addReceiverData()
                        }
                    }
                }

//                NotificationGroupManager.getInstance().getNotificationGroup("Display Notification")
//                    .createNotification(Constants.NOTIFICATION_TITLE,"Push Amplification has been successfully added.", NotificationType.INFORMATION)
//                    .notify(project)

            }
            catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }
        view.close()
    }

}