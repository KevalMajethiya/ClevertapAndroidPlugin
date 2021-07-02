package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.*


class Geofence: AnAction() {

    override fun actionPerformed(event: AnActionEvent) {

        Geofence_Dialog(event).show()
    }
}