package ui

import util.Constants
import util.Constants.XIOMI_APPID
import util.Constants.XIOMI_APP_KEY
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

class XiomiInputPanel:JPanel() {

    val fileContent = ClevertapInputPanel::class.java.getResource("/icons/hint.png")
    val icon = ImageIcon(fileContent);

    val Xiomi_App_ID = JLabel(XIOMI_APPID)
    val Xiomi_App_Key = JLabel(XIOMI_APP_KEY)
    var x1 = JFrame()
    val appId = JTextField(25)
    val appKey = JTextField(25)
    val newPanel = JPanel(GridBagLayout())

    init {




        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)

        constraints.gridx = 0
        constraints.gridy = 0
        Xiomi_App_ID.setToolTipText("Yes Or No")
        Xiomi_App_ID.setIcon(icon)
        Xiomi_App_ID.setHorizontalTextPosition( SwingConstants.LEFT)
        Xiomi_App_ID.setVerticalTextPosition( SwingConstants.CENTER )
        newPanel.add(Xiomi_App_ID, constraints)

        constraints.gridx = 1
        newPanel.add(appId,constraints)

        constraints.gridx =0
        constraints.gridy =1
        Xiomi_App_Key.setToolTipText("Yes Or No")
        Xiomi_App_Key.setIcon(icon)
        Xiomi_App_Key.setHorizontalTextPosition( SwingConstants.LEFT)
        Xiomi_App_Key.setVerticalTextPosition( SwingConstants.CENTER )
        newPanel.add(Xiomi_App_Key,constraints)

        constraints.gridx = 1
        newPanel.add(appKey,constraints)

        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), Constants.FCM_PANEL
        )

        add(newPanel)
    }

//    override fun getPreferredSize(): Dimension? {
//        return Dimension(800, 400)
//    }
}