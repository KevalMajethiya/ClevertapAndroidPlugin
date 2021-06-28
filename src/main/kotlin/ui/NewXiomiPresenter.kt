package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import data.file.CurrentPathfcm
import data.file.FileCreator
import data.file.PackageExtractor
import data.file.WriteActionDispatcher
import data.repository.ModuleRepository
import java.io.FileNotFoundException

class NewXiomiPresenter(
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
        moduleName: String,
        appID:String,
        appKey: String
    ) {
        event.project?.let {
            XiomiApprovalDialog(event, appID, appKey).show()
            try {

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        view.close()
    }
}