package ui
import util.Constants.FCM_PANEL
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.net.URI
import javax.swing.*
import javax.swing.BorderFactory
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.JButton
import javax.swing.JFrame


class HuaweInputPanel :JPanel() {

    val fileContent = ClevertapInputPanel::class.java.getResource("/icons/hint.png")
    val icon = ImageIcon(fileContent)

    val clevertap_logo = ClevertapInputPanel::class.java.getResource("/icons/clevertap_logo.png")
    val c_img = ImageIcon(clevertap_logo)
    val img_label = JLabel()

    val console_img = ClevertapInputPanel::class.java.getResource("/icons/Huawei_SS.png")
    val registration_img = ImageIcon(console_img)
    val r_img_label = JLabel()


    val introduction_label = JLabel("<html>"+"<b>"+"Introduction"+"</b>"+"<hr>"+"</html>")
    val intro_content = JLabel("CleverTap Huawei Push SDK provides an out of the box service to use the Huwaei Push Kit.")

    val register_label = JLabel("<html>"+"<b>"+"Register"+"</b>"+"<hr>"+"</html>")
    val register_content = JLabel("<html>"+"The first step to access the Huawei cloud push is registered as a Huawei developer on the "+"<a href=\"\">Huawei Website.</a>"+"</html>")

    val enable_pushkit_label = JLabel("<html>"+"<b>"+"Enable Push Kit"+"</b>"+"<hr>"+"</html>")
    val enable_pushkit_content = JLabel("Once you login to the console, enable the Push Kit.")



    // val icon = ImageIcon("/Users/kevalmajethiya/Desktop/ClevrtapPluginWithFCM-master/images/hint.png")
//    val labelServiceName = JLabel("Huawei Service Name")
//    val fcm_sender_id=JLabel("FCM Sender_ID:")
      val labeladd_agconnect_service_file=JLabel("<html>"+"<b>"+"Add Agconnect-Service File"+"</b>"+"<hr>"+"</html>")
    val label_file_status=JLabel("NO File Selected")
//    val label_fcm_pushnotification_implemented=JLabel("Have you implemented push notifications using fcm ?")

    val buttontoadd_Agconnect_ervicefile = JButton("Upload!")

//    var rb1_fcm =JRadioButton("Yes")
//    var rb2_fcm =JRadioButton("No")
//    var bg_fcm= ButtonGroup()
    var f1= JFrame()
//    var l1= JLabel("abc")
//    var b1= JButton("Check")


    //val label=JLabel(" ",50)
//    val serviceNameTextField = JTextField("MyPushService", 25)
//
    val newPanel = JPanel(GridBagLayout())


    init {

        val newPanel1 = JPanel(GridBagLayout())


        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        //constraints.insets = Insets(inset, inset, inset, inset)
        constraints.insets = Insets(inset, inset, inset, inset)









//        constraints.gridx = 0
//        constraints.gridy = 2
//        labelServiceName.setVisible(false)
//        labelServiceName.setToolTipText("Enter FCM Service Name")
//        labelServiceName.setIcon(icon)
//        labelServiceName.setHorizontalTextPosition( SwingConstants.LEFT);
//        labelServiceName.setVerticalTextPosition( SwingConstants.CENTER );
//        newPanel.add(labelServiceName, constraints)
//        constraints.gridx = 1
//        serviceNameTextField.setVisible(false)
//        newPanel.add(serviceNameTextField, constraints)


//        constraints.gridx = 0
//        constraints.gridy = 0
//        img_label.setIcon(c_img)
//        img_label.setHorizontalAlignment(JLabel.CENTER)
//        img_label.setVerticalAlignment(JLabel.CENTER)
//
//        newPanel.add(img_label,constraints.fill)


        constraints.gridx = 0
        constraints.gridy = 1
        newPanel.add(introduction_label, constraints)


        constraints.gridx = 0
        constraints.gridy = 2
        newPanel.add(intro_content, constraints)

        constraints.gridx = 0
        constraints.gridy = 3
        newPanel.add(register_label, constraints)


        constraints.gridx = 0
        constraints.gridy = 4
        register_content.addMouseListener(object : MouseAdapter() {


            override fun mouseClicked(e: MouseEvent)
            {
                Desktop.getDesktop().browse(URI("https://id5.cloud.huawei.com/CAS/portal/loginAuth.html"))

            }

            override fun mouseExited(e: MouseEvent)
            {

            }

            override fun mouseEntered(e: MouseEvent)
            {


            }
        })
        newPanel.add(register_content, constraints)

        constraints.gridx = 0
        constraints.gridy = 5
        newPanel.add(enable_pushkit_label, constraints)


        constraints.gridx = 0
        constraints.gridy = 6
        newPanel.add(enable_pushkit_content, constraints)

//        constraints.gridx = 0
//        constraints.gridy = 7
//        r_img_label.setIcon(registration_img)
//        r_img_label.setHorizontalAlignment(JLabel.CENTER)
//        r_img_label.setVerticalAlignment(JLabel.CENTER)
//
//        newPanel.add(r_img_label,constraints)







        constraints.gridx = 0
        constraints.gridy = 8
        labeladd_agconnect_service_file.setToolTipText("Upload your Agcoonect_service.json by clicking on upload button.")
        //labeladd_agconnect_service_file.setIcon(icon)
        labeladd_agconnect_service_file.setHorizontalTextPosition( SwingConstants.LEFT)
        labeladd_agconnect_service_file.setVerticalTextPosition( SwingConstants.CENTER )
        newPanel.add(labeladd_agconnect_service_file, constraints)

        constraints.gridx = 0
        constraints.gridy = 9
        newPanel.add(buttontoadd_Agconnect_ervicefile, constraints)


        constraints.gridx = 0
        constraints.gridy = 10
       // label_file_status.setVisible(false)
        newPanel.add(label_file_status, constraints)






        // set border for the panel

        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), FCM_PANEL
        )


        add(newPanel)


    }
//    override fun getPreferredSize(): Dimension? {
//        return Dimension(800, 500)
//    }


}
