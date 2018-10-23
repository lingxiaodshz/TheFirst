#include "com_lingxiao_thefirst_mine_ndk_HelloJni.h"

JNIEXPORT jstring JNICALL Java_com_lingxiao_thefirst_mine_ndk_HelloJni_getCString
        (JNIEnv *env, jclass clazz){
        //通过env里面的转换方法，将字符串转成UTF格式的jstring
    return env->NewStringUTF("I am from C String!");
}