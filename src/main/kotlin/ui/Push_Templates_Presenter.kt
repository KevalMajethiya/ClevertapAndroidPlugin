package ui

import com.intellij.notification.*
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.util.NotNullLazyValue
import data.file.CurrentPath
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import managers.Push_Templates_Test_Manager
import managers.Rewriting_PT_service_file
import util.Constants
import java.io.FileNotFoundException

class Push_Templates_Presenter(
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPathfcm: CurrentPath?
) {



    private var Push_Templates_Test_Manager:Push_Templates_Test_Manager?=null
    private var Rewritng_PT_service_file:Rewriting_PT_service_file?=null

    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPathfcm?.let { view.selectModule(currentPathfcm.module) }
    }

    fun onOkClick(
        event: AnActionEvent,
        template_type:String,
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
        dl2:String,
        dln:String,
        img1:String,
        img2:String,
        img3:String,
        imgn:String,
        default_dl:String,
        dl3:String,
        dl4:String,
        dl5:String,
        bt1:String,
        bt2:String,
        bt3:String,
        st1:String,
        st2:String,
        st3:String,
        price1:String,
        price2:String,
        price3:String,
        img4:String,
        img5:String,
        title_alt:String,
        msg_alt:String,
        big_img_alt:String,
        timer_threshold:String,
        timer_end:String,
        video_url:String,
        small_view:String,
        event_name:String,
        event_property1:String,
        event_property2:String,
        event_property3:String,
        input_label:String,
        input_auto_open:String,
        input_feedback:String,
        product_display_action:String,
        product_display_linear:String,
        product_display_action_clr:String




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

                AdbFacade.startDefaultActivity(project,template_type,title,message,msg_summary,subtitle,bg,
                    big_img,ico,dl1,titlte_clr,msg_clr,small_icon_clr,dl2,dln,img1,img2,img3,imgn,default_dl,
                    dl3, dl4, dl5, bt1, bt2, bt3, st1, st2, st3, price1, price2, price3, img4, img5,title_alt,msg_alt,big_img_alt,timer_threshold,timer_end,video_url,small_view,event_name,
                    event_property1,event_property2,event_property3,input_label,input_auto_open,input_feedback,
                    product_display_action,product_display_linear,product_display_action_clr)

                Push_Templates_Test_Manager?.let{
                    //it.AndroidManifest()
                    // if(it.initapplicationclass()){
                    //it.initapplicationclass()
                    //it.checkinsertion()
                    if(it.initapplicationclass()){
                        it.start_service()
                    }
                }
//                Rewritng_PT_service_file?.let{
//                    //it.initapplicationclass(basic_temp_title,basic_temp_message,basic_temp_msg_summary,ico,)
//                    if(it.initapplicationclass())
//                    {
//                        it.rewriting_servicefile(title,message,msg_summary,subtitle,bg,big_img,ico,dl1,
//                            titlte_clr,msg_clr,small_icon_clr,template_type)
//                    }
//                }
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









                NotificationGroupManager.getInstance().getNotificationGroup("Display Notification")
                    .createNotification(Constants.NOTIFICATION_TITLE," Push Template successfully rendered.", NotificationType.INFORMATION)
                    .notify(project)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }
        view.close()
    }
}