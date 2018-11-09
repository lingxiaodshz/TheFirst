package com.lingxiao.thefirst.mine.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/11/9.
 */

public class TeacherBean implements Parcelable {

    private String name;

    public TeacherBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected TeacherBean(Parcel in) {
        this.name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public static final Creator<TeacherBean> CREATOR = new Creator<TeacherBean>() {
        @Override
        public TeacherBean createFromParcel(Parcel in) {
            return new TeacherBean(in);
        }

        @Override
        public TeacherBean[] newArray(int size) {
            return new TeacherBean[size];
        }
    };
}
