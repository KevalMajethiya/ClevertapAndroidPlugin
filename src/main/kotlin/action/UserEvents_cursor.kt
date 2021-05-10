package action

import com.intellij.codeInspection.ex.SeverityEditorDialog.show
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.JoptionPaneCopy
import ui.demo1
import ui.*
import ui.PushProfile
import ui.UserEvents

class UserEvents_cursor: AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
//        event.project?.let { project ->
//            UserEvents_cursor(event,project)
//        }
        UserEvents_cursor(event)

    }

}
