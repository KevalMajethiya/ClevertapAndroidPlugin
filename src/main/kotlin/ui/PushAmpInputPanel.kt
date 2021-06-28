package ui

import util.Constants
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

class PushAmpInputPanel: JPanel() {

    private val fileContent = ClevertapInputPanel::class.java.getResource("/icons/hint.png")
    private val icon = ImageIcon(fileContent)
    private val custRender = JLabel("Are you Custom Rendering Push Notifications :")
    var rb1 = JRadioButton("Yes")
    var rb2 = JRadioButton("No")
    private var bg = ButtonGroup()
    val receiverLabel = JLabel("FCM Receiver class :")
    val receiverClass = JTextField(30)
    val newPanel = JPanel(GridBagLayout())

    init {

        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)

        constraints.gridx = 0
        constraints.gridy = 0
        custRender.toolTipText = "Yes Or No"
        custRender.horizontalTextPosition = SwingConstants.LEFT
        custRender.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(custRender, constraints)

        constraints.gridx=1
        bg.add(rb1)
        bg.add(rb2)
        newPanel.add(rb1,constraints)
        constraints.gridy = 1
        newPanel.add(rb2,constraints)

        constraints.gridx = 0
        constraints.gridy = 2
        receiverLabel.isVisible = false
        receiverLabel.toolTipText = "Enter Receiver Class Name"
        receiverLabel.icon = icon
        receiverLabel.horizontalTextPosition = SwingConstants.LEFT
        receiverLabel.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(receiverLabel, constraints)
        constraints.gridx = 1
        receiverClass.isVisible = false
        newPanel.add(receiverClass, constraints)

        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), Constants.FCM_PANEL
        )

        add(newPanel)

    }
    override fun getPreferredSize(): Dimension {
        return Dimension(800, 200)
    }
}