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
import util.Methods
import java.io.FileNotFoundException

class Push_Templates_Presenter(
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

    private var Push_Templates_Test_Manager:Push_Templates_Test_Manager?=null
    private var Rewritng_PT_service_file:Rewriting_PT_service_file?=null

    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPathfcm?.let { view.selectModule(currentPathfcm.module) }
    }

    fun onOkClick(
        event: AnActionEvent,
        title: String,
        message: String,
        msg_summary:String,
        subtitle:String,
        bg:String,
        big_img:String,
        ico:String,
        dl1:String,
        titlte_clr:String,
        msg_clr:String,
        small_icon_clr:String,
        template_type:String
        //fcm1: fcm1
    ) {
        event.project?.let { project ->

//            Huawei_Push = Huawei_Push(project)
//            Huawei_Gradle_Manager = Huawei_Gradle_Manager(project)
//            ManifestManager_Huawei_Push= ManifestManager_Huawei_Push(project)
              Push_Templates_Test_Manager= Push_Templates_Test_Manager(project)
              Rewritng_PT_service_file = Rewriting_PT_service_file(project)
//
            try {

                Push_Templates_Test_Manager?.let{
                    //it.AndroidManifest()
                    // if(it.initapplicationclass()){
                    //it.initapplicationclass()
                    //it.checkinsertion()
                    if(it.initapplicationclass()){
                        it.start_service()
                    }
                }
                Rewritng_PT_service_file?.let{
                    //it.initapplicationclass(basic_temp_title,basic_temp_message,basic_temp_msg_summary,ico,)
                    if(it.initapplicationclass())
                    {
                        it.rewriting_servicefile(title,message,msg_summary,subtitle,bg,big_img,ico,dl1,
                            titlte_clr,msg_clr,small_icon_clr,template_type)
                    }
                }
//                Huawei_Gradle_Manager?.let{
//                    if(it.initBuildGradle())
//                    {
//                        it.addDependency(event)
//
//                    }
//
//
//                }

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