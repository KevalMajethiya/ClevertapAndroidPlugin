
package ui

import com.intellij.openapi.project.Project


import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import javax.swing.Action
import javax.swing.text.Document
import org.jdom.filter2.Filters.document

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants
import java.io.File


class InitializeCleverTap_cursor (var event: AnActionEvent)
{

    init {
        //intializeClevertap()
        var project = event.getData(PlatformDataKeys.PROJECT)
        var editor  = event.getData(PlatformDataKeys.EDITOR)
        var selectionmodel = editor?.selectionModel
        var document = editor?.document
        var a = document.toString()
        var b = a.split(".")
        var c = b[1]
        var d = c
        var e = d.split("]")
        var file_extension = e[0]
        var linenumber = document!!.getLineNumber(selectionmodel!!.getSelectionStart())
        if(file_extension=="java")
        {
            WriteCommandAction.runWriteCommandAction(
                project
            ) {  var ans =document.getLineStartOffset(linenumber)
                document.insertString(ans,"\t\tCleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());//added by CleverTap Assistant")
            }
        }
        if(file_extension=="kt")
        {
            WriteCommandAction.runWriteCommandAction(
                project
            ) {  var ans =document.getLineStartOffset(linenumber)
                document.insertString(ans,"\t\tvar clevertapDefaultInstance: CleverTapAPI? = null\n" +
                        "\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)//added by CleverTap Assistant\n")
            }

        }

    }


}