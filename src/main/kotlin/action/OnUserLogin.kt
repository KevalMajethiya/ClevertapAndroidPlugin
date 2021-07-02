package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.*

class OnUserLogin : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        onUserLogin(event)

    }
}
