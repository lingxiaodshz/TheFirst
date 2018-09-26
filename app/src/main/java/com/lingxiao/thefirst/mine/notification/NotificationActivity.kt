package com.lingxiao.thefirst.mine.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.view.View
import com.lingxiao.thefirst.MainActivity
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_common -> {
//                showToast("common")
                showCommonNotification()
            }
            R.id.tv_fold -> {
                showToast("fold")
                showFoldNotification()
            }
            R.id.tv_hang -> {
                showToast("hang")
                showHangNotification()
            }
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_notification
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_common.setOnClickListener(this)
        tv_fold.setOnClickListener(this)
        tv_hang.setOnClickListener(this)

    }

    private fun showCommonNotification(): Unit {
//        var builder = NotificationCompat.Builder(this,"common_id")
        var builder = NotificationCompat.Builder(this)
        var intent = Intent(this, MainActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        builder.setContentIntent(pendingIntent)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_dog))
        builder.setAutoCancel(true)
        builder.setContentTitle("普通通知")
        builder.setContentText("普通通知 啦啦啦")
        // 注意安卓8.0必须设置channelId，在此处设置可以，也可以在Builder创建时作为参数传入
        builder.setChannelId("common_id")
        var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            var channel = NotificationChannel("common_id", "common_name", NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(channel)
//        }
        notificationManager.notify(10, builder.build())
    }

    private fun showFoldNotification(): Unit {
        var pendingIntent = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), 0);
        var notify = Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏中的小图片，尺寸一般建议在24×24， 这里也可以设置大图标
                .setTicker("有新短消息了！")
                .setContentTitle("Title")// 设置显示的标题
                .setContentText("This is message content")// 消息的详细内容
                .setContentIntent(pendingIntent) // 关联PendingIntent
                .setNumber(1) // 在TextView的右方显示的数字，可以在外部定义一个变量，点击累加setNumber(count),这时显示的和
                .build() // 需要注意build()是在API level16及之后增加的，在API11中可以使用getNotificatin()来代替
        var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notify)
    }

    private fun showHangNotification(): Unit {

    }
}