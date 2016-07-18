package zhangruofan.notificationdemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TimeUtils
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

import zhangruofan.notificationdemo.R
import zhangruofan.notificationdemo.util.NotificationBuilder

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        longTxtBtn.setOnClickListener {
            NotificationBuilder.buildLongTextNotification()
        }

        bigImageBtn.setOnClickListener {
            Single.create<String> {
                NotificationBuilder.buildImageNotification()
                it.onSuccess("")
            }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        Log.e(TAG, "构建通知成功")
                    }, {
                        Log.e(TAG, "构建通知失败:$it")
                    })
        }

        inboxBtn.setOnClickListener {
            Single.create<String> {
                NotificationBuilder.buildInboxNotification()
                it.onSuccess("")
            }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        Log.e(TAG, "构建通知成功")
                    }, {
                        Log.e(TAG, "构建通知失败:$it")
                    })
        }
    }
}
