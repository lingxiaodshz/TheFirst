package com.lingxiao.thefirst;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.luckw.myapplication.IMyAidlInterface;
import com.lingxiao.thefirst.base.BaseActivity;
import com.lingxiao.thefirst.base.BaseFragment;
import com.lingxiao.thefirst.map.MapFirstActiviy;
import com.lingxiao.thefirst.mine.MineFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private String[] mTitles = {"首页", "测试", "我的"};

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        if (mToolbar != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        hideBackButton();
        // setTitle("MainActivity");

        showToast(new SimpleDateFormat().format(new Date()));

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // setTitle(mTitles[position]);
                setTitleCenter(mTitles[position]);
                mBottomBar.selectTabAtPosition(position);
                //刷新menu
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_main:
                        setPage(0);
                        break;
                    case R.id.tab_test:
                        setPage(1);
                        break;
                    case R.id.tab_mine:
                        setPage(2);
                        break;
                }
            }
        });

        // 此处会在onresume执行完成之后才会执行，也就说不会在此阻塞，
        // 另外runnable内部可以修改view,表明是在主线程中执行的
        mBottomBar.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "postdelay");
//                setTitleCenter("postdelay");
            }
        });

        CharSequence text = getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);
        if (!TextUtils.isEmpty(text)) {
            showToast(text.toString());
        }

        /**
         * 两个APP之间AIDL通信，在另一个APP下创建一个service,如下，
         * public class MyService extends Service {
         *     @Override
         *     public IBinder onBind(Intent intent) {
         *         return new MyBinder();
         *     }
         *
         *     class MyBinder extends IMyAidlInterface.Stub {
         *
         *         @Override
         *         public String getName() throws RemoteException {
         *             Date date = Calendar.getInstance().getTime();
         *             return "MyService  " + date.getMinutes() + ":" + date.getSeconds();
         *         }
         *     }
         * }
         *
         * 然后在AndroidMenifest文件里面配置
         * <service android:name=".MyService">
         *             <intent-filter>
         *                 <action android:name="aidl.test1" />
         *             </intent-filter>
         *         </service>
         * 创建IMyAidlInterface.aidl文件和当前app的包名相同，文件也相同
         * 另一个APP为Server端，则
         * Intent intent = new Intent("aidl.test1"); 使用server端的service的action
         * intent.setPackage("com.example.luckw.myapplication"); 使用server端的aidl文件包名
         *
         * 客户端代码如下：
         */
        Intent intent = new Intent("aidl.test1");
        intent.setPackage("com.example.luckw.myapplication");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);

        findViewById(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Toast.makeText(MainActivity.this, iMyAidlInterface.getName(), Toast.LENGTH_SHORT).show();
                }
                catch (RemoteException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "resume");
    }

    private void setPage(int position) {
        mViewPager.setCurrentItem(position);
        // setTitle(mTitles[position]);
        setTitleCenter(mTitles[position]);
        //刷新menu
        invalidateOptionsMenu();
//        showToast(mTitles[position]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem info = menu.findItem(R.id.info);
        MenuItem main = menu.findItem(R.id.map);
        MenuItem setting = menu.findItem(R.id.setting);
        switch (mViewPager.getCurrentItem()) {
            case 0:
                info.setVisible(true);
                main.setVisible(false);
                setting.setVisible(false);
                break;
            case 1:
                info.setVisible(false);
                main.setVisible(false);
                setting.setVisible(false);
                break;
            case 2:
                info.setVisible(false);
                main.setVisible(true);
                setting.setVisible(true);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:

                return true;
            case R.id.map:
                startActivity(new Intent(mContext, MapFirstActiviy.class));
                return true;
            case R.id.setting:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class MyAdapter extends FragmentPagerAdapter {
        List<BaseFragment> mList = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
            mList.add(MainFragment.newInsatanc());
            mList.add(TestFragment.newInsatanc());
            mList.add(MineFragment.newInsatanc());
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
