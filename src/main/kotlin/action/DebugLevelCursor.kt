package action;

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.DebugLevelCursor

class DebugLevelCursor : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        DebugLevelCursor(event)

    }
}
