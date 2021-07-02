package ui

import com.intellij.notification.*
import com.intellij.openapi.actionSystem.AnActionEvent
import data.file.CurrentPathfcm
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import managers.AuditreportManager
import util.Constants
import java.io.FileNotFoundException

class auditreportpresenter(
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPath: CurrentPathfcm?
) {

    private var AuditreportManager:AuditreportManager?=null

    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPath?.let { view.selectModule(currentPath.module) }

    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,

        moduleName: String
        //launchingactivityname:String
    ) {
        event.project?.let { project ->

            writeActionDispatcher.dispatch {
                /*       fileCreator.createScreenFiles(
                           packageName,
                           serviceNameText,
                           pendingIntentText,
                           contentTitleText,
                           contentTextText,
                           isNeedReadMeForInstructions,
                           moduleName,
                           Methods.checkPrimaryColorInColorsFile(project)
                       )*/
            }

            AuditreportManager=AuditreportManager(project)


            try {

                AuditreportManager?.initapplicationclass()

                NotificationGroupManager.getInstance().getNotificationGroup("Display Notification")
                    .createNotification(Constants.NOTIFICATION_TITLE,"Audit Report generation function has been successfully added.", NotificationType.INFORMATION)
                    .notify(project)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        view.close()
    }
}