package ui
import util.Constants.Exclude_files
import util.Constants.DEPENDENCY_VERSION_VALUE
import util.Constants.FCM_PANEL
import util.Constants.FCM_SERVICE_NAME
import util.Constants.MY_FIREBASE_MESSAGING_SERVICE
import util.Constants.NEED_INSTRUCTION
import util.Constants.PENDINGINTENT_ACTIVITY_NAME
import java.awt.*
import javax.swing.*
import javax.swing.BorderFactory
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.JLabel
import java.awt.event.MouseEvent
import java.awt.event.MouseAdapter

import javax.swing.ImageIcon

import java.net.URI
import javax.swing.JRadioButton


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
    val label_small_icon_clr= JLabel("Enter  Small Icon Color in HEXe")


    val label_img1= JLabel("Image 1 *")
    val label_img2= JLabel("Image 2 *")
    val label_img3= JLabel("Image 3 *")
    val label_imgn= JLabel("Image N ")


    val label_dl2= JLabel("Enter Deep Link")
    val label_dln= JLabel("Enter Deep Link")



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




    var template = arrayOf<String>("Basic","Auto Carousel","Manual Carousel","Rating","Product Catalog",
        "Five Icons","Timer","Video","Zero Bezel","Input Box")
    var template_value = JComboBox(template)


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
        label_template_type.setToolTipText("Set your template.")
        label_template_type.setIcon(icon)
        label_template_type.setHorizontalTextPosition(SwingConstants.LEFT)
        label_template_type.setVerticalTextPosition(SwingConstants.TOP)
        newPanel.add(label_template_type, constraints)

        constraints.gridx = 1
        newPanel.add(template_value, constraints)



        constraints.gridx = 0
        constraints.gridy = 1
        label_title.setToolTipText("Set the appropriate log level.")
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
        label_msg.setToolTipText("Set location accuracy.")
        label_msg.setIcon(icon)
        label_msg.setHorizontalTextPosition(SwingConstants.LEFT)
        label_msg.setVerticalTextPosition(SwingConstants.TOP)
        label_msg.setVisible(false)

        newPanel.add(label_msg, constraints)

        constraints.gridx = 1
        msg_TextField.setVisible(false)
        newPanel.add( msg_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 3
        label_msg_summary.setToolTipText("Select the appropriate location fetch mode.")
        label_msg_summary.setIcon(icon)
        label_msg_summary.setHorizontalTextPosition(SwingConstants.LEFT)
        label_msg_summary.setVerticalTextPosition(SwingConstants.TOP)
        label_msg_summary.setVisible(false)

        newPanel.add(label_msg_summary, constraints)

        constraints.gridx = 1
        msg_summary_TextField.setVisible(false)
        newPanel.add(msg_summary_TextField, constraints)

        constraints.gridx = 0
        constraints.gridy = 4
        label_subtitle.setToolTipText("Select the appropriate location fetch mode.")
        label_subtitle.setIcon(icon)
        label_subtitle.setHorizontalTextPosition(SwingConstants.LEFT)
        label_subtitle.setVerticalTextPosition(SwingConstants.TOP)
        label_subtitle.setVisible(false)

        newPanel.add(label_subtitle, constraints)

        constraints.gridx = 1
        subtitle_TextField.setVisible(false)
        newPanel.add(subtitle_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 5
        label_bg.setToolTipText("Select the appropriate location fetch mode.")
        label_bg.setIcon(icon)
        label_bg.setHorizontalTextPosition(SwingConstants.LEFT)
        label_bg.setVerticalTextPosition(SwingConstants.TOP)
        label_bg.setVisible(false)

        newPanel.add(label_bg, constraints)

        constraints.gridx = 1
        bg_TextField.setVisible(false)
        newPanel.add(bg_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 6
        label_big_img.setToolTipText("Select the appropriate location fetch mode.")
        label_big_img.setIcon(icon)
        label_big_img.setHorizontalTextPosition(SwingConstants.LEFT)
        label_big_img.setVerticalTextPosition(SwingConstants.TOP)
        label_big_img.setVisible(false)

        newPanel.add(label_big_img, constraints)

        constraints.gridx = 1
        big_img_TextField.setVisible(false)
        newPanel.add(big_img_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 7
        label_ico.setToolTipText("Select the appropriate location fetch mode.")
        label_ico.setIcon(icon)
        label_ico.setHorizontalTextPosition(SwingConstants.LEFT)
        label_ico.setVerticalTextPosition(SwingConstants.TOP)
        label_ico.setVisible(false)

        newPanel.add(label_ico, constraints)

        constraints.gridx = 1
        ico_TextField.setVisible(false)
        newPanel.add(ico_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 8
        label_dl1.setToolTipText("Select the appropriate location fetch mode.")
        label_dl1.setIcon(icon)
        label_dl1.setHorizontalTextPosition(SwingConstants.LEFT)
        label_dl1.setVerticalTextPosition(SwingConstants.TOP)
        label_dl1.setVisible(false)

        newPanel.add(label_dl1, constraints)

        constraints.gridx = 1
        dl1_TextField.setVisible(false)
        newPanel.add(dl1_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 9
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
        constraints.gridy = 10
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
        constraints.gridy = 11
        label_small_icon_clr.setToolTipText("Select the appropriate location fetch mode.")
        label_small_icon_clr.setIcon(icon)
        label_small_icon_clr.setHorizontalTextPosition(SwingConstants.LEFT)
        label_small_icon_clr.setVerticalTextPosition(SwingConstants.TOP)
        label_small_icon_clr.setVisible(false)

        newPanel.add(label_small_icon_clr, constraints)

        constraints.gridx = 1
        small_icon_clr_TextField.setVisible(false)
        newPanel.add(small_icon_clr_TextField, constraints)

        constraints.gridx = 0
        constraints.gridy = 12
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
        constraints.gridy = 13
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
        constraints.gridy = 14
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
        constraints.gridy = 15
        label_imgn.setToolTipText("Select the appropriate location fetch mode.")
        label_imgn.setIcon(icon)
        label_imgn.setHorizontalTextPosition(SwingConstants.LEFT)
        label_imgn.setVerticalTextPosition(SwingConstants.TOP)
        label_imgn.setVisible(false)

        newPanel.add(label_img3, constraints)

        constraints.gridx = 1
        imgn_TextField.setVisible(false)
        newPanel.add(imgn_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 16
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
        constraints.gridy = 17
        label_dln.setToolTipText("Select the appropriate location fetch mode.")
        label_dln.setIcon(icon)
        label_dln.setHorizontalTextPosition(SwingConstants.LEFT)
        label_dln.setVerticalTextPosition(SwingConstants.TOP)
        label_dln.setVisible(false)

        newPanel.add(label_img3, constraints)

        constraints.gridx = 1
        dln_TextField.setVisible(false)
        newPanel.add(dln_TextField, constraints)



        // set border for the panel
        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), FCM_PANEL
        )

        // add the panel to this frame
        add(newPanel)

    }
    override fun getPreferredSize(): Dimension {
        return Dimension(800, 600)
    }
}
