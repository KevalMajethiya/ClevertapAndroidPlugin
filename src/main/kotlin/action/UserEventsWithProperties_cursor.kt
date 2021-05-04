package action

import com.intellij.codeInspection.ex.SeverityEditorDialog.show
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.JoptionPaneCopy
import ui.demo1
import ui.*
import ui.PushProfile
import ui.UserEvents
import ui.UserEventsWithProperties_cursor

class UserEventsWithProperties_cursor: AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        UserEventsWithProperties_cursor(event)

    }

}
