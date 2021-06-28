package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.PushProfile_cursor

class PushProfile_cursor: AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        PushProfile_cursor(event)

    }

}
