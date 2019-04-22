package com.zx.mvpdemo;

import android.annotation.TargetApi;
import android.app.Presentation;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaRouter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

import com.zx.mvpdemo.demo.CustomDisplay;
import com.zx.mvpdemo.framework.InjectUtils;
import com.zx.mvpdemo.utils.CustomerEngine;

public class TwoScreen extends AppCompatActivity {
    MediaRouter mediaRouter;
    Presentation presentation;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.fristscreen);
             CustomerEngine.getInstance(getApplicationContext());
//            mediaRouter= (MediaRouter) getSystemService(Context.MEDIA_ROUTER_SERVICE);
//            MediaRouter.RouteInfo route =mediaRouter.getSelectedRoute(MediaRouter.ROUTE_TYPE_LIVE_VIDEO);
//            Display presentationDisplay=route !=null ? route.getPresentationDisplay() :null;
//            if(presentation !=null && presentation.getDisplay() != presentationDisplay){
//                presentation.dismiss();
//                presentation=null;
//            }
//
//            if(presentation==null && presentationDisplay != null){
//                presentation=new CustomDisplay(getApplicationContext(),presentationDisplay);
//                presentation.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialogInterface) {
//
//                    }
//                });
//                presentation.show();
//            }



    }

    @Override
    public void onBackPressed() {
        onDestroy();
        //完全退出应用，取消双屏异显
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        System.exit(0);

    }
}
