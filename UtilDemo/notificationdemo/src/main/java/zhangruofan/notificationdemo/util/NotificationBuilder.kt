package zhangruofan.notificationdemo.util

import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import zhangruofan.notificationdemo.NotificationApplication
import zhangruofan.notificationdemo.R

/**
 * Created by zhangruofan on 16-7-15.
 * This class presents the usage of Notification
 *
 * see: [material design notifications](http://www.materialdoc.com/big-style-notifications/)
 */
object NotificationBuilder {

    fun buildLongTextNotification() {
        val builder = NotificationCompat.Builder(NotificationApplication.appCtx);

        val style = NotificationCompat.BigTextStyle(builder)

        style.setBigContentTitle("Big Content Title")
                .bigText("Big Text Style is used to show large quantities of text. The notification body can hold around 450 characters. The rest of the text will be trimmed without the use of an ellipsis.")
                .setSummaryText("summary text: 594074064@qq.com")

        val notification = builder
                .setContentTitle("Title")
                .setContentText("This is a notification!")
                .setSmallIcon(R.mipmap.avatar)
                .build();
        NotificationManagerCompat.from(NotificationApplication.appCtx).notify(0x1234, notification)
    }

    fun buildImageNotification() {
        val builder = NotificationCompat.Builder(NotificationApplication.appCtx);

        val style = NotificationCompat.BigPictureStyle(builder)

        val avatar = BitmapFactory.decodeResource(NotificationApplication.appCtx.resources, R.mipmap.logo)
        val image = BitmapFactory.decodeResource(NotificationApplication.appCtx.resources, R.mipmap.iphone7)

        style.setBigContentTitle("This is Content Title")
                .bigPicture(image)
                .bigLargeIcon(avatar)
                .setSummaryText("this is a summary text! \n this is a summary text!")

        val notification = builder
                .setContentTitle("Default Title")
                .setContentText("Default content!")
                .setLargeIcon(avatar)
                .setSmallIcon(R.mipmap.avatar)
                .build();
        NotificationManagerCompat.from(NotificationApplication.appCtx).notify(0x2234, notification)
    }

    fun buildInboxNotification() {
        val builder = NotificationCompat.Builder(NotificationApplication.appCtx);

        val style = NotificationCompat.InboxStyle(builder)

        style.setBigContentTitle("This is Content Title")
                .addLine("Lily :\t Hi Allen!")
                .addLine("Tom :\t Text me later.")
                .addLine("Henry :\t Oh man!")
                .setSummaryText("you got few messages!")

        val avatar = BitmapFactory.decodeResource(NotificationApplication.appCtx.resources, R.mipmap.logo)

        val notification = builder
                .setContentTitle("Default Title")
                .setContentText("Default content!")
                .setSmallIcon(R.mipmap.avatar)
                .setLargeIcon(avatar)
                .build();
        NotificationManagerCompat.from(NotificationApplication.appCtx).notify(0x3234, notification)
    }
}