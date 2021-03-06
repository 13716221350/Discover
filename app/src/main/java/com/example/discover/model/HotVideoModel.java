package com.example.discover.model;

import com.example.discover.bean.HotEyeBean;
import com.example.discover.bean.LitePalBean.LikeVideo;
import com.example.discover.http.HttpClient;
import com.example.discover.http.RequestListener;
import com.example.discover.ui.Video.VideoFragment;
import com.example.discover.utils.DebugUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/12/12 0012.
 */

public class HotVideoModel {

    public static void showVideo(VideoFragment context, int start, int num, final RequestListener listener) {

        DebugUtil.debug("test12345", "successed111");
            HttpClient.Builder.getEyeService().getEyeHot(start, num)
                    .compose(context.<HotEyeBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<HotEyeBean>() {
                        @Override
                        public void onSubscribe(Subscription s) {

                            s.request(Long.MAX_VALUE);
                        }

                        @Override
                        public void onNext(HotEyeBean hotEyeBean) {

                            listener.onSuccess(hotEyeBean);
                        }

                        @Override
                        public void onError(Throwable t) {

                            listener.onFailed(t);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }


}
