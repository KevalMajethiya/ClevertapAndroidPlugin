package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import managers.ProfilePush
import java.io.FileNotFoundException

class PushProfile(var event: AnActionEvent) {
    private var ProfilePush:ProfilePush?=null


    init {
        event.project?.let { project ->

            ProfilePush = ProfilePush(project)

            try {

                ProfilePush?.initapplicationclass()





            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}