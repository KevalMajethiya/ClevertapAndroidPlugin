package action

import com.intellij.codeInspection.ex.SeverityEditorDialog.show
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.JoptionPaneCopy
import ui.demo1
import ui.*
import ui.d

class Demo : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        //demo1(event).show()
        //d()
        fcm1(event).show()
       // JoptionPaneCopy()
        //codesnippet_fcm()
    }

}
