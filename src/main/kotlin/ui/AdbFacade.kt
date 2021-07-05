package ui


import com.google.common.util.concurrent.ThreadFactoryBuilder
import com.intellij.openapi.project.Project
import java.util.concurrent.Executors

object AdbFacade {
    private val EXECUTOR = Executors.newCachedThreadPool(ThreadFactoryBuilder().setNameFormat("AdbIdea-%d").build())


    fun startDefaultActivity(project: Project,template_type:String,title:String,message:String,msg_summary:String,subtitle:String,bg:String,
                             big_img:String,ico:String,dl1:String,titlte_clr:String,msg_clr:String,small_icon_clr:String,
                             dl2:String,dln:String,img1:String,img2:String,img3:String,imgn:String,default_dl:String,
                             dl3:String, dl4:String, dl5:String, bt1:String, bt2:String, bt3:String,
                             st1:String, st2:String, st3:String, price1:String, price2:String, price3:String,
                             img4:String, img5:String,
                             title_alt:String, msg_alt:String, big_img_alt:String,
                             timer_threshold:String, timer_end:String, video_url:String,
                             small_view:String, event_name:String, event_property1:String,
                             event_property2:String, event_property3:String,
                             input_label:String, input_auto_open:String, input_feedback:String,
                             product_display_action:String, product_display_linear:String,
                             product_display_action_clr:String) = executeOnDevice(project,
        StartDefaultActivityCommand(false,template_type,title,message,msg_summary,subtitle,bg,big_img,ico,dl1,titlte_clr,msg_clr,small_icon_clr,dl2,
            dln,img1,img2,img3,imgn,default_dl,dl3,dl4,dl5,bt1,bt2,bt3,st1,st2,st3,price1,price2,price3,img4,
        img5,title_alt,msg_alt,big_img_alt,timer_threshold,timer_end,video_url,small_view,event_name,
        event_property1,event_property2,event_property3,input_label,input_auto_open,input_feedback,product_display_action,product_display_linear,product_display_action_clr))

    private fun executeOnDevice(project: Project, runnable: Command) {
//        if (AdbUtil.isGradleSyncInProgress(project)) {
//            NotificationHelper.error("Gradle sync is in progress")
//            return
//        }

        val result = project.getComponent(ObjectGraph::class.java)
            .deviceResultFetcher
            .fetch()

        if (result != null) {
            for (device in result.devices) {
                EXECUTOR.submit { runnable.run(project, device, result.facet, result.packageName) }
            }
        }
//        else {
//            NotificationHelper.error("No Device found")
//        }
    }
}
