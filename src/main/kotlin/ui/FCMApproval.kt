package ui

import util.Constants
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

class FCMApproval() : JPanel()
{

    var file1 = JLabel("AndroidManifest file:")
    var manifest = JLabel("Following code snippet would be added in the manifest file:")
    var manifest_content= JLabel()

    var file2 = JLabel("Launching_Activity")
    var launchingact = JLabel("Following code snippet would be added in the Launching_Activity file:")
    var launchingact_content= JLabel()

    var file3 = JLabel("Build.gadle(Project)")
    var project_gradle = JLabel("Following code snippet would be added in your Build.gradle(Project) file:")
    var project_gradle_content= JLabel()

    var file4 = JLabel("Build.gadle(app)")
    var app_gradle = JLabel("Following code snippet would be added in your Build.gradle(app) file:")
    var app_gradle_content= JLabel()

    var file5 = JLabel("AndroidManifest file:")
    var manifest_yes = JLabel("Following code snippet would be added in the manifest file:")
    var fcm_implemented=JLabel()

    var file6 = JLabel("FCM_Service file:")
    var fcm_service_class = JLabel("Following code snippet would be added in the FCM_Service file:")
    var fcm_service_class_content1=JLabel()

    var fcm_service_class_content2=JLabel()





//    var b= JButton("Copy")
//    var b1= JButton("Copy")

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
        project_gradle_content.setText("<html>"+"classpath 'com.google.gms:google-services:4.3.3'"+"<br>"+
                "</html>")

//        app_gradle_content.setText("<html>"+"implementation 'com.google.firebase:firebase-messaging:20.2.4'"+"<br>"+
//                "implementation 'com.android.installreferrer:installreferrer:2.1'"+"<br>"+
//                "apply plugin: 'com.google.gms.google-services'"+"<br>"+"<br>"+
//                "</html>")
//


        constraints.gridx = 0
        constraints.gridy = 0
        file1.setVisible(false)
        newPanel.add(file1, constraints)


        constraints.gridx = 0
        constraints.gridy = 1
        manifest.setVisible(false)
        newPanel.add(manifest, constraints)

        constraints.gridx = 0
        constraints.gridy = 2
        manifest_content.setVisible(false)
        newPanel.add(manifest_content, constraints)

//        constraints.gridx = 0
//        constraints.gridy = 3
//        newPanel.add(b, constraints)

        constraints.gridx = 0
        constraints.gridy = 3
        file2.setVisible(false)
        newPanel.add(file2, constraints)

        constraints.gridx = 0
        constraints.gridy = 4
        launchingact.setVisible(false)
        newPanel.add(launchingact, constraints)

        constraints.gridx = 0
        constraints.gridy = 5
        launchingact_content.setVisible(false)
        newPanel.add(launchingact_content, constraints)

        constraints.gridx = 0
        constraints.gridy = 6
        file3.setVisible(false)
        newPanel.add(file3, constraints)

        constraints.gridx = 0
        constraints.gridy = 7
        project_gradle.setVisible(false)
        //project_gradle_content.setVisible(false)
        newPanel.add(project_gradle, constraints)

        constraints.gridx = 0
        constraints.gridy = 8
        project_gradle_content.setVisible(false)
        //myapplication_class_content.setVisible(false)
        newPanel.add(project_gradle_content, constraints)

        constraints.gridx = 0
        constraints.gridy = 9
        file4.setVisible(false)
        //file3.setVisible(false)
        newPanel.add(file4, constraints)

        constraints.gridx = 0
        constraints.gridy = 10
        app_gradle.setVisible(false)
        //project_gradle_content.setVisible(false)
        newPanel.add(app_gradle, constraints)

        constraints.gridx = 0
        constraints.gridy = 11
        app_gradle_content.setVisible(false)
        //myapplication_class_content.setVisible(false)
        newPanel.add(app_gradle_content, constraints)

        constraints.gridx = 0
        constraints.gridy = 12
        file5.setVisible(false)
        newPanel.add(file5, constraints)

        constraints.gridx = 0
        constraints.gridy = 13
        manifest_yes.setVisible(false)
        newPanel.add(manifest_yes, constraints)


        constraints.gridx = 0
        constraints.gridy = 14
        fcm_implemented.setVisible(false)
        newPanel.add(fcm_implemented, constraints)

        constraints.gridx = 0
        constraints.gridy = 15
        file6.setVisible(false)
        newPanel.add(file6, constraints)

        constraints.gridx = 0
        constraints.gridy = 16
        fcm_service_class.setVisible(false)
        newPanel.add(fcm_service_class, constraints)

        constraints.gridx = 0
        constraints.gridy = 17
        fcm_service_class_content1.setVisible(false)
        newPanel.add(fcm_service_class_content1, constraints)

        constraints.gridx = 0
        constraints.gridy = 18
        fcm_service_class_content2.setVisible(false)
        newPanel.add(fcm_service_class_content2, constraints)







        // set border for the panel
        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), Constants.FCM_PANEL
        )

        // add the panel to this frame
        add(newPanel)






    }

}