package ui

import util.Constants.ChannelID_Name_FCM
import util.Constants.DEPENDENCY_VERSION
import util.Constants.DEPENDENCY_VERSION_VALUE_FCM
import util.Constants.FCM_PANEL
import util.Constants.FCM_SERVICE_NAME_FCM
import util.Constants.MY_FIREBASE_MESSAGING_SERVICE_FCM
import util.Constants.NEED_INSTRUCTION
import javax.swing.*
import javax.swing.BorderFactory
import java.awt.GridBagConstraints
import java.awt.Insets
import java.awt.GridBagLayout
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.JButton
import java.awt.Dimension


class FCMInputPanel :JPanel() {

    val fileContent = ClevertapInputPanel::class.java.getResource("/icons/hint.png")
    private val icon = ImageIcon(fileContent)

    // val icon = ImageIcon("/Users/kevalmajethiya/Desktop/ClevrtapPluginWithFCM-master/images/hint.png")
     val labelServiceName = JLabel(FCM_SERVICE_NAME_FCM)
     val labelContentTitle = JLabel(ChannelID_Name_FCM)
     val labelVersion = JLabel(DEPENDENCY_VERSION)
    val fcm_sender_id=JLabel("FCM Sender_ID:")
     val labeladdgoogle_service_file=JLabel("Add Google Service File")
    val label_file_status=JLabel("NO File Selected")
    val label_fcm_pushnotification_implemented=JLabel("Have you implemented push notifications using fcm ?")

    val buttontoaddgservicefile = JButton("Upload!")
    val button = JButton("Hint!")
    var rb1_fcm =JRadioButton("Yes")
    var rb2_fcm =JRadioButton("No")
    private var bg_fcm= ButtonGroup()
//    var l1= JLabel("abc")
//    var b1= JButton("Check")


    //val label=JLabel(" ",50)
    val isNeedReadMeForInstructions = JCheckBox(NEED_INSTRUCTION)

    val serviceNameTextField = JTextField(MY_FIREBASE_MESSAGING_SERVICE_FCM, 25)
    val dependencyVersionTextField = JTextField(DEPENDENCY_VERSION_VALUE_FCM, 25)
    val pendingIntentTextField = JTextField(25)
    val contentTitleTextField = JTextField(25)
    val contentTextTextField = JTextField(25)
    val fcm_sender_id_TextField = JTextField(25)
    val newPanel = JPanel(GridBagLayout())

    //val label_for_nestedpanel=JLabel("Hello")

    init {
//        f1.add(l1)
//        f1.add(b1)
//        f1.setSize(600,600);
//        f1.setLayout(null);
//        f1.setVisible(false);



        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        //constraints.insets = Insets(inset, inset, inset, inset)
        constraints.insets = Insets(inset, inset, inset, inset)






//        constraints.gridx = 0
//        constraints.gridy = 0
//        //label_for_nestedpanel
//        newPanel1.add(l1, constraints)

        // add components to the panel

        constraints.gridx = 0
        constraints.gridy = 0
        label_fcm_pushnotification_implemented.toolTipText = "Yes Or No"
        label_fcm_pushnotification_implemented.icon = icon
        label_fcm_pushnotification_implemented.horizontalTextPosition = SwingConstants.LEFT
        label_fcm_pushnotification_implemented.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(label_fcm_pushnotification_implemented, constraints)
        constraints.gridx = 1
        bg_fcm.add(rb1_fcm)
        bg_fcm.add(rb2_fcm)

        //newPanel.add(contentTitleTextField, constraints)
        newPanel.add(rb1_fcm, constraints)
        //constraints.gridx = 2
        constraints.gridy =1
        newPanel.add(rb2_fcm, constraints)



        constraints.gridx = 0
        constraints.gridy = 2
        labelServiceName.isVisible = false
        labelServiceName.toolTipText = "Enter FCM Service Name"
        labelServiceName.icon = icon
        labelServiceName.horizontalTextPosition = SwingConstants.LEFT
        labelServiceName.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(labelServiceName, constraints)
        constraints.gridx = 1
        serviceNameTextField.isVisible = false
        newPanel.add(serviceNameTextField, constraints)

      /*  constraints.gridx = 0
        constraints.gridy = 1
        newPanel.add(labelPendingIntent, constraints)
        constraints.gridx = 1
        newPanel.add(pendingIntentTextField, constraints)*/

        constraints.gridx = 0
        constraints.gridy = 3
        labelContentTitle.isVisible = false
        labelContentTitle.toolTipText = "CleverTap Dashboard -> Settings -> Channels"
        labelContentTitle.icon = icon
        labelContentTitle.horizontalTextPosition = SwingConstants.LEFT
        labelContentTitle.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(labelContentTitle, constraints)
        constraints.gridx = 1
        contentTitleTextField.isVisible = false
        newPanel.add(contentTitleTextField, constraints)



//        constraints.gridx = 0
//        constraints.gridy = 3
//        newPanel.add(, constraints)

        /*   constraints.gridx = 0
          constraints.gridy = 3
          newPanel.add(labelContentText, constraints)
          constraints.gridx = 1
          newPanel.add(contentTextTextField, constraints)
*/
        constraints.gridx = 0
        constraints.gridy = 4
        fcm_sender_id.isVisible = false
        fcm_sender_id.toolTipText = "Enter FCM Sender ID."
        fcm_sender_id.icon = icon
        fcm_sender_id.horizontalTextPosition = SwingConstants.LEFT
        fcm_sender_id.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(fcm_sender_id, constraints)
        constraints.gridx = 1
        fcm_sender_id_TextField.isVisible = false
        newPanel.add(fcm_sender_id_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 5
        labelVersion.isVisible = false
        labelVersion.toolTipText = "Enter Dependency Version."
        labelVersion.icon = icon
        labelVersion.horizontalTextPosition = SwingConstants.LEFT
        labelVersion.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(labelVersion, constraints)
        constraints.gridx = 1
        dependencyVersionTextField.isVisible = false
        newPanel.add(dependencyVersionTextField, constraints)

        constraints.gridx = 0
        constraints.gridy = 6
        labeladdgoogle_service_file.isVisible = false
        labeladdgoogle_service_file.toolTipText = "Upload your google_service.json by clicking on upload button."
        labeladdgoogle_service_file.icon = icon
        labeladdgoogle_service_file.horizontalTextPosition = SwingConstants.LEFT
        labeladdgoogle_service_file.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(labeladdgoogle_service_file, constraints)
        constraints.gridx = 1
        buttontoaddgservicefile.isVisible = false
        newPanel.add(buttontoaddgservicefile, constraints)

        constraints.gridx = 1
        constraints.gridy = 7
        label_file_status.isVisible = false
        newPanel.add(label_file_status, constraints)
        constraints.gridx = 1






        // newPanel.add(buttontoaddgservicefile, constraints)
/*
              constraints.gridx = 0
              constraints.gridy = 5
              newPanel.add(isNeedReadMeForInstructions, constraints)

              constraints.gridwidth = 2
              constraints.gridy = 6
              constraints.anchor = GridBagConstraints.CENTER
              newPanel.add(labelInstruction, constraints)*/

        // set border for the panel

        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), FCM_PANEL
        )

        // add the panel to this frame
        //newPanel.preferredSize()

        //newPanel.setSize(1000,1000)

        add(newPanel)

        //f1.isVisible=true
        //add(f1).isVisible=false
       // add(newPanel1).isVisible=false

    }

    override fun getPreferredSize(): Dimension {
        return Dimension(800, 400)
    }

}
