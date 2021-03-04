package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.google_service_file

class google_service_file: AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
       google_service_file(event).show()
    }
}
