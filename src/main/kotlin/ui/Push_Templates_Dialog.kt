package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.layout.panel
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import util.Constants
import javax.swing.JComponent

class Push_Templates_Dialog(var event: AnActionEvent) : DialogWrapper(true),  NewScreenView {

    private val panelForPushTemplates = PushTemplateInput()

    private val presenter: Push_Templates_Presenter
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
            val template_selected = panelForPushTemplates.template_value.selectedItem
            if(template_selected=="Basic")
            {
                hide()
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
            if(template_selected=="Auto Carousel")
            {
                hide()
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

            if(template_selected=="Manual Carousel")
            {
                hide()
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

            if(template_selected=="Rating")
            {
                hide()
                panelForPushTemplates.label_title.isVisible = true
                panelForPushTemplates.label_msg.isVisible = true
                panelForPushTemplates.label_msg_summary.isVisible = true
                panelForPushTemplates.label_subtitle.isVisible = true
                panelForPushTemplates.label_default_dl.isVisible =true
                panelForPushTemplates.label_dl1.isVisible =true
                panelForPushTemplates.label_dl2.isVisible =true
                panelForPushTemplates.label_dl3.isVisible =true
                panelForPushTemplates.label_dl4.isVisible =true
                panelForPushTemplates.label_dl5.isVisible =true
                panelForPushTemplates.label_bg.isVisible = true
                panelForPushTemplates.label_ico.isVisible = true
                panelForPushTemplates.label_titlte_clr.isVisible = true
                panelForPushTemplates.label_msg_clr.isVisible = true
                panelForPushTemplates.label_small_icon_clr.isVisible = true


                panelForPushTemplates.title_Textfield.isVisible = true
                panelForPushTemplates.msg_TextField.isVisible = true
                panelForPushTemplates.msg_summary_TextField.isVisible = true
                panelForPushTemplates.subtitle_TextField.isVisible = true
                panelForPushTemplates.default_dl_TextField.isVisible = true
                panelForPushTemplates.dl1_TextField.isVisible = true
                panelForPushTemplates.dl2_TextField.isVisible = true
                panelForPushTemplates.dl3_TextField.isVisible = true
                panelForPushTemplates.dl4_TextField.isVisible = true
                panelForPushTemplates.dl5_TextField.isVisible = true
                panelForPushTemplates.bg_TextField.isVisible = true
                panelForPushTemplates.ico_TextField.isVisible = true
                panelForPushTemplates.titlte_clr_TextField.isVisible = true
                panelForPushTemplates.msg_clr_TextField.isVisible = true
                panelForPushTemplates.small_icon_clr_TextField.isVisible = true
            }

            if(template_selected=="Product Catalog")
            {
                hide()
                panelForPushTemplates.label_title.isVisible = true
                panelForPushTemplates.label_msg.isVisible = true
                panelForPushTemplates.label_subtitle.isVisible = true
                panelForPushTemplates.label_img1.isVisible =true
                panelForPushTemplates.label_img2.isVisible =true
                panelForPushTemplates.label_img3.isVisible =true
                panelForPushTemplates.label_bt1.isVisible =true
                panelForPushTemplates.label_bt2.isVisible =true
                panelForPushTemplates.label_bt3.isVisible =true
                panelForPushTemplates.label_st1.isVisible =true
                panelForPushTemplates.label_st2.isVisible =true
                panelForPushTemplates.label_st3.isVisible =true
                panelForPushTemplates.label_dl1.isVisible =true
                panelForPushTemplates.label_dl2.isVisible =true
                panelForPushTemplates.label_dl3.isVisible =true
                panelForPushTemplates.label_pt_price1.isVisible =true
                panelForPushTemplates.label_pt_price2.isVisible =true
                panelForPushTemplates.label_pt_price3.isVisible =true

                panelForPushTemplates.label_product_display_action.isVisible=true
                panelForPushTemplates.label_product_display_linear.isVisible=true
                panelForPushTemplates.label_product_display_action_clr.isVisible=true

                panelForPushTemplates.label_bg.isVisible = true
                panelForPushTemplates.label_titlte_clr.isVisible = true
                panelForPushTemplates.label_msg_clr.isVisible = true
                panelForPushTemplates.label_small_icon_clr.isVisible = true


                panelForPushTemplates.title_Textfield.isVisible = true
                panelForPushTemplates.msg_TextField.isVisible = true
                panelForPushTemplates.msg_summary_TextField.isVisible = true
                panelForPushTemplates.subtitle_TextField.isVisible = true
                panelForPushTemplates.img1_TextField.isVisible = true
                panelForPushTemplates.img2_TextField.isVisible = true
                panelForPushTemplates.img3_TextField.isVisible = true
                panelForPushTemplates.bt1_TextField.isVisible = true
                panelForPushTemplates.bt2_TextField.isVisible = true
                panelForPushTemplates.bt3_TextField.isVisible = true
                panelForPushTemplates.st1_TextField.isVisible = true
                panelForPushTemplates.st2_TextField.isVisible = true
                panelForPushTemplates.st3_TextField.isVisible = true
                panelForPushTemplates.dl1_TextField.isVisible = true
                panelForPushTemplates.dl2_TextField.isVisible = true
                panelForPushTemplates.dl3_TextField.isVisible = true
                panelForPushTemplates.pt_price1_TextField.isVisible = true
                panelForPushTemplates.pt_price2_TextField.isVisible = true
                panelForPushTemplates.pt_price3_TextField.isVisible = true
                panelForPushTemplates.product_display_action_TextField.isVisible=true
                panelForPushTemplates.product_display_linear_TextField.isVisible=true
                panelForPushTemplates.product_display_action_clr_TextField.isVisible=true
                panelForPushTemplates.bg_TextField.isVisible = true
                panelForPushTemplates.titlte_clr_TextField.isVisible = true
                panelForPushTemplates.msg_clr_TextField.isVisible = true
                panelForPushTemplates.small_icon_clr_TextField.isVisible = true
            }


            if(template_selected=="Five Icons")
            {
                hide()
                panelForPushTemplates.label_img1.isVisible =true
                panelForPushTemplates.label_img2.isVisible =true
                panelForPushTemplates.label_img2.isVisible =true
                panelForPushTemplates.label_img3.isVisible =true
                panelForPushTemplates.label_img4.isVisible =true
                panelForPushTemplates.label_img5.isVisible =true
                panelForPushTemplates.label_dl1.isVisible =true
                panelForPushTemplates.label_dl2.isVisible =true
                panelForPushTemplates.label_dl3.isVisible =true
                panelForPushTemplates.label_dl4.isVisible =true
                panelForPushTemplates.label_dl5.isVisible =true
                panelForPushTemplates.label_bg.isVisible = true
                panelForPushTemplates.label_small_icon_clr.isVisible = true


                panelForPushTemplates.img1_TextField.isVisible = true
                panelForPushTemplates.img2_TextField.isVisible = true
                panelForPushTemplates.img3_TextField.isVisible = true
                panelForPushTemplates.img4_TextField.isVisible = true
                panelForPushTemplates.img5_TextField.isVisible = true
                panelForPushTemplates.dl1_TextField.isVisible = true
                panelForPushTemplates.dl2_TextField.isVisible = true
                panelForPushTemplates.dl3_TextField.isVisible = true
                panelForPushTemplates.dl4_TextField.isVisible = true
                panelForPushTemplates.dl5_TextField.isVisible = true
                panelForPushTemplates.bg_TextField.isVisible = true
                panelForPushTemplates.small_icon_clr_TextField.isVisible = true

            }

            if(template_selected=="Timer")
            {
                hide()
                panelForPushTemplates.label_title.isVisible = true
                panelForPushTemplates.label_title_alt.isVisible = true
                panelForPushTemplates.label_msg.isVisible = true
                panelForPushTemplates.label_msg_alt.isVisible = true
                panelForPushTemplates.label_msg_summary.isVisible = true
                panelForPushTemplates.label_subtitle.isVisible = true
                panelForPushTemplates.label_dl1.isVisible =true
                panelForPushTemplates.label_big_img.isVisible =true
                panelForPushTemplates.label_big_img_alt.isVisible =true
                panelForPushTemplates.label_bg.isVisible =true
                panelForPushTemplates.label_timer_threshold.isVisible =true
                panelForPushTemplates.label_timer_end.isVisible = true
                panelForPushTemplates.label_titlte_clr.isVisible = true
                panelForPushTemplates.label_msg_clr.isVisible = true
                panelForPushTemplates.label_small_icon_clr.isVisible = true


                panelForPushTemplates.title_Textfield.isVisible = true
                panelForPushTemplates.title_alt_TextField.isVisible = true
                panelForPushTemplates.msg_TextField.isVisible = true
                panelForPushTemplates.msg_alt_TextField.isVisible = true
                panelForPushTemplates.msg_summary_TextField.isVisible = true
                panelForPushTemplates.subtitle_TextField.isVisible = true
                panelForPushTemplates.dl1_TextField.isVisible = true
                panelForPushTemplates.big_img_TextField.isVisible = true
                panelForPushTemplates.big_img_alt_TextField.isVisible = true
                panelForPushTemplates.bg_TextField.isVisible = true
                panelForPushTemplates.timer_threshold_TextField.isVisible = true
                panelForPushTemplates.timer_end_TextField.isVisible = true
                panelForPushTemplates.titlte_clr_TextField.isVisible = true
                panelForPushTemplates.msg_clr_TextField.isVisible = true
                panelForPushTemplates.small_icon_clr_TextField.isVisible = true
            }

            if(template_selected=="Video")
            {
                hide()
                panelForPushTemplates.label_title.isVisible = true
                panelForPushTemplates.label_msg.isVisible = true
                panelForPushTemplates.label_msg_summary.isVisible = true
                panelForPushTemplates.label_subtitle.isVisible = true
                panelForPushTemplates.label_big_img.isVisible =true
                panelForPushTemplates.label_pt_video_url.isVisible =true
                panelForPushTemplates.label_bg.isVisible =true
                panelForPushTemplates.label_dl1.isVisible =true
                panelForPushTemplates.label_titlte_clr.isVisible = true
                panelForPushTemplates.label_msg_clr.isVisible = true
                panelForPushTemplates.label_small_icon_clr.isVisible = true
                panelForPushTemplates.label_ico.isVisible = true


                panelForPushTemplates.title_Textfield.isVisible = true
                panelForPushTemplates.msg_TextField.isVisible = true
                panelForPushTemplates.msg_summary_TextField.isVisible = true
                panelForPushTemplates.subtitle_TextField.isVisible = true
                panelForPushTemplates.big_img_TextField.isVisible = true
                panelForPushTemplates.pt_video_url_TextField.isVisible = true
                panelForPushTemplates.bg_TextField.isVisible = true
                panelForPushTemplates.dl1_TextField.isVisible = true
                panelForPushTemplates.titlte_clr_TextField.isVisible = true
                panelForPushTemplates.msg_clr_TextField.isVisible = true
                panelForPushTemplates.small_icon_clr_TextField.isVisible = true
                panelForPushTemplates.ico_TextField.isVisible = true
            }

            if(template_selected=="Zero Bezel")
            {
                hide()
                panelForPushTemplates.label_title.isVisible = true
                panelForPushTemplates.label_msg.isVisible = true
                panelForPushTemplates.label_msg_summary.isVisible = true
                panelForPushTemplates.label_subtitle.isVisible = true
                panelForPushTemplates.label_big_img.isVisible =true
                panelForPushTemplates.label_pt_small_view.isVisible =true
                panelForPushTemplates.label_dl1.isVisible =true
                panelForPushTemplates.label_titlte_clr.isVisible = true
                panelForPushTemplates.label_msg_clr.isVisible = true
                panelForPushTemplates.label_small_icon_clr.isVisible = true
                panelForPushTemplates.label_ico.isVisible = true


                panelForPushTemplates.title_Textfield.isVisible = true
                panelForPushTemplates.msg_TextField.isVisible = true
                panelForPushTemplates.msg_summary_TextField.isVisible = true
                panelForPushTemplates.subtitle_TextField.isVisible = true
                panelForPushTemplates.big_img_TextField.isVisible = true
                panelForPushTemplates.pt_small_view_TextField.isVisible = true
                panelForPushTemplates.dl1_TextField.isVisible = true
                panelForPushTemplates.titlte_clr_TextField.isVisible = true
                panelForPushTemplates.msg_clr_TextField.isVisible = true
                panelForPushTemplates.small_icon_clr_TextField.isVisible = true
                panelForPushTemplates.ico_TextField.isVisible = true
            }

            if(template_selected=="Input Box")
            {
                hide()
                panelForPushTemplates.label_title.isVisible = true
                panelForPushTemplates.label_msg.isVisible = true
                panelForPushTemplates.label_msg_summary.isVisible = true
                panelForPushTemplates.label_subtitle.isVisible = true
                panelForPushTemplates.label_big_img.isVisible =true
                panelForPushTemplates.label_big_img_alt.isVisible =true
                panelForPushTemplates.label_pt_event_name.isVisible =true
                panelForPushTemplates.label_pt_event_property1.isVisible =true
                panelForPushTemplates.label_pt_event_property2.isVisible =true
                panelForPushTemplates.label_pt_event_property3.isVisible =true
                panelForPushTemplates.label_pt_input_label.isVisible =true
                panelForPushTemplates.label_pt_input_auto_open.isVisible =true
                panelForPushTemplates.label_pt_input_feedback.isVisible =true
                panelForPushTemplates.label_dl1.isVisible =true
                panelForPushTemplates.label_titlte_clr.isVisible = true
                panelForPushTemplates.label_msg_clr.isVisible = true
                panelForPushTemplates.label_small_icon_clr.isVisible = true
                panelForPushTemplates.label_ico.isVisible = true
                panelForPushTemplates.label_pt_dismiss_on_click.isVisible =true


                panelForPushTemplates.title_Textfield.isVisible = true
                panelForPushTemplates.msg_TextField.isVisible = true
                panelForPushTemplates.msg_summary_TextField.isVisible = true
                panelForPushTemplates.subtitle_TextField.isVisible = true
                panelForPushTemplates.big_img_TextField.isVisible = true
                panelForPushTemplates.big_img_alt_TextField.isVisible = true
                panelForPushTemplates.pt_event_name_TextField.isVisible = true
                panelForPushTemplates.pt_event_property1_TextField.isVisible = true
                panelForPushTemplates.pt_event_property2_TextField.isVisible = true
                panelForPushTemplates.pt_event_property3_TextField.isVisible = true
                panelForPushTemplates.pt_input_label_TextField.isVisible = true
                panelForPushTemplates.label_pt_input_auto_open.isVisible = true
                panelForPushTemplates.pt_input_feedback_TextField.isVisible = true
                panelForPushTemplates.dl1_TextField.isVisible = true
                panelForPushTemplates.titlte_clr_TextField.isVisible = true
                panelForPushTemplates.msg_clr_TextField.isVisible = true
                panelForPushTemplates.small_icon_clr_TextField.isVisible = true
                panelForPushTemplates.ico_TextField.isVisible = true
                panelForPushTemplates.pt_dismiss_on_click_TextField.isVisible = true
            }


        }

        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME
        init()
    }

    fun hide()
    {
        panelForPushTemplates.label_title.isVisible=false
        panelForPushTemplates.label_title_alt.isVisible=false
        panelForPushTemplates.label_msg.isVisible=false
        panelForPushTemplates.label_msg_alt.isVisible=false
        panelForPushTemplates.label_msg_summary.isVisible=false
        panelForPushTemplates.label_subtitle.isVisible=false
        panelForPushTemplates.label_bg.isVisible=false
        panelForPushTemplates.label_big_img.isVisible=false
        panelForPushTemplates.label_big_img_alt.isVisible=false
        panelForPushTemplates.label_img1.isVisible=false
        panelForPushTemplates.label_img2.isVisible=false
        panelForPushTemplates.label_img3.isVisible=false
        panelForPushTemplates.label_img4.isVisible=false
        panelForPushTemplates.label_img5.isVisible=false
        panelForPushTemplates.label_imgn.isVisible=false
        panelForPushTemplates.label_pt_video_url.isVisible=false
        panelForPushTemplates.label_bt1.isVisible=false
        panelForPushTemplates.label_bt2.isVisible=false
        panelForPushTemplates.label_bt3.isVisible=false
        panelForPushTemplates.label_st1.isVisible=false
        panelForPushTemplates.label_st2.isVisible=false
        panelForPushTemplates.label_st3.isVisible=false
        panelForPushTemplates.label_pt_small_view.isVisible=false
        panelForPushTemplates.label_ico.isVisible=false
        panelForPushTemplates.label_pt_event_name.isVisible=false
        panelForPushTemplates.label_pt_event_property1.isVisible=false
        panelForPushTemplates.label_pt_event_property2.isVisible=false
        panelForPushTemplates.label_pt_event_property3.isVisible=false
        panelForPushTemplates.label_pt_input_label.isVisible=false
        panelForPushTemplates.label_pt_input_auto_open.isVisible=false
        panelForPushTemplates.label_pt_input_feedback.isVisible=false
        panelForPushTemplates.label_pt_dismiss_on_click.isVisible=false
        panelForPushTemplates.label_default_dl.isVisible=false
        panelForPushTemplates.label_dl1.isVisible=false
        panelForPushTemplates.label_dl2.isVisible=false
        panelForPushTemplates.label_dl3.isVisible=false
        panelForPushTemplates.label_dl4.isVisible=false
        panelForPushTemplates.label_dl5.isVisible=false
        panelForPushTemplates.label_dln.isVisible=false
        panelForPushTemplates.label_pt_price1.isVisible=false
        panelForPushTemplates.label_pt_price2.isVisible=false
        panelForPushTemplates.label_pt_price3.isVisible=false
        panelForPushTemplates.label_timer_threshold.isVisible=false
        panelForPushTemplates.label_timer_end.isVisible=false
        panelForPushTemplates.label_titlte_clr.isVisible=false
        panelForPushTemplates.label_msg_clr.isVisible=false
        panelForPushTemplates.label_small_icon_clr.isVisible=false
        panelForPushTemplates.label_product_display_action.isVisible=false
        panelForPushTemplates.label_product_display_linear.isVisible=false
        panelForPushTemplates.label_product_display_action_clr.isVisible=false



        panelForPushTemplates.title_Textfield.isVisible=false
        panelForPushTemplates.title_alt_TextField.isVisible=false
        panelForPushTemplates.msg_TextField.isVisible=false
        panelForPushTemplates.msg_alt_TextField.isVisible=false
        panelForPushTemplates.msg_summary_TextField.isVisible=false
        panelForPushTemplates.subtitle_TextField.isVisible=false
        panelForPushTemplates.bg_TextField.isVisible=false
        panelForPushTemplates.big_img_TextField.isVisible=false
        panelForPushTemplates.big_img_alt_TextField.isVisible=false
        panelForPushTemplates.img1_TextField.isVisible=false
        panelForPushTemplates.img2_TextField.isVisible=false
        panelForPushTemplates.img3_TextField.isVisible=false
        panelForPushTemplates.img4_TextField.isVisible=false
        panelForPushTemplates.img5_TextField.isVisible=false
        panelForPushTemplates.imgn_TextField.isVisible=false
        panelForPushTemplates.pt_video_url_TextField.isVisible=false
        panelForPushTemplates.bt1_TextField.isVisible=false
        panelForPushTemplates.bt2_TextField.isVisible=false
        panelForPushTemplates.bt3_TextField.isVisible=false
        panelForPushTemplates.st1_TextField.isVisible=false
        panelForPushTemplates.st2_TextField.isVisible=false
        panelForPushTemplates.st3_TextField.isVisible=false
        panelForPushTemplates.pt_small_view_TextField.isVisible=false
        panelForPushTemplates.ico_TextField.isVisible=false
        panelForPushTemplates.pt_event_name_TextField.isVisible=false
        panelForPushTemplates.pt_event_property1_TextField.isVisible=false
        panelForPushTemplates.pt_event_property2_TextField.isVisible=false
        panelForPushTemplates.pt_event_property3_TextField.isVisible=false
        panelForPushTemplates.pt_input_label_TextField.isVisible=false
        panelForPushTemplates.pt_input_auto_open_TextField.isVisible=false
        panelForPushTemplates.pt_input_feedback_TextField.isVisible=false
        panelForPushTemplates.pt_dismiss_on_click_TextField.isVisible=false
        panelForPushTemplates.default_dl_TextField.isVisible=false
        panelForPushTemplates.dl1_TextField.isVisible=false
        panelForPushTemplates.dl2_TextField.isVisible=false
        panelForPushTemplates.dl3_TextField.isVisible=false
        panelForPushTemplates.dl4_TextField.isVisible=false
        panelForPushTemplates.dl5_TextField.isVisible=false
        panelForPushTemplates.dln_TextField.isVisible=false
        panelForPushTemplates.pt_price1_TextField.isVisible=false
        panelForPushTemplates.pt_price2_TextField.isVisible=false
        panelForPushTemplates.pt_price3_TextField.isVisible=false
        panelForPushTemplates.timer_threshold_TextField.isVisible=false
        panelForPushTemplates.timer_end_TextField.isVisible=false
        panelForPushTemplates.titlte_clr_TextField.isVisible=false
        panelForPushTemplates.msg_clr_TextField.isVisible=false
        panelForPushTemplates.small_icon_clr_TextField.isVisible=false
        panelForPushTemplates.product_display_action_TextField.isVisible=false
        panelForPushTemplates.product_display_linear_TextField.isVisible=false
        panelForPushTemplates.product_display_action_clr_TextField.isVisible=false


    }

    override fun doOKAction() =

        presenter.onOkClick(
            event,

            //this.packageName,
            panelForPushTemplates.template_value.selectedItem.toString(),
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
            panelForPushTemplates.dl2_TextField.text,
            panelForPushTemplates.dln_TextField.text,
            panelForPushTemplates.img1_TextField.text,
            panelForPushTemplates.img2_TextField.text,
            panelForPushTemplates.img3_TextField.text,
            panelForPushTemplates.imgn_TextField.text,

            panelForPushTemplates.default_dl_TextField.text,
            panelForPushTemplates.dl3_TextField.text,
            panelForPushTemplates.dl4_TextField.text,
            panelForPushTemplates.dl5_TextField.text,

            panelForPushTemplates.bt1_TextField.text,
            panelForPushTemplates.bt2_TextField.text,
            panelForPushTemplates.bt3_TextField.text,
            panelForPushTemplates.st1_TextField.text,
            panelForPushTemplates.st2_TextField.text,
            panelForPushTemplates.st3_TextField.text,
            panelForPushTemplates.pt_price1_TextField.text,
            panelForPushTemplates.pt_price2_TextField.text,
            panelForPushTemplates.pt_price3_TextField.text,
            panelForPushTemplates.img4_TextField.text,
            panelForPushTemplates.img5_TextField.text,

            panelForPushTemplates.title_alt_TextField.text,
            panelForPushTemplates.msg_alt_TextField.text,
            panelForPushTemplates.big_img_alt_TextField.text,
            panelForPushTemplates.timer_threshold_TextField.text,
            panelForPushTemplates.timer_end_TextField.text,
            panelForPushTemplates.pt_video_url_TextField.text,
            panelForPushTemplates.pt_small_view_TextField.text,
            panelForPushTemplates.pt_event_name_TextField.text,
            panelForPushTemplates.pt_event_property1_TextField.text,
            panelForPushTemplates.pt_event_property2_TextField.text,
            panelForPushTemplates.pt_event_property3_TextField.text,
            panelForPushTemplates.pt_input_label_TextField.text,
            panelForPushTemplates.pt_input_auto_open_TextField.text,
            panelForPushTemplates.pt_input_feedback_TextField.text,

            panelForPushTemplates.product_display_action_TextField.text,
            panelForPushTemplates.product_display_linear_TextField.text,
            panelForPushTemplates.product_display_action_clr_TextField.text






            //moduleName
        )

    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForPushTemplates
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