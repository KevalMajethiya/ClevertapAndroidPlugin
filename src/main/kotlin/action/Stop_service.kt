package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.*
import ui.Stop_service
import ui.UserEvents


class Stop_service : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        // auditreportfile1(event).show()

        Stop_service(event)

    }
}
