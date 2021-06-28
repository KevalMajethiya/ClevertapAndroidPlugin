package ui

import java.awt.*


import javax.swing.JLabel

import javax.swing.JPanel

class auditreport: JPanel()
{
    val task = JLabel("Generating Audit File :")
    //val l = JLabel("CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());");
    //val copyToClipboardButton =  Button("Copy to clipboard");

    init
    {
        val newPanel = JPanel(GridBagLayout())
        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)

        constraints.gridx = 0
        constraints.gridy = 0
        newPanel.add(task, constraints)
//        constraints.gridx = 0
//        constraints.gridy = 1
//        newPanel.add(l, constraints)
//
//        constraints.gridx = 0
//        constraints.gridy = 2
//        newPanel.add(copyToClipboardButton, constraints)


        // add the panel to this frame
        add(newPanel)



    }

}