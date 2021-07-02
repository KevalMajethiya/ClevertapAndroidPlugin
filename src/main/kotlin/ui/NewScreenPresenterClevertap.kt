package ui


import com.intellij.openapi.actionSystem.AnActionEvent
import data.file.CurrentPath
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import managers.check_language
import java.io.FileNotFoundException

class NewScreenPresenterClevertap(
    private val view: NewScreenView,
    private val fileCreator: FileCreator,
    private val packageExtractor: PackageExtractor,
    private val writeActionDispatcher: WriteActionDispatcher,
    private val moduleRepository: ModuleRepository,
    private val currentPath: CurrentPath?
) {

    fun onLoadView() {
        view.showPackage(packageExtractor.extractFromCurrentPath())
        view.showModules(moduleRepository.getAllModules())
        currentPath?.let { view.selectModule(currentPath.module) }

    }

    fun onOkClick(
        event: AnActionEvent,
        packageName: String,
        Account_id: String,
        Account_token: String,
        //use_google_ad_id: String,
        use_google_ad_id_rb1 : Boolean,
        use_google_ad_id_rb2: Boolean,
        region_selected : String,
        Exclude_filesText :String,
        contentTextText: String,
        applicationclassname: String,
        IsRadiobuttonrb1Selected: Boolean,
        dependencyVersionText: String,
        isNeedReadMeForInstructions: Boolean,
        moduleName: String
        //launchingactivityname:String
    ) {



        event.project?.let { project ->
            val check= check_language(project)
            val lang= check.find_language()



            CTInputApprovalDialog(event,Account_id,Account_token,use_google_ad_id_rb1,use_google_ad_id_rb2,region_selected,
                Exclude_filesText,applicationclassname,IsRadiobuttonrb1Selected,lang).show()

            writeActionDispatcher.dispatch {
            }



            try {

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        view.close()
    }
}