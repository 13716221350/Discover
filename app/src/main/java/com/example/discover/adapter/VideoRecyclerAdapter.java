package com.example.discover.adapter;

import android.view.ViewGroup;

import com.example.discover.R;
import com.example.discover.base.baseadapter.BaseRecyclerAdapter;
import com.example.discover.base.baseadapter.BaseViewHolder;
import com.example.discover.bean.EyeBean;
import com.example.discover.databinding.VideoCardBinding;

import java.util.LinkedHashMap;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class VideoRecyclerAdapter extends BaseRecyclerAdapter<EyeBean.ItemListBean> {

    public LinkedHashMap map;
    public Object[] objects;
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoHolder(parent, R.layout.video_card);
    }

    public class VideoHolder extends BaseViewHolder<EyeBean.ItemListBean, VideoCardBinding> {



        public VideoHolder(ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        public void fillHolder(EyeBean.ItemListBean list) {
            map = new LinkedHashMap();
            objects = new Object[1];

            if (list != null && list.getData().getPlayInfo().size() > 0) {
                if (list.getData().getPlayInfo().size() == 1) { //标清||高清
                    itemViewBinding.jzVideoPlayer.setUp(list.getData().getPlayInfo().get(0).getUrlList().get(2).getUrl(),
                            JZVideoPlayer.SCREEN_WINDOW_NORMAL, list.getData().getTitle());
                    itemViewBinding.videoTitle.setText(list.getData().getTitle());
                    itemViewBinding.videoDesc.setText(list.getData().getDescription());
                } else if (list.getData().getPlayInfo().size() > 1) { //标清&&高清
                    for (int i = 0; i < list.getData().getPlayInfo().size(); i ++) {
                        map.put(list.getData().getPlayInfo().get(i).getName(),
                                list.getData().getPlayInfo().get(i).getUrlList().get(2).getUrl());
                    }
                    objects[0] = map;
                    itemViewBinding.jzVideoPlayer.setUp(objects , 1, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                            list.getData().getTitle());
                    itemViewBinding.videoTitle.setText(list.getData().getTitle());
                    itemViewBinding.videoDesc.setText(list.getData().getDescription());
                }
            }
            //itemViewBinding.tvTest.setText(list.getData().getPlayInfo().size() + "");
        }

    }
}