package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import managers.onUserLoginManager
import java.io.FileNotFoundException

class onUserLogin(var event: AnActionEvent) {
    private var onUserLoginManager:onUserLoginManager?=null


    init {
        event.project?.let { project ->

            onUserLoginManager = onUserLoginManager(project)

            try {

                onUserLoginManager?.initapplicationclass()





            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}