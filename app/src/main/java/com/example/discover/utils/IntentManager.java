package com.example.discover.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Pair;
import android.view.View;

import com.example.discover.AuthorHomeActivity;
import com.example.discover.R;
import com.example.discover.ResultActivity;
import com.example.discover.VideoDetailActivity;
import com.example.discover.ViewBigImageActivity;
import com.example.discover.app.Constant;
import com.example.discover.bean.DetailBean.ItemList;
import com.example.discover.bean.LitePalBean.LikeVideo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeyWiiu on 2018/1/30.
 */

public class IntentManager {

    public static void toVideoDetailActivity(Activity context, ItemList item, View view) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra("item", item);
        /*ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                context,
                Pair.create(view, context.getString(R.string.transition_shot)),
                Pair.create(view, context.getString(R.string.transition_shot_background))
        );*/
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context,
                android.support.v4.util.Pair.create(view, context.getString(R.string.transition_shot)),
                android.support.v4.util.Pair.create(view, context.getString(R.string.transition_shot_background))
        );
        ActivityCompat.startActivity(context, intent, optionsCompat.toBundle());
        //context.startActivity(intent);

    }

    public static void toAuthorHomeActivity(Context context,ItemList list) {
        int color = 0;
        if (list.getData().getItemList().size()  > 0) {
            color = (Integer) Constant.LabelMap.get(list.getData().getItemList().get(0).getData().getCategory());
        }
        Intent intent = new Intent(context, AuthorHomeActivity.class);
        intent.putExtra("AuthorId", list.getData().getHeader().getId());
        intent.putExtra("AuthorName", list.getData().getHeader().getTitle());
        intent.putExtra("AuthorIcon", list.getData().getHeader().getIcon());
        intent.putExtra("AuthorDesc", list.getData().getHeader().getDescription());
        intent.putExtra("Color", color);
        try {
            intent.putExtra("AuthorBack", list.getData().getItemList().get(0).getData().getTags().get(0).getHeaderImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        context.startActivity(intent);
    }

    public static void fromDetailtoAuthor(Context context, ItemList list) {
        int color = 0;
        color = (int) Constant.LabelMap.get(list.getData().getCategory());
        Intent intent = new Intent(context, AuthorHomeActivity.class);
        intent.putExtra("AuthorId", list.getData().getAuthor().getId());
        intent.putExtra("AuthorName", list.getData().getAuthor().getName());
        intent.putExtra("AuthorIcon", list.getData().getAuthor().getIcon());
        intent.putExtra("AuthorDesc", list.getData().getAuthor().getDescription());
        intent.putExtra("Color", color);
        try {
            intent.putExtra("AuthorBack", list.getData().getTags().get(0).getHeaderImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        context.startActivity(intent);
    }

    public static void fromPersonalToAuthor(Context context, LikeVideo likeVideo) {

        Intent intent = new Intent(context, AuthorHomeActivity.class);
        intent.putExtra("AuthorId", likeVideo.getAuthorId());
        intent.putExtra("AuthorName", likeVideo.getAuthorName());
        intent.putExtra("AuthorIcon", likeVideo.getAuthorIcon());
        intent.putExtra("AuthorDesc", likeVideo.getAuthorDesc());
        intent.putExtra("Color", likeVideo.getLabelColor());
        intent.putExtra("AuthorBack", likeVideo.getImageUrl());


        context.startActivity(intent);
    }
    public static void toBigImageActivity(Activity context, View view, int position, ArrayList<String> imgList) {
        Bundle bundle = new Bundle();
        bundle.putInt("selet", 2);// 2,大图显示当前页数，1,头像，不显示页数
        bundle.putInt("code", position);//第几张
        bundle.putStringArrayList("imageuri", imgList);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context,
                android.support.v4.util.Pair.create(view, context.getString(R.string.transition_shot)),
                android.support.v4.util.Pair.create(view, context.getString(R.string.transition_shot_background))
        );
        Intent intent = new Intent(context, ViewBigImageActivity.class);
        intent.putExtras(bundle);
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
}
