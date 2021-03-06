package ui

import util.Constants
import util.Constants.XIOMI_APPID
import util.Constants.XIOMI_APP_KEY
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.net.URI
import javax.swing.*

class XiomiInputPanel:JPanel() {

//    val fileContent = ClevertapInputPanel::class.java.getResource("/icons/hint.png")
//    val icon = ImageIcon(fileContent)

    private val Xiomi_App_ID = JLabel(XIOMI_APPID)
    private val Xiomi_App_Key = JLabel(XIOMI_APP_KEY)
    private val LinkDoc = JLabel("<html> <a href=\"https://www.w3schools.com/\">Link to documentation !</a>")
//    var x1 = JFrame()
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
        Xiomi_App_ID.horizontalTextPosition = SwingConstants.LEFT
        Xiomi_App_ID.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(Xiomi_App_ID, constraints)

        constraints.gridx = 1
        newPanel.add(appId,constraints)

        constraints.gridx =0
        constraints.gridy =1
        Xiomi_App_Key.horizontalTextPosition = SwingConstants.LEFT
        Xiomi_App_Key.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(Xiomi_App_Key,constraints)

        constraints.gridx = 1
        newPanel.add(appKey,constraints)

        constraints.gridx = 0
        constraints.gridy = 2
        LinkDoc.horizontalTextPosition = SwingConstants.LEFT
        LinkDoc.verticalTextPosition = SwingConstants.CENTER
        LinkDoc.toolTipText = "https://developer.clevertap.com/docs/xiaomi-push-notifications"
        LinkDoc.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent)
            {
                Desktop.getDesktop().browse(URI("https://developer.clevertap.com/docs/xiaomi-push-notifications"))

            }
            override fun mouseExited(e: MouseEvent)
            {

            }

            override fun mouseEntered(e: MouseEvent)
            {


            }
        })
        newPanel.add(LinkDoc,constraints)

        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), Constants.FCM_PANEL
        )

        add(newPanel)
    }

}