package util

import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants.FCM_DIRECTORY

object Methods {

    fun checkPrimaryColorInColorsFile(project: Project): Boolean {
        val basePath = project.basePath
        val projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)
        val colorsVirtualFile: VirtualFile? = projectBaseDir!!
            .findChild(Constants.DEFAULT_MODULE_NAME)!!
            .findChild("src")!!
            .findChild("main")!!
            .findChild("res")!!
            .findChild("values")!!
            .findChild("colors.xml")
        return if (colorsVirtualFile != null) {
            val colorsXML = FileDocumentManager.getInstance().getDocument(colorsVirtualFile)
            val documentText = colorsXML!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val colorPrimary = documentText.indices.filter {
                (documentText[it].contains(Constants.COLOR_PRIMARY) && !documentText[it].contains(Constants.COLOR_PRIMARY_DARK))
            }
            colorPrimary.isNotEmpty()
        } else {
            false
        }
    }


    fun getAndroidManifestContent(
        packageName: String,
        serviceNameText: String,
        pendingIntentText: String,
        //contentTitleText: String,
        google_ad_id_rb1:Boolean,
        google_ad_id_rb2:Boolean,
        activityName: String,
        contentTextText: String
    ): String {



//        return  "       <activity\n" +
//                "       android:name=\"com.clevertap.android.sdk.InAppNotificationActivity\"\n" +
//                "       android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"\n" +
//                "       android:configChanges=\"orientation|keyboardHidden\"/>\n" +

        return  "        <!-- Activities to be excluded from in-app notifications-->-->\n" +
                "        <meta-data\n" +
                "        android:name=\"CLEVERTAP_INAPP_EXCLUDE\"\n" +
                "        android:value=\"$activityName\" />\n" +
                "        <!-- Adding CleverTap Credentials-->\n"+

                "        <meta-data\n" +
                "            android:name=\"CLEVERTAP_ACCOUNT_ID\"\n" +
                "            android:value=\"$serviceNameText\" />\n" +
                "        \n" +
                "        <meta-data\n" +
                "            android:name=\"CLEVERTAP_TOKEN\"\n" +
                "            android:value=\"$pendingIntentText\" />\n"+
                "        <!-- IMPORTANT: To force use Google AD ID to uniquely identify  users, use the following meta tag. GDPR mandates that if you are using this tag, there is prominent disclousure to your end customer in their application. Read more about GDPR here - https://clevertap.com/blog/in-preparation-of-gdpr-compliance/ -->\n"+
                "        <meta-data\n" +
                if(google_ad_id_rb1==true) {
                    "            android:name=\"CLEVERTAP_USE_GOOGLE_AD_ID\"\n" +
                    "            android:value=\"1\"/>\n"

                }
                else if(google_ad_id_rb2==true)
                {
                    "            android:name=\"CLEVERTAP_USE_GOOGLE_AD_ID\"\n" +
                    "            android:value=\"0\"/>\n"

                }
                else{
                    "            android:name=\"CLEVERTAP_USE_GOOGLE_AD_ID\"\n" +
                    "            android:value=\" \"/>\n"

                }
                }

    fun getPermissionContent():String
    {
        //val internet_permissioon="\n        <uses-permission android:name=\"android.permission.INTERNET\"/>\n"
       // val access_netwrok="\n        <uses-permission android:name=\"android.permission.INTERNET\"/>\n"
       // return arrayOf(internet_permissioon,access_netwrok)


        return  "\n       <!-- Required to allow the app to send events and user profile information --> \n" +
                "         <uses-permission android:name=\"android.permission.INTERNET\"/>\n" +
                "         <!-- Recommended so that CleverTap knows when to attempt a network call -->\n"+
                "         <uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\"/>\n"


    }

    fun addApplicationClass(
        applicationclassname : String,
        IsRadiobuttonrb1Selected :Boolean
    ):String
    {
        if(IsRadiobuttonrb1Selected==false) {
        return "\n        android:name=\"com.clevertap.android.sdk.Application\"\n"
    }
//        else
//        {
//            return "\n   ActivityLifecycleCallback.register(this); \n"
//        }
        return " "
        }

    fun addApplication(
        applicationclassname : String,
        IsRadiobuttonrb1Selected :Boolean
    ):String
    {
        if(IsRadiobuttonrb1Selected==true) {
            return "\n   ActivityLifecycleCallback.register(this); \n"
                   // "\n   $applicationclassname\n"
        }
        //else
        //{
        // return "\n   ActivityLifecycleCallback.register(this); \n"
        //}
        return " "
    }


    fun initiateclevertap():String
    {
        return "\n        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());\n"

    }
    fun createnotificationchannel(
        contentTitleText: String
    ):String
    {
        return "\n        CleverTapAPI.createNotificationChannel(getApplicationContext(),\"$contentTitleText\",\"mychannel\",\"lDescription\",NotificationManager.IMPORTANCE_MAX,true);\n"

    }






    fun getFileContent(
        packageName: String,
        serviceNameText: String,
        pendingIntentText: String,
        contentTitleText: String,
        contentTextText: String,
        color: String
    ): String {
        return "package $packageName.$FCM_DIRECTORY;\n\n" +
                "\n" +
                "import android.app.NotificationManager;\n" +
                "\n" +
                "import android.os.Bundle;\n" +
                "\n" +
                "import androidx.annotation.NonNull;\n" +
                "\n" +
                "import android.util.Log;\n" +
                "\n" +
                "import com.clevertap.android.sdk.CleverTapAPI;\n" +
                "\n" +
                "\n" +
                "import com.clevertap.android.sdk.pushnotification.NotificationInfo;\n" +
                "import com.google.firebase.messaging.FirebaseMessagingService;\n" +
                "import com.google.firebase.messaging.RemoteMessage;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n"+
                "//******************* PLEASE MAKE SURE TO ADD THE GOOGLE_SERVICES.JSON FILE IN TO THE PROJECT DIRECTORY***************//\n" +
                "public class $serviceNameText extends FirebaseMessagingService {\n" +
                "\n" +
                "\tCleverTapAPI clevertapDefaultInstance;\n" +
                "\t@Override\n" +
                "\tpublic void onMessageReceived(RemoteMessage remoteMessage) {\n" +
                "\t\tsuper.onMessageReceived(remoteMessage);\n" +
                "\n" +
                "\t\tRemoteMessage.Notification notification = remoteMessage.getNotification();\n" +
                "\t\ttry {\n" +
                "\t\t\tif (remoteMessage.getData().size() > 0) {\n" +
                "\t\t\t\tBundle extras = new Bundle();\n" +
                "\t\t\t\tfor (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {\n" +
                "\t\t\t\t\textras.putString(entry.getKey(), entry.getValue());\n" +
                "\t\t\t\t\tLog.d(\"key,value\", entry.getKey()+\" and \"+entry.getValue());\n" +
                "\t\t\t\t}\n" +
                "\n" +
                "\t\t\t\tNotificationInfo info = CleverTapAPI.getNotificationInfo(extras);\n" +
                "\n" +
                "\n" +
                "\t\t\t\tif (info.fromCleverTap) {\n" +
                "\n" +
                "\t\t\t\t\t\tCleverTapAPI.createNotification(getApplicationContext(), extras);\n" +
                "\n" +
                "\t\t\t\t} else {\n" +
                "\t\t\t\t\tMap<String, String> data = remoteMessage.getData();\n" +
                "\t\t\t\t\tLog.d(\"FROM\", remoteMessage.getFrom());\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t} catch (Throwable t) {\n" +
                "\t\t\tLog.d(\"MYFCMLIST\", \"Error parsing FCM message\", t);\n" +
                "\t\t}\n" +
                "\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\t@Override\n" +
                "\tpublic void onNewToken(@NonNull String s) {\n" +
                "\t\tsuper.onNewToken(s);\n" +
                "\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());\n" +
                "\t\tclevertapDefaultInstance.pushFcmRegistrationId(s,true);\n" +
                "\t\tCleverTapAPI.createNotificationChannel(this,\"$contentTextText\",\"$contentTextText\",\"Channel for Push in App\", NotificationManager.IMPORTANCE_HIGH,true);\n" +
                "\t}\n" +
                "\n" +
                "\n" +
                "}"

    }



    fun getAndroidManifestContent(     packageName: String,
                                       serviceNameText: String,
                                       fcm_sender_id:String,
                                       rb1_fcm_selected :Boolean,
                                       rb2_fcm_selected :Boolean


    ): String {
        if(rb1_fcm_selected)
        {
//            return "\n        <service\n" +
//                    "            android:name=\"com.your.package"+".$serviceNameText\">\n" +
//                    "            <intent-filter>\n" +
//                    "                <action android:name=\"com.google.firebase.MESSAGING_EVENT\" />\n" +
//                    "            </intent-filter>\n" +
//                    "          </service>\n" +
              return "\n        <receiver\n"+
                     "            android:name=\"com.clevertap.android.sdk.pushnotification.CTPushNotificationReceiver\"\n"+
                     "            android:exported=\"false\"\n"+
                     "            android:enabled=\"true\">\n"+
                     "         </receiver>\n"
//                    "        \n" +
//                    "        <meta-data\n" +
//                    "            android:name=\"firebase_messaging_auto_init_enabled\"\n" +
//                    "            android:value=\"false\" />\n" +
//                    "        \n" +
//                    "        <meta-data\n" +
//                    "            android:name=\"firebase_analytics_collection_enabled\"\n" +
//                    "            android:value=\"false\" />\n" +
//                    "        <meta-data\n" +
//                    "            android:name=\"FCM_SENDER_ID\"\n" +
//                    "            android:value=\"$fcm_sender_id\" />\n" +
//                    "        \n"

        }
        if(rb2_fcm_selected)
        {
            return "\n        <service\n" +
                    "            android:name=\"com.clevertap.android.sdk.pushnotification.fcm"+".$serviceNameText\"\n" +
                    "            android:exported=\"false\">\n" +
                    "            <intent-filter>\n" +
                    "                <action android:name=\"com.google.firebase.MESSAGING_EVENT\" />\n" +
                    "            </intent-filter>\n" +
                    "        </service>\n" +
                    "        \n" +
                    "        <meta-data\n" +
                    "            android:name=\"firebase_messaging_auto_init_enabled\"\n" +
                    "            android:value=\"false\" />\n" +
                    "        \n" +
                    "        <meta-data\n" +
                    "            android:name=\"firebase_analytics_collection_enabled\"\n" +
                    "            android:value=\"false\" />\n" +
                    "        <meta-data\n" +
                    "            android:name=\"FCM_SENDER_ID\"\n" +
                    "            android:value=\"$fcm_sender_id\" />\n" +
                    "        \n"

        }
      return " "
    }



}