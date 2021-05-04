
package ui

import com.intellij.openapi.project.Project


import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import javax.swing.Action
import javax.swing.text.Document
import org.jdom.filter2.Filters.document

import com.intellij.openapi.command.WriteCommandAction




class UserEvents_cursor (var event: AnActionEvent)
{
    init {
        var project = event.getData(PlatformDataKeys.PROJECT)
        var editor  = event.getData(PlatformDataKeys.EDITOR)
        var selectionmodel = editor?.selectionModel
        var document = editor?.document
        var linenumber = document!!.getLineNumber(selectionmodel!!.getSelectionStart())
        print(linenumber)
        WriteCommandAction.runWriteCommandAction(
            project
        ) {  var ans =document.getLineStartOffset(linenumber)
            document.insertString(ans,"        clevertapDefaultInstance.pushEvent(\"event_name\");//added by CleverTap Assistant") }

    }
}