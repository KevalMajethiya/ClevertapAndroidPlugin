package ui

import com.intellij.notification.*
import com.intellij.openapi.actionSystem.AnActionEvent
import data.file.*
import data.repository.ModuleRepository
import managers.*
import util.Constants
import util.Methods
import java.io.FileNotFoundException

class CTApprovalPresenter(
    //private val account_id,
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPath: CurrentPath?
) {

    private var gradleManager: GradleManager? = null
    private var manifestManager: ManifestManager? = null
    private var applicationClassManager:ApplicationClassManager?=null
    private var abc:abc?=null
    private var AuditreportManager:AuditreportManager?=null



    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPath?.let { view.selectModule(currentPath.module) }

    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,
        Account_id: String,
        pendingIntentText: String,
        //contentTitleText: String,
        google_ad_id_rb1:Boolean,
        google_ad_id_rb2:Boolean,
        region_selected :String,
        Exclude_filesText :String,
        contentTextText: String,
        applicationclassname: String,
        IsRadiobuttonrb1Selected: Boolean,
        //dependencyVersionText: String,
        //isNeedReadMeForInstructions: Boolean,
        moduleName: String
        //launchingactivityname:String
    ) {
        event.project?.let { project ->

            writeActionDispatcher.dispatch {
                /*       fileCreator.createScreenFiles(
                           packageName,
                           serviceNameText,
                           pendingIntentText,
                           contentTitleText,
                           contentTextText,
                           isNeedReadMeForInstructions,
                           moduleName,
                           Methods.checkPrimaryColorInColorsFile(project)
                       )*/
            }
            gradleManager = GradleManager(project)
            manifestManager = ManifestManager(project)
            applicationClassManager=ApplicationClassManager(project)
            abc=abc(project)
            AuditreportManager=AuditreportManager(project)


            try {
                abc?.initapplicationclass()
//                AuditreportManager?.let{
//
//                    it.initapplicationclass()
//
//                }
                //}
                gradleManager?.let {

                    if (it.initBuildGradle()) {
                        it.addDependency("com.clevertap.android:clevertap-android-sdk:4.0.2", event)

                    }
                }
                if(IsRadiobuttonrb1Selected) {
                    applicationClassManager?.initapplicationclass(applicationclassname, IsRadiobuttonrb1Selected)
                }

                // it.initiateclevertap(
                //Methods.addApplication(applicationclassname, IsRadiobuttonrb1Selected)
                // )

                // }
                // }



//                    if(it.AndroidManifest()){
//                        it.getpackagename()
//                    }
                //               }


//final
                manifestManager?.let {
                    if(it.initAndroidManifest()){
                        it.addData( Methods.getPermissionContent(),Methods.addApplicationClass(applicationclassname,IsRadiobuttonrb1Selected),
                            Methods.getAndroidManifestContent(
                                packageName,
                                Account_id,
                                pendingIntentText,
                                //contentTitleText,
                                google_ad_id_rb1,
                                google_ad_id_rb2,
                                region_selected,
                                Exclude_filesText,
                                //contentTextText,
                                applicationclassname
                            ),Account_id,pendingIntentText,google_ad_id_rb1,google_ad_id_rb2,region_selected,Exclude_filesText

                        )
                    }

                }


//                       manifestManager?.let {
//
//                    if (it.initAndroidManifest()) {
//
//
//
//                        it.addMetaDataContent(
//                            Methods.getAndroidManifestContent(
//                                packageName,
//                                serviceNameText,
//                                pendingIntentText,
//                                contentTitleText,
//                                Exclude_filesText,
//                                contentTextText
//                            )
//                        )
//
//
//                    }
//
//
//
//                }

//                manifestManager?.let {
//
//                    if (it.initAndroidManifest()) {
//                          it.launchingactname()
////                        it.addPermissionData(
////                            Methods.getPermissionContent()
////                        )
////                        it.addApplicationClass(
////                            Methods.addApplicationClass(applicationclassname,IsRadiobuttonrb1Selected)
////                        )
//                    }
//                }



                NotificationGroupManager.getInstance().getNotificationGroup("Display Notification")
                    .createNotification(Constants.NOTIFICATION_TITLE,"Basic Clevertap integration has been  successfully completed.", NotificationType.INFORMATION)
                    .notify(project)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        view.close()
    }
}