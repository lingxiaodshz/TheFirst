package com.lingxiao.thefirst.map;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;
import com.lingxiao.thefirst.utils.KeyboardUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/11/14.
 */

public class MapPOIActivity extends BaseActivity implements PoiSearch.OnPoiSearchListener {
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.et_address)
    EditText mEtAddress;

    private static final int PERMISSON_REQUESTCODE = 0;
    private static final int SETTING_RESULT = 100;

    private AMap mAMap;
    private UiSettings mUiSettings;
    private PoiSearch.Query mQuery;
    private LatLonPoint curPoint;
    private PoiSearch mPoiSearch;
    private PoiResult mPoiResult;
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            //不检测GPS是否开启
//            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_map_poi;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);

        checkPermissions(needPermissions);

    }

    private void initMapView() {
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mUiSettings = mAMap.getUiSettings();
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
        mAMap.setMyLocationStyle(myLocationStyle);
        // 设置默认定位按钮是否显示，非必需设置。
        // mAMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        mAMap.setMyLocationEnabled(true);

        //3D 地图 SDK中默认会关闭室内地图显示，如有需要可使用类AMap 中的 showIndoorMap(boolean enable)自行开启。
        //true：显示室内地图；false：不显示；
        mAMap.showIndoorMap(true);
        //设置缩放级别
        mAMap.moveCamera(CameraUpdateFactory.zoomTo(17));

        //设置地图底图语言，目前支持中文底图和英文底图
        mAMap.setMapLanguage(AMap.CHINESE);

        //设置缩放图标是否显示
        mUiSettings.setZoomControlsEnabled(false);
        //设置指南针是否显示
        mUiSettings.setCompassEnabled(false);

//        setMapCustomStyleFile(this);
        mAMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                curPoint = new LatLonPoint(location.getLatitude(), location.getLongitude());
            }
        });
    }

    /**
     * 搜索思路，先设置一个Query对象，这个对象中需要设置需要查找的信息以及查询范围，如果不设置查询范围默认为全国范围内
     * 设置每页返回多少条数据，设置查找第一页，数字0表示第一页
     * 判断当前的定位点是否为空，这个值是启动时自动定位来的
     * 创建一个PoiSearch，将刚才的请求数据对象Query作为参数传递进去，并设置查询监听回调
     * 设置查询范围SearchBound中需要传入定位点信息，周边距离，5000表示5000米的范围内，true为默认值，表示由近到远排序
     * 采用异步搜索查询
     */
    @OnClick(R.id.tv_search)
    void onClick1(View view) {
        if (TextUtils.isEmpty(mEtAddress.getText())) {
            showToast("搜索内容为空");
            return;
        }
        KeyboardUtil.hideInputMethod(this);
        // 第二个参数代表所谓城市，如果不传表示全国范围
        mQuery = new PoiSearch.Query(mEtAddress.getText().toString().trim(), "", "");
        mQuery.setPageSize(20);// 设置每页最多返回多少条poiitem
        mQuery.setPageNum(0);// 设置查第一页
        if (curPoint != null) {
            mPoiSearch = new PoiSearch(this, mQuery);
            mPoiSearch.setOnPoiSearchListener(this);
            // 设置搜索区域为以curPoint点为圆心，其周围5000米范围
            mPoiSearch.setBound(new PoiSearch.SearchBound(curPoint, 5000, true));
            mPoiSearch.searchPOIAsyn();// 异步搜索
        }
    }

    /**
     * 返回POI搜索异步处理的结果
     * @param result
     * @param rCode
     */
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result.getQuery().equals(mQuery)) {
                // 取得搜索到的poiItems有多少页
                List<PoiItem> poiItems = result.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                List<SuggestionCity> suggestionCities = result
                        .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                if (poiItems != null && poiItems.size() > 0) {
                    mAMap.clear();// 清理之前的图标
                    PoiOverlay poiOverlay = new PoiOverlay(mAMap, poiItems);
                    poiOverlay.removeFromMap();
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                }else if (suggestionCities != null
                        && suggestionCities.size() > 0) {
                    showSuggestCity(suggestionCities);
                } else {
                    showToast("没有搜索到相关数据！");
                }
            }
        } else {

        }
    }

    /**
     * poi id搜索的结果回调
     * @param poiItem
     * @param rCode
     */
    @Override
    public void onPoiItemSearched(PoiItem poiItem, int rCode) {

    }

    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities) {
        String info = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            info += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        showToast(info);
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
    protected void onResume() {
        super.onResume();
//        if (isNeedCheck) {
//            checkPermissions(needPermissions);
//        }
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mMapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}
