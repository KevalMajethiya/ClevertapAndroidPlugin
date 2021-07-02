package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import managers.AuditreportManager
import managers.GradleManager_for_audit
import managers.ManifestManager_audit
import managers.createFCMfile
import java.io.FileNotFoundException

class test(var event: AnActionEvent) {
    private var AuditreportManager:AuditreportManager?=null
    private var GradleManager_for_audit: GradleManager_for_audit?= null
    private var manifestManager_audit: ManifestManager_audit? = null
    private  var createFCMfile:createFCMfile?=null


    init {
        event.project?.let { project ->

            AuditreportManager = AuditreportManager(project)
            GradleManager_for_audit= GradleManager_for_audit(project)
            manifestManager_audit = ManifestManager_audit(project)
            createFCMfile=createFCMfile(project)

            try {

                AuditreportManager?.let{

                    it.initapplicationclass()

                }

                GradleManager_for_audit?.let {
                    if (it.initBuildGradle()) {
                        it.addDependency("    implementation 'com.github.Anu2897:ReportGenerate-Android:1.1.5'", event)

                    }
                }

                manifestManager_audit?.let {
                    if(it.initAndroidManifest()){
                        it.addData()
                    }

                }



            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}