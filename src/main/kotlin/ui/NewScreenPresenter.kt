package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import data.file.CurrentPathfcm
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import managers.check_language
import java.io.FileNotFoundException

class NewScreenPresenter(
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPathfcm: CurrentPathfcm?
) {



    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPathfcm?.let { view.selectModule(currentPathfcm.module) }
    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,
        serviceNameText: String,
        pendingIntentText: String,
        contentTitleText: String,
        contentTextText: String,
        fcm_sender_id :String,
        dependencyVersionText: String,
        IsRadiobuttonrb1Selected: Boolean,
        IsRadiobuttonrb2Selected: Boolean,
        isNeedReadMeForInstructions: Boolean,
        moduleName: String
        //fcm1: fcm1
    ) {
        event.project?.let { project ->
            val check= check_language(project)
            val lang= check.find_language()

            FCMApprovalDialog(event,serviceNameText,contentTitleText,fcm_sender_id,dependencyVersionText,IsRadiobuttonrb1Selected,IsRadiobuttonrb2Selected,lang).show()


            try {


            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }
        view.close()
    }
}