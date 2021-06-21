package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.layout.panel
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import managers.ManifestManager
import util.Constants
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JTextField

class Push_Templates_Dialog(var event: AnActionEvent) : DialogWrapper(true),  NewScreenView {

    private val panelForPushTemplates = PushTemplateInput()

    private val presenter: Push_Templates_Presenter
    private var packageName = ""
    private var moduleName = ""
    var region_selected = ""

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
        presenter = Push_Templates_Presenter(
            this,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath

        )

        panelForPushTemplates.template_value.addActionListener()
        {
            var template_selected = panelForPushTemplates.template_value.getSelectedItem()
            if(template_selected=="Basic")
            {
                panelForPushTemplates.label_title.isVisible = true
                panelForPushTemplates.label_msg.isVisible = true
                panelForPushTemplates.label_msg_summary.isVisible = true
                panelForPushTemplates.label_subtitle.isVisible = true
                panelForPushTemplates.label_bg.isVisible = true
                panelForPushTemplates.label_big_img.isVisible = true
                panelForPushTemplates.label_ico.isVisible = true
                panelForPushTemplates.label_dl1.isVisible = true
                panelForPushTemplates.label_titlte_clr.isVisible = true
                panelForPushTemplates.label_msg_clr.isVisible = true
                panelForPushTemplates.label_small_icon_clr.isVisible = true


                panelForPushTemplates.title_Textfield.isVisible = true
                panelForPushTemplates.msg_TextField.isVisible = true
                panelForPushTemplates.msg_summary_TextField.isVisible = true
                panelForPushTemplates.subtitle_TextField.isVisible = true
                panelForPushTemplates.bg_TextField.isVisible = true
                panelForPushTemplates.big_img_TextField.isVisible = true
                panelForPushTemplates.ico_TextField.isVisible = true
                panelForPushTemplates.dl1_TextField.isVisible = true
                panelForPushTemplates.titlte_clr_TextField.isVisible = true
                panelForPushTemplates.msg_clr_TextField.isVisible = true
                panelForPushTemplates.small_icon_clr_TextField.isVisible = true



            }
            if(template_selected!="Basic")
            {
                panelForPushTemplates.label_title.isVisible = false
                panelForPushTemplates.label_msg.isVisible = false
                panelForPushTemplates.label_msg_summary.isVisible = false
                panelForPushTemplates.label_subtitle.isVisible = false
                panelForPushTemplates.label_bg.isVisible = false
                panelForPushTemplates.label_big_img.isVisible = false
                panelForPushTemplates.label_ico.isVisible = false
                panelForPushTemplates.label_dl1.isVisible = false
                panelForPushTemplates.label_titlte_clr.isVisible = false
                panelForPushTemplates.label_msg_clr.isVisible = false
                panelForPushTemplates.label_small_icon_clr.isVisible = false


                panelForPushTemplates.title_Textfield.isVisible = false
                panelForPushTemplates.msg_TextField.isVisible = false
                panelForPushTemplates.msg_summary_TextField.isVisible = false
                panelForPushTemplates.subtitle_TextField.isVisible = false
                panelForPushTemplates.bg_TextField.isVisible = false
                panelForPushTemplates.big_img_TextField.isVisible = false
                panelForPushTemplates.ico_TextField.isVisible = false
                panelForPushTemplates.dl1_TextField.isVisible = false
                panelForPushTemplates.titlte_clr_TextField.isVisible = false
                panelForPushTemplates.msg_clr_TextField.isVisible = false
                panelForPushTemplates.small_icon_clr_TextField.isVisible = false
            }
            if(template_selected=="Auto Carousel")
            {
                panelForPushTemplates.label_title.isVisible = true
                panelForPushTemplates.label_msg.isVisible = true
                panelForPushTemplates.label_msg_summary.isVisible = true
                panelForPushTemplates.label_subtitle.isVisible = true
                panelForPushTemplates.label_dl1.isVisible =true
                panelForPushTemplates.label_img1.isVisible =true
                panelForPushTemplates.label_img2.isVisible =true
                panelForPushTemplates.label_img3.isVisible =true
                panelForPushTemplates.label_imgn.isVisible =true
                panelForPushTemplates.label_bg.isVisible = true
                panelForPushTemplates.label_ico.isVisible = true
                panelForPushTemplates.label_titlte_clr.isVisible = true
                panelForPushTemplates.label_msg_clr.isVisible = true
                panelForPushTemplates.label_small_icon_clr.isVisible = true


                panelForPushTemplates.title_Textfield.isVisible = true
                panelForPushTemplates.msg_TextField.isVisible = true
                panelForPushTemplates.msg_summary_TextField.isVisible = true
                panelForPushTemplates.subtitle_TextField.isVisible = true
                panelForPushTemplates.dl1_TextField.isVisible = true
                panelForPushTemplates.img1_TextField.isVisible = true
                panelForPushTemplates.img2_TextField.isVisible = true
                panelForPushTemplates.img3_TextField.isVisible = true
                panelForPushTemplates.imgn_TextField.isVisible = true
                panelForPushTemplates.bg_TextField.isVisible = true
                panelForPushTemplates.ico_TextField.isVisible = true
                panelForPushTemplates.titlte_clr_TextField.isVisible = true
                panelForPushTemplates.msg_clr_TextField.isVisible = true
                panelForPushTemplates.small_icon_clr_TextField.isVisible = true
            }
            if(template_selected!="Auto Carousel")
            {
                panelForPushTemplates.label_title.isVisible = false
                panelForPushTemplates.label_msg.isVisible = false
                panelForPushTemplates.label_msg_summary.isVisible = false
                panelForPushTemplates.label_subtitle.isVisible = false
                panelForPushTemplates.label_dl1.isVisible =false
                panelForPushTemplates.label_img1.isVisible =false
                panelForPushTemplates.label_img2.isVisible =false
                panelForPushTemplates.label_img3.isVisible =false
                panelForPushTemplates.label_imgn.isVisible =false
                panelForPushTemplates.label_bg.isVisible = false
                panelForPushTemplates.label_ico.isVisible = false
                panelForPushTemplates.label_titlte_clr.isVisible = false
                panelForPushTemplates.label_msg_clr.isVisible = false
                panelForPushTemplates.label_small_icon_clr.isVisible = false


                panelForPushTemplates.title_Textfield.isVisible = false
                panelForPushTemplates.msg_TextField.isVisible = false
                panelForPushTemplates.msg_summary_TextField.isVisible = false
                panelForPushTemplates.subtitle_TextField.isVisible = false
                panelForPushTemplates.dl1_TextField.isVisible = false
                panelForPushTemplates.img1_TextField.isVisible = false
                panelForPushTemplates.img2_TextField.isVisible = false
                panelForPushTemplates.img3_TextField.isVisible = false
                panelForPushTemplates.imgn_TextField.isVisible = false
                panelForPushTemplates.bg_TextField.isVisible = false
                panelForPushTemplates.ico_TextField.isVisible = false
                panelForPushTemplates.titlte_clr_TextField.isVisible = false
                panelForPushTemplates.msg_clr_TextField.isVisible = false
                panelForPushTemplates.small_icon_clr_TextField.isVisible = false
            }

            if(template_selected=="Manual Carousel")
            {
                panelForPushTemplates.label_title.isVisible = true
                panelForPushTemplates.label_msg.isVisible = true
                panelForPushTemplates.label_msg_summary.isVisible = true
                panelForPushTemplates.label_subtitle.isVisible = true
                panelForPushTemplates.label_dl1.isVisible =true
                panelForPushTemplates.label_dl2.isVisible =true
                panelForPushTemplates.label_dln.isVisible =true
                panelForPushTemplates.label_img1.isVisible =true
                panelForPushTemplates.label_img2.isVisible =true
                panelForPushTemplates.label_img3.isVisible =true
                panelForPushTemplates.label_imgn.isVisible =true
                panelForPushTemplates.label_bg.isVisible = true
                panelForPushTemplates.label_ico.isVisible = true
                panelForPushTemplates.label_titlte_clr.isVisible = true
                panelForPushTemplates.label_msg_clr.isVisible = true
                panelForPushTemplates.label_small_icon_clr.isVisible = true


                panelForPushTemplates.title_Textfield.isVisible = true
                panelForPushTemplates.msg_TextField.isVisible = true
                panelForPushTemplates.msg_summary_TextField.isVisible = true
                panelForPushTemplates.subtitle_TextField.isVisible = true
                panelForPushTemplates.dl1_TextField.isVisible = true
                panelForPushTemplates.dl2_TextField.isVisible = true
                panelForPushTemplates.dln_TextField.isVisible = true
                panelForPushTemplates.img1_TextField.isVisible = true
                panelForPushTemplates.img2_TextField.isVisible = true
                panelForPushTemplates.img3_TextField.isVisible = true
                panelForPushTemplates.imgn_TextField.isVisible = true
                panelForPushTemplates.bg_TextField.isVisible = true
                panelForPushTemplates.ico_TextField.isVisible = true
                panelForPushTemplates.titlte_clr_TextField.isVisible = true
                panelForPushTemplates.msg_clr_TextField.isVisible = true
                panelForPushTemplates.small_icon_clr_TextField.isVisible = true
            }

        }

        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME
        init()
    }

    override fun doOKAction() =

        presenter.onOkClick(
            event,

            //this.packageName,
            panelForPushTemplates.title_Textfield.text,
            panelForPushTemplates.msg_TextField.text,
            panelForPushTemplates.msg_summary_TextField.text,
            panelForPushTemplates.subtitle_TextField.text,
            panelForPushTemplates.bg_TextField.text,
            panelForPushTemplates.big_img_TextField.text,
            panelForPushTemplates.ico_TextField.text,
            panelForPushTemplates.dl1_TextField.text,
            panelForPushTemplates.titlte_clr_TextField.text,
            panelForPushTemplates.msg_clr_TextField.text,
            panelForPushTemplates.small_icon_clr_TextField.text,
            panelForPushTemplates.template_value.getSelectedItem().toString()
            //moduleName
        )

    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForPushTemplates
    }

    override fun close() = close(DialogWrapper.OK_EXIT_CODE)

    override fun showPackage(packageName: String) {
        this.packageName = packageName
    }

    override fun showModules(modules: List<String>) {}

    override fun selectModule(module: String) {
        moduleName = module
    }
}