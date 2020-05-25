package com.lingxiao.thefirst.mine.jetpack.room

import android.arch.persistence.room.*

/**
 * @author luckw
 * @date   2020/5/25
 */

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Insert //vararg是kotlin的可变参数字段
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("select * from users")
    fun getAllUsers(): List<User>

    @Update
    fun update(user: User)
}