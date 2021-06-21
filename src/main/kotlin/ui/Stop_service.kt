package ui

import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState

import util.Constants
import managers.*
import util.Methods
import java.io.FileNotFoundException

class Stop_service(var event: AnActionEvent) {
    private var Rewritng_PT_service_file:Rewriting_PT_service_file?=null
    private var Push_Templates_Test_Manager:Push_Templates_Test_Manager?=null

    init {
        event.project?.let { project ->


            Rewritng_PT_service_file = Rewriting_PT_service_file(project)
            Push_Templates_Test_Manager= Push_Templates_Test_Manager(project)

            try {
                Rewritng_PT_service_file?.let{
                    //it.initapplicationclass(basic_temp_title,basic_temp_message,basic_temp_msg_summary)
                    if(it.initapplicationclass())
                    {
                        it.removing_extrastuff()
                    }
                }
                Push_Templates_Test_Manager?.let{

                    if(it.initapplicationclass()){
                        it.stop_service()
                    }
                }






            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}