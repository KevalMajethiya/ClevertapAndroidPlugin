package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.PushDemo

class Demo : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        //demo1(event).show()
        //d()
        //fcm1(event).show()
       // JoptionPaneCopy()
        //codesnippet_fcm()
        PushDemo(event)
    }

}
