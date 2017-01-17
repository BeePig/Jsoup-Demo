package com.example.beepi.jsoupdemo;

import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.beepi.jsoupdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements ParseContract.View,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private String url = new String("https://learnenglishkids.britishcouncil.org/en/songs");
    private Button mButtonStart;
    private TextView mTextview;
    private ContentSong mContentSong;
    private Presenter mPresenter;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.
                setContentView(this, R.layout.activity_main);
        mContentSong = new ContentSong();
        String urlSong = "https://learnenglishkids.britishcouncil.org/en/songs";
        ParseUrlAsynTask mParseUrlAsynTask = (ParseUrlAsynTask) new ParseUrlAsynTask(mContentSong).execute(urlSong);


        mPresenter = new Presenter(this, this, mParseUrlAsynTask);

        mPresenter.checkAndRequestPermission();
        mainBinding.setContentSong(mContentSong);
        mainBinding.setPresenter(mPresenter);

    }


    @Override
    public void showContent(String url) {
        mainBinding.textContent.setText(url);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constant.REQUEST_INTERNET:
                if (grantResults.length > 0) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                        mPresenter.checkAndRequestPermission();
                    }
                }
                break;
        }
    }
}
