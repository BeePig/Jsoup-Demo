package com.example.beepi.jsoupdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by beepi on 17/01/2017.
 */

public class Presenter implements ParseContract.Presenter{
    private ParseContract.View mView;
    private ParseUrlAsynTask mParseUrlAsynTask;
    private Context mContext;
    public Presenter(Context context,ParseContract.View view,ParseUrlAsynTask parseUrlAsynTask){
        mView = view;
        mParseUrlAsynTask = parseUrlAsynTask;
        mContext = context;
    }
    @Override
    public String getContent() {

        String urlImg = mParseUrlAsynTask.mContentSong.getmUrlImage();
        mView.showContent(urlImg);
        return urlImg;
    }

    @Override
    public Boolean checkAndRequestPermission() {
        int permisionCheck = ContextCompat.checkSelfPermission(mContext,Constant.PERMISSION_INTERNET);
        if(permisionCheck!= PackageManager.PERMISSION_GRANTED){
            return true;
        }else {
            if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext,
                    Constant.PERMISSION_INTERNET)){
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Permission neccessery");
                builder.setMessage("permission internet is neccessery");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) mContext,new String[]{
                                Constant.PERMISSION_INTERNET},Constant.REQUEST_INTERNET);
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkAndRequestPermission();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;

            }else {
                ActivityCompat.requestPermissions((Activity) mContext,new String[]{
                        Constant.PERMISSION_INTERNET},Constant.REQUEST_INTERNET);
                return true;
            }
        }
    }
}
