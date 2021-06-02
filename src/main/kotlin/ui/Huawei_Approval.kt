package ui

import util.Constants
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

class Huawei_Approval() : JPanel()
{

    var file1 = JLabel("Build.gadle(Project)")
    var project_gradle = JLabel("Following dependency would be added to your Project-level build.gradle file:")
    var project_gradle_content = JLabel()



    var file2 = JLabel("Build.gadle(app)")
    var app_gradle = JLabel("Following code snippet would be added in your app's build.gradle file:")
    var app_gradle_content= JLabel()




    init
    {

        val newPanel = JPanel(GridBagLayout())
        val inset = 6
        val constraints = GridBagConstraints()
        constraints.anchor = GridBagConstraints.WEST
        constraints.insets = Insets(inset, inset, inset, inset)


        project_gradle_content.setText("<html>"+"buildscript {"+"<br>"+
                "    repositories { "+"<br>"+
                ""+"<br>"+
                "        maven {url 'http://developer.huawei.com/repo/'}"+"<br>"+
                "        } "+"<br>"+
                "} "+"<br>"+
                "dependencies {"+"<br>"+
                "        classpath \"com.huawei.agconnect:agcp:1.4.1.300\" "+"<br>"+
                " }"+ "<br>"+
                "all projects {"+"<br>"+
                "    repositories { "+"<br>"+
                ""+"<br>"+
                "        maven {url 'http://developer.huawei.com/repo/'}"+"<br>"+
                "        } "+"<br>"+
                "} "+"<br>"+
                "</html>")

        app_gradle_content.setText("<html>"+"implementation \"com.clevertap.android:clevertap-hms-sdk:1.0.1\""+"<br>"+
                "implementation \"com.huawei.hms:push:5.1.1.301\"\n"+"<br>"+
                "<br>"+
                "apply plugin: 'com.huawei.agconnect'\n'"+"<br>"+"<br>"+
                "</html>")



        constraints.gridx = 0
        constraints.gridy = 0
        newPanel.add(file1, constraints)



        constraints.gridx = 0
        constraints.gridy = 1
        newPanel.add(project_gradle, constraints)

        constraints.gridx = 0
        constraints.gridy = 2
        newPanel.add(project_gradle_content, constraints)

        constraints.gridx = 0
        constraints.gridy = 3
        newPanel.add(file2, constraints)

        constraints.gridx = 0
        constraints.gridy = 4
        newPanel.add(app_gradle, constraints)

        constraints.gridx = 0
        constraints.gridy = 5
        newPanel.add(app_gradle_content, constraints)

       
        // set border for the panel
        newPanel.border = BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), Constants.FCM_PANEL
        )

        // add the panel to this frame
        add(newPanel)






    }

}