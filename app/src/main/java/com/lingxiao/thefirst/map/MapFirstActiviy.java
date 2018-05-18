package com.lingxiao.thefirst.map;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;
import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapFirstActiviy extends BaseActivity implements View.OnClickListener {

    private MapView mMapView = null;
    //初始化地图控制器对象
    private AMap aMap = null;
    private UiSettings mUiSettings;
    private TextView mTvStandard;//标准地图
    private TextView mTvSatellite;//卫星地图
    private TextView mTvNightMode;//夜间模式

    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            //不检测GPS是否开启
//            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
    private static final int PERMISSON_REQUESTCODE = 0;
    private static final int SETTING_RESULT = 100;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_map_first;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("地图测试一");
        hideBackButton();

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        mTvStandard = findViewById(R.id.tv_standard);
        mTvSatellite = findViewById(R.id.tv_satellite);
        mTvNightMode = findViewById(R.id.tv_night_mode);

        mTvStandard.setOnClickListener(this);
        mTvSatellite.setOnClickListener(this);
        mTvNightMode.setOnClickListener(this);

        checkPermissions(needPermissions);
    }

    private void initMapView() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            mUiSettings = aMap.getUiSettings();
        }

        //定位蓝点
        MyLocationStyle myLocationStyle;
        //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        // 连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle = new MyLocationStyle();
        //设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
        myLocationStyle.showMyLocation(true);
        //设置只定位一次，且将视角移动到地图中心点。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        // 设置默认定位按钮是否显示，非必需设置。
        // aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);

        //3D 地图 SDK中默认会关闭室内地图显示，如有需要可使用类AMap 中的 showIndoorMap(boolean enable)自行开启。
        //true：显示室内地图；false：不显示；
        aMap.showIndoorMap(true);
        //设置缩放级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));

        //设置地图底图语言，目前支持中文底图和英文底图
        aMap.setMapLanguage(AMap.CHINESE);

        //设置缩放图标是否显示
        mUiSettings.setZoomControlsEnabled(false);
        //设置指南针是否显示
        mUiSettings.setCompassEnabled(false);

//        setMapCustomStyleFile(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_map_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (aMap == null) {
            aMap = mMapView.getMap();
            mUiSettings = aMap.getUiSettings();
        }
        switch (item.getItemId()) {
            case R.id.language:
                if (TextUtils.equals("中文", item.getTitle())) {
                    item.setTitle("英文");
                    aMap.setMapLanguage(AMap.CHINESE);
                } else {
                    item.setTitle("中文");
                    aMap.setMapLanguage(AMap.ENGLISH);
                }
                return true;
            case R.id.location:
                if (TextUtils.equals("显示定位", item.getTitle())) {
                    item.setTitle("隐藏定位");
                    mUiSettings.setMyLocationButtonEnabled(true);
                } else {
                    item.setTitle("显示定位");
                    mUiSettings.setMyLocationButtonEnabled(false);
                }
                return true;
            case R.id.zoom:
                if (TextUtils.equals("显示缩放", item.getTitle())) {
                    item.setTitle("隐藏缩放");
                    mUiSettings.setZoomControlsEnabled(true);
                } else {
                    item.setTitle("显示缩放");
                    mUiSettings.setZoomControlsEnabled(false);
                }
                return true;
            case R.id.compass:
                if (TextUtils.equals("显示指南针", item.getTitle())) {
                    item.setTitle("隐藏指南针");
                    mUiSettings.setCompassEnabled(true);
                } else {
                    item.setTitle("显示指南针");
                    mUiSettings.setCompassEnabled(false);
                }
                return true;
            case R.id.scale:
                if (TextUtils.equals("显示比例尺", item.getTitle())) {
                    item.setTitle("隐藏比例尺");
                    mUiSettings.setScaleControlsEnabled(true);
                } else {
                    item.setTitle("显示比例尺");
                    mUiSettings.setScaleControlsEnabled(false);
                }
                return true;
            case R.id.shot:
                mapScreenShot();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //地图截屏
    private void mapScreenShot() {
        aMap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
            @Override
            public void onMapScreenShot(Bitmap bitmap) {

            }

            @Override
            public void onMapScreenShot(Bitmap bitmap, int status) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                if (null == bitmap) {
                    return;
                }
                try {
                    FileOutputStream fos = new FileOutputStream(
                            Environment.getExternalStorageDirectory() + "/test_"
                                    + sdf.format(new Date()) + ".png");
                    boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    try {
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    StringBuffer buffer = new StringBuffer();
                    if (b)
                        buffer.append("截屏成功 ");
                    else {
                        buffer.append("截屏失败 ");
                    }
                    if (status != 0)
                        buffer.append("地图渲染完成，截屏无网格");
                    else {
                        buffer.append("地图未渲染完成，截屏有网格");
                    }
                    Toast.makeText(MapFirstActiviy.this, buffer.toString(), Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (isNeedCheck) {
//            checkPermissions(needPermissions);
//        }
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    private void setMapCustomStyleFile(Context context) {
        String styleName = "style_json.json";
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        String filePath = null;
        try {
            inputStream = context.getAssets().open(styleName);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            filePath = context.getFilesDir().getAbsolutePath();
            File file = new File(filePath + "/" + styleName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            outputStream.write(b);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();

                if (outputStream != null)
                    outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        aMap.setCustomMapStylePath(filePath + "/" + styleName);

        aMap.showMapText(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_standard:
                if (aMap != null) {
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                }
                break;
            case R.id.tv_satellite:
                if (aMap != null) {
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                }
                break;
            case R.id.tv_night_mode:
                if (aMap != null) {
                    aMap.setMapType(AMap.MAP_TYPE_NIGHT);
                }
                break;
        }
    }

    //检查权限
    private void checkPermissions(String... permissions) {
        //获取权限列表
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            //list.toarray将集合转化为数组
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        } else {
            initMapView();
        }
    }

    //获取权限集中需要申请权限的列表
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        //for (循环变量类型 循环变量名称 : 要被遍历的对象)
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    permission) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, permission)) {
                needRequestPermissonList.add(permission);
            }
        }
        return needRequestPermissonList;
    }

    //检测是否说有的权限都已经授权
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {      //没有授权
                showMissingPermissionDialog();              //显示提示信息
                isNeedCheck = false;
            } else {
                initMapView();
            }
        }
    }

    //显示提示信息
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("开启定位");
        builder.setMessage("是否开启定位权限");

        // 拒绝, 退出应用
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    //启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SETTING_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTING_RESULT) {
            checkPermissions(needPermissions);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
