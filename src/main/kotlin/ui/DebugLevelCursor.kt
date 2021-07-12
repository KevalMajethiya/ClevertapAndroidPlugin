package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction

class DebugLevelCursor (var event: AnActionEvent) {

    init {

        val project = event.getData(PlatformDataKeys.PROJECT)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        val selectionModel = editor?.selectionModel
        val document = editor?.document
        val a = document.toString()
        val b = a.split(".")
        val c = b[1]
        val e = c.split("]")
        val fileExtension = e[0]
        val linenumber = document!!.getLineNumber(selectionModel!!.selectionStart)
        if (fileExtension == "java") {
            WriteCommandAction.runWriteCommandAction(
                project
            ) {
                val ans = document.getLineStartOffset(linenumber)
                document.insertString(
                    ans,
                    "\t\tclevertapDefaultInstance.setDebugLevel(3);\t\t//added by CleverTap Assistant\n"
                )
            }
        }
        if (fileExtension == "kt") {
            WriteCommandAction.runWriteCommandAction(
                project
            ) {
                val ans = document.getLineStartOffset(linenumber)
                document.insertString(
                    ans, "\t\tclevertapDefaultInstance.setDebugLevel(3);\t\t//added by CleverTap Assistant\n"
                )
            }

        }

    }
}