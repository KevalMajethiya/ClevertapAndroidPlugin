
package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys

import com.intellij.openapi.command.WriteCommandAction




class c (var event: AnActionEvent)
{
    init {
        val project = event.getData(PlatformDataKeys.PROJECT)
        val editor  = event.getData(PlatformDataKeys.EDITOR)
        val selectionmodel = editor?.selectionModel
        val document = editor?.document
        val linenumber = document!!.getLineNumber(selectionmodel!!.selectionStart)
        print(linenumber)
        WriteCommandAction.runWriteCommandAction(
            project
        ) {  val ans =document.getLineStartOffset(linenumber)
            document.insertString(ans,"CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());") }

    }
}