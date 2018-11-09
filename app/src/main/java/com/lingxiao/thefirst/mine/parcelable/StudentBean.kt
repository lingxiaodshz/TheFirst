package com.lingxiao.thefirst.mine.parcelable

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Administrator on 2018/11/9.
 */
class StudentBean(name: String, age: Int) : Parcelable {
    private var name = name
    private var age = age

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt())

    // 将变量序列化
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        if (dest != null) {
            dest.writeString(name)
            dest.writeInt(age)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StudentBean> {
        // 反序列化获取数据的过程
        override fun createFromParcel(parcel: Parcel): StudentBean {
            return StudentBean(parcel)
        }

        override fun newArray(size: Int): Array<StudentBean?> {
            return arrayOfNulls(size)
        }
    }

    fun getName(): String {

        return name
    }
    fun getAge(): Int {
        return age
    }


}