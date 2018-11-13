package com.lingxiao.thefirst.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.RotateAnimation;
import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/11/13.
 */

public class MapMarkerActivity extends BaseActivity {
    @BindView(R.id.map)
    MapView mMapView;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_map_marker;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        // 禁止旋转
        mMapView.getMap().getUiSettings().setRotateGesturesEnabled(false);
        AMap aMap = mMapView.getMap();

        // 注意拖拽需要长按浮动起来之后才可以
        LatLng latLng = new LatLng(39.906901,116.397972);
        aMap.addMarker(new MarkerOptions()
                .position(latLng).title("北京").snippet("北京欢迎你，长按可拖动")
                .draggable(true).alpha(0.8f));

        LatLng latLng2 = new LatLng(39.926901, 116.377972);
        aMap.addMarker(new MarkerOptions()
                .position(latLng2).title("北京").snippet("北京欢迎你2")
                .draggable(false).alpha(0.8f));

        // 点击每个Marker都会调用这个监听
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                // 给marker添加动画
                Animation animation = new RotateAnimation(marker.getRotateAngle(),
                        marker.getRotateAngle()+180,0,0,0);
                long duration = 1000L;
                animation.setDuration(duration);
                animation.setInterpolator(new LinearInterpolator());

                marker.setAnimation(animation);
                marker.startAnimation();

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart() {
                        marker.hideInfoWindow();
                    }

                    @Override
                    public void onAnimationEnd() {
                        marker.showInfoWindow();
                    }
                });

                return false;
            }
        });

        // 点击每个InfoWindow都会调用这个监听
        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                marker.hideInfoWindow();
            }
        });

        //TODO 自定义InfoWindow
        // 不要随意添加，此处返回null，marker的infoWindow则为null
//        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
//            @Override
//            public View getInfoWindow(Marker marker) {
//                return null;
//            }
//
//            @Override
//            public View getInfoContents(Marker marker) {
//                return null;
//            }
//        });

        aMap.setOnMarkerDragListener(new AMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                showToast("start drag");
            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                showToast("end drag");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
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
