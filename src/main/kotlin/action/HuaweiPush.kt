package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.Huawei_Dialog

class HuaweiPush: AnAction() {

    override fun actionPerformed(event: AnActionEvent) {

        Huawei_Dialog(event).show()
    }
}