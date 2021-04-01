package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.demo1

class Demo : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        demo1(event).show()
    }

}