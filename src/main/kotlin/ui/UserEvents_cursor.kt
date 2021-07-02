
package ui


import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction


class UserEvents_cursor (var event: AnActionEvent)
{

    init {

        val project = event.getData(PlatformDataKeys.PROJECT)
        val editor  = event.getData(PlatformDataKeys.EDITOR)
        val selectionmodel = editor?.selectionModel
        val document = editor?.document
        val a = document.toString()
        val b = a.split(".")
        val c = b[1]
        val d = c
        val e = d.split("]")
        val file_extension = e[0]
        val linenumber = document!!.getLineNumber(selectionmodel!!.selectionStart)
        print(linenumber)
        if(file_extension=="java") {
            WriteCommandAction.runWriteCommandAction(
                project
            ) {
                val ans = document.getLineStartOffset(linenumber)
                document.insertString(
                    ans,
                    "\t\tclevertapDefaultInstance.pushEvent(\"event_name\");//added by CleverTap Assistant\n"
                )
            }
        }
        if(file_extension=="kt")
        {
            WriteCommandAction.runWriteCommandAction(
                project
            ) {  val ans =document.getLineStartOffset(linenumber)
                document.insertString(ans,"\t\tclevertapDefaultInstance?.pushEvent(\"event_name\")//added by CleverTap Assistant\n")
            }
        }

    }

}