package action

import com.intellij.codeInspection.ex.SeverityEditorDialog.show
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.JoptionPaneCopy
import ui.demo1
import ui.*

class IntializeCleverTap_cursor: AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
//        event.project?.let { project ->
//            InitializeCleverTap_cursor(event, project)
//        }
        InitializeCleverTap_cursor(event)

    }

}
