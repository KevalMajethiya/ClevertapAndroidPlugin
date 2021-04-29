package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.*
import ui.PushProfile

class PushProfile : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        // auditreportfile1(event).show()

        PushProfile(event)

    }
}
