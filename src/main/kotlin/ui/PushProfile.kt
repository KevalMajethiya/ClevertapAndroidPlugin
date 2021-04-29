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

class PushProfile(var event: AnActionEvent) {
    private var ProfilePush:ProfilePush?=null


    init {
        event.project?.let { project ->

            ProfilePush = ProfilePush(project)

            try {

                ProfilePush?.let{

                    it.initapplicationclass()

                }





            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}