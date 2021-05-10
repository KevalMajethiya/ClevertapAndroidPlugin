package ui

import action.Demo
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.DialogWrapper
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import managers.PushNotificationManager
import util.Constants
import util.Methods
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.awt.event.ActionEvent;
import javax.swing.*
import action.*
import java.awt.Panel


class NewScreenDialog(var event: AnActionEvent) : DialogWrapper(true), NewScreenView {

    private val panelForFCM = FCMInputPanel()
    //private val panelFord=d()

    private val presenter: NewScreenPresenter
    private var packageName = ""
    private var moduleName = ""

    init {
        val currentPath = event.getData(DataKeys.VIRTUAL_FILE)?.let {
            val module = ModuleUtil.findModuleForFile(it, event.project!!)?.name ?: Constants.DEFAULT_MODULE_NAME
            CurrentPathfcm(it.path, it.isDirectory, module)
        }
        val projectStructure = ProjectStructureImpl(event.project!!)
        val sourceRootRepository = SourceRootRepositoryImpl(projectStructure)
        val fileCreator = FileCreatorImpl(sourceRootRepository)
        val packageExtractor = PackageExtractorImpl_FCM(currentPath, sourceRootRepository)
        val writeActionDispatcher = WriteActionDispatcherImpl()
        val moduleRepository = ModuleRepositoryImpl(projectStructure)
        presenter = NewScreenPresenter(
            this,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath
        )
        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME
        panelForFCM.rb1_fcm.addActionListener(){
            if (panelForFCM.rb1_fcm.isSelected()) {
               // var ans=panelForFCM.rb1_fcm.isSelected

                panelForFCM.labelServiceName.isVisible = false
                panelForFCM.serviceNameTextField.isVisible = false

                panelForFCM.fcm_sender_id.isVisible = false
                panelForFCM.fcm_sender_id_TextField.isVisible = false

                panelForFCM.labelVersion.isVisible = false
                panelForFCM.dependencyVersionTextField.isVisible = false

                panelForFCM.labeladdgoogle_service_file.isVisible = false



                panelForFCM.buttontoaddgservicefile.isVisible = false

                panelForFCM.labelContentTitle.isVisible=true
                panelForFCM.contentTitleTextField.isVisible=true


                //d()
                //panelForFCM.isVisible=false
                //fcm1(event).show()
                event.project?.let{
                    project->
                    fcm1(event,project).show()
                }
//                d().f.isVisible=true
//                d().f.toFront()
//                d().f.setAlwaysOnTop(true)



                //panelForFCM.toBack()

//                JOptionPane.showOptionDialog(null, "Add the below code In the oncreate method of your launching activity:\n\n String fcmRegId = FirebaseInstanceId.getInstance().getToken();\n" +
//                        "clevertapDefaultInstance.pushFcmRegistrationId(fcmRegId,true);", "Info",
//                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,null,null)
               //JoptionPaneCopy()
                //dispose()
                //panelForFCM.add(panelFord.f).isVisible=true
                //panelForFCM.hide()
                //if(panelFord.f.isVisible==false) {
                    //dispose()
                 //panelFord.f.isVisible = true
                   // d()

               // }
               // panelForFCM.isVisible=true

            }

       }



        panelForFCM.rb2_fcm.addActionListener(){
             if (panelForFCM.rb2_fcm.isSelected()) {
                // d().f.isVisible=false
                //panelFord.f.isVisible = false
                panelForFCM.labelServiceName.isVisible = true
                panelForFCM.serviceNameTextField.isVisible = true

                panelForFCM.fcm_sender_id.isVisible = true
                panelForFCM.fcm_sender_id_TextField.isVisible = true

                panelForFCM.labelVersion.isVisible = true
                panelForFCM.dependencyVersionTextField.isVisible = true

                panelForFCM.labeladdgoogle_service_file.isVisible = true
                panelForFCM.buttontoaddgservicefile.isVisible = true

                 panelForFCM.label_file_status.isVisible = true

                 panelForFCM.labelContentTitle.isVisible=true
                 panelForFCM.contentTitleTextField.isVisible=true

            }

        }

        panelForFCM.buttontoaddgservicefile.addActionListener()
        {
           // d().f.isVisible=false
            val fileChooser = JFileChooser()
            val option = fileChooser.showSaveDialog(null)
            if (option == JFileChooser.APPROVE_OPTION) {
                val file = fileChooser.selectedFile
                val fname: String
                fname = file.name
                val fn = "google-services.json"
                // String fnn="google-services.json";
                // String fnn="google-services.json";

                //label.setText("File Saved as: " + file.getName());
                val project = event.getProject()
                
                val sourcePath = project?.getBasePath()
                // panelForFCM.label.setText(sourcePath)
                //label.text = "File Saved as: " + file.path
                val toFile = sourcePath + "/app/" + file.name
                val source = Paths.get(file.path)
                val target = Paths.get(toFile)
                // val p = System.getProperty("user.dir")
                //
                // val ans= event.project

                if (fname.equals(fn, ignoreCase = true)) {
                    try {
                        Files.move(source, target)
                    } catch (e1: IOException) {
                        // e.printStackTrace();
                    }
                    panelForFCM.label_file_status.setText("Google Service file added")
                } else {
                    panelForFCM.label_file_status.setText("You have selected wrong file")
                }


            } else {
                panelForFCM.label_file_status.text = "Save command canceled"
            }
        }


        panelForFCM.button.addActionListener { JOptionPane.showOptionDialog(null, "Add the below code In the oncreate method of your launching activity\n CleverTapAPI.createNotificationChannel(getApplicationContext(),\"3131\",\"mychannel\",\"Description\",NotificationManager.IMPORTANCE_MAX,true);", "Info",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,null,null) }



        init()

    }


        override fun doOKAction() =
            presenter.onOkClick(
                    event,
                    this.packageName,
                    panelForFCM.serviceNameTextField.text,
                    panelForFCM.pendingIntentTextField.text,
                    panelForFCM.contentTitleTextField.text,
                    panelForFCM.contentTextTextField.text,
                    panelForFCM.fcm_sender_id_TextField.text,
                    //panelForFCM.Exclude_filesTextField.text,
                     panelForFCM.dependencyVersionTextField.text,
                    panelForFCM.rb1_fcm.isSelected,
                    panelForFCM.rb2_fcm.isSelected,
                    panelForFCM.isNeedReadMeForInstructions.isSelected,

                    //Methods.createnotificationchannel(panelForFCM.contentTitleTextField.text),
                    moduleName
                   // fcm1(event)


            )
        override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForFCM
            //return panelForFCM
    }

    override fun close() = close(DialogWrapper.OK_EXIT_CODE)

    override fun showPackage(packageName: String) {
        this.packageName = packageName
    }

    override fun showModules(modules: List<String>) {}

    override fun selectModule(module: String) {
        moduleName = module
    }
}