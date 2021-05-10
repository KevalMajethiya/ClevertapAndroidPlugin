package action

import com.intellij.codeInspection.ex.SeverityEditorDialog.show
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.JoptionPaneCopy
import ui.demo1
import ui.*

class Cursor: AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
    c(event)

    }

}
