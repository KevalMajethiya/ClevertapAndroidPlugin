package managers

//package managers
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.kotlin.idea.util.findModule

import util.Constants
import java.io.File
import java.io.FileNotFoundException


class Geofence_Manager(private val project: Project)
{
    private var androidapplicationclass: Document? = null
    //private var androidapplicationclass1: Document? = null
    private var androidManifestfile: Document? = null
    private var ans:String=""
    private var clevertap_object:Boolean=false
    private var CleverTapAPI_sdk_import_stmt:Boolean=false
    private var packagename:String=""
    private var launchingactivityname:String=""
    private var CTGeofenceAPI_import_stmt:Boolean=false
    private var geofence_Logger_import_stmt:Boolean=false
    private var CTGeofenceSettings_import_stmt:Boolean=false
    private var CTGeofenceSettings_object:Boolean=false
    private var CTGeofenceAPI_initialization:Boolean=false
    private var bglocation:Boolean=false




    private var projectBaseDir: VirtualFile? = null

    @Throws(FileNotFoundException::class)
    fun AndroidManifest(): Boolean {
        val basePath = project.basePath

        projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)

        val manifestVirtualFile: VirtualFile? = projectBaseDir!!
            .findChild(Constants.DEFAULT_MODULE_NAME)!!
            .findChild("src")!!
            .findChild("main")!!
            .findChild("AndroidManifest.xml")
        return if (manifestVirtualFile != null) {
            androidManifestfile = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
            getpackagename()
            true
        } else {
            false
        }
    }

    fun getpackagename():String{


        val documentText = androidManifestfile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()

        for (i in documentText.indices) {
            val line = documentText[i]
            if (line.contains("android.intent.category.LAUNCHER"))
            {
                for(j in i downTo 1)
                {
                    val line1=documentText[j]
                    if(line1.contains("activity")) {
                        var ans=line1
                        var b= ans.split(".")
                        var c=b[1]
                        var d=b[1].split("\"")
                        launchingactivityname=d[0]
                        print(launchingactivityname)
                        // launchingactivityname="line"
                        return launchingactivityname
                        //initapplicationclass(activityname)
                        //sb
                        //.append(activityname)
                        // .append("\n")

                    }
                }
            }

            if (line.contains("package")) {
                if (line.contains("=")) {
                    var a = line
                    var b = a.split("=")
                    //var d=
                    var c = b[1]
                    var d = c.split("\"")
                    packagename = d[1]
                    //return "abc"
                    //initapplicationclass(packagename!!)
                }
            }
            sb
                .append(line)
                .append("\n")

            //  }

        }
        writeToManifest(sb)
        return "com"
    }
//    fun check():String
//    {
//        //val documentText = androidManifestfile!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//        ans = "com"
//        return ans
//
//    }


    @Throws(FileNotFoundException::class)
    fun initapplicationclass(log_value:String,location_accuracy_value:String,location_fetch_mode_value:String,Geofence_Monitoring_count:String,interval:String,fastest_interval:String,displacement:String,
                             geofence_notification_responsiveness:String,enable_bglocation_y:Boolean,enable_bglocation_n:Boolean,lang: String): Boolean {
        AndroidManifest()
        val op=launchingactivityname
        var op1=packagename
        // val ans=pkg
        var ans1=op1.replace(".","/")
        // val ans2=ans1.replace("\"","")
        print(ans1)
        val basePath = project.basePath
        //projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
        print(projectBaseDir)

        var file = File(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
        var file1 = File(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".kt")
        var java_file_exist = file.exists()
        var kotlin_file_exist = file1.exists()
        if(java_file_exist==true)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".java")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                initiateclevertap(log_value,location_accuracy_value,location_fetch_mode_value,Geofence_Monitoring_count,interval,fastest_interval,displacement,
                    geofence_notification_responsiveness,enable_bglocation_y,enable_bglocation_n,lang)
                true
            } else {
                false
            }
        }
        if(kotlin_file_exist==true)
        {
            projectBaseDir = LocalFileSystem.getInstance().findFileByPath(project.basePath +"/app/src/main/java/"+ans1+"/" + op +".kt")
            val manifestVirtualFile: VirtualFile? = projectBaseDir
            return if (manifestVirtualFile != null) {
                androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
                //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)

                initiateclevertap_kt(log_value,location_accuracy_value,location_fetch_mode_value,Geofence_Monitoring_count,interval,fastest_interval,displacement,
                    geofence_notification_responsiveness,enable_bglocation_y,enable_bglocation_n,lang)
                true
            } else {
                false
            }
        }

//        val manifestVirtualFile: VirtualFile? = projectBaseDir
//        return if (manifestVirtualFile != null) {
//            androidapplicationclass = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
//            //androidapplicationclass1 = FileDocumentManager.getInstance().getDocument(manifestVirtualFile)
//
//            initiateclevertap()
//            true
//        } else {
//            false
//        }
        return true
    }

    fun checkinsertion()
    {
        //val opp=launchingactivityname
        val documentText = androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()

        for (i in documentText.indices)
        {
            var line = documentText[i]
            if(line.contains("CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());"))
            {
                clevertap_object=true

            }
            if(line.contains("import com.clevertap.android.sdk.CleverTapAPI;"))
            {
                CleverTapAPI_sdk_import_stmt=true

            }
            if(line.contains("import com.clevertap.android.geofence.CTGeofenceAPI;"))
            {
                CTGeofenceAPI_import_stmt=true
            }
            if(line.contains("import com.clevertap.android.geofence.Logger;"))
            {
                geofence_Logger_import_stmt=true
            }
            if(line.contains("import com.clevertap.android.geofence.CTGeofenceSettings;"))
            {
                CTGeofenceSettings_import_stmt=true
            }
            if(line.contains("CTGeofenceSettings ctGeofenceSettings = new CTGeofenceSettings.Builder()"))
            {
                CTGeofenceSettings_object=true
            }
            if(line.contains("CTGeofenceAPI.getInstance(getApplicationContext()).init(ctGeofenceSettings,clevertapDefaultInstance);"))
            {
                CTGeofenceAPI_initialization=true
            }

        }
    }

    fun initiateclevertap(log_value:String,location_accuracy_value:String,location_fetch_mode_value:String,Geofence_Monitoring_count:String,interval:String,fastest_interval:String,displacement:String,
                          geofence_notification_responsiveness:String,enable_bglocation_y:Boolean,enable_bglocation_n:Boolean,lang: String) {
        checkinsertion()
        //var c= codeexist
        // if(c==false) {
        // val opp=launchingactivityname
        val documentText =
            androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()

        for (i in documentText.indices) {
            var line = documentText[i]
            sb
                .append(line)
                .append("\n")
            if(CleverTapAPI_sdk_import_stmt==false) {
                if (line.contains("package")) {
                    sb
                        .append("import com.clevertap.android.sdk.CleverTapAPI;")
                        .append(" //added by CleverTap Assistant")
                        //.append("   //Initializing the CleverTap SDK")
                        .append("\n")
                    CleverTapAPI_sdk_import_stmt=true
                    //.append("CleverTapAPI.createNotificationChannel(getApplicationContext(),\"3131\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true);")
                    //.append("\n")
                }
            }
                if(CTGeofenceAPI_import_stmt==false) {
                    if (line.contains("package")) {
                        // if (line.contains("/")) {
                        sb
                            .append("import com.clevertap.android.geofence.CTGeofenceAPI;")
                            .append(" //added by CleverTap Assistant")
                            .append("\n")
                        CTGeofenceAPI_import_stmt=true
                        // }
                    }
                }
            if(CTGeofenceSettings_import_stmt==false) {
                if (line.contains("package")){
                    sb
                        .append("import com.clevertap.android.geofence.CTGeofenceSettings;")
                        .append(" //added by CleverTap Assistant")
                        .append("\n")
                CTGeofenceSettings_import_stmt=true
                }
            }

            if(geofence_Logger_import_stmt==false) {
                if (line.contains("package")){
                    sb
                        .append("import com.clevertap.android.geofence.Logger;")
                        .append("             //added by CleverTap Assistant")
                        .append("\n")
                    geofence_Logger_import_stmt=true
                }
            }


            if(clevertap_object==false) {
                if (line.contains("void onCreate")) {
                    sb
                        .append("\n")
                        .append("        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());")
                        .append("   //Initializing the CleverTap SDK")
                        .append("\n")
                    clevertap_object=true
                }
            }

            if(enable_bglocation_y==true)
            {
                bglocation=true
            }
            if(enable_bglocation_n==true)
            {
                bglocation=false
            }

            if(CTGeofenceSettings_object==false) {
                if (line.contains("setContentView")) {
                    sb
                        .append("\t\t//Added by CleverTap Assistant")
                        .append("\n")
                        .append("\t\tCTGeofenceSettings ctGeofenceSettings = new CTGeofenceSettings.Builder()")
                        //.append("   //added by CleverTap Assistant")
                        .append("\n")
                        .append("\t\t\t\t.enableBackgroundLocationUpdates($bglocation)//boolean to enable background location updates")
                        .append("\n")
                        .append("\t\t\t\t.setLogLevel(Logger.$log_value)//Log Level")
                        .append("\n")
                        .append("\t\t\t\t.setLocationAccuracy(CTGeofenceSettings.ACCURACY_$location_accuracy_value)//byte value for Location Accuracy")
                        .append("\n")
                        .append("\t\t\t\t.setLocationFetchMode(CTGeofenceSettings.$location_fetch_mode_value)//byte value for Fetch Mode")
                        .append("\n")
                        .append("\t\t\t\t.setGeofenceMonitoringCount($Geofence_Monitoring_count)//int value for number of Geofences CleverTap can monitor")
                        .append("\n")
                        .append("\t\t\t\t.setInterval($interval)//long value for interval in milliseconds")
                        .append("\n")
                        .append("\t\t\t\t.setFastestInterval($fastest_interval)//long value for fastest interval in milliseconds")
                        .append("\n")
                        .append("\t\t\t\t.setSmallestDisplacement($displacement)//float value for smallest Displacement in meters")
                        .append("\n")
                        .append("\t\t\t\t.setGeofenceNotificationResponsiveness($geofence_notification_responsiveness)// int value for geofence notification responsiveness in milliseconds")
                        .append("\n")
                        .append("\t\t\t\t.build();")
                        .append("\n")
                    CTGeofenceSettings_object=true
                }
            }
            if(CTGeofenceAPI_initialization==false) {
                if (line.contains("setContentView")) {
                    sb
                        .append("\n")
                        .append("\t\tCTGeofenceAPI.getInstance(getApplicationContext()).init(ctGeofenceSettings,clevertapDefaultInstance);")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    CTGeofenceAPI_initialization=true
                }
            }

        }


        writeToManifest(sb)
        // }

    }

    fun checkinsertion_kt()
    {
        //val opp=launchingactivityname
        val documentText = androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        // val sb = StringBuilder()

        for (i in documentText.indices)
        {
            var line = documentText[i]
            if(line.contains("CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext())"))
            {
                clevertap_object=true

            }
            if(line.contains("import com.clevertap.android.sdk.CleverTapAPI"))
            {
                CleverTapAPI_sdk_import_stmt=true

            }
            if(line.contains("import com.clevertap.android.geofence.CTGeofenceAPI"))
            {
                CTGeofenceAPI_import_stmt=true
            }
            if(line.contains("import com.clevertap.android.geofence.Logger"))
            {
                geofence_Logger_import_stmt=true
            }
            if(line.contains("import com.clevertap.android.geofence.CTGeofenceSettings"))
            {
                CTGeofenceSettings_import_stmt=true
            }
            if(line.contains("CTGeofenceSettings ctGeofenceSettings = new CTGeofenceSettings.Builder()"))
            {
                CTGeofenceSettings_object=true
            }
            if(line.contains("CTGeofenceAPI.getInstance(getApplicationContext()).init(ctGeofenceSettings,clevertapDefaultInstance)"))
            {
                CTGeofenceAPI_initialization=true
            }

        }
    }


    fun initiateclevertap_kt(log_value:String,location_accuracy_value:String,location_fetch_mode_value:String,Geofence_Monitoring_count:String,interval:String,fastest_interval:String,displacement:String,
                             geofence_notification_responsiveness:String,enable_bglocation_y:Boolean,enable_bglocation_n:Boolean,lang: String) {
        checkinsertion_kt()
        //var c= codeexist
        // if(c==false) {
        // val opp=launchingactivityname
        val documentText =
            androidapplicationclass!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()

        for (i in documentText.indices) {
            var line = documentText[i]
            sb
                .append(line)
                .append("\n")
            if(CleverTapAPI_sdk_import_stmt==false) {
                if (line.contains("package")) {
                    sb
                        .append("import com.clevertap.android.sdk.CleverTapAPI")
                        .append(" //added by CleverTap Assistant")
                        //.append("   //Initializing the CleverTap SDK")
                        .append("\n")
                    CleverTapAPI_sdk_import_stmt=true

                }
            }
            if(CTGeofenceAPI_import_stmt==false) {
                if (line.contains("package")) {
                    // if (line.contains("/")) {
                    sb
                        .append("import com.clevertap.android.geofence.CTGeofenceAPI")
                        .append(" //added by CleverTap Assistant")
                        .append("\n")
                    CTGeofenceAPI_import_stmt=true
                    // }
                }
            }
            if(CTGeofenceSettings_import_stmt==false) {
                if (line.contains("package")){
                    sb
                        .append("import com.clevertap.android.geofence.CTGeofenceSettings")
                        .append(" //added by CleverTap Assistant")
                        .append("\n")
                    CTGeofenceSettings_import_stmt=true
                }
            }

            if(geofence_Logger_import_stmt==false) {
                if (line.contains("package")){
                    sb
                        .append("import com.clevertap.android.geofence.Logger")
                        .append("                  //added by CleverTap Assistant")
                        .append("\n")
                    geofence_Logger_import_stmt=true
                }
            }



            if(clevertap_object==false) {
                if (line.contains("fun onCreate")) {
                    sb
                        .append("\n")
                        .append("        var clevertapDefaultInstance: CleverTapAPI? = null")
                        .append("\n")
                        .append("\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    clevertap_object=true

                }
            }
            if(enable_bglocation_y==true)
            {
                bglocation=true
            }
            if(enable_bglocation_n==false)
            {
                bglocation=false
            }

            if(CTGeofenceSettings_object==false) {
                if (line.contains("setContentView")) {
                    sb
                        .append("//Added by CleverTap Assistant")
                        .append("\t\tvar ctGeofenceSettings =CTGeofenceSettings.Builder()")
                        //.append("   //added by CleverTap Assistant")
                        .append("\n")
                        .append("\t\t\t\t.enableBackgroundLocationUpdates($bglocation)//boolean to enable background location updates")
                        .append("\n")
                        .append("\t\t\t\t.setLogLevel(Logger.$log_value)//Log Level")
                        .append("\n")
                        .append("\t\t\t\t.setLocationAccuracy(CTGeofenceSettings.ACCURACY_$location_accuracy_value)//byte value for Location Accuracy")
                        .append("\n")
                        .append("\t\t\t\t.setLocationFetchMode(CTGeofenceSettings.$location_fetch_mode_value)//byte value for Fetch Mode")
                        .append("\n")
                        .append("\t\t\t\t.setGeofenceMonitoringCount($Geofence_Monitoring_count)//int value for number of Geofences CleverTap can monitor")
                        .append("\n")
                        .append("\t\t\t\t.setInterval($interval)//long value for interval in milliseconds")
                        .append("\n")
                        .append("\t\t\t\t.setFastestInterval($fastest_interval)//long value for fastest interval in milliseconds\n")
                        .append("\n")
                        .append("\t\t\t\t.setSmallestDisplacement($displacement)//float value for smallest Displacement in meters")
                        .append("\n")
                        .append("\t\t\t\t.setGeofenceNotificationResponsiveness($geofence_notification_responsiveness)// int value for geofence notification responsiveness in milliseconds")
                        .append("\n")
                        .append("\t\t\t\t.build()")
                        .append("\n")
                    CTGeofenceSettings_object=true
                }
            }
            if(CTGeofenceAPI_initialization==false) {
                if (line.contains("setContentView")) {
                    sb
                        .append("\n")
                        .append("\t\tCTGeofenceAPI.getInstance(getApplicationContext()).init(ctGeofenceSettings,clevertapDefaultInstance);")
                        .append("   //added by CleverTap Assistant")
                        .append("\n")
                    CTGeofenceAPI_initialization=true
                }
            }


        }

        writeToManifest(sb)

    }




    private fun writeToManifest(stringBuilder: StringBuilder) {
        val application = ApplicationManager.getApplication()
        application.invokeLater {
            application.runWriteAction { androidapplicationclass!!.setText(stringBuilder) }
        }
    }
}