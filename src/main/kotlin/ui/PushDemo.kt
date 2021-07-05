package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import managers.PushDemo
import java.io.FileNotFoundException

class PushDemo(var event: AnActionEvent) {
    private var PushDemo:PushDemo?=null


    init {
        event.project?.let { project ->

            PushDemo = PushDemo(project)

            try {

                PushDemo?.addMetaDataContent()





            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}