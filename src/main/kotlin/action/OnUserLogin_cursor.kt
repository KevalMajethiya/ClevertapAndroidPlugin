package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.OnuserLogin_Cursor

class OnUserLogin_cursor: AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        OnuserLogin_Cursor(event)

    }

}
