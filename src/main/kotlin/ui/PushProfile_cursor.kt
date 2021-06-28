
package ui


import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction


class PushProfile_cursor (var event: AnActionEvent)
{
//    private var projectBaseDir: VirtualFile? = null
//    private var androidManifestfile: com.intellij.openapi.editor.Document? = null
//    private var packagename:String=""
//    private var launchingactivityname:String=""

    init {
        //pushprofile()
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
                    "\t\tHashMap<String, Object> ProfilePush = new HashMap<String, Object>();//added by CleverTap Assistant\n" +
                            "\t\tProfilePush.put(\"USer Property_name \", \"value\");//added by CleverTap Assistant\n" +
                            "\t\tclevertapDefaultInstance.pushProfile(ProfilePush);//added by CleverTap Assistant\n"
                )
            }

        }
        if(file_extension=="kt")
        {
            WriteCommandAction.runWriteCommandAction(
                project
            ) {  val ans =document.getLineStartOffset(linenumber)
                document.insertString(ans,"\t\tval ProfilePush =HashMap<String, Any>()//added by CleverTap Assistant\n" +
                        "\t\tProfilePush[\"USer Property_name\"]= \"value\"//added by CleverTap Assistant\n" +
                        "\t\tclevertapDefaultInstance?.pushProfile(ProfilePush)//added by CleverTap Assistant\n") }

        }
    }


}