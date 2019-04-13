package com.zf.eth.utils;

import android.content.Context;
import android.widget.ImageView;

import com.zf.eth.GlideApp;

public class GlideUtils {
    //普通加载图片
    public static void loadUrlImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context).asBitmap()
                .load(url)
//                .error(R.drawable.icon_jiazaisibai) //加载失败占位图
//                .placeholder(R.drawable.ixon_180px) //加载中占位图
                .into(imageView);
    }


}
