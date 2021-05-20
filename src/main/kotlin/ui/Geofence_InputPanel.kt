package ui
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

    var logs = arrayOf<String>("DEBUG", "INFO", "VERBOSE", "OFF")
    var log_values = JComboBox(logs)

    var location_accuracy = arrayOf<String>("HIGH", "MEDIUM", "LOW")
    var location_accuracy_values = JComboBox(location_accuracy)

    var location_fetch_mode = arrayOf<String>("FETCH_LAST_LOCATION_PERIODIC", "FETCH_CURRENT_LOCATION_PERIODIC")
    var location_fetch_mode_values = JComboBox(location_fetch_mode)


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
        label_loglevel.setToolTipText("Set the appropriate log level.")
        label_loglevel.setIcon(icon)
        label_loglevel.setHorizontalTextPosition(SwingConstants.LEFT)
        label_loglevel.setVerticalTextPosition(SwingConstants.TOP)
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
        label_location_accuracy.setToolTipText("Set location accuracy.")
        label_location_accuracy.setIcon(icon)
        label_location_accuracy.setHorizontalTextPosition(SwingConstants.LEFT)
        label_location_accuracy.setVerticalTextPosition(SwingConstants.TOP)

        newPanel.add(label_location_accuracy, constraints)

        constraints.gridx = 1
        newPanel.add( location_accuracy_values, constraints)


        constraints.gridx = 0
        constraints.gridy = 2
        label_location_fetch_mode.setToolTipText("Select the appropriate location fetch mode.")
        label_location_fetch_mode.setIcon(icon)
        label_location_fetch_mode.setHorizontalTextPosition(SwingConstants.LEFT)
        label_location_fetch_mode.setVerticalTextPosition(SwingConstants.TOP)

        newPanel.add(label_location_fetch_mode, constraints)

        constraints.gridx = 1
        newPanel.add(location_fetch_mode_values, constraints)




        constraints.gridx = 0
        constraints.gridy = 3
        label_Geofence_Monitoring_count.setIcon(icon)


        label_Geofence_Monitoring_count.setToolTipText("<html>"+"Enter the Geofence Geofence Monitoring Count."+"<br>"+"Default Value is 50 "+"<html>")
        label_Geofence_Monitoring_count.setIcon(icon)
        label_Geofence_Monitoring_count.setHorizontalTextPosition(SwingConstants.LEFT)
        label_Geofence_Monitoring_count.setVerticalTextPosition(SwingConstants.CENTER)
        newPanel.add(label_Geofence_Monitoring_count, constraints)

        constraints.gridx = 1
        newPanel.add(Geofence_Monitoring_count_Textfield, constraints)




        constraints.gridx = 0
        constraints.gridy = 4
        label_interval.setToolTipText("<html>"+"Enter the interval in milliseconds."+"<br>"+"Default Value is 30 minutes "+"<br>"+"Values less than 30 minutes will be ignored by SDK."+"<html>")
        label_interval.setIcon(icon)
        label_interval.setHorizontalTextPosition(SwingConstants.LEFT)
        label_interval.setVerticalTextPosition(SwingConstants.CENTER)
        newPanel.add(label_interval, constraints)
        //labelPendingIntent.setToolTipText("This is a demo tooltip")
        constraints.gridx = 1
        newPanel.add(interval_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 5
        label_fastest_interval.setToolTipText("<html>"+"Enter the Fastest interval in milliseconds."+"<br>"+"Default Value is 30 minutes "+"<br>"+"Values less than 30 minutes will be ignored by SDK."+"<html>")
        label_fastest_interval.setIcon(icon)
        label_fastest_interval.setHorizontalTextPosition(SwingConstants.LEFT)
        label_fastest_interval.setVerticalTextPosition(SwingConstants.CENTER)
        newPanel.add(label_fastest_interval, constraints)
        constraints.gridx = 1
        newPanel.add(fastest_interval_TextField, constraints)



        constraints.gridx = 0
        constraints.gridy = 6
        label_displacement.setToolTipText("<html>"+"Enter the Smallest Dispalcement Distance in metres."+"<br>"+"Default Value is 200 metres "+"<br>"+"Values less than 200 meters will be ignored by SDK."+"<html>")
        label_displacement.setIcon(icon)
        label_displacement.setHorizontalTextPosition(SwingConstants.LEFT)
        label_displacement.setVerticalTextPosition(SwingConstants.CENTER)
        newPanel.add(label_displacement, constraints)
        constraints.gridx = 1
        newPanel.add(displacement_TextField, constraints)




        constraints.gridx = 0
        constraints.gridy = 7
        label_geofence_notification_responsiveness.setToolTipText("Enter the value for geofence notification responsiveness in milliseconds.")
        label_geofence_notification_responsiveness.setIcon(icon)
        label_geofence_notification_responsiveness.setHorizontalTextPosition(SwingConstants.LEFT)
        label_geofence_notification_responsiveness.setVerticalTextPosition(SwingConstants.TOP)
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
        label_bglocation.setToolTipText("Yes or No")
        label_bglocation.setIcon(icon)
        label_bglocation.setHorizontalTextPosition(SwingConstants.LEFT)
        label_bglocation.setVerticalTextPosition(SwingConstants.TOP)

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
