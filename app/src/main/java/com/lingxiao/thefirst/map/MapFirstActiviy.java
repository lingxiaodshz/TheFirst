package com.lingxiao.thefirst.map;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;
import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MapFirstActiviy extends BaseActivity implements View.OnClickListener {

    private MapView mMapView = null;
    //初始化地图控制器对象
    private AMap aMap = null;
    private UiSettings mUiSettings;
    private TextView mTvStandard;//标准地图
    private TextView mTvSatellite;//卫星地图
    private TextView mTvNightMode;//夜间模式


    @Override
    public int getLayoutResource() {
        return R.layout.activity_map_first;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("地图测试一");


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
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
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
            default:
                return super.onOptionsItemSelected(item);
        }
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
}
