package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.*

class PushTemplates_initalization : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        // auditreportfile1(event).show()
        //test(event)
        PushTemplates(event)

    }
}
