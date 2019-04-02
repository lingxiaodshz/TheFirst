package com.lingxiao.thefirst.mine.selectcontract;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;
import com.lingxiao.thefirst.utils.ContactsUtils;

/**
 * @author luckw
 * @date 2019/4/1
 */
public class MediumActivity extends BaseActivity {
    @Override
    public int getLayoutResource() {
        return 0;
    }

    private final int CONTACT_REQUEST_CODE = 100;
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent,CONTACT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTACT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                Cursor cursor = managedQuery(uri, null, null, null, null);
                try {
                    if (cursor != null) {
                        cursor.moveToFirst();
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        number = ContactsUtils.trimPhoneNumber(number);
                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("number", number);
                        setResult(RESULT_OK, intent);
                    }
                } catch (Exception e) {

                }finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        }
        finish();
    }
}
