package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.UserEventsWithProperties_cursor

class UserEventsWithProperties_cursor: AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        UserEventsWithProperties_cursor(event)

    }

}
