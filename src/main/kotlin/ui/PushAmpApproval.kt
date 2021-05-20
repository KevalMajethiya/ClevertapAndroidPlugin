package ui

import util.Constants
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.JPanel

class PushAmpApproval(className:String,lang : String,appclassName:String): JPanel() {

    private var recieverClass = className
    private var Lang = lang
    private var appClassName = appclassName
    var file1 = JLabel("<Html>"+"<b>"+"AndroidManifest file:"+"</b>"+"</Html>")
    var manifest = JLabel("Following code snippet would be added in the manifest file:")
    var manifest_content= JLabel()

    var file2 =JLabel()
    var line1 = JLabel()
    var receiver_Class = JLabel()

    var file3 = JLabel()
    var line2 = JLabel()
    var appclass_content =JLabel()

    init{
        val newPanel = JPanel(GridBagLayout())
        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)

        constraints.gridx =0
        constraints.gridy = 0
        file1.setVisible(false)
        newPanel.add(file1,constraints)

        constraints.gridy = 1
        manifest.setVisible(false)
        newPanel.add(manifest,constraints)

        constraints.gridy = 2
        manifest_content.setVisible(false)
        newPanel.add(manifest_content,constraints)

        constraints.gridx =0


        if (lang=="java"){

            constraints.gridy = 0
            file2.setVisible(false)
            file2.setText("<Html><b>$recieverClass.java : </b></Html>")
            newPanel.add(file2,constraints)

            constraints.gridy = 1
            line1.setText("Following code snippet would be added in the $recieverClass.java file:")
            line1.setVisible(false)
            newPanel.add(line1,constraints)

            constraints.gridy = 3
            file3.setText("<Html><b>$appClassName.java : </b></Html>")
            file3.setVisible(false)
            newPanel.add(file3,constraints)

            constraints.gridy = 4
            line2.setText("Following code snippet would be added in the $appClassName.java file:")
            line2.setVisible(false)
            newPanel.add(line2,constraints)
        }
        if(lang=="kotlin"){

            constraints.gridy = 0
            file2.setVisible(false)
            file2.setText("<Html><b>$recieverClass.kt : </b></Html>")
            newPanel.add(file2,constraints)

            constraints.gridy = 1
            line1.setText("Following code snippet would be added in the $recieverClass.kt file:")
            line1.setVisible(false)
            newPanel.add(line1,constraints)

            constraints.gridy = 3
            file3.setText("<Html><b>$appClassName.kt : </b></Html>")
            file3.setVisible(false)
            newPanel.add(file3,constraints)

            constraints.gridy = 4
            line2.setText("Following code snippet would be added in the $appClassName.kt file:")
            line2.setVisible(false)
            newPanel.add(line2,constraints)
        }

        constraints.gridy = 2
        receiver_Class.setVisible(false)
        newPanel.add(receiver_Class,constraints)

        constraints.gridy = 5
        appclass_content.setVisible(false)
        newPanel.add(appclass_content,constraints)





        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), Constants.FCM_PANEL
        )
        add(newPanel)

    }

}