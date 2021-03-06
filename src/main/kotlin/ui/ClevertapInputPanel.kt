package ui
import com.intellij.openapi.ui.ComboBox
import util.Constants.Exclude_files
import util.Constants.DEPENDENCY_VERSION_VALUE
import util.Constants.FCM_PANEL
import util.Constants.FCM_SERVICE_NAME
import util.Constants.MY_FIREBASE_MESSAGING_SERVICE
import util.Constants.NEED_INSTRUCTION
import util.Constants.PENDINGINTENT_ACTIVITY_NAME
import javax.swing.*
import javax.swing.BorderFactory
import java.awt.GridBagConstraints
import java.awt.Insets
import java.awt.GridBagLayout
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.JLabel
import java.awt.event.MouseEvent
import java.awt.event.MouseAdapter

import java.awt.Desktop

import javax.swing.ImageIcon

import java.net.URI
import javax.swing.JRadioButton


class ClevertapInputPanel : JPanel()
{

    private val fileContent = ClevertapInputPanel::class.java.getResource("/icons/hint.png")
    private val icon = ImageIcon(fileContent)

    private val labelServiceName = JLabel(FCM_SERVICE_NAME)


    private val labelPendingIntent = JLabel(PENDINGINTENT_ACTIVITY_NAME)

    private val labelContentTitle = JLabel("<html>"+"Should Google AD ID be the CleverTap ID(yes/no)?" +"<br>"+"<a href=\"https://www.w3schools.com/\">Link to documentation!</a>")

    //private val labelContentText = JLabel(CONTENT_TEXT)
    private val labelExcludefiles = JLabel("<html>$Exclude_files<br><a href=\"https://www.w3schools.com/\">Link to documentation!</a>")
    private val applicationclass = JLabel("<html>"+"Do you have Application Class:"+"<br>"+"<a href=\"https://www.w3schools.com/\">Link to documentation!</a>")
     val labelapplicationclassname = JLabel("<html>"+"Application Class Name:"+"<br>" + "<a href=\"https://www.w3schools.com/\">Link to documentation!</a>")


    //private val labelVersion = JLabel(DEPENDENCY_VERSION)
    //private val labelInstruction = JLabel(NOTES_INSTRUCTION)
     var rb1 =JRadioButton("Yes")
     var rb2 =JRadioButton("No")
     var bg= ButtonGroup()

    var rb1_labelContentText =JRadioButton("Yes")
    var rb2_labelContentText =JRadioButton("No")
    var bg_labelContentText= ButtonGroup()

    var label_region = JLabel("<html>"+"Select Your CleverTap Region:"+"<br>" + "<a href=\"https://www.w3schools.com/\">Link to documentation!</a>")



    //private val demo = JLabel("Demo")
    

    val isNeedReadMeForInstructions = JCheckBox(NEED_INSTRUCTION)

    val serviceNameTextField = JTextField(MY_FIREBASE_MESSAGING_SERVICE, 25)
    val dependencyVersionTextField = JTextField(DEPENDENCY_VERSION_VALUE, 25)
    val pendingIntentTextField = JTextField(25)
   // val contentTitleTextField = JTextField(25)
    val contentTextTextField = JTextField(25)
    val Exclude_filesTextField = JTextField(25)
    val application_classname_TextField = JTextField(25)
    var regions = arrayOf<String>("eu1","in1","sg1","sk1","us1")
    var region_value = ComboBox(regions)



    init {


        //labelPendingIntent.setToolTipText("This is a demo tooltip")
        val newPanel = JPanel(GridBagLayout())

        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)

        // add components to the panel
        constraints.gridx = 0
        constraints.gridy = 0
        //labelServiceName.setIcon(icon)


        labelServiceName.toolTipText = "CleverTap Dashboard -> Settings -> Project -> Project Id"
        labelServiceName.icon = icon
        labelServiceName.horizontalTextPosition = SwingConstants.LEFT
        labelServiceName.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(labelServiceName, constraints)


        constraints.gridx = 1
        //serviceNameTextField.setPlaceholder("CleverTap Dashboard -> Settings -> Project -> Project Id")
        newPanel.add(serviceNameTextField, constraints)
        //labelimg.setIcon(icon);
       // newPanel.add(labelimg, constraints)

        //constraints.gridx = 2
        //newPanel.add(serviceNameTextField, constraints)



        serviceNameTextField
        constraints.gridx = 0
        constraints.gridy = 1

        //labelPendingIntent.setToolTipText("<html> CleverTap Dashboard -> Settings -> Project -> Project Token <br> <a href=\"https://eu1.dashboard.clevertap.com/RZ7-Z94-K95Z/account-setup/account-details/overview/\">Visit W3Schools.com!</a>\n </html>")
        labelPendingIntent.toolTipText = "CleverTap Dashboard -> Settings -> Project -> Project Token"
        labelPendingIntent.icon = icon
        labelPendingIntent.horizontalTextPosition = SwingConstants.LEFT
        labelPendingIntent.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(labelPendingIntent, constraints)
        //labelPendingIntent.setToolTipText("This is a demo tooltip")
        constraints.gridx = 1
        newPanel.add(pendingIntentTextField, constraints)


        constraints.gridx = 0
        constraints.gridy = 2
        labelContentTitle.toolTipText = "Yes Or No"
        labelContentTitle.icon = icon
        labelContentTitle.horizontalTextPosition = SwingConstants.LEFT
        labelContentTitle.verticalTextPosition = SwingConstants.CENTER
        labelContentTitle.addMouseListener(object : MouseAdapter() {


            override fun mouseClicked(e: MouseEvent)
            {
                Desktop.getDesktop().browse(URI("https://developer.clevertap.com/docs/sdk-changes-for-gdpr-compliance"))

            }

            override fun mouseExited(e: MouseEvent)
            {

            }

            override fun mouseEntered(e: MouseEvent)
            {


            }
        })
        newPanel.add(labelContentTitle, constraints)
        constraints.gridx = 1
        bg_labelContentText.add(rb1_labelContentText)
        bg_labelContentText.add(rb2_labelContentText)
        constraints.gridx = 1
        newPanel.add(rb1_labelContentText, constraints)
        //newPanel.add(rb2_labelContentText, constraints)
        //constraints.gridx = 2
        constraints.gridy =3
        newPanel.add(rb2_labelContentText, constraints)


       // newPanel.add(contentTitleTextField, constraints)
        constraints.gridx = 0
        constraints.gridy = 4
        label_region.toolTipText = "Select Your CleverTap Region "
        label_region.icon = icon
        label_region.horizontalTextPosition = SwingConstants.LEFT
        label_region.verticalTextPosition = SwingConstants.CENTER
        label_region.addMouseListener(object : MouseAdapter() {


            override fun mouseClicked(e: MouseEvent)
            {
                Desktop.getDesktop().browse(URI("https://developer.clevertap.com/docs/idc"))

            }

            override fun mouseExited(e: MouseEvent)
            {

            }

            override fun mouseEntered(e: MouseEvent)
            {


            }
        })
        newPanel.add(label_region, constraints)
        constraints.gridx = 1
        newPanel.add(region_value, constraints)




        constraints.gridx = 0
        constraints.gridy = 5
        labelExcludefiles.toolTipText = "<html>" + " To exclude one or more activities from showing Clevertap's INAPP notification."+ "<br>"+"Mention the name of activities with commas as a separator as shown below. " +"<br>" + "(Example :YourSplashActivity1, YourSplashActivity2)" + "</html>"
        labelExcludefiles.icon = icon
        labelExcludefiles.horizontalTextPosition = SwingConstants.LEFT
        labelExcludefiles.verticalTextPosition = SwingConstants.TOP
        labelExcludefiles.addMouseListener(object : MouseAdapter() {


            override fun mouseClicked(e: MouseEvent)
            {
                Desktop.getDesktop().browse(URI("https://developer.clevertap.com/docs/android#section-in-app-notifications"))

            }

            override fun mouseExited(e: MouseEvent)
            {

            }

            override fun mouseEntered(e: MouseEvent)
            {

            }
        })
        newPanel.add(labelExcludefiles, constraints)
        constraints.gridx = 1
        //newPanel.add(contentTextTextField, constraints)
        newPanel.add(Exclude_filesTextField, constraints)
        
        /*constraints.gridx = 0
        constraints.gridy = 4
        demo.setToolTipText("This is a demo tooltip")
        newPanel.add(demo)*/


        constraints.gridx = 0
        constraints.gridy = 6
        applicationclass.toolTipText = "Yes Or No"
        applicationclass.icon = icon
        applicationclass.horizontalTextPosition = SwingConstants.LEFT
        applicationclass.verticalTextPosition = SwingConstants.TOP
        applicationclass.addMouseListener(object : MouseAdapter() {


            override fun mouseClicked(e: MouseEvent)
            {
                Desktop.getDesktop().browse(URI("https://developer.clevertap.com/docs/android#section-changing-account-credentials"))

            }

            override fun mouseExited(e: MouseEvent)
            {

            }

            override fun mouseEntered(e: MouseEvent)
            {

            }
        })
        newPanel.add(applicationclass, constraints)
        bg.add(rb1)
        bg.add(rb2)
        constraints.gridx = 1
        newPanel.add(rb1, constraints)
        //constraints.gridx = 2
        constraints.gridy =7
        newPanel.add(rb2, constraints)

        constraints.gridx = 0
        constraints.gridy = 8
        labelapplicationclassname.toolTipText = "<html>" +"Enter Application Class name" +"<br>"+ "(Example :MyApplcationClass)"+ "</html>"
        labelapplicationclassname.icon = icon
        labelapplicationclassname.horizontalTextPosition = SwingConstants.LEFT
        labelapplicationclassname.verticalTextPosition = SwingConstants.TOP
        labelapplicationclassname.isVisible = false
        labelapplicationclassname.addMouseListener(object : MouseAdapter() {


            override fun mouseClicked(e: MouseEvent)
            {
                Desktop.getDesktop().browse(URI("https://developer.clevertap.com/docs/android-quickstart-guide"))

            }

            override fun mouseExited(e: MouseEvent)
            {

            }

            override fun mouseEntered(e: MouseEvent)
            {

            }
        })
        newPanel.add(labelapplicationclassname, constraints)

        constraints.gridx = 1
        application_classname_TextField.isVisible = false
       // constraints.gridy = 6
       // constraints.anchor = GridBagConstraints.CENTER
        newPanel.add(application_classname_TextField, constraints)

        // set border for the panel
        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), FCM_PANEL
        )

        // add the panel to this frame
        add(newPanel)
    }
}
