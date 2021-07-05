package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import managers.startactivity
import java.io.FileNotFoundException

class start(var event: AnActionEvent) {
    private var startactivity:startactivity?=null


    init {
        event.project?.let { project ->

            startactivity = startactivity(project)

            try {

                startactivity?.let{

                    if(it.AndroidManifest())
                    {
                        it.startActivity()
                    }

                }





            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}