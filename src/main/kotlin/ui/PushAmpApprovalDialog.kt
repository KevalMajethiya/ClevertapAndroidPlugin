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

class PushAmpApprovalDialog(var event:AnActionEvent, className:String, private var IsRadiobuttonrb1Selected: Boolean,
                            private var IsRadiobuttonrb2Selected: Boolean, lang: String,
                            private var appclassname: String
) : DialogWrapper(true),  NewScreenView  {

    private val panelForPushAmp = PushAmpInputPanel()
    private val approvalPushAmp = PushAmpApproval(className,lang,appclassname)
    private val presenter: PushAmpApprovalPresenter

    private var packageName = ""
    private var moduleName = ""
    private var receicerClass = className

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
        presenter = PushAmpApprovalPresenter(
            this,
            // Account_id,
            fileCreator,
            packageExtractor,
            writeActionDispatcher,
            moduleRepository,
            currentPath

        )
        if(IsRadiobuttonrb1Selected){

            approvalPushAmp.file1.isVisible = false
            approvalPushAmp.manifest.isVisible = false
            approvalPushAmp.manifest_content.isVisible = false
            approvalPushAmp.file2.isVisible = true
            approvalPushAmp.line1.isVisible = true
            if (lang == "java"){
                approvalPushAmp.receiver_Class.text = "<html>"+"if (message.getData().size() > 0) {"+"<br>" +
                        "                Bundle extras = new Bundle();"+"<br>" +
                        "                Iterator var = message.getData().entrySet().iterator();"+"<br><br>" +
                        "                while(var.hasNext()) {"+"<br>" +
                        "                    Map.Entry entry = (Map.Entry)var.next();"+"<br>" +
                        "                    extras.putString((String)entry.getKey(), (String)entry.getValue());"+"<br>" +
                        "                }"+"<br>" +
                        "                    CleverTapAPI.processPushNotification(getApplicationContext(),extras);"+"<br>" +
                        "                }"+"<br>" +
                        "            }"+"</html>"

                approvalPushAmp.appclass_content.text = "<html>"+"public class MyApplication extends Application implements CTPushAmpListener {"+"<br><br>" +
                        "@Override"+"<br>" +
                        "    public void onCreate() {"+"<br>" +
                        "CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());"+"<br>" +
                        "cleverTapAPI.setCTPushAmpListener(this);"+"<br>"+
                        "        super.onCreate();"+"<br>" +
                        "    }"+"<br>" +
                        "    @Override"+"<br>" +
                        "    public void onPushPayloadReceived(Bundle bundle) {"+"<br>" +
                        "        //write push notification rendering logic here"+"<br>" +
                        "    }"+"<br>"+
                        "}"+"<br></html>"
            }
            if (lang =="kotlin"){
                approvalPushAmp.receiver_Class.text = "<html>"+"if (message.getData().size() > 0)"+"<br>" +
                        "{"+"<br>"  +
                        "  val extras = Bundle()"+"<br>" +
                        "  val `var` = message.getData().entrySet().iterator()"+"<br>" +
                        "  while (`var`.hasNext())"+"<br>" +
                        "  {"+"<br>" +
                        "    val entry = `var`.next() as Map.Entry"+"<br>" +
                        "    extras.putString(entry.getKey() as String, entry.getValue() as String)"+"<br>" +
                        "  }"+"<br>" +
                        "  CleverTapAPI.processPushNotification(getApplicationContext(), extras)"+"<br>" +
                        "}"+"</html>"
                approvalPushAmp.appclass_content.text = "<html>"+"class MyApplication:Application(), CTPushAmpListener {"+"<br>" +
                        "    override fun onCreate(savedInstanceState: Bundle?) {"+"<br>" +
                        "        val cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext())"+"<br>" +
                        "        cleverTapAPI.setCTPushAmpListener(this)"+"<br>"+
                        "        super.onCreate(savedInstanceState)"+"<br>" +
                        "    }"+"<br>" +
                        "    fun onPushPayloadReceived(bundle:Bundle) {"+"<br>" +
                        "      //write push notification rendering logic here"+"<br>" +
                        "    }"+"<br>"+
                        "}"+"</html>"
            }
            approvalPushAmp.receiver_Class.isVisible = true
            approvalPushAmp.file3.isVisible = true
            approvalPushAmp.line2.isVisible = true
            approvalPushAmp.appclass_content.isVisible = true

        }
        if (IsRadiobuttonrb2Selected){
            approvalPushAmp.file1.isVisible = true
            approvalPushAmp.manifest.isVisible = true
            approvalPushAmp.manifest_content.text = "<html>"+ "&lt meta-data" + "<br>" +
                    "        android:name=\"CLEVERTAP_BACKGROUND_SYNC\""+ "<br>" +
                    "        android:value=\" 1 \" / &gt"
            approvalPushAmp.manifest_content.isVisible = true
        }



        moduleName = currentPath?.module ?: Constants.DEFAULT_MODULE_NAME
        init()
    }

    override fun doOKAction() =
        presenter.onOkClick(
            event,
            this.packageName,
            IsRadiobuttonrb1Selected,
            IsRadiobuttonrb2Selected,
            receicerClass,
            appclassname,
            moduleName
        )


    override fun createCenterPanel(): JComponent {
        presenter.onLoadView()
        return approvalPushAmp
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