package com.lingxiao.thefirst.mine.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.view.View
import android.widget.RemoteViews
import com.lingxiao.thefirst.MainActivity
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_common -> {
                showCommonNotification()
            }
            R.id.tv_fold -> {
                showFoldNotification()
            }
            R.id.tv_hang -> {
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
        var builder = NotificationCompat.Builder(this, "common_id")
//        var builder = NotificationCompat.Builder(this)
        var intent = Intent(this, MainActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this, 1, intent, 0)
        builder.setContentIntent(pendingIntent)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_dog))
        builder.setAutoCancel(true)
        builder.setContentTitle("普通通知")
        builder.setContentText("普通通知 啦啦啦啦啦啦啦啦啦")
        // 注意安卓8.0必须设置channelId，在此处设置可以，也可以在Builder创建时作为参数传入
        // builder.setChannelId("common_id")
        var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 注意8.1的版本必须创建通道，8.0的版本三星S9不需要
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 注意 importance这个字段NotificationManager.IMPORTANCE_DEFAULT这个代表普通效果，来通知时不会悬挂出现，只会在状态栏显示图标
            // 如果NotificationManager.IMPORTANCE_HIGH，则会出现悬挂效果，几秒后自动消失
            var channel = NotificationChannel("common_id", "common_name", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, builder.build())
    }

    private fun showFoldNotification(): Unit {
        var pendingIntent = PendingIntent.getActivity(this, 2,
                Intent(this, MainActivity::class.java), 0)

        var remoteViews = RemoteViews(packageName, R.layout.activity_notification_fold)

        var builder = NotificationCompat.Builder(this, "fold_id")
        builder.setContentIntent(pendingIntent)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_dog))
        builder.setAutoCancel(true)
        builder.setContentTitle("折叠式通知")
        builder.setContentText("this is a fold notification~~~~~")
        builder.setCustomBigContentView(remoteViews)

        var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel("fold_id", "fold_name", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(notificationChannel)
        }
        manager.notify(2, builder.build())
    }

    // TODO 悬挂式通知在8.0以下正常显示，显示之后浮在页面上不会消失，在8.0以上效果异常，暂未解决
    private fun showHangNotification(): Unit {
        var builder = NotificationCompat.Builder(this, "hang_id")
        var pendingIntent = PendingIntent.getActivity(this, 3,
                Intent(this, MainActivity::class.java), 0)
        builder.setContentIntent(pendingIntent)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_dog))
        builder.setAutoCancel(true)
        builder.setContentTitle("悬挂式通知")
        builder.setContentText("this is a hang notification~~~~~")

        var hangIntent=Intent()
        hangIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        hangIntent.setClass(this,MainActivity::class.java)
        val hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        builder.setFullScreenIntent(hangPendingIntent,true)

        var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel("hang_id", "hang_name", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(notificationChannel)
        }
        manager.notify(3, builder.build())
    }
}