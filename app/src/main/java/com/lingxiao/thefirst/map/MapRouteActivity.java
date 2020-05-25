package com.lingxiao.thefirst.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
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

    private AMap mAMap;
    private RouteSearch mRouteSearch;

    private PoiSearch.Query mStartQuery;
    private PoiSearch.Query mEndQuery;
    private Tip mStartTip;
    private Tip mEndTip;
    private DriveRouteResult mDriveRouteResult;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_map_route;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        if (mAMap == null) {
            mAMap = mMapView.getMap();
        }

        mStartQuery = new PoiSearch.Query(mTvStartAddress.getText().toString(), "", "");
        mStartQuery.setPageSize(10);
        mStartQuery.setPageNum(0);

        mRouteSearch = new RouteSearch(mContext);
        mRouteSearch.setRouteSearchListener(this);
    }

    @OnClick({R.id.tv_start_address, R.id.tv_end_address, R.id.tv_search})
    void onClick1(View view) {
        switch (view.getId()) {
            case R.id.tv_start_address:
                Intent startIntent = new Intent(mContext, MapInputSearchActivity.class);
                startActivityForResult(startIntent, START_REQUEST_CODE);
                break;
            case R.id.tv_end_address:
                Intent endIntent = new Intent(mContext, MapInputSearchActivity.class);
                startActivityForResult(endIntent, END_REQUEST_CODE);
                break;
            case R.id.tv_search:
                if (mStartTip == null) {
                    showToast("起点不能为空");
                    return;
                }
                if (mEndTip == null) {
                    showToast("终点不能为空");
                    return;
                }
                RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(mStartTip.getPoint(), mEndTip.getPoint());
                RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, 0, null, null, "");
                mRouteSearch.calculateDriveRouteAsyn(query);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case START_REQUEST_CODE:
                Tip startTip = data.getParcelableExtra("tip");
                mStartTip = startTip;
                mTvStartAddress.setText(startTip.getName());
                break;
            case END_REQUEST_CODE:
                Tip endTip = data.getParcelableExtra("tip");
                mEndTip = endTip;
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
    public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
        mAMap.clear();// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mDriveRouteResult = result;
                    final DrivePath drivePath = mDriveRouteResult.getPaths()
                            .get(0);
                    if (drivePath == null) {
                        return;
                    }
                    DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                            mContext, mAMap, drivePath,
                            mDriveRouteResult.getStartPos(),
                            mDriveRouteResult.getTargetPos(), null);
                    drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();

                    // TODO 增加路径详情部分
//                    mBottomLayout.setVisibility(View.VISIBLE);
//                    int dis = (int) drivePath.getDistance();
//                    int dur = (int) drivePath.getDuration();
//                    String des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")";
//                    mRotueTimeDes.setText(des);
//                    mRouteDetailDes.setVisibility(View.VISIBLE);
//                    int taxiCost = (int) mDriveRouteResult.getTaxiCost();
//                    mRouteDetailDes.setText("打车约" + taxiCost + "元");
//                    mBottomLayout.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(mContext,
//                                    DriveRouteDetailActivity.class);
//                            intent.putExtra("drive_path", drivePath);
//                            intent.putExtra("drive_result",
//                                    mDriveRouteResult);
//                            startActivity(intent);
//                        }
//                    });

                } else if (result != null && result.getPaths() == null) {
                    showToast("对不起，没有搜索到相关数据！");
                }

            } else {
                showToast("对不起，没有搜索到相关数据！");
            }
        } else {
            showToast("" + errorCode);
        }
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
