
package ui


import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction


class InitializeCleverTap_cursor (var event: AnActionEvent)
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
        if(file_extension=="java")
        {
            WriteCommandAction.runWriteCommandAction(
                project
            ) {  val ans =document.getLineStartOffset(linenumber)
                document.insertString(ans,"\t\tCleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());//added by CleverTap Assistant")
            }
        }
        if(file_extension=="kt")
        {
            WriteCommandAction.runWriteCommandAction(
                project
            ) {  val ans =document.getLineStartOffset(linenumber)
                document.insertString(ans,"\t\tvar clevertapDefaultInstance: CleverTapAPI? = null\n" +
                        "\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)//added by CleverTap Assistant\n")
            }

        }

    }


}