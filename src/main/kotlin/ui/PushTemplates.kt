package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import managers.*
import java.io.FileNotFoundException

class PushTemplates(var event: AnActionEvent) {
    private var Firebase_Service_PushTemplates:Firebase_Service_PushTemplates?=null
    private var Manifest_Manager_PushTemplates:Manifest_Manager_PushTemplates?=null
    private var Create_PushTemplate_service_file:Create_PushTemplate_service_file?=null
    private var GradleManager_PushTemplates_Integration:GradleManager_PushTemplates_Integration?=null
    private var Push_Templates_Integration_LaunchingAct_Manager:Push_Templates_Integration_LaunchingAct_Manager?=null



    init {
        event.project?.let { project ->

            Firebase_Service_PushTemplates = Firebase_Service_PushTemplates(project)
            Manifest_Manager_PushTemplates = Manifest_Manager_PushTemplates(project)
            Create_PushTemplate_service_file=Create_PushTemplate_service_file(project)
            GradleManager_PushTemplates_Integration= GradleManager_PushTemplates_Integration(project)
            Push_Templates_Integration_LaunchingAct_Manager=Push_Templates_Integration_LaunchingAct_Manager(project)

            try {

//                Firebase_Service_PushTemplates?.let{
//
//                    it.initlaunchingactivity()
//
//                }
                Manifest_Manager_PushTemplates?.let{

                    if (it.initAndroidManifest()) {

                        it.addMetaDataContent()

                    }

                }
                Create_PushTemplate_service_file?.let{
                    it.initapplicationclass()
                }
                GradleManager_PushTemplates_Integration?.let{
                    if(it.initBuildGradle())
                    {
                        it.addDependency(event)
                    }
                }
                Push_Templates_Integration_LaunchingAct_Manager?.let {
                    it.initapplicationclass()
                }





            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}