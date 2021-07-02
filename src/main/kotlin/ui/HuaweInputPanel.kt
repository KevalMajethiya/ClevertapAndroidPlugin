package ui
import util.Constants.FCM_PANEL
import java.awt.Desktop
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.net.URI
import javax.swing.*


class HuaweInputPanel :JPanel() {

    private val introduction_label = JLabel("<html>"+"<b>"+"Introduction"+"</b>"+"<hr>"+"</html>")
    private val intro_content = JLabel("CleverTap Huawei Push SDK provides an out of the box service to use the Huwaei Push Kit.")

    private val register_label = JLabel("<html>"+"<b>"+"Register"+"</b>"+"<hr>"+"</html>")
    private val register_content = JLabel("<html>"+"The first step to access the Huawei cloud push is registered as a Huawei developer on the "+"<a href=\"\">Huawei Website.</a>"+"</html>")

    private val enable_pushkit_label = JLabel("<html>"+"<b>"+"Enable Push Kit"+"</b>"+"<hr>"+"</html>")
    private val enable_pushkit_content = JLabel("Once you login to the console, enable the Push Kit.")



    // val icon = ImageIcon("/Users/kevalmajethiya/Desktop/ClevrtapPluginWithFCM-master/images/hint.png")
//    val labelServiceName = JLabel("Huawei Service Name")
//    val fcm_sender_id=JLabel("FCM Sender_ID:")
    private val labeladd_agconnect_service_file=JLabel("<html>"+"<b>"+"Add Agconnect-Service File"+"</b>"+"<hr>"+"</html>")
    val label_file_status=JLabel("NO File Selected")
//    val label_fcm_pushnotification_implemented=JLabel("Have you implemented push notifications using fcm ?")

    val buttontoadd_Agconnect_ervicefile = JButton("Upload!")

//    var rb1_fcm =JRadioButton("Yes")
//    var rb2_fcm =JRadioButton("No")
//    var bg_fcm= ButtonGroup()
//    var l1= JLabel("abc")
//    var b1= JButton("Check")


    //val label=JLabel(" ",50)
//    val serviceNameTextField = JTextField("MyPushService", 25)
//
    val newPanel = JPanel(GridBagLayout())


    init {



        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        //constraints.insets = Insets(inset, inset, inset, inset)
        constraints.insets = Insets(inset, inset, inset, inset)



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


        constraints.gridx = 0
        constraints.gridy = 8
        labeladd_agconnect_service_file.toolTipText = "Upload your Agcoonect_service.json by clicking on upload button."
        //labeladd_agconnect_service_file.setIcon(icon)
        labeladd_agconnect_service_file.horizontalTextPosition = SwingConstants.LEFT
        labeladd_agconnect_service_file.verticalTextPosition = SwingConstants.CENTER
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

}
