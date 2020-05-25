package com.lingxiao.thefirst.mine.jetpack.room

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.*
import android.content.Context

/**
 * @author luckw
 * @date   2020/5/25
 */

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDataBase : RoomDatabase() {

    //必须包含一个具有0个参数且返回带@Dao注释的类的抽象方法，
    // 也就是返回 @Dao 标记的访问数据库的接口类
    abstract fun userDao(): UserDao

    //使用单例，提供外部获得AppDataBase 实例的创建
    companion object{
        private var appDataBase: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase {
            if (appDataBase == null) {
                synchronized(AppDataBase::class.java) {
                    appDataBase = Room.databaseBuilder(context.applicationContext,
                            AppDataBase::class.java, "user_database").build()
                }
            }
            return appDataBase!!
        }
    }
}