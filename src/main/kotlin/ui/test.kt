package ui

import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import managers.AuditreportManager
import util.Constants
import managers.*
import util.Methods
import java.io.FileNotFoundException

class test(var event: AnActionEvent) {
    private var AuditreportManager:AuditreportManager?=null
    private var GradleManager_for_audit: GradleManager_for_audit?= null
    private var manifestManager_audit: ManifestManager_audit? = null


    init {
        event.project?.let { project ->

            AuditreportManager = AuditreportManager(project)
            GradleManager_for_audit= GradleManager_for_audit(project)
            manifestManager_audit = ManifestManager_audit(project)

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