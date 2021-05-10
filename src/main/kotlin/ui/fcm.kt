package ui

import util.Constants
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

class fcm : JPanel()
{
    var l1 = JLabel("Step 1:")

    var l2 = JLabel("Add following code snippet in the onMessageReceived method:")
    var l3 = JLabel()
    var l4 = JLabel("Step 2:")
    var l5= JLabel("<html>"+"Pass the fcm token to clevertap by adding the below code in the"+"<br>"+
            "onNewToken method of the firebase messaging class.:"+"<br>"+"</html>")
    var l6 = JLabel()

    var b= JButton("Copy")
    var b1= JButton("Copy")

    init
    {

        val newPanel = JPanel(GridBagLayout())
        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)

//        l3.setText("<html>"+"try {"+"<br>"+
//                "if (message.getData().size() > 0) {"+"<br>"+
//                "                Bundle extras = new Bundle();"+"<br>"+
//                "                for (Map.Entry<String, String> entry : message.getData().entrySet()) {"+"<br>"+
//                "                    extras.putString(entry.getKey(), entry.getValue());"+"<br>"+
//                "}"+"<br>"+
//                "                NotificationInfo info = CleverTapAPI.getNotificationInfo(extras);"+"<br>"+
//                "                if (info.fromCleverTap) {"+"<br>"+
//                "                    CleverTapAPI.createNotification(getApplicationContext(), extras);"+"<br>"+
//                "                } else {"+"<br>"+
//                "                    // not from CleverTap handle yourself or pass to another provider"+"<br>"+
//                "}"+"<br>"+
//                "}"+"<br>"+
//                "        } catch (Throwable t) {"+"<br>"+
//                "           Log.d(\"MYFCMLIST\", \"Error parsing FCM message\", t);"+"<br>"+
//                "}"+
//                "</html>")
//        l6.setText("<html>"+"@Override"+"<br>"+"public void onNewToken(@NonNull String s) {"+"<br>"+"    super.onNewToken(s);"+"<br>"+"\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());"+"<br>"+"clevertapDefaultInstance.pushFcmRegistrationId(s,true);"+"<br>"+"}"+"<br>"+"</html>")


        constraints.gridx = 0
        constraints.gridy = 0
        newPanel.add(l1, constraints)


        constraints.gridx = 0
        constraints.gridy = 1
        newPanel.add(l2, constraints)

        constraints.gridx = 0
        constraints.gridy = 2
        newPanel.add(l3, constraints)

        constraints.gridx = 0
        constraints.gridy = 3
        newPanel.add(b, constraints)

        constraints.gridx = 0
        constraints.gridy = 4
        newPanel.add(l4, constraints)

        constraints.gridx = 0
        constraints.gridy = 5
        newPanel.add(l5, constraints)

        constraints.gridx = 0
        constraints.gridy = 6
        newPanel.add(l6, constraints)

        constraints.gridx = 0
        constraints.gridy = 7
        newPanel.add(b1, constraints)

        // set border for the panel
        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), Constants.FCM_PANEL
        )

        // add the panel to this frame
        add(newPanel)






    }

}