
package ui

import com.intellij.openapi.project.Project


import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import javax.swing.Action
import javax.swing.text.Document
import org.jdom.filter2.Filters.document

import com.intellij.openapi.command.WriteCommandAction




class PushProfile_cursor (var event: AnActionEvent)
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
            document.insertString(ans,"        HashMap<String, Object> ProfilePush = new HashMap<String, Object>();//added by CleverTap Assistant\n" +
                    "\t\tProfilePush.put(\"USer Property_name \", \"value\");//added by CleverTap Assistant\n" +
                    "\t\tclevertapDefaultInstance.pushProfile(ProfilePush);//added by CleverTap Assistant") }

    }
}