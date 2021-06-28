package ui

import util.Constants
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

class Geofence_Input_Approval : JPanel()
{

    var file1 = JLabel("AndroidManifest file:")
    var manifest = JLabel("Following code snippet would be added in the manifest file:")
    var manifest_content= JLabel()

    private var file2 = JLabel("Launching_Activity")
    private var launchingact = JLabel("Following code snippet would be added in the Launching_Activity file:")
    var launchingact_content= JLabel()

    private var file3 = JLabel("Build.gadle(app)")
    private var app_gradle = JLabel("Following code snippet would be added in your app's build.gradle file:")
    private var app_gradle_content= JLabel()



    init
    {

        val newPanel = JPanel(GridBagLayout())
        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)

        manifest_content.text = "<html>"+"Permissions :"+"<br>"+
                "&lt uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" &gt"+"<br>"+
                "&lt uses-permission android:name=\"android.permission.ACCESS_BACKGROUND_LOCATION\" &gt"+"<br>"+
                "&lt uses-permission android:name=\"android.permission.WAKE_LOCK\" &gt"+"<br>"+
                "&lt uses-permission android:name=\"android.permission.RECEIVE_BOOT_COMPLETED\" &gt"+"<br>"+
                "</html>"

        app_gradle_content.text = "<html>"+"implementation 'com.clevertap.android:clevertap-geofence-sdk:1.0.2'"+"<br>"+
                "implementation 'com.clevertap.android:clevertap-android-sdk:4.1.0'"+"<br>"+
                "implementation 'com.google.android.gms:play-services-location:17.0.0'"+"<br>"+
                "implementation 'androidx.work:work-runtime:2.3.4'"+"<br>"+
                "implementation 'androidx.concurrent:concurrent-futures:1.0.0'"+"<br>"+
                "<br>"+
                "</html>"


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
        newPanel.add(file3, constraints)

        constraints.gridx = 0
        constraints.gridy = 8
        newPanel.add(app_gradle, constraints)

        constraints.gridx = 0
        constraints.gridy = 9
        newPanel.add(app_gradle_content, constraints)

        // set border for the panel
        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), Constants.FCM_PANEL
        )

        // add the panel to this frame
        add(newPanel)






    }

}