package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.DialogWrapper
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import util.Constants
import javax.swing.JComponent

class NewScreenDialog_Clevertap(var event: AnActionEvent) : DialogWrapper(true),  NewScreenView {

    private val panelForFCM = ClevertapInputPanel()

    private val presenter: NewScreenPresenterClevertap
    private var packageName = ""
    private var moduleName = ""
//    var region_selected = ""

    init {
        val currentPath = event.getData(DataKeys.VIRTUAL_FILE)?.let {
            val module = ModuleUtil.findModuleForFile(it, event.project!!)?.name ?: Constants.DEFAULT_MODULE_NAME
            CurrentPath(it.path, it.isDirectory, module)
        }
        val projectStructure = ProjectStructureImpl(event.project!!)
        val sourceRootRepository = SourceRootRepositoryImpl(projectStructure)
        val fileCreator = FileCreatorImpl(sourceRootRepository)
        val packageExtractor = PackageExtractorImpl(currentPath, sourceRootRepository)
        val writeActionDispatcher = WriteActionDispatcherImpl()
        val moduleRepository = ModuleRepositoryImpl(projectStructure)
        presenter = NewScreenPresenterClevertap(
            this,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath

        )
        panelForFCM.rb1.addActionListener{
            if (panelForFCM.rb1.isSelected) {
                panelForFCM.labelapplicationclassname.isVisible = true
                panelForFCM.application_classname_TextField.isVisible = true

            }
        }
        panelForFCM.rb2.addActionListener{
            if (panelForFCM.rb2.isSelected) {
                panelForFCM.labelapplicationclassname.isVisible = false
                panelForFCM.application_classname_TextField.isVisible =false

            }
        }

        panelForFCM.region_value.addActionListener()
        {
//            var region_selected = panelForFCM.region_value.getSelectedItem()
//            region_selected.toString()
        }

        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME
        init()
    }

    override fun doOKAction() =

            presenter.onOkClick(
                    event,

                    this.packageName,
                    panelForFCM.serviceNameTextField.text,
                    panelForFCM.pendingIntentTextField.text,
                    //panelForFCM.contentTitleTextField.text,
                    panelForFCM.rb1_labelContentText.isSelected,
                    panelForFCM.rb2_labelContentText.isSelected,
                    //region_selected,
                    panelForFCM.region_value.selectedItem.toString(),

                    panelForFCM.Exclude_filesTextField.text,
                    panelForFCM.contentTextTextField.text,
                    panelForFCM.application_classname_TextField.text,
                    panelForFCM.rb1.isSelected,
                    panelForFCM.dependencyVersionTextField.text,

                    panelForFCM.isNeedReadMeForInstructions.isSelected,
                    moduleName
            )

    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForFCM
    }

    override fun close() = close(OK_EXIT_CODE)

    override fun showPackage(packageName: String) {
        this.packageName = packageName
    }

    override fun showModules(modules: List<String>) {}

    override fun selectModule(module: String) {
        moduleName = module
    }
}