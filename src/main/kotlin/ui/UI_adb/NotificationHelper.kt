package UI_adb

import com.intellij.notification.*
import util.Constants

object NotificationHelper {
    //private val INFO = NotificationGroup("ADB Idea (Logging)", NotificationDisplayType.NONE, true, null, null)
    //private val ERRORS = NotificationGroup("ADB Idea (Errors)", NotificationDisplayType.BALLOON, true, null, null)

//    private val INFO1= NotificationGroupManager.getInstance().getNotificationGroup("Display Notification")
//    .createNotification(Constants.NOTIFICATION_TITLE,"Huawei Push has successfully been added.", NotificationType.INFORMATION)
//
//    private val ERRORS1= NotificationGroupManager.getInstance().getNotificationGroup("Display Notification")
//        .createNotification(Constants.NOTIFICATION_TITLE,"Huawei Push has successfully been added.", NotificationType.INFORMATION)

//    fun info(message: String) = sendNotification(message, NotificationType.INFORMATION, INFO1)
//
//    fun error(message: String) = sendNotification(message, NotificationType.ERROR, ERRORS1)

    private fun sendNotification(message: String, notificationType: NotificationType, notificationGroup: Notification) {
        notificationGroup.notify(null)
    }

    private fun escapeString(string: String) = string.replace("\n".toRegex(), "\n<br />")
}