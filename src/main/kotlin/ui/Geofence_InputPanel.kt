package ui

import util.Constants.FCM_PANEL
import java.awt.Desktop
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.net.URI
import javax.swing.*
import com.intellij.openapi.ui.ComboBox


class Geofence_InputPanel : JPanel() {
    val fileContent = ClevertapInputPanel::class.java.getResource("/icons/hint.png")
    val icon = ImageIcon(fileContent)

    private val label_Geofence_Monitoring_count = JLabel("Enter the Geofence Monitoring Count")
    private val label_interval = JLabel("Enter Interval time")
    private val label_fastest_interval = JLabel("Enter Fastest Interval time")
    private val label_displacement = JLabel("Enter smallest Displacement Distance")
    private val label_geofence_notification_responsiveness = JLabel("Enter Geofence Notification Responsiveness time")

    var label_loglevel = JLabel( "Select LogLevel:")
    var label_location_accuracy = JLabel("Select Location Accuracy:")
    var label_location_fetch_mode = JLabel("Select Location FetchMode:")

    var label_bglocation =
        JLabel("Enable BackGround Location:")
    var enable_bglocation_y = JRadioButton("Yes")
    var enable_bglocation_n = JRadioButton("No")
    var bg = ButtonGroup()


    //val labelapplicationclassname = JLabel("<html>"+"Application Class Name:"+"<br>" + "<a href=\"https://www.w3schools.com/\">Link to documentation!</a>")


    val Geofence_Monitoring_count_Textfield = JTextField(20)
    val interval_TextField = JTextField(20)
    val fastest_interval_TextField = JTextField(20)
    val displacement_TextField = JTextField(20)
    val geofence_notification_responsiveness_TextField = JTextField(25)

    var logs = arrayOf("DEBUG", "INFO", "VERBOSE", "OFF")
    var log_values = ComboBox(logs)

    var location_accuracy = arrayOf("HIGH", "MEDIUM", "LOW")
    var location_accuracy_values = ComboBox(location_accuracy)

    var location_fetch_mode = arrayOf("FETCH_LAST_LOCATION_PERIODIC", "FETCH_CURRENT_LOCATION_PERIODIC")
    var location_fetch_mode_values = ComboBox(location_fetch_mode)


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
        label_loglevel.toolTipText = "Set the appropriate log level."
        label_loglevel.icon = icon
        label_loglevel.horizontalTextPosition = SwingConstants.LEFT
        label_loglevel.verticalTextPosition = SwingConstants.TOP
//        label_loglevel.addMouseListener(object : MouseAdapter() {
//
//
//            override fun mouseClicked(e: MouseEvent) {
//                Desktop.getDesktop().browse(URI("https://developer.clevertap.com/docs/android-quickstart-guide"))
//
//            }
//
//            override fun mouseExited(e: MouseEvent) {
//
//            }
//
//            override fun mouseEntered(e: MouseEvent) {
//
//            }
//        })
        newPanel.add(label_loglevel, constraints)

        constraints.gridx = 1
        newPanel.add(log_values, constraints)


        constraints.gridx = 0
        constraints.gridy = 1
        label_location_accuracy.toolTipText = "Set location accuracy."
        label_location_accuracy.icon = icon
        label_location_accuracy.horizontalTextPosition = SwingConstants.LEFT
        label_location_accuracy.verticalTextPosition = SwingConstants.TOP

        newPanel.add(label_location_accuracy, constraints)

        constraints.gridx = 1
        newPanel.add( location_accuracy_values, constraints)


        constraints.gridx = 0
        constraints.gridy = 2
        label_location_fetch_mode.toolTipText = "Select the appropriate location fetch mode."
        label_location_fetch_mode.icon = icon
        label_location_fetch_mode.horizontalTextPosition = SwingConstants.LEFT
        label_location_fetch_mode.verticalTextPosition = SwingConstants.TOP

        newPanel.add(label_location_fetch_mode, constraints)

        constraints.gridx = 1
        newPanel.add(location_fetch_mode_values, constraints)




        constraints.gridx = 0
        constraints.gridy = 3
        label_Geofence_Monitoring_count.icon = icon


        label_Geofence_Monitoring_count.toolTipText = "<html>"+"Enter the Geofence Geofence Monitoring Count."+"<br>"+"Default Value is 50 "+"<html>"
        label_Geofence_Monitoring_count.icon = icon
        label_Geofence_Monitoring_count.horizontalTextPosition = SwingConstants.LEFT
        label_Geofence_Monitoring_count.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(label_Geofence_Monitoring_count, constraints)

        constraints.gridx = 1
        newPanel.add(Geofence_Monitoring_count_Textfield, constraints)




        constraints.gridx = 0
        constraints.gridy = 4
        label_interval.toolTipText = "<html>"+"Enter the interval in milliseconds."+"<br>"+"Default Value is 30 minutes "+"<br>"+"Values less than 30 minutes will be ignored by SDK."+"<html>"
        label_interval.icon = icon
        label_interval.horizontalTextPosition = SwingConstants.LEFT
        label_interval.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(label_interval, constraints)
        //labelPendingIntent.setToolTipText("This is a demo tooltip")
        constraints.gridx = 1
        newPanel.add(interval_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 5
        label_fastest_interval.toolTipText = "<html>"+"Enter the Fastest interval in milliseconds."+"<br>"+"Default Value is 30 minutes "+"<br>"+"Values less than 30 minutes will be ignored by SDK."+"<html>"
        label_fastest_interval.icon = icon
        label_fastest_interval.horizontalTextPosition = SwingConstants.LEFT
        label_fastest_interval.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(label_fastest_interval, constraints)
        constraints.gridx = 1
        newPanel.add(fastest_interval_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 6
        label_displacement.toolTipText = "<html>"+"Enter the Smallest Dispalcement Distance in metres."+"<br>"+"Default Value is 200 metres "+"<br>"+"Values less than 200 meters will be ignored by SDK."+"<html>"
        label_displacement.icon = icon
        label_displacement.horizontalTextPosition = SwingConstants.LEFT
        label_displacement.verticalTextPosition = SwingConstants.CENTER
        newPanel.add(label_displacement, constraints)
        constraints.gridx = 1
        newPanel.add(displacement_TextField, constraints)




        constraints.gridx = 0
        constraints.gridy = 7
        label_geofence_notification_responsiveness.toolTipText = "Enter the value for geofence notification responsiveness in milliseconds."
        label_geofence_notification_responsiveness.icon = icon
        label_geofence_notification_responsiveness.horizontalTextPosition = SwingConstants.LEFT
        label_geofence_notification_responsiveness.verticalTextPosition = SwingConstants.TOP
        label_geofence_notification_responsiveness.addMouseListener(object : MouseAdapter() {


            override fun mouseClicked(e: MouseEvent) {
                Desktop.getDesktop()
                    .browse(URI("https://developer.clevertap.com/docs/android#section-in-app-notifications"))

            }

            override fun mouseExited(e: MouseEvent) {

            }

            override fun mouseEntered(e: MouseEvent) {

            }
        })
        newPanel.add(label_geofence_notification_responsiveness, constraints)
        constraints.gridx = 1
        newPanel.add(geofence_notification_responsiveness_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 8
        label_bglocation.toolTipText = "Yes or No"
        label_bglocation.icon = icon
        label_bglocation.horizontalTextPosition = SwingConstants.LEFT
        label_bglocation.verticalTextPosition = SwingConstants.TOP

        newPanel.add(label_bglocation, constraints)
        bg.add(enable_bglocation_y)
        bg.add(enable_bglocation_n)
        constraints.gridx = 1
        newPanel.add(enable_bglocation_y, constraints)
        //constraints.gridx = 2
        constraints.gridy = 9
        newPanel.add(enable_bglocation_n, constraints)







        // set border for the panel
        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), FCM_PANEL
        )

        // add the panel to this frame
        add(newPanel)
    }
}
