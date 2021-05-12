package ui

import util.Constants
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.JPanel

class XiomiApproval:JPanel() {

    var file1 = JLabel("<Html>"+"<b>"+"AndroidManifest file:"+"</b>"+"</Html>")
    var manifest = JLabel("Following code snippet would be added in the manifest file:")
    var manifest_content= JLabel()

    var file2 = JLabel("<Html>"+"<b>"+"Strings.xml"+"</b>"+"</Html>")
    var strings_xml = JLabel("Following code snippet would be added in your res/strings.xml file:")
    var strings_xml_content= JLabel()

    var file3 = JLabel("<Html>"+"<b>"+"Build.gadle(app)"+"</b>"+"</Html>")
    var app_gradle = JLabel("Following code snippet would be added in your Build.gradle(app) file:")
    var app_gradle_content= JLabel()

    init {
        val newPanel = JPanel(GridBagLayout())
        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)

        app_gradle_content.setText("<HTML>"+"implementation 'com.clevertap.android:clevertap-xiaomi-sdk:1.0.2'"+"<br>"+"</html>")

        constraints.gridx =0
        constraints.gridy = 0
        newPanel.add(file1,constraints)

        constraints.gridy = 1
        newPanel.add(manifest,constraints)

        constraints.gridy = 2
        newPanel.add(manifest_content,constraints)

        constraints.gridy = 3
        newPanel.add(file2,constraints)

        constraints.gridy = 4
        newPanel.add(strings_xml,constraints)

        constraints.gridy = 5
        newPanel.add(strings_xml_content,constraints)

        constraints.gridy = 6
        newPanel.add(file3,constraints)

        constraints.gridy = 7
        newPanel.add(app_gradle,constraints)

        constraints.gridy = 8
        newPanel.add(app_gradle_content,constraints)


        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), Constants.FCM_PANEL
        )
        add(newPanel)
    }
}