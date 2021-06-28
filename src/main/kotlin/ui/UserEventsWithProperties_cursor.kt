
package ui


import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction


class UserEventsWithProperties_cursor (var event: AnActionEvent)
{

    init {
        val project = event.getData(PlatformDataKeys.PROJECT)
        val editor  = event.getData(PlatformDataKeys.EDITOR)
        val selectionmodel = editor?.selectionModel
        val document = editor?.document
        val linenumber = document!!.getLineNumber(selectionmodel!!.selectionStart)
        print(linenumber)
        val a = document.toString()
        val b = a.split(".")
        val c = b[1]
        val d = c
        val e = d.split("]")
        val file_extension = e[0]
        if(file_extension=="java")
        {
            WriteCommandAction.runWriteCommandAction(
                project
            ) {  val ans =document.getLineStartOffset(linenumber)
                document.insertString(ans,"\t\tHashMap<String, Object> eventProperties = new HashMap<String, Object>();//added by CleverTap Assistant\n" +
                        "\t\teventProperties.put(\"Event Property_name \", \"value\");//added by CleverTap Assistant\n" +
                        "\t\tclevertapDefaultInstance.pushEvent(\"event_name\", eventProperties);//added by CleverTap Assistant\n")
            }
        }
        if(file_extension=="kt")
        {
            WriteCommandAction.runWriteCommandAction(
                project
            ) {  val ans =document.getLineStartOffset(linenumber)
                document.insertString(ans,"\t\tval eventProperties =HashMap<String, Any>() //added by CleverTap Assistant\n" +
                        "\t\teventProperties[\"USer Property_name \"]=\"value\" //added by CleverTap Assistant\n" +
                        "\t\tclevertapDefaultInstance?.pushEvent(\"event_name\", eventProperties) //added by CleverTap Assistant\n")
            }

        }
    }
}