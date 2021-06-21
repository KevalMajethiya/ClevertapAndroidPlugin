package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.*

class Push_Templates : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        Push_Templates_Dialog(event).show()

    }
}
