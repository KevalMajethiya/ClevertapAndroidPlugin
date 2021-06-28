package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import data.file.CurrentPathfcm
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import managers.check_language
import java.io.FileNotFoundException

class PushAmpPresenter(
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
        IsRadiobuttonrb1Selected: Boolean,
        IsRadiobuttonrb2Selected: Boolean,
        className : String,
        moduleName: String
    ){
        event.project?.let { project ->
            val check= check_language(project)
            val lang= check.find_language()
            val appclassName = check.find_appClass()
            val receiver_class=check.find_receiver_class()
            PushAmpApprovalDialog(event,receiver_class,IsRadiobuttonrb1Selected,IsRadiobuttonrb2Selected,lang,appclassName).show()
            try {

            }
            catch (e: FileNotFoundException){
                e.printStackTrace()
            }
        }

        view.close()
    }


}