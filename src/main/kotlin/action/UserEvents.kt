package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.UserEvents


class UserEvents : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        UserEvents(event)

    }
}
