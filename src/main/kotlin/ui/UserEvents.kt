package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import managers.UserEventsManager
import java.io.FileNotFoundException

class UserEvents(var event: AnActionEvent) {
    private var UserEventsManager:UserEventsManager?=null


    init {
        event.project?.let { project ->

            UserEventsManager = UserEventsManager(project)

            try {

                UserEventsManager?.initapplicationclass()





            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}