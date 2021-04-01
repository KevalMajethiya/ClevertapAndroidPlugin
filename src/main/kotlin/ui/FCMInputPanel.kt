package ui


import util.Constants.CONTENT_TEXT
import util.Constants.CONTENT_TITLE
import util.Constants.ChannelID_Name_FCM
import util.Constants.DEPENDENCY_VERSION
import util.Constants.DEPENDENCY_VERSION_VALUE
import util.Constants.DEPENDENCY_VERSION_VALUE_FCM
import util.Constants.FCM_PANEL
import util.Constants.FCM_SERVICE_NAME
import util.Constants.FCM_SERVICE_NAME_FCM
import util.Constants.MY_FIREBASE_MESSAGING_SERVICE
import util.Constants.MY_FIREBASE_MESSAGING_SERVICE_FCM
import util.Constants.NEED_INSTRUCTION
import util.Constants.NOTES_INSTRUCTION
import util.Constants.PENDINGINTENT_ACTIVITY_NAME
import util.Constants.PENDINGINTENT_ACTIVITY_NAME_FCM
import javax.swing.*
import javax.swing.BorderFactory
import java.awt.GridBagConstraints
import java.awt.Insets
import java.awt.GridBagLayout
import java.awt.Toolkit
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.JButton




class FCMInputPanel : JPanel() {

    val fileContent = ClevertapInputPanel::class.java.getResource("/icons/hint.png")
    val icon = ImageIcon(fileContent);

    // val icon = ImageIcon("/Users/kevalmajethiya/Desktop/ClevrtapPluginWithFCM-master/images/hint.png")
    private val labelServiceName = JLabel(FCM_SERVICE_NAME_FCM)
    private val labelPendingIntent = JLabel(PENDINGINTENT_ACTIVITY_NAME_FCM)
    private val labelContentTitle = JLabel(ChannelID_Name_FCM)
    private val labelContentText = JLabel(CONTENT_TEXT)
    private val labelVersion = JLabel(DEPENDENCY_VERSION)
    private val labelInstruction = JLabel(NOTES_INSTRUCTION)
    val fcm_sender_id=JLabel("FCM Sender_ID:")
     val labeladdgoogle_service_file=JLabel("Add Google Service File")
    val label_file_status=JLabel("NO File Selected")
    val buttontoaddgservicefile = JButton("Upload!")
    val button = JButton("Hint!")


    val isNeedReadMeForInstructions = JCheckBox(NEED_INSTRUCTION)

    val serviceNameTextField = JTextField(MY_FIREBASE_MESSAGING_SERVICE_FCM, 25)
    val dependencyVersionTextField = JTextField(DEPENDENCY_VERSION_VALUE_FCM, 25)
    val pendingIntentTextField = JTextField(25)
    val contentTitleTextField = JTextField(25)
    val contentTextTextField = JTextField(25)
    val fcm_sender_id_TextField = JTextField(25)

    init {

        val newPanel = JPanel(GridBagLayout())


        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)

        // add components to the panel
        constraints.gridx = 0
        constraints.gridy = 0
        labelServiceName.setToolTipText("Enter FCM Service Name")
        labelServiceName.setIcon(icon)
        labelServiceName.setHorizontalTextPosition( SwingConstants.LEFT);
        labelServiceName.setVerticalTextPosition( SwingConstants.CENTER );
        newPanel.add(labelServiceName, constraints)
        constraints.gridx = 1
        newPanel.add(serviceNameTextField, constraints)

      /*  constraints.gridx = 0
        constraints.gridy = 1
        newPanel.add(labelPendingIntent, constraints)
        constraints.gridx = 1
        newPanel.add(pendingIntentTextField, constraints)*/

        constraints.gridx = 0
        constraints.gridy = 2
        labelContentTitle.setToolTipText("CleverTap Dashboard -> Settings -> Channels")
        labelContentTitle.setIcon(icon)
        labelContentTitle.setHorizontalTextPosition( SwingConstants.LEFT);
        labelContentTitle.setVerticalTextPosition( SwingConstants.CENTER );
        newPanel.add(labelContentTitle, constraints)
        constraints.gridx = 1
        newPanel.add(contentTitleTextField, constraints)

//        constraints.gridx = 0
//        constraints.gridy = 3
//        newPanel.add(button, constraints)

        /*   constraints.gridx = 0
          constraints.gridy = 3
          newPanel.add(labelContentText, constraints)
          constraints.gridx = 1
          newPanel.add(contentTextTextField, constraints)
*/
        constraints.gridx = 0
        constraints.gridy = 4
        fcm_sender_id.setToolTipText("Enter FCM Sender ID.")
        fcm_sender_id.setIcon(icon)
        fcm_sender_id.setHorizontalTextPosition( SwingConstants.LEFT);
        fcm_sender_id.setVerticalTextPosition( SwingConstants.CENTER );
        newPanel.add(fcm_sender_id, constraints)
        constraints.gridx = 1
        newPanel.add(fcm_sender_id_TextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 5
        labelVersion.setToolTipText("Enter Dependency Version.")
        labelVersion.setIcon(icon)
        labelVersion.setHorizontalTextPosition( SwingConstants.LEFT);
        labelVersion.setVerticalTextPosition( SwingConstants.CENTER );
        newPanel.add(labelVersion, constraints)
        constraints.gridx = 1
        newPanel.add(dependencyVersionTextField, constraints)

        constraints.gridx = 0
        constraints.gridy = 6
        labeladdgoogle_service_file.setToolTipText("Upload your google_service.json by clicking on upload button.")
        labeladdgoogle_service_file.setIcon(icon)
        labeladdgoogle_service_file.setHorizontalTextPosition( SwingConstants.LEFT);
        labeladdgoogle_service_file.setVerticalTextPosition( SwingConstants.CENTER );
        newPanel.add(labeladdgoogle_service_file, constraints)
        constraints.gridx = 1
        newPanel.add(buttontoaddgservicefile, constraints)

        constraints.gridx = 1
        constraints.gridy = 7
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
        add(newPanel)
    }


}
