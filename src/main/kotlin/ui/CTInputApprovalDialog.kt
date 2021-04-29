package ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.ui.DialogWrapper
import data.file.*
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepositoryImpl
import managers.ManifestManager
import util.Constants
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JComponent

class CTInputApprovalDialog(var event: AnActionEvent,Account_id:String,Account_token:String,use_google_ad_id_rb1:Boolean,use_google_ad_id_rb2: Boolean, Exclude_filesText:String,applicationclassname:String,IsRadiobuttonrb1Selected:Boolean) : DialogWrapper(true),  NewScreenView {

    private val panelForFCM1 = CleverTapInputApproval()
    private val panelForFCM = ClevertapInputPanel()

    private val presenter: CTApprovalPresenter
    private val presenter1: NewScreenPresenterClevertap

    private var packageName = ""
    private var moduleName = ""
    private var ac_id=Account_id
    private var ac_token=Account_token
    private var google_ad_id_rb1=use_google_ad_id_rb1
    private var google_ad_id_rb2=use_google_ad_id_rb2
    private var use_google_ad_id=""
    private var Exclude_files =Exclude_filesText
    private var applicationclassname = applicationclassname
    private var Radiobuttonrb1Selected = IsRadiobuttonrb1Selected

    init {
        val currentPath = event.getData(DataKeys.VIRTUAL_FILE)?.let {
            val module = ModuleUtil.findModuleForFile(it, event.project!!)?.name ?: Constants.DEFAULT_MODULE_NAME
            CurrentPath(it.path, it.isDirectory, module)
        }
        val projectStructure = ProjectStructureImpl(event.project!!)
        val sourceRootRepository = SourceRootRepositoryImpl(projectStructure)
        val fileCreator = FileCreatorImpl(sourceRootRepository)
        val packageExtractor = PackageExtractorImpl(currentPath, sourceRootRepository)
        val writeActionDispatcher = WriteActionDispatcherImpl()
        val moduleRepository = ModuleRepositoryImpl(projectStructure)
        presenter = CTApprovalPresenter(
            this,
           // Account_id,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath

        )
        presenter1 = NewScreenPresenterClevertap(
            this,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath

        )
        if(google_ad_id_rb1==true)
        {
            use_google_ad_id="1"
        }
        if(google_ad_id_rb2==true)
        {
            use_google_ad_id="0"
        }
        if(IsRadiobuttonrb1Selected==false) {

                panelForFCM1.manifest_content.setText(
                    "<html>" + "Default CleverTapApplication Class" + "<br>" +
                            "&lt application " + "<br>" +
                            "android:name=\"com.clevertap.android.sdk.Application\"" + "<br>" +
                            "&lt /application /&gt" + "<br>" +
                            "Permissions :" + "<br>" +
                            "&lt uses-permission android:name=\"android.permission.INTERNET\" &gt" + "<br>" +
                            "&lt uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" &gt" + "<br>" +
                            "<br>" +
                            "CleverTapCredentials :" + "<br>" +
                            "&lt meta-data" + "<br>" +
                            "        \t\tandroid:name=\"CLEVERTAP_INAPP_EXCLUDE\"" + "<br>" +
                            "        \t\tandroid:value=\" $Exclude_files\" / &gt" + "<br>" +
                            "&lt meta-data" + "<br>" +
                            "        \t\tandroid:name=\"CLEVERTAP_ACCOUNT_ID\"" + "<br>" +
                            "        \t\tandroid:value=\" $Account_id\" / &gt" + "<br>" +
                            "&lt meta-data" + "<br>" +
                            "        \t\tandroid:name=\"CLEVERTAP_TOKEN\"" + "<br>" +
                            "        \t\tandroid:value=\"$Account_token \" /&gt" + "<br>" +
                            "&lt meta-data" + "<br>" +

                            "        \t\tandroid:name=\"CLEVERTAP_USE_GOOGLE_AD_ID\"" + "<br>" +
                            "        \t\tandroid:value=\"$use_google_ad_id \" /&gt" + "<br>" +
                            "<br>" +
                            "</html>"
                )
            }

        else{
            panelForFCM1.file3.setVisible(true)
            panelForFCM1.myapplication_class.setVisible(true)
            panelForFCM1.myapplication_class_content.setVisible(true)

            panelForFCM1.manifest_content.setText(
                "<html>"+ "Permissions :" + "<br>" +
                        "&lt uses-permission android:name=\"android.permission.INTERNET\" &gt" + "<br>" +
                        "&lt uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" &gt" + "<br>" +
                        "<br>" +
                        "CleverTapCredentials :" + "<br>" +
                        "&lt meta-data" + "<br>" +
                        "        \t\tandroid:name=\"CLEVERTAP_INAPP_EXCLUDE\"" + "<br>" +
                        "        \t\tandroid:value=\" $Exclude_files\" / &gt" + "<br>" +
                        "&lt meta-data" + "<br>" +
                        "        \t\tandroid:name=\"CLEVERTAP_ACCOUNT_ID\"" + "<br>" +
                        "        \t\tandroid:value=\" $Account_id\" / &gt" + "<br>" +
                        "&lt meta-data" + "<br>" +
                        "        \t\tandroid:name=\"CLEVERTAP_TOKEN\"" + "<br>" +
                        "        \t\tandroid:value=\"$Account_token \" /&gt" + "<br>" +
                        "&lt meta-data" + "<br>" +
                        "        \t\tandroid:name=\"CLEVERTAP_USE_GOOGLE_AD_ID\"" + "<br>" +
                        "        \t\tandroid:value=\"$use_google_ad_id \" /&gt" + "<br>" +
                        "<br>" +
                        "</html>"
            )

        }
        panelForFCM.rb1.addActionListener(){
            if (panelForFCM.rb1.isSelected()) {
                panelForFCM.labelapplicationclassname.isVisible = true
                panelForFCM.application_classname_TextField.isVisible = true

            }
        }
        panelForFCM.rb2.addActionListener(){
            if (panelForFCM.rb2.isSelected()) {
                panelForFCM.labelapplicationclassname.isVisible = false
                panelForFCM.application_classname_TextField.isVisible =false

            }
        }

        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME
        init()
    }

    override fun doOKAction() =
        presenter.onOkClick(
            event,
            this.packageName,
            //Account_id,
            ac_id,
            ac_token,
            google_ad_id_rb1,
            google_ad_id_rb2,
            Exclude_files,

            //panelForFCM.serviceNameTextField.text,
           // panelForFCM.pendingIntentTextField.text,
            //panelForFCM.contentTitleTextField.text,
            //panelForFCM.Exclude_filesTextField.text,
            panelForFCM.contentTextTextField.text,
            applicationclassname,
            Radiobuttonrb1Selected,


            //panelForFCM.application_classname_TextField.text,
            //panelForFCM.rb1.isSelected,
            //panelForFCM.dependencyVersionTextField.text,

            //panelForFCM.isNeedReadMeForInstructions.isSelected,
            moduleName
        )



    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return panelForFCM1
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