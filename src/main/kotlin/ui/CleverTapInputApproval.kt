package ui

import util.Constants
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

class CleverTapInputApproval() : JPanel()
{

    var file1 = JLabel("AndroidManifest file:")

    var manifest = JLabel("Following code snippet would be added in the manifest file:")
    var manifest_content= JLabel()
    var file2 = JLabel("Launching_Activity")
    var launchingact = JLabel("Following code snippet would be added in the Launching_Activity file:")
    var launchingact_content= JLabel()
    var file3 = JLabel("Application Class")
    var myapplication_class = JLabel("Following code snippet would be added in your ApplicationClass file:")
    var myapplication_class_content= JLabel()


    var b= JButton("Copy")
    var b1= JButton("Copy")

    init
    {

        val newPanel = JPanel(GridBagLayout())
        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)

//        manifest_content.setText("<html>"+"Permissions :"+"<br>"+
//                "&lt uses-permission android:name=\"android.permission.INTERNET\" &gt"+"<br>"+
//                "&lt uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" &gt"+"<br>"+
//                "<br>"+
//                "CleverTapCredentials :"+"<br>"+
//                "&lt meta-data"+"<br>"+
//                "        android:name=\"CLEVERTAP_ACCOUNT_ID\""+"<br>"+
//                "        android:value=\" \" / &gt"+"<br>"+
//                "&lt meta-data"+"<br>"+
//                "        android:name=\"CLEVERTAP_TOKEN\""+"<br>"+
//                "        android:value=\" \" /&gt"+"<br>"+
//                "&lt meta-data"+"<br>"+
//                "        android:name=\"CLEVERTAP_USE_GOOGLE_AD_ID\""+"<br>"+
//                "        android:value=\" \" /&gt"+"<br>"+
//                "<br>"+
//               // "}"+
//                "</html>")
//        launchingact_content.setText("<html>"+"import com.clevertap.android.sdk.CleverTapAPI;"+"<br>"+
//                "\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());"+"<br>"+
//                "</html>")
//        myapplication_class_content.setText("<html>"+"ActivityLifecycleCallback.register(this);"+"<br>"+
//                "</html>")



        constraints.gridx = 0
        constraints.gridy = 0
        newPanel.add(file1, constraints)


        constraints.gridx = 0
        constraints.gridy = 1
        newPanel.add(manifest, constraints)

        constraints.gridx = 0
        constraints.gridy = 2
        newPanel.add(manifest_content, constraints)

//        constraints.gridx = 0
//        constraints.gridy = 3
//        newPanel.add(b, constraints)

        constraints.gridx = 0
        constraints.gridy = 4
        newPanel.add(file2, constraints)

        constraints.gridx = 0
        constraints.gridy = 5
        newPanel.add(launchingact, constraints)

        constraints.gridx = 0
        constraints.gridy = 6
        newPanel.add(launchingact_content, constraints)

        constraints.gridx = 0
        constraints.gridy = 7
        newPanel.add(file3, constraints)

        constraints.gridx = 0
        constraints.gridy = 7
        file3.setVisible(false)
        newPanel.add(file3, constraints)

        constraints.gridx = 0
        constraints.gridy = 8
        myapplication_class.setVisible(false)
        newPanel.add(myapplication_class, constraints)

        constraints.gridx = 0
        constraints.gridy = 9
        myapplication_class_content.setVisible(false)
        newPanel.add(myapplication_class_content, constraints)

        // set border for the panel
        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), Constants.FCM_PANEL
        )

        // add the panel to this frame
        add(newPanel)






    }

}