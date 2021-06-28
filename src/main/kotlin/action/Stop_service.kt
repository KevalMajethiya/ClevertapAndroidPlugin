package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.Stop_service


class Stop_service : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        Stop_service(event)

    }
}
