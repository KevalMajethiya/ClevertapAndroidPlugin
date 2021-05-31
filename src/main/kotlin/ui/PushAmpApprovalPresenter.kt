package ui

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.util.NotNullLazyValue
import data.file.CurrentPath
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import managers.Manager_PushAmp
import managers.check_language
import util.Constants
import java.io.FileNotFoundException

class PushAmpApprovalPresenter (
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPathfcm: CurrentPath?
) {

    companion object {
        private val NOTIFICATION_GROUP = object :
            NotNullLazyValue<NotificationGroup>() {
            override fun compute(): NotificationGroup {
                return NotificationGroup(
                    Constants.DISPLAY_ID,
                    NotificationDisplayType.BALLOON,
                    true
                )
            }
        }
    }


    private var managerForPushAmp : Manager_PushAmp? = null


    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPathfcm?.let { view.selectModule(currentPathfcm.module) }
    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,
        IsRadiobuttonrb1Selected: Boolean,
        IsRadiobuttonrb2Selected: Boolean,
        receivcerClass: String,
        appclassname:String,
        moduleName: String
    ){
        event.project?.let { project ->

            val check= check_language(project)
            val lang= check.find_language()
            val receiver_class=check.find_receiver_class()

            managerForPushAmp = Manager_PushAmp(project)


            try {
                managerForPushAmp?.let {
                    if (IsRadiobuttonrb2Selected==true){
                        if (it.initAndroidManifest()){
                            it.addMetaDataContent(IsRadiobuttonrb2Selected)
                        }
                    }
                    if (IsRadiobuttonrb1Selected==true){
                        if (it.initAppClass(appclassname) && it.initReceiverClass(receivcerClass)){
                            it.addApplicationData(IsRadiobuttonrb1Selected,appclassname,lang)
                            it.addReceiverData()
                        }
                    }
                }
            }
            catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }
        view.close()
    }

}