package ui

import com.intellij.openapi.actionSystem.AnActionEvent

import managers.*
import java.io.FileNotFoundException

class HuaweiPush(var event: AnActionEvent) {
    private var Huawei_Push:Huawei_Push?=null
    private var Huawei_Gradle_Manager:Huawei_Gradle_Manager?=null
    private var ManifestManager_Huawei_Push:ManifestManager_Huawei_Push?=null




    init {
        event.project?.let { project ->

            Huawei_Push = Huawei_Push(project)
            Huawei_Gradle_Manager = Huawei_Gradle_Manager(project)
            ManifestManager_Huawei_Push= ManifestManager_Huawei_Push(project)


            try {

                Huawei_Gradle_Manager?.let{
                    if(it.initBuildGradle())
                    {
                        it.addDependency(event)

                    }


                }

                Huawei_Push?.let{
                    it.initapplicationclass()
                }
                ManifestManager_Huawei_Push?.let{
                    if (it.initAndroidManifest()) {
                        it.addMetaDataContent()
                    }
                }





            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}