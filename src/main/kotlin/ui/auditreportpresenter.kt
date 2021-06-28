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
import managers.*
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
                //}





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