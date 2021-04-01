package ui
import util.Constants
import java.awt.*

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory
import javax.swing.JPanel

class demo: JPanel()
{
    val task = JLabel("Paste the following code in onCreate method :");
    val l = JLabel("CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());");
    val copyToClipboardButton =  Button("Copy to clipboard");

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
        constraints.gridx = 0
        constraints.gridy = 1
        newPanel.add(l, constraints)

        constraints.gridx = 0
        constraints.gridy = 2
        newPanel.add(copyToClipboardButton, constraints)


        // add the panel to this frame
        add(newPanel)



    }

}