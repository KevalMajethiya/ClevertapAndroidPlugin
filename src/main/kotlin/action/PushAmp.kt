package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.PushAmpDialog

class PushAmp: AnAction()  {

    override fun actionPerformed(event: AnActionEvent) {
        PushAmpDialog(event).show()
    }
}