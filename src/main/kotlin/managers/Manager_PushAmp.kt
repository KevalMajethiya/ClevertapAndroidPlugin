package managers

import com.intellij.formatting.blocks.split
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants
import java.io.File
import java.io.FileNotFoundException

class Manager_PushAmp (private val project: Project) {

    private var androidManifest: Document? = null
    private var receiverClassFile : Document? = null
    private var appClass : Document? = null
    private var packagename :String =""
    private var projectBaseDir: VirtualFile? = null
    private var syncVal: Boolean = false
    private var receiveVal : Boolean = false
    private var receiveData : String =""
    private var methVal : Boolean = false
    private var methData : String = ""
    private var impVal : Boolean = false
    private var impData : String = ""
    private var listVal : Boolean = false
    private var listData : String = ""

    @Throws(FileNotFoundException::class)
    fun initAndroidManifest(): Boolean {
        val basePath = project.basePath
        projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)
        val manifestVirtualFile: VirtualFile? = projectBaseDir!!
            .findChild(Constants.DEFAULT_MODULE_NAME)!!
            .findChild("src")!!
            .findChild("main")!!
            .findChild("AndroidManifest.xml")
        return if (manifestVirtualFile != null) {
            androidManifest = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
            true
        } else {
            false
        }
    }

    fun getpackagename():String {

        initAndroidManifest()
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in documentText.indices) {
            val line = documentText[i]

            if (line.contains("package")) {
                if (line.contains("=")) {

                    val a = line
                    val b = a.split("=")
                    val c = b[1]
                    val d = c.split("\"")
                    packagename = d[1]

                }
            }
        }
        return "com"
    }



    @Throws(FileNotFoundException::class)
    fun initReceiverClass(receiverClass:String) : Boolean{
        getpackagename()
        val op1=packagename
        val ans1 =op1.replace(".","/")
        val file = File(project.basePath +"/app/src/main/java/"+ans1+"/" + receiverClass +".java")
        val file1 = File(project.basePath +"/app/src/main/java/"+ans1+"/" + receiverClass +".kt")
        val java_file_exist = file.exists()
        val kotlin_file_exist = file1.exists()
        if (java_file_exist==true){
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath+"/app/src/main/java/"+ans1+"/" + receiverClass +".java")

        }
        if (kotlin_file_exist==true){
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath+"/app/src/main/java/"+ans1+"/" + receiverClass +".kt")
        }
        val manifestVirtualFile: VirtualFile? = projectBaseDir
        return if (manifestVirtualFile != null) {
            receiverClassFile = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
            true
        } else {
            false
        }
    }




    @Throws(FileNotFoundException::class)
    fun initAppClass(appclassname:String) : Boolean{
        getpackagename()
        val op1=packagename
        val ans1 =op1.replace(".","/")
        val file = File(project.basePath +"/app/src/main/java/"+ans1+"/" + appclassname +".java")
        val file1 = File(project.basePath +"/app/src/main/java/"+ans1+"/" + appclassname +".kt")
        val java_file_exist = file.exists()
        val kotlin_file_exist = file1.exists()
        if (java_file_exist==true){
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath+"/app/src/main/java/"+ans1+"/" + appclassname +".java")
            listData = "        " + "CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());\n" +
                    "        cleverTapAPI.setCTPushAmpListener(this);\n"
            methData ="@Override\n" +
                    "public void onPushAmpPayloadReceived(Bundle bundle) {\n" +
                    "    //write push notification rendering logic here\n" +
                    "}"
            impData = " implements CTPushAmpListener"

            receiveData = "if (message.getData().size() > 0) {\n" +
                    "                Bundle extras = new Bundle();\n" +
                    "                Iterator var = message.getData().entrySet().iterator();\n" +
                    "\n" +
                    "                while(var.hasNext()) {\n" +
                    "                    Map.Entry entry = (Map.Entry)var.next();\n" +
                    "                    extras.putString((String)entry.getKey(), (String)entry.getValue());\n" +
                    "                }\n" +
                    "                    CleverTapAPI.processPushNotification(getApplicationContext(),extras);\n" +
                    "                }\n" +
                    "            }\n"


        }
        if (kotlin_file_exist==true){
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath+"/app/src/main/java/"+ans1+"/" + appclassname +".kt")
            listData ="        val cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext())\n" +
                    "        cleverTapAPI.setCTPushAmpListener(this)"
            impData = ": CTPushAmpListener"

            methData = "override fun onPushAmpPayloadReceived(bundle:Bundle) {\n" +
                    "  //write push notification rendering logic here\n" +
                    "}"

            receiveData = "if (message.getData().size() > 0)\n" +
                    "{\n" +
                    "  val extras = Bundle()\n" +
                    "  val `var` = message.getData().entrySet().iterator()\n" +
                    "  while (`var`.hasNext())\n" +
                    "  {\n" +
                    "    val entry = `var`.next() as Map.Entry\n" +
                    "    extras.putString(entry.getKey() as String, entry.getValue() as String)\n" +
                    "  }\n" +
                    "  CleverTapAPI.processPushNotification(getApplicationContext(), extras)\n" +
                    "}"
        }
        val manifestVirtualFile: VirtualFile? = projectBaseDir
        return if (manifestVirtualFile != null) {
            appClass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
            true
        } else {
            false
        }
    }

    fun checkbeforeinsertion(){

        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in documentText.indices){

            var line = documentText[i]
            if (line.contains("CLEVERTAP_BACKGROUND_SYNC")){
                syncVal = true
            }
        }
    }
    fun checkbeforeinsertion1(){
        val documentText = receiverClassFile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in documentText.indices){
            var line = documentText[i]
            if (line.contains("CleverTapAPI.processPushNotification(getApplicationContext(),extras);")){
                receiveVal = true
            }
        }
    }
    fun checkbeforeinsertion2(){
        val documentText = appClass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in documentText.indices){
            var line = documentText[i]
            if (line.contains("implements") && line.contains("CTPushAmpListener")){
                impVal = true

            }
            if (line.contains("cleverTapAPI.setCTPushAmpListener(this)")){
                listVal = true
            }
            if(line.contains("onPushPayloadReceived")){
                methVal = true
            }
        }
    }


    fun addMetaDataContent(IsRadiobuttonrb2Selected:Boolean){
        checkbeforeinsertion()
        val documentText = androidManifest!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices){
            val line = documentText[i]
            if (IsRadiobuttonrb2Selected==true){
                if (syncVal == false){
                    if (line.contains(Constants.APPLICATION)) {
                        if (line.contains("/")) {
                            sb
                                .append("         <!-- Added by CleverTap Assistant-->")
                                .append("\n")
                                .append("         <meta-data")
                                .append("\n")
                                .append("             android:name=\"CLEVERTAP_BACKGROUND_SYNC\"")
                                .append("\n")
                                .append("             android:value=\"1\" />")
                                .append("\n")
                            syncVal=true

                        }
                    }
                }
            }

            sb
                .append(line)
                .append("\n")

        }
        writeToManifest(sb)

    }
    fun addApplicationData(IsRadiobuttonrb1Selected:Boolean,appclassname:String,lang : String){
        checkbeforeinsertion2()
        val documentText = appClass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices){
            var line = documentText[i]
            if (IsRadiobuttonrb1Selected == true){
                if(listVal==false){
                    if (line.contains("public void onCreate() { super.onCreate();")) {
                        val ans = line
                        val ans1 = ans.split("{")
                        val ans2 = ans1[0]
                        val ans3 = ans1[1]
                        line.split("{")
                        line = line[0].toString()

                        sb
                            .append(ans2)
                            .append("\n")
                            .append("    {")
                            .append("\n")
                            .append("       $ans3")
                            .append("\n")
                            .append("$listData   //added by CleverTap Assistant")
                            .append("\n")
                        listVal=true


                    }
                    if (line.contains("{ super.onCreate();")) {
                        val ans = line
                        val ans1 = ans.split("{")
                        val ans2 = ans1[1]
                        //val ans3=ans1[1]
                        line.split("{")
                        //line=line[0].toString()
                        line = line[0].toString()

                        sb
                            .append("{")
                            .append("\n")
                            .append(ans2)
                            .append("\n")
                            .append("$listData   //added by CleverTap Assistant")
                            .append("\n")
                        listVal=true


                    }
                    if (line.contains(" super.onCreate()")) {


                        sb
                            .append("$listData   //added by CleverTap Assistant")
                            .append("\n")
                        listVal=true
                        //   }

                    }
                }

                if (impVal == false){
                    if (line.contains("class "+ appclassname)){
                        if (lang=="java"){
                            if (line.contains("implements")){
                                val ans = line
                                val ans1 = ans.split("implements")
                                line = ans1[0] + impData+"," + ans1[1]
                            }
                            else{
                                val ans = line
                                val ans1 = ans.split("{")
                                line = ans1[0] + impData + "{"
                            }
                        }
                        if (lang=="kotlin") {
                            if (line.contains(":")) {
                                val ans = line
                                val ans1 = ans.split(":")
                                line = ans1[0] + impData + "," + ans1[1]
                            } else {
                                val ans = line
                                val ans1 = ans.split("{")
                                line = ans1[0] + impData + "{"
                            }
                        }
                    }
                }

                if(methVal == false){
                    if (i == documentText.lastIndexOf("}")){
                        sb
                            .append(methData)
                            .append("\n")

                        methVal =true
                    }
                }

            }
            sb
                .append(line)
                .append("\n")
        }
        writeToApplication(sb)
    }

    fun addReceiverData(){
        checkbeforeinsertion1()
        val documentText = receiverClassFile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in documentText.indices){
            val line =documentText[i]
            if (receiveVal== false){
                if (i == documentText.lastIndexOf("}")){
                    sb
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                        .append(receiveData)
                        .append("\n")

                    receiveVal =true
                }
            }
            sb.append(line)
                .append("\n")
        }
        writeToReceiver(sb)
    }

    private fun writeToManifest(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { androidManifest!!.setText(stringBuilder) }
        }
    }

    private fun writeToApplication(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { appClass!!.setText(stringBuilder) }
        }
    }

    private fun writeToReceiver(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { receiverClassFile!!.setText(stringBuilder) }
        }
    }

}