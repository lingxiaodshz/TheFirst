package com.lingxiao.thefirst.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;
import com.lingxiao.thefirst.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/11/14.
 */

public class MapInputSearchActivity extends BaseActivity implements TextWatcher, Inputtips.InputtipsListener {
    @BindView(R.id.et_input)
    AutoCompleteTextView mEtInput;
    @BindView(R.id.list_view)
    ListView mListView;


    @Override
    public int getLayoutResource() {
        return R.layout.activity_map_input_search;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mEtInput.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String newText = s.toString().trim();
        InputtipsQuery inputQuery = new InputtipsQuery(newText, "");
        inputQuery.setCityLimit(true);
        Inputtips inputTips = new Inputtips(mContext, inputQuery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onGetInputtips(final List<Tip> tipList, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            List<HashMap<String, String>> listString = new ArrayList<HashMap<String, String>>();
            if(tipList != null) {
                int size = tipList.size();
                for (int i = 0; i < size; i++) {
                    Tip tip = tipList.get(i);
                    if(tip != null) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("name", tipList.get(i).getName());
                        map.put("address", tipList.get(i).getDistrict());
                        listString.add(map);
                    }
                }
                SimpleAdapter aAdapter = new SimpleAdapter(getApplicationContext(), listString, R.layout.activity_map_route_item,
                        new String[]{"name", "address"}, new int[]{R.id.poi_field_id, R.id.poi_value_id});

                mListView.setAdapter(aAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent();
                        intent.putExtra("tip", tipList.get(position));
                        setResult(100, intent);
                        finish();
                    }
                });
                aAdapter.notifyDataSetChanged();
            }

        } else {
            showToast("error");
        }
    }
}
