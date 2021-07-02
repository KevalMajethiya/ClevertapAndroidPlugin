package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.*

class Auditreport : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        test(event)

    }
}
