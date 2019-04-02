package com.lingxiao.thefirst.mine.selectcontract;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;
import com.lingxiao.thefirst.utils.ContactsUtils;

/**
 * @author luckw
 * @date 2019/3/29
 */
public class SelectContractActivity extends BaseActivity {
    @Override
    public int getLayoutResource() {
        return R.layout.activity_select_contract;
    }

    private TextView mTvContackShow;
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        findViewById(R.id.tv_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MediumActivity.class);
                startActivityForResult(intent, 1000);
            }
        });
        mTvContackShow = findViewById(R.id.tv_contact_show);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("name");
            String number = data.getStringExtra("number");
            mTvContackShow.setText(name + "\n" + number);
        }
    }

    /**
     * 读取联系人信息
     *
     * @param uri
     */
    private String[] getPhoneContacts(Uri uri) {
        String[] contact = new String[3];
        //得到ContentResolver对象
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            //取得联系人姓名
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0] = cursor.getString(nameFieldColumnIndex);
            contact[1] = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if (checkPhoneNumeberCountryCode(contact[1])) {
                contact[2] = "86";
            } else {
                contact[2] = "";
            }
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }

    // 判断手机号是不是+86开头
    public static boolean checkPhoneNumeberCountryCode(String phone) {
        if (!TextUtils.isEmpty(phone) && phone.startsWith("+86")) {
            return true;
        }
        return false;
    }
}
