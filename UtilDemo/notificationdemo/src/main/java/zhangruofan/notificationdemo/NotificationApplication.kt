package zhangruofan.notificationdemo

import android.app.Application

/**
 * Created by zhangruofan on 16-7-15.
 */
class NotificationApplication : Application() {

    companion object {
        lateinit var appCtx: NotificationApplication
    }

    override fun onCreate() {
        super.onCreate()
        appCtx = this
    }
}