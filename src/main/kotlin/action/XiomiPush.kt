package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.XiomiDialog

class XiomiPush: AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        XiomiDialog(event).show()
    }
}