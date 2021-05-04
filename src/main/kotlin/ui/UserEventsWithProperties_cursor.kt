
package ui

import com.intellij.openapi.project.Project


import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import javax.swing.Action
import javax.swing.text.Document
import org.jdom.filter2.Filters.document

import com.intellij.openapi.command.WriteCommandAction




class UserEventsWithProperties_cursor (var event: AnActionEvent)
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
            document.insertString(ans,"        HashMap<String, Object> eventProperties = new HashMap<String, Object>();//added by CleverTap Assistant\n" +
                    "\t\teventProperties.put(\"Event Property_name \", \"value\");//added by CleverTap Assistant\n" +
                    "\t\tclevertapDefaultInstance.pushEvent(\"event_name\", eventProperties);//added by CleverTap Assistant") }

    }
}