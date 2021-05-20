package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.*
import ui.HuaweiPush

class HuaweiPush: AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        //HuaweiPush(event)
        Huawei_Dialog(event).show()
    }
}