package ui

import com.intellij.openapi.ui.ComboBox
import util.Constants.FCM_PANEL
import javax.swing.*
import com.intellij.ui.components.JBScrollPane
import java.awt.*


class PushTemplateInput : JPanel() {
    val fileContent = ClevertapInputPanel::class.java.getResource("/icons/hint.png")
    val icon = ImageIcon(fileContent)

    val label_template_type= JLabel("Select your Template")

    val label_title = JLabel("Enter your Title *")
    val label_msg = JLabel("Enter your Message *")
    val label_msg_summary = JLabel("Enter Message line when Notification is expanded *")
    val label_subtitle= JLabel("Enter Subtitle")
    val label_bg= JLabel("Enter Background Color in HEX *")
    val label_big_img= JLabel("Enter the link for image")
    val label_ico= JLabel("Enter the link for Large Icon")
    val label_dl1= JLabel("Enter Deep Link")
    val label_titlte_clr= JLabel("Enter Title Color in HEX")
    val label_msg_clr= JLabel("Enter Message Color in HEX")
    val label_small_icon_clr= JLabel("Enter  Small Icon Color in HEX")


    val label_img1= JLabel("Image 1 *")
    val label_img2= JLabel("Image 2 *")
    val label_img3= JLabel("Image 3 *")
    val label_imgn= JLabel("Image N ")


    val label_dl2= JLabel("Enter Deep Link")
    val label_dln= JLabel("Enter Deep Link")

    val label_default_dl= JLabel("Enter Deep Link")
    val label_dl3= JLabel("Enter Deep Link")
    val label_dl4= JLabel("Enter Deep Link")
    val label_dl5= JLabel("Enter Deep Link")


    val label_bt1= JLabel("Big Text for first image")
    val label_bt2= JLabel("Big Text for second image")
    val label_bt3= JLabel("Big Text for third image")
    val label_st1= JLabel("Small Text for first image")
    val label_st2= JLabel("Small Text for second image")
    val label_st3= JLabel("Small Text for third image")
    val label_pt_price1= JLabel("Price for first image")
    val label_pt_price2= JLabel("Price for second image")
    val label_pt_price3= JLabel("Price for third image")

    val label_img4= JLabel("Image 4 *")
    val label_img5= JLabel("Image 5 *")


    val label_title_alt= JLabel("Title to show after timer expires")
    val label_msg_alt = JLabel("Message to show after timer expires")
    val label_big_img_alt= JLabel("Image to show when timer expires")
    val label_timer_threshold= JLabel("Time Duration in seconds (Min 10)")
    val label_timer_end= JLabel("Epoch Timestamp to countdown to (for example, 1595871380)")

    val label_pt_video_url= JLabel("Video URL")

    val label_pt_small_view= JLabel("Select text-only small view layout(text-only) ")

    val label_pt_event_name= JLabel("Name of event to be raised")
    val label_pt_event_property1= JLabel("Name of event property_<property_name_1>")
    val label_pt_event_property2= JLabel("Name of property_<property_name_2>")
    val label_pt_event_property3= JLabel("Name of event property_<property_name_3>")
    val label_pt_input_label= JLabel("Label text to be shown on the input")
    val label_pt_input_auto_open= JLabel("Auto open the app after feedback")
    val label_pt_input_feedback= JLabel("Feedback")
    val label_pt_dismiss_on_click= JLabel("Dismiss notification on click")

    val label_product_display_action = JLabel("Action Button Label Text")
    val label_product_display_linear = JLabel("Linear Layout Template (\"true\"/\"false\")")
    val label_product_display_action_clr = JLabel("Action Button Background Color in HEX")




    val title_Textfield = JTextField(20)
    val msg_TextField = JTextField(20)
    val msg_summary_TextField = JTextField(20)
    val subtitle_TextField= JTextField(20)
    val bg_TextField=JTextField(20)
    val big_img_TextField= JTextField(20)
    val ico_TextField= JTextField(20)
    val dl1_TextField= JTextField(20)
    val titlte_clr_TextField= JTextField(20)
    val msg_clr_TextField= JTextField(20)
    val small_icon_clr_TextField= JTextField(20)

    val img1_TextField= JTextField(20)
    val img2_TextField= JTextField(20)
    val img3_TextField= JTextField(20)
    val imgn_TextField= JTextField(20)


    val dl2_TextField= JTextField(20)
    val dln_TextField= JTextField(20)


    val default_dl_TextField= JTextField(20)
    val dl3_TextField= JTextField(20)
    val dl4_TextField= JTextField(20)
    val dl5_TextField= JTextField(20)


    val bt1_TextField= JTextField(20)
    val bt2_TextField= JTextField(20)
    val bt3_TextField= JTextField(20)
    val st1_TextField= JTextField(20)
    val st2_TextField= JTextField(20)
    val st3_TextField= JTextField(20)
    val pt_price1_TextField= JTextField(20)
    val pt_price2_TextField= JTextField(20)
    val pt_price3_TextField= JTextField(20)

    val img4_TextField= JTextField(20)
    val img5_TextField= JTextField(20)

    val title_alt_TextField= JTextField(20)
    val msg_alt_TextField= JTextField(20)
    val big_img_alt_TextField= JTextField(20)
    val timer_threshold_TextField= JTextField(20)
    val timer_end_TextField= JTextField(20)

    val pt_video_url_TextField= JTextField(20)
    val pt_small_view_TextField= JTextField(20)

    val pt_event_name_TextField= JTextField(20)
    val pt_event_property1_TextField= JTextField(20)
    val pt_event_property2_TextField= JTextField(20)
    val pt_event_property3_TextField= JTextField(20)
    val pt_input_label_TextField= JTextField(20)
    val pt_input_auto_open_TextField= JTextField(20)
    val pt_input_feedback_TextField= JTextField(20)
    val pt_dismiss_on_click_TextField= JTextField(20)

    val product_display_action_TextField = JTextField(20)
    val product_display_linear_TextField = JTextField(20)
    val product_display_action_clr_TextField = JTextField(20)



    var template = arrayOf<String>("Basic","Auto Carousel","Manual Carousel","Rating","Product Catalog",
        "Five Icons","Timer","Video","Zero Bezel","Input Box")
    var template_value = ComboBox(template)


    private val scroll = JBScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED)



    init {



        //labelPendingIntent.setToolTipText("This is a demo tooltip")
        val newPanel = JPanel(GridBagLayout())

        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)

        // add components to the panel
        constraints.gridx = 0
        constraints.gridy = 0
        label_template_type.setToolTipText("Select your template.")
        label_template_type.setIcon(icon)
        label_template_type.setHorizontalTextPosition(SwingConstants.LEFT)
        label_template_type.setVerticalTextPosition(SwingConstants.TOP)
        newPanel.add(label_template_type, constraints)

        constraints.gridx = 1
        newPanel.add(template_value, constraints)



        constraints.gridx = 0
        constraints.gridy = 1
        label_title.setToolTipText("Title.")
        label_title.setIcon(icon)
        label_title.setHorizontalTextPosition(SwingConstants.LEFT)
        label_title.setVerticalTextPosition(SwingConstants.TOP)
        label_title.setVisible(false)
        newPanel.add(label_title, constraints)

        constraints.gridx = 1
        title_Textfield.setVisible(false)
        newPanel.add(title_Textfield, constraints)

        constraints.gridx = 0
        constraints.gridy = 2
        label_title_alt.setToolTipText("Title.")
        label_title_alt.setIcon(icon)
        label_title_alt.setHorizontalTextPosition(SwingConstants.LEFT)
        label_title_alt.setVerticalTextPosition(SwingConstants.TOP)
        label_title_alt.setVisible(false)
        newPanel.add(label_title_alt, constraints)

        constraints.gridx = 1
        title_alt_TextField.setVisible(false)
        newPanel.add(title_alt_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 3
        label_msg.setToolTipText("Message.")
        label_msg.setIcon(icon)
        label_msg.setHorizontalTextPosition(SwingConstants.LEFT)
        label_msg.setVerticalTextPosition(SwingConstants.TOP)
        label_msg.setVisible(false)
        newPanel.add(label_msg, constraints)

        constraints.gridx = 1
        msg_TextField.setVisible(false)
        newPanel.add( msg_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 4
        label_msg_alt.setToolTipText("Message.")
        label_msg_alt.setIcon(icon)
        label_msg_alt.setHorizontalTextPosition(SwingConstants.LEFT)
        label_msg_alt.setVerticalTextPosition(SwingConstants.TOP)
        label_msg_alt.setVisible(false)
        newPanel.add(label_msg_alt, constraints)

        constraints.gridx = 1
        msg_alt_TextField.setVisible(false)
        newPanel.add( msg_alt_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 5
        label_msg_summary.setToolTipText("Message Summary.")
        label_msg_summary.setIcon(icon)
        label_msg_summary.setHorizontalTextPosition(SwingConstants.LEFT)
        label_msg_summary.setVerticalTextPosition(SwingConstants.TOP)
        label_msg_summary.setVisible(false)

        newPanel.add(label_msg_summary, constraints)

        constraints.gridx = 1
        msg_summary_TextField.setVisible(false)
        newPanel.add(msg_summary_TextField, constraints)

        constraints.gridx = 0
        constraints.gridy = 6
        label_subtitle.setToolTipText("Subtitle.")
        label_subtitle.setIcon(icon)
        label_subtitle.setHorizontalTextPosition(SwingConstants.LEFT)
        label_subtitle.setVerticalTextPosition(SwingConstants.TOP)
        label_subtitle.setVisible(false)
        newPanel.add(label_subtitle, constraints)

        constraints.gridx = 1
        subtitle_TextField.setVisible(false)
        newPanel.add(subtitle_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 7
        label_big_img.setToolTipText("Image")
        label_big_img.setIcon(icon)
        label_big_img.setHorizontalTextPosition(SwingConstants.LEFT)
        label_big_img.setVerticalTextPosition(SwingConstants.TOP)
        label_big_img.setVisible(false)
        newPanel.add(label_big_img, constraints)

        constraints.gridx = 1
        big_img_TextField.setVisible(false)
        newPanel.add(big_img_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 8
        label_big_img_alt.setToolTipText("Image")
        label_big_img_alt.setIcon(icon)
        label_big_img_alt.setHorizontalTextPosition(SwingConstants.LEFT)
        label_big_img_alt.setVerticalTextPosition(SwingConstants.TOP)
        label_big_img_alt.setVisible(false)
        newPanel.add(label_big_img_alt, constraints)

        constraints.gridx = 1
        big_img_alt_TextField.setVisible(false)
        newPanel.add(big_img_alt_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 9
        label_img1.setToolTipText("Select the appropriate location fetch mode.")
        label_img1.setIcon(icon)
        label_img1.setHorizontalTextPosition(SwingConstants.LEFT)
        label_img1.setVerticalTextPosition(SwingConstants.TOP)
        label_img1.setVisible(false)
        newPanel.add(label_img1, constraints)

        constraints.gridx = 1
        img1_TextField.setVisible(false)
        newPanel.add(img1_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 10
        label_img2.setToolTipText("Select the appropriate location fetch mode.")
        label_img2.setIcon(icon)
        label_img2.setHorizontalTextPosition(SwingConstants.LEFT)
        label_img2.setVerticalTextPosition(SwingConstants.TOP)
        label_img2.setVisible(false)
        newPanel.add(label_img2, constraints)

        constraints.gridx = 1
        img2_TextField.setVisible(false)
        newPanel.add(img2_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 11
        label_img3.setToolTipText("Select the appropriate location fetch mode.")
        label_img3.setIcon(icon)
        label_img3.setHorizontalTextPosition(SwingConstants.LEFT)
        label_img3.setVerticalTextPosition(SwingConstants.TOP)
        label_img3.setVisible(false)
        newPanel.add(label_img3, constraints)

        constraints.gridx = 1
        img3_TextField.setVisible(false)
        newPanel.add(img3_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 12
        label_img4.setToolTipText("Select the appropriate location fetch mode.")
        label_img4.setIcon(icon)
        label_img4.setHorizontalTextPosition(SwingConstants.LEFT)
        label_img4.setVerticalTextPosition(SwingConstants.TOP)
        label_img4.setVisible(false)
        newPanel.add(label_img4, constraints)

        constraints.gridx = 1
        img4_TextField.setVisible(false)
        newPanel.add(img4_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 13
        label_img5.setToolTipText("Select the appropriate location fetch mode.")
        label_img5.setIcon(icon)
        label_img5.setHorizontalTextPosition(SwingConstants.LEFT)
        label_img5.setVerticalTextPosition(SwingConstants.TOP)
        label_img5.setVisible(false)
        newPanel.add(label_img5, constraints)

        constraints.gridx = 1
        img5_TextField.setVisible(false)
        newPanel.add(img5_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 14
        label_imgn.setToolTipText("Select the appropriate location fetch mode.")
        label_imgn.setIcon(icon)
        label_imgn.setHorizontalTextPosition(SwingConstants.LEFT)
        label_imgn.setVerticalTextPosition(SwingConstants.TOP)
        label_imgn.setVisible(false)
        newPanel.add(label_imgn, constraints)

        constraints.gridx = 1
        imgn_TextField.setVisible(false)
        newPanel.add(imgn_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 15
        label_pt_video_url.setToolTipText("Select the appropriate location fetch mode.")
        label_pt_video_url.setIcon(icon)
        label_pt_video_url.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_video_url.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_video_url.setVisible(false)
        newPanel.add(label_pt_video_url, constraints)

        constraints.gridx = 1
        pt_video_url_TextField.setVisible(false)
        newPanel.add(pt_video_url_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 16
        label_bt1.setToolTipText("Select the appropriate location fetch mode.")
        label_bt1.setIcon(icon)
        label_bt1.setHorizontalTextPosition(SwingConstants.LEFT)
        label_bt1.setVerticalTextPosition(SwingConstants.TOP)
        label_bt1.setVisible(false)
        newPanel.add(label_bt1, constraints)

        constraints.gridx = 1
        bt1_TextField.setVisible(false)
        newPanel.add(bt1_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 17
        label_bt2.setToolTipText("Select the appropriate location fetch mode.")
        label_bt2.setIcon(icon)
        label_bt2.setHorizontalTextPosition(SwingConstants.LEFT)
        label_bt2.setVerticalTextPosition(SwingConstants.TOP)
        label_bt2.setVisible(false)
        newPanel.add(label_bt2, constraints)

        constraints.gridx = 1
        bt2_TextField.setVisible(false)
        newPanel.add(bt2_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 18
        label_bt3.setToolTipText("Select the appropriate location fetch mode.")
        label_bt3.setIcon(icon)
        label_bt3.setHorizontalTextPosition(SwingConstants.LEFT)
        label_bt3.setVerticalTextPosition(SwingConstants.TOP)
        label_bt3.setVisible(false)
        newPanel.add(label_bt3, constraints)

        constraints.gridx = 1
        bt3_TextField.setVisible(false)
        newPanel.add(bt3_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 19
        label_st1.setToolTipText("Select the appropriate location fetch mode.")
        label_st1.setIcon(icon)
        label_st1.setHorizontalTextPosition(SwingConstants.LEFT)
        label_st1.setVerticalTextPosition(SwingConstants.TOP)
        label_st1.setVisible(false)
        newPanel.add(label_st1, constraints)

        constraints.gridx = 1
        st1_TextField.setVisible(false)
        newPanel.add(st1_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 20
        label_st2.setToolTipText("Select the appropriate location fetch mode.")
        label_st2.setIcon(icon)
        label_st2.setHorizontalTextPosition(SwingConstants.LEFT)
        label_st2.setVerticalTextPosition(SwingConstants.TOP)
        label_st2.setVisible(false)
        newPanel.add(label_st2, constraints)

        constraints.gridx = 1
        st2_TextField.setVisible(false)
        newPanel.add(st2_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 21
        label_st3.setToolTipText("Select the appropriate location fetch mode.")
        label_st3.setIcon(icon)
        label_st3.setHorizontalTextPosition(SwingConstants.LEFT)
        label_st3.setVerticalTextPosition(SwingConstants.TOP)
        label_st3.setVisible(false)
        newPanel.add(label_st3, constraints)

        constraints.gridx = 1
        st3_TextField.setVisible(false)
        newPanel.add(st3_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 22
        label_pt_small_view.setToolTipText("Icon")
        label_pt_small_view.setIcon(icon)
        label_pt_small_view.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_small_view.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_small_view.setVisible(false)
        newPanel.add(label_pt_small_view, constraints)

        constraints.gridx = 1
        pt_small_view_TextField.setVisible(false)
        newPanel.add(pt_small_view_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 23
        label_ico.setToolTipText("Icon")
        label_ico.setIcon(icon)
        label_ico.setHorizontalTextPosition(SwingConstants.LEFT)
        label_ico.setVerticalTextPosition(SwingConstants.TOP)
        label_ico.setVisible(false)
        newPanel.add(label_ico, constraints)

        constraints.gridx = 1
        ico_TextField.setVisible(false)
        newPanel.add(ico_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 24
        label_pt_event_name.setToolTipText("Icon")
        label_pt_event_name.setIcon(icon)
        label_pt_event_name.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_event_name.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_event_name.setVisible(false)
        newPanel.add(label_pt_event_name, constraints)

        constraints.gridx = 1
        pt_event_name_TextField.setVisible(false)
        newPanel.add(pt_event_name_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 25
        label_pt_event_property1.setToolTipText("Icon")
        label_pt_event_property1.setIcon(icon)
        label_pt_event_property1.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_event_property1.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_event_property1.setVisible(false)
        newPanel.add(label_pt_event_property1, constraints)

        constraints.gridx = 1
        pt_event_property1_TextField.setVisible(false)
        newPanel.add(pt_event_property1_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 26
        label_pt_event_property2.setToolTipText("Icon")
        label_pt_event_property2.setIcon(icon)
        label_pt_event_property2.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_event_property2.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_event_property2.setVisible(false)
        newPanel.add(label_pt_event_property2, constraints)

        constraints.gridx = 1
        pt_event_property2_TextField.setVisible(false)
        newPanel.add(pt_event_property2_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 27
        label_pt_event_property3.setToolTipText("Icon")
        label_pt_event_property3.setIcon(icon)
        label_pt_event_property3.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_event_property3.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_event_property3.setVisible(false)
        newPanel.add(label_pt_event_property3, constraints)

        constraints.gridx = 1
        pt_event_property3_TextField.setVisible(false)
        newPanel.add(pt_event_property3_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 28
        label_pt_input_label.setToolTipText("Icon")
        label_pt_input_label.setIcon(icon)
        label_pt_input_label.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_input_label.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_input_label.setVisible(false)
        newPanel.add(label_pt_input_label, constraints)

        constraints.gridx = 1
        pt_input_label_TextField.setVisible(false)
        newPanel.add(pt_input_label_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 29
        label_pt_input_auto_open.setToolTipText("Icon")
        label_pt_input_auto_open.setIcon(icon)
        label_pt_input_auto_open.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_input_auto_open.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_input_auto_open.setVisible(false)
        newPanel.add(label_pt_input_auto_open, constraints)

        constraints.gridx = 1
        pt_input_auto_open_TextField.setVisible(false)
        newPanel.add(pt_input_auto_open_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 30
        label_pt_input_feedback.setToolTipText("Icon")
        label_pt_input_feedback.setIcon(icon)
        label_pt_input_feedback.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_input_feedback.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_input_feedback.setVisible(false)
        newPanel.add(label_pt_input_feedback, constraints)

        constraints.gridx = 1
        pt_input_feedback_TextField.setVisible(false)
        newPanel.add(pt_input_feedback_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 31
        label_pt_dismiss_on_click.setToolTipText("Icon")
        label_pt_dismiss_on_click.setIcon(icon)
        label_pt_dismiss_on_click.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_dismiss_on_click.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_dismiss_on_click.setVisible(false)
        newPanel.add(label_pt_dismiss_on_click, constraints)

        constraints.gridx = 1
        pt_dismiss_on_click_TextField.setVisible(false)
        newPanel.add(pt_dismiss_on_click_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 32
        label_default_dl.setToolTipText("Deep Link")
        label_default_dl.setIcon(icon)
        label_default_dl.setHorizontalTextPosition(SwingConstants.LEFT)
        label_default_dl.setVerticalTextPosition(SwingConstants.TOP)
        label_default_dl.setVisible(false)
        newPanel.add(label_default_dl, constraints)

        constraints.gridx = 1
        default_dl_TextField.setVisible(false)
        newPanel.add(default_dl_TextField, constraints)

        constraints.gridx = 0
        constraints.gridy = 33
        label_dl1.setToolTipText("Deep Link")
        label_dl1.setIcon(icon)
        label_dl1.setHorizontalTextPosition(SwingConstants.LEFT)
        label_dl1.setVerticalTextPosition(SwingConstants.TOP)
        label_dl1.setVisible(false)
        newPanel.add(label_dl1, constraints)

        constraints.gridx = 1
        dl1_TextField.setVisible(false)
        newPanel.add(dl1_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 34
        label_dl2.setToolTipText("Select the appropriate location fetch mode.")
        label_dl2.setIcon(icon)
        label_dl2.setHorizontalTextPosition(SwingConstants.LEFT)
        label_dl2.setVerticalTextPosition(SwingConstants.TOP)
        label_dl2.setVisible(false)
        newPanel.add(label_dl2, constraints)

        constraints.gridx = 1
        dl2_TextField.setVisible(false)
        newPanel.add(dl2_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 35
        label_dl3.setToolTipText("Select the appropriate location fetch mode.")
        label_dl3.setIcon(icon)
        label_dl3.setHorizontalTextPosition(SwingConstants.LEFT)
        label_dl3.setVerticalTextPosition(SwingConstants.TOP)
        label_dl3.setVisible(false)
        newPanel.add(label_dl3, constraints)

        constraints.gridx = 1
        dl3_TextField.setVisible(false)
        newPanel.add(dl3_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 36
        label_dl4.setToolTipText("Select the appropriate location fetch mode.")
        label_dl4.setIcon(icon)
        label_dl4.setHorizontalTextPosition(SwingConstants.LEFT)
        label_dl4.setVerticalTextPosition(SwingConstants.TOP)
        label_dl4.setVisible(false)
        newPanel.add(label_dl4, constraints)

        constraints.gridx = 1
        dl4_TextField.setVisible(false)
        newPanel.add(dl4_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 37
        label_dl5.setToolTipText("Select the appropriate location fetch mode.")
        label_dl5.setIcon(icon)
        label_dl5.setHorizontalTextPosition(SwingConstants.LEFT)
        label_dl5.setVerticalTextPosition(SwingConstants.TOP)
        label_dl5.setVisible(false)
        newPanel.add(label_dl5, constraints)

        constraints.gridx = 1
        dl5_TextField.setVisible(false)
        newPanel.add(dl5_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 38
        label_dln.setToolTipText("Select the appropriate location fetch mode.")
        label_dln.setIcon(icon)
        label_dln.setHorizontalTextPosition(SwingConstants.LEFT)
        label_dln.setVerticalTextPosition(SwingConstants.TOP)
        label_dln.setVisible(false)
        newPanel.add(label_dln, constraints)

        constraints.gridx = 1
        dln_TextField.setVisible(false)
        newPanel.add(dln_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 39
        label_pt_price1.setToolTipText("Select the appropriate location fetch mode.")
        label_pt_price1.setIcon(icon)
        label_pt_price1.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_price1.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_price1.setVisible(false)
        newPanel.add(label_pt_price1, constraints)

        constraints.gridx = 1
        pt_price1_TextField.setVisible(false)
        newPanel.add(pt_price1_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 40
        label_pt_price2.setToolTipText("Select the appropriate location fetch mode.")
        label_pt_price2.setIcon(icon)
        label_pt_price2.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_price2.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_price2.setVisible(false)
        newPanel.add(label_pt_price2, constraints)

        constraints.gridx = 1
        pt_price2_TextField.setVisible(false)
        newPanel.add(pt_price2_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 41
        label_pt_price3.setToolTipText("Select the appropriate location fetch mode.")
        label_pt_price3.setIcon(icon)
        label_pt_price3.setHorizontalTextPosition(SwingConstants.LEFT)
        label_pt_price3.setVerticalTextPosition(SwingConstants.TOP)
        label_pt_price3.setVisible(false)
        newPanel.add(label_pt_price3, constraints)

        constraints.gridx = 1
        pt_price3_TextField.setVisible(false)
        newPanel.add(pt_price3_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 42
        label_timer_threshold.setToolTipText("Select the appropriate location fetch mode.")
        label_timer_threshold.setIcon(icon)
        label_timer_threshold.setHorizontalTextPosition(SwingConstants.LEFT)
        label_timer_threshold.setVerticalTextPosition(SwingConstants.TOP)
        label_timer_threshold.setVisible(false)
        newPanel.add(label_timer_threshold, constraints)

        constraints.gridx = 1
        timer_threshold_TextField.setVisible(false)
        newPanel.add(timer_threshold_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 43
        label_timer_end.setToolTipText("Select the appropriate location fetch mode.")
        label_timer_end.setIcon(icon)
        label_timer_end.setHorizontalTextPosition(SwingConstants.LEFT)
        label_timer_end.setVerticalTextPosition(SwingConstants.TOP)
        label_timer_end.setVisible(false)
        newPanel.add(label_timer_end, constraints)

        constraints.gridx = 1
        timer_end_TextField.setVisible(false)
        newPanel.add(timer_end_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 44
        label_product_display_action.setToolTipText("Background Color")
        label_product_display_action.setIcon(icon)
        label_product_display_action.setHorizontalTextPosition(SwingConstants.LEFT)
        label_product_display_action.setVerticalTextPosition(SwingConstants.TOP)
        label_product_display_action.setVisible(false)
        newPanel.add(label_product_display_action, constraints)

        constraints.gridx = 1
        product_display_action_TextField.setVisible(false)
        newPanel.add(product_display_action_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 45
        label_product_display_linear.setToolTipText("Background Color")
        label_product_display_linear.setIcon(icon)
        label_product_display_linear.setHorizontalTextPosition(SwingConstants.LEFT)
        label_product_display_linear.setVerticalTextPosition(SwingConstants.TOP)
        label_product_display_linear.setVisible(false)
        newPanel.add(label_product_display_linear, constraints)

        constraints.gridx = 1
        product_display_linear_TextField.setVisible(false)
        newPanel.add(product_display_linear_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 46
        label_product_display_action_clr.setToolTipText("Background Color")
        label_product_display_action_clr.setIcon(icon)
        label_product_display_action_clr.setHorizontalTextPosition(SwingConstants.LEFT)
        label_product_display_action_clr.setVerticalTextPosition(SwingConstants.TOP)
        label_product_display_action_clr.setVisible(false)
        newPanel.add(label_product_display_action_clr, constraints)

        constraints.gridx = 1
        product_display_action_clr_TextField.setVisible(false)
        newPanel.add(product_display_action_clr_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 47
        label_bg.setToolTipText("Background Color")
        label_bg.setIcon(icon)
        label_bg.setHorizontalTextPosition(SwingConstants.LEFT)
        label_bg.setVerticalTextPosition(SwingConstants.TOP)
        label_bg.setVisible(false)
        newPanel.add(label_bg, constraints)

        constraints.gridx = 1
        bg_TextField.setVisible(false)
        newPanel.add(bg_TextField, constraints)




        constraints.gridx = 0
        constraints.gridy = 48
        label_titlte_clr.setToolTipText("Select the appropriate location fetch mode.")
        label_titlte_clr.setIcon(icon)
        label_titlte_clr.setHorizontalTextPosition(SwingConstants.LEFT)
        label_titlte_clr.setVerticalTextPosition(SwingConstants.TOP)
        label_titlte_clr.setVisible(false)

        newPanel.add(label_titlte_clr, constraints)

        constraints.gridx = 1
        titlte_clr_TextField.setVisible(false)
        newPanel.add(titlte_clr_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 49
        label_msg_clr.setToolTipText("Select the appropriate location fetch mode.")
        label_msg_clr.setIcon(icon)
        label_msg_clr.setHorizontalTextPosition(SwingConstants.LEFT)
        label_msg_clr.setVerticalTextPosition(SwingConstants.TOP)
        label_msg_clr.setVisible(false)

        newPanel.add(label_msg_clr, constraints)

        constraints.gridx = 1
        msg_clr_TextField.setVisible(false)
        newPanel.add(msg_clr_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 50
        label_small_icon_clr.setToolTipText("Select the appropriate location fetch mode.")
        label_small_icon_clr.setIcon(icon)
        label_small_icon_clr.setHorizontalTextPosition(SwingConstants.LEFT)
        label_small_icon_clr.setVerticalTextPosition(SwingConstants.TOP)
        label_small_icon_clr.setVisible(false)

        newPanel.add(label_small_icon_clr, constraints)

        constraints.gridx = 1
        small_icon_clr_TextField.setVisible(false)
        newPanel.add(small_icon_clr_TextField, constraints)




        // set border for the panel
        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), FCM_PANEL
        )

        // add the panel to this frame

        //newPanel.add(scroll)
        add(newPanel)
//        scroll.add(newPanel)
//        scroll.horizontalScrollBar

    }
    override fun getPreferredSize(): Dimension {
        return Dimension(800, 800)
    }

//    override fun setAutoscrolls(autoscrolls: Boolean) {
//        super.setAutoscrolls(true)
//    }


}
