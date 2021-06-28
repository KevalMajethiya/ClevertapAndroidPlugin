package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.UserEvents_cursor

class UserEvents_cursor: AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        UserEvents_cursor(event)

    }

}
