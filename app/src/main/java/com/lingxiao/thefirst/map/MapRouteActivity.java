package com.lingxiao.thefirst.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.MapView;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/11/14.
 */

public class MapRouteActivity extends BaseActivity implements RouteSearch.OnRouteSearchListener {
    private static final int START_REQUEST_CODE = 1;
    private static final int END_REQUEST_CODE = 2;

    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.tv_start_address)
    TextView mTvStartAddress;
    @BindView(R.id.tv_end_address)
    TextView mTvEndAddress;

    private PoiSearch.Query mStartQuery;
    private PoiSearch.Query mEndQuery;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_map_route;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);

        mStartQuery = new PoiSearch.Query(mTvStartAddress.getText().toString(), "", "");
        mStartQuery.setPageSize(10);
        mStartQuery.setPageNum(0);

    }

    @OnClick({R.id.tv_start_address, R.id.tv_end_address})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start_address:
                Intent startIntent = new Intent(mContext, MapInputSearchActivity.class);
                startActivityForResult(startIntent, START_REQUEST_CODE);
                break;
            case R.id.tv_end_address:
                Intent endIntent = new Intent(mContext, MapInputSearchActivity.class);
                startActivityForResult(endIntent, END_REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case START_REQUEST_CODE:
                Tip startTip = data.getParcelableExtra("tip");
                showToast(startTip.getName());
                mTvStartAddress.setText(startTip.getName());
                break;
            case END_REQUEST_CODE:
                Tip endTip = data.getParcelableExtra("tip");
                showToast(endTip.getName());
                mTvEndAddress.setText(endTip.getName());
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

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
