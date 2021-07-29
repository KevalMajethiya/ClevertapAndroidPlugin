package ui

//import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import data.file.CurrentPath
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import managers.XiomiGradleManager
import managers.XiomiManifestManager
import managers.XiomiStringmanager
import util.Constants
import java.io.FileNotFoundException

class XiomiApprovalPresenter(
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPathfcm: CurrentPath?
) {

    private var gradleManager: XiomiGradleManager? = null
    private var manifestManager: XiomiManifestManager? = null
    private var stringManager: XiomiStringmanager? = null

    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPathfcm?.let { view.selectModule(currentPathfcm.module) }
    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,
        AppID:String,
        AppKey:String
    ){
        event.project?.let { project ->
            gradleManager = XiomiGradleManager(project)
            manifestManager = XiomiManifestManager(project)
            stringManager = XiomiStringmanager(project)

            try {
                gradleManager?.let {
                    if (it.initBuildGradle()) {
                        it.addDependency(Constants.XiomiSDK, event)

                    }
                }
                manifestManager?.let {
                    if (it.initAndroidManifest()) {
                        it.addMetaDataContent(packageName,AppID,AppKey)
                    }
                }
                stringManager?.let {
                    if(it.initStringXml()){
                        it.addStringXmlContent(AppID,AppKey)
                    }
                }

//                NotificationGroupManager.getInstance().getNotificationGroup("Display Notification")
//                    .createNotification(Constants.NOTIFICATION_TITLE,"Xiaomi Push has been successfully added.", NotificationType.INFORMATION)
//                    .notify(project)

            }
            catch (e: FileNotFoundException){
                e.printStackTrace()
            }
        }
        view.close()
    }
}