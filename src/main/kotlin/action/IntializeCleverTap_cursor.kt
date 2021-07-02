package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.InitializeCleverTap_cursor

class IntializeCleverTap_cursor: AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        InitializeCleverTap_cursor(event)

    }

}
