package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.DialogWrapper
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import util.Constants
import javax.swing.JComponent

class FCMApprovalDialog(var event: AnActionEvent, serviceNameText:String,
                        private var channel_id: String,
                        private var fcm_sender_id: String, dependencyVersionText:String,
                        private var IsRadiobuttonrb1Selected: Boolean,
                        private var IsRadiobuttonrb2Selected: Boolean, lang:String) : DialogWrapper(true),  NewScreenView {

    private val panelForFCM1 = FCMApproval()
    private val panelForFCM = FCMInputPanel()

    private val presenter: FCMApprovaPresenter

    private var packageName = ""
    private var moduleName = ""
    private var service_name= serviceNameText
    private var dependencyVersion =dependencyVersionText

    init {
        val currentPath = event.getData(LangDataKeys.VIRTUAL_FILE)?.let {
            val module = ModuleUtil.findModuleForFile(it, event.project!!)?.name ?: Constants.DEFAULT_MODULE_NAME
            CurrentPath(it.path, it.isDirectory, module)
        }
        val projectStructure = ProjectStructureImpl(event.project!!)
        val sourceRootRepository = SourceRootRepositoryImpl(projectStructure)
        val fileCreator = FileCreatorImpl(sourceRootRepository)
        val packageExtractor = PackageExtractorImpl(currentPath, sourceRootRepository)
        val writeActionDispatcher = WriteActionDispatcherImpl()
        val moduleRepository = ModuleRepositoryImpl(projectStructure)
        presenter = FCMApprovaPresenter(
            this,
            // Account_id,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath

        )
        if(IsRadiobuttonrb2Selected) {
            panelForFCM1.file1.isVisible = true
            panelForFCM1.manifest.isVisible = true
            panelForFCM1.manifest_content.isVisible = true
            panelForFCM1. file2.isVisible = true
            panelForFCM1.launchingact.isVisible = true
            panelForFCM1.launchingact_content.isVisible = true
            panelForFCM1. file3.isVisible = true
            panelForFCM1.project_gradle.isVisible = true
            panelForFCM1.project_gradle_content.isVisible = true
            panelForFCM1.file4.isVisible = true
            panelForFCM1.app_gradle.isVisible = true
            panelForFCM1.app_gradle_content.isVisible = true


            panelForFCM1.manifest_content.text = "<html>"+ "&lt meta-data" + "<br>" +
                    "        android:name=\"FCM_SENDER_ID\"" + "<br>" +
                    "        android:value=\"$fcm_sender_id \" / &gt" + "<br>" +
                    "&lt meta-data" + "<br>" +
                    "        android:name=\"firebase_messaging_auto_init_enabled\"" + "<br>" +
                    "        android:value=\" false\" / &gt" + "<br>" +
                    "&lt meta-data" + "<br>" +
                    "        android:name=\"firebase_analytics_collection_enabled\"" + "<br>" +
                    "        android:value=\" false\" / &gt" + "<br>" +
                    "&lt service" + "<br>" +
                    "android:name=\"com.clevertap.android.sdk.pushnotification.fcm.FcmMessageListenerService\"" + "<br>" +
                    "android:exported=\"false\">" + "<br>" +
                    "&lt intent-filter &gt" + "<br>" +
                    "&lt action android:name=\"com.google.firebase.MESSAGING_EVENT\" /&gt" + "<br>" +
                    "&lt /intent-filter &gt" + "<br>" +
                    "&lt /service &gt" + "<br>" +
                    //"<br>" +
                    "</html>"

            if(lang=="java") {
                panelForFCM1.launchingact_content.text = "<html>" + "import android.app.NotificationManager;" + "<br>" +
                        "CleverTapAPI.createNotificationChannel(getApplicationContext(),\"$channel_id\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true);" + "<br>" +
                        "</html>"
            }
            if(lang=="kotlin") {
                panelForFCM1.launchingact_content.text = "<html>" + "import android.app.NotificationManager" + "<br>" +
                        "CleverTapAPI.createNotificationChannel(getApplicationContext(),\"$channel_id\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true)" + "<br>" +
                        "</html>"
            }


            panelForFCM1.app_gradle_content.text = "<html>"+"implementation 'com.google.firebase:firebase-messaging:20.2.4'"+"<br>"+
                    "implementation 'com.android.installreferrer:installreferrer:2.1'"+"<br>"+
                    "apply plugin: 'com.google.gms.google-services'"+"<br>"+"<br>"+
                    "</html>"


        }
        if(IsRadiobuttonrb1Selected){
            panelForFCM1.file1.isVisible = false
            panelForFCM1.manifest.isVisible = false
            panelForFCM1.manifest_content.isVisible = false
            panelForFCM1. file3.isVisible = false
            panelForFCM1.project_gradle.isVisible = false
            panelForFCM1.project_gradle_content.isVisible = false
            panelForFCM1.file4.isVisible = false
            panelForFCM1.app_gradle.isVisible = false
            panelForFCM1.app_gradle_content.isVisible = false
            panelForFCM1. file2.isVisible = true
            panelForFCM1.launchingact.isVisible = true
            panelForFCM1.launchingact_content.isVisible = true


            panelForFCM1.file5.isVisible = true
            panelForFCM1.manifest_yes.isVisible = true
            panelForFCM1.fcm_implemented.isVisible = true

            panelForFCM1.file6.isVisible = true
            panelForFCM1.fcm_service_class.isVisible = true
            panelForFCM1.fcm_service_class_content1.isVisible = true

            panelForFCM1.fcm_service_class_content2.isVisible = true





            panelForFCM1.fcm_implemented.text = "<html>"+ "&lt receiver" + "<br>" +
                    "android:name=\"com.clevertap.android.sdk.pushnotification.CTPushNotificationReceiver\"" + "<br>" +
                    "android:exported=\"false\">" + "<br>" +
                    "android:enabled=\"true\" &gt" + "<br>" +
                    "&lt /receiver &gt" + "<br>" +
                    //"<br>" +
                    "</html>"

            if(lang=="java") {

                panelForFCM1.launchingact_content.text = "<html>" + "import android.app.NotificationManager;" + "<br>" +
                        "CleverTapAPI.createNotificationChannel(getApplicationContext(),\"$channel_id\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true);" + "<br>" +
                        "</html>"

                panelForFCM1.fcm_service_class_content1.text = "<html>" + "try {" + "<br>" +
                        "if (remotemessage.getData().size() > 0) {" + "<br>" +
                        "                Bundle extras = new Bundle();" + "<br>" +
                        "                for (Map.Entry<String, String> entry : remotemessage.getData().entrySet()) {" + "<br>" +
                        "                    extras.putString(entry.getKey(), entry.getValue()) };" + "<br>" +
                        "                NotificationInfo info = CleverTapAPI.getNotificationInfo(extras);" + "<br>" +
                        "                if (info.fromCleverTap) {" + "<br>" +
                        "                    CleverTapAPI.createNotification(getApplicationContext(), extras);" + "<br>" +
                        "                } else {" + "<br>" +
                        "                    // not from CleverTap handle yourself or pass to another provider  }" + "<br>" +
                        "}" + "<br>" +
                        "        } catch (Throwable t) {" + "<br>" +
                        "           Log.d(\"MYFCMLIST\", \"Error parsing FCM message\", t); }" + "<br>" +
                        "</html>"

                panelForFCM1.fcm_service_class_content2.text = "<html>" + "@Override" + "<br>" + "public void onNewToken(@NonNull String s) {" + "<br>" + "    super.onNewToken(s);" + "<br>" + "\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());" + "<br>" + "clevertapDefaultInstance.pushFcmRegistrationId(s,true);" + "<br>" + "}" + "<br>" + "</html>"
            }
            if(lang=="kotlin")
            {
                panelForFCM1.launchingact_content.text = "<html>" + "import android.app.NotificationManager" + "<br>" +
                        "CleverTapAPI.createNotificationChannel(getApplicationContext(),\"$channel_id\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true)" + "<br>" +
                        "</html>"

                panelForFCM1.fcm_service_class_content1.text = "<html>"+"remotemessage.data.apply {"+"<br>"+
                        "try {"+"<br>"+
                        "if (size() > 0) {"+"<br>"+
                        "                val extras =Bundle()"+"<br>"+
                        "                for ((key,value) in this ) {"+"<br>"+
                        "                    extras.putString(key, value)"+"<br>"+
                        "}"+"<br>"+
                        "                val info = CleverTapAPI.getNotificationInfo(extras)"+"<br>"+
                        "                if (info.fromCleverTap) {"+"<br>"+
                        "                    CleverTapAPI.createNotification(ApplicationContext(), extras)"+"<br>"+
                        "                } else {"+
                        "                    // not from CleverTap handle yourself or pass to another provider }" +"<br>"+
                        "}"+"<br>"+
                        "        } catch (t: Throwable) {"+
                        "           Log.d(\"MYFCMLIST\", \"Error parsing FCM message\", t)"+
                        "}"+
                        "</html>"

                panelForFCM1.fcm_service_class_content2.text = "<html>"+"@Override"+"<br>"+"fun onNewToken(@NonNull String s) {"+"<br>"+"    super.onNewToken(s)"+"<br>"+"\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext())"+"<br>"+"clevertapDefaultInstance.pushFcmRegistrationId(s,true)"+"<br>"+"}"+"<br>"+"</html>"
            }


        }
//        panelForFCM.rb1.addActionListener(){
//            if (panelForFCM.rb1.isSelected()) {
//                panelForFCM.labelapplicationclassname.isVisible = true
//                panelForFCM.application_classname_TextField.isVisible = true
//
//            }
//        }
//        panelForFCM.rb2.addActionListener(){
//            if (panelForFCM.rb2.isSelected()) {
//                panelForFCM.labelapplicationclassname.isVisible = false
//                panelForFCM.application_classname_TextField.isVisible =false
//
//            }
//        }

        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME
        init()
    }

    override fun doOKAction() =
        presenter.onOkClick(
            event,
            this.packageName,
            service_name,

            //panelForFCM.serviceNameTextField.text,
            //panelForFCM.pendingIntentTextField.text,
            channel_id,
            //panelForFCM.contentTextTextField.text,
            fcm_sender_id,
            dependencyVersion,
            IsRadiobuttonrb1Selected,
            IsRadiobuttonrb2Selected,


            //panelForFCM.contentTitleTextField.text,
            //panelForFCM.contentTextTextField.text,
            //panelForFCM.fcm_sender_id_TextField.text,
            //panelForFCM.Exclude_filesTextField.text,
            //panelForFCM.dependencyVersionTextField.text,
            //panelForFCM.rb1_fcm.isSelected,
            //panelForFCM.rb2_fcm.isSelected,
            panelForFCM.isNeedReadMeForInstructions.isSelected,

            //Methods.createnotificationchannel(panelForFCM.contentTitleTextField.text),
            moduleName
            // fcm1(event)
        )



    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForFCM1
    }

    override fun close() = close(OK_EXIT_CODE)

    override fun showPackage(packageName: String) {
        this.packageName = packageName
    }

    override fun showModules(modules: List<String>) {}

    override fun selectModule(module: String) {
        moduleName = module
    }
}