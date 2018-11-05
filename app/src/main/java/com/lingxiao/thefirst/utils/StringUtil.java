package com.lingxiao.thefirst.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 * Some common string manipulation utilities.
 */
public final class StringUtil {

    /****
     * 创建显示差异颜色的字符串
     * 支持服务器传输 字体大小  字体颜色
     *
     * @param str
     * @return
     */
    public static SpannableStringBuilder createJsonDataSpan(String str, Context context) {
        SpannableStringBuilder spanStringBuilder = new SpannableStringBuilder();
        try {

            StringTokenizer datas = new StringTokenizer(str, "$");

            while (datas.hasMoreElements()) {
                String data = (String) datas.nextElement();

                if (!data.equals("") && data.contains("{")) {
                    int indexbegin = data.indexOf("{");
                    int indexend = data.indexOf("}");
                    JSONObject response = null;
                    String jsdondata = data.substring(indexbegin, indexend + 1);
                    response = new JSONObject(jsdondata);

                    int fontPixel = Integer.parseInt(response.optString("fontPixel", "-100"));
                    String content = response.optString("content");
                    String color = response.optString("color", "");

                    data = data.replace(jsdondata, content);
                    data = data.replace("$", "");

                    int beginlocation = data.indexOf(content);
                    int endlocation = data.indexOf(content) + content.length();

                    if (data != null && !data.trim().equals("")) {
                        SpannableStringBuilder builder = new SpannableStringBuilder(data);

                        if (fontPixel != -100) {
//							int resourceId =  getResourceSourceId("dd_dimen_"+fontSize+"px" ,context);
                            int resourceId = getResourceId(context, "dd_dimen_" + fontPixel + "px", "dimen");
//  							UCARDebug.Log("createJsonDataSpan", "createJsonDataSpan resID = " +resourceId);
                            int size = context.getResources().getDimensionPixelSize(resourceId);
                            AbsoluteSizeSpan span_ = new AbsoluteSizeSpan(size);
                            builder.setSpan(span_, beginlocation, endlocation, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }

                        if (color != null && !color.trim().equals("")) {
                            if (color.indexOf("#") == -1) {
                                color = "#" + color;
                            }
                            ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor(color));
                            builder.setSpan(span, beginlocation, endlocation, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                        }

                        spanStringBuilder.append(builder);
                    }

                } else {
                    spanStringBuilder.append(data);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return spanStringBuilder;
    }

    private static int getResourceId(Context context, String name, String type) {
        String packageName = context.getPackageName();
        Resources themeResources = null;
        PackageManager pm = context.getPackageManager();
        try {
            themeResources = pm.getResourcesForApplication(packageName);
            return themeResources.getIdentifier(name, type, packageName);
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 解析带有一种颜色字体的文本
     *
     * @param des  距离出发时间还剩${"content":"01"}小时${"content":"48"}分
     * @param color 颜色数组  dbac6f
     * retrn  距离出发时间还剩<font color='#dbac6f'>01</font>小时<font color='#dbac6f'>48</font>分<br />
     */
    private static final String DEFAULT_COLOR = "dbac6f";  //专车通用黄色4.0期开始使用
    public static CharSequence parseColorString(String des, String... color) {
        String defaultColor = DEFAULT_COLOR;
        if (color.length > 0) {
            defaultColor = color[0];
        }
        return parseColorString(des, false, defaultColor);
    }

    private static CharSequence parseColorString(String des, boolean isBold, String... color) {
        String defaultColor = DEFAULT_COLOR;
        if (color.length > 0) {
            defaultColor = color[0];
        }
        try {
            StringBuffer sb = null;
            if (!TextUtils.isEmpty(des) && des.contains("$")) {
                des = des.replace("\\n","<br>");
                des = des.replace("\n","<br>");
                String u[] = des.split("\\$");
                sb = new StringBuffer();
                for (String c2 : u) {
                    if (!c2.equals("") && c2.contains("{")) {
                        int indexbegin = c2.indexOf("{");
                        int indexend = c2.indexOf("}");
                        JSONObject response = null;
                        response = new JSONObject(c2.substring(indexbegin, indexend + 1));
                        sb.append(getSZZCAlertColorStr(defaultColor,
                                response.optString("content"), isBold) + c2.substring(indexend + 1, c2.length()));
                    } else {
                        sb.append(c2);
                    }
                }
                return Html.fromHtml(sb.toString());
            } else {
                return Html.fromHtml(des);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private static String getSZZCAlertColorStr(String color, String str, boolean isBold) {
        if (color == null || "".equals(color)) {
            color = "FF0000";
        }

        if (str == null) {
            str = "";
        }

        String result = null;
        if (contains(color, '#')) {
            if (isBold) {
                result = "<font color='" + color + "'><big><b>" + str + "</b></big></font>";
            } else {
                result = "<font color='" + color + "'>" + str + "</font>";
            }

        } else {
            if (isBold) {
                result = "<font color='#" + color + "'><big><b>" + str + "</b></big></font>";
            } else {
                result = "<font color='#" + color + "'>" + str + "</font>";
            }
        }

        return result;

    }

    private static boolean contains(String str, char searchChar) {
        if (isEmpty(str)) {
            return false;
        }
        return str.indexOf(searchChar) >= 0;
    }

    private static boolean isEmpty(String s) {
        return makeSafe(s).length() == 0;
    }

    private static String makeSafe(String s) {
        return (s == null) ? "" : s;
    }

    /**
     *如果是整数,不显示小数点,如果小数点有一位,则保留成两位
     */
    public static String doubleFormat(double d){
        return (d - (double)(int)d) == 0 ? String.valueOf((int)d) :
                new DecimalFormat("0.00").format(d);
    }

    /**
     * 1.00 ->1
     * 1.0 ->1
     * 1.10 ->1.1
     * @param d
     * @return
     */
    public static String doubleFormatDouble(double d) {

        String s = new DecimalFormat("0.00").format(d) + "";
        if(s.indexOf(".") > 0){
            //正则表达
            s = s.replaceAll("0+?$", "");//去掉后面无用的零
            s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return s;
    }

}
