package com.lingxiao.thefirst.mine.jetpack.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author luckw
 * @date   2020/5/25
 */

@Entity(tableName = "users")
class User {

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo
    var name: String = ""

    @ColumnInfo
    var age: Int = 0

    override fun toString(): String {
        return "{User, uid : $uid, name : $name, age : $age}"

    }
}