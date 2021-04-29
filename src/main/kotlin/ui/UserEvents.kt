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

class UserEvents(var event: AnActionEvent) {
    private var UserEventsManager:UserEventsManager?=null


    init {
        event.project?.let { project ->

            UserEventsManager = UserEventsManager(project)

            try {

                UserEventsManager?.let{

                    it.initapplicationclass()

                }





            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}