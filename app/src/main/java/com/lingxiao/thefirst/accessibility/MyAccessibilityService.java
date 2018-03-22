package com.lingxiao.thefirst.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Administrator on 2018/3/22.
 */

public class MyAccessibilityService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();//事件类型
        Log.e("MyAccessibilityService", "packageName:" + event.getPackageName() + "");//响应事件的包名，也就是哪个应用才响应了这个事件
        Log.e("MyAccessibilityService", "source:" + event.getSource() + "");//事件源信息
        Log.e("MyAccessibilityService", "source class:" + event.getClassName() + "");//事件源的类名，比如android.widget.TextView
        Log.e("MyAccessibilityService", "event type(int):" + eventType + "");

        switch (eventType) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:// 通知栏事件
                Log.e("MyAccessibilityService", "event type:TYPE_NOTIFICATION_STATE_CHANGED");
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED://窗体状态改变
                Log.e("MyAccessibilityService", "event type:TYPE_WINDOW_STATE_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED://View获取到焦点
                Log.e("MyAccessibilityService", "event type:TYPE_VIEW_ACCESSIBILITY_FOCUSED");
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
                Log.e("MyAccessibilityService", "event type:TYPE_VIEW_ACCESSIBILITY_FOCUSED");
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
                Log.e("MyAccessibilityService", "event type:TYPE_GESTURE_DETECTION_END");
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                Log.e("MyAccessibilityService", "event type:TYPE_WINDOW_CONTENT_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                Log.e("MyAccessibilityService", "event type:TYPE_VIEW_CLICKED");
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                Log.e("MyAccessibilityService", "event type:TYPE_VIEW_TEXT_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                Log.e("MyAccessibilityService", "event type:TYPE_VIEW_SCROLLED");
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                Log.e("MyAccessibilityService", "event type:TYPE_VIEW_TEXT_SELECTION_CHANGED");
                break;
        }

        for (CharSequence txt : event.getText()) {
            Log.e("MyAccessibilityService", "text:" + txt);//输出当前事件包含的文本信息
        }
    }

    @Override
    public void onInterrupt() {
        InputMethodManager manager;

    }


}
