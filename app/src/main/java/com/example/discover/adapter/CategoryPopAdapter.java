package com.example.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.discover.R;
import com.example.discover.base.baseadapter.BaseRecyclerAdapter;
import com.example.discover.base.baseadapter.BaseViewHolder;
import com.example.discover.bean.CategoryDetailBean.ItemList;
import com.example.discover.bean.CategoryDetailBean.SectionList;
import com.example.discover.databinding.CategoryCardBinding;
import com.example.discover.databinding.TestCardviewBinding;

import java.util.List;

/**
 * Created by monkeyWiiu on 2018/1/18.
 */

public class CategoryPopAdapter extends BaseRecyclerAdapter<ItemList> {
    public CategoryPopAdapter(Context context ) {
        super(context);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CateGoryCardHolder(parent, R.layout.category_card);
    }

    public class CateGoryCardHolder extends BaseViewHolder<ItemList, CategoryCardBinding> {
        public CateGoryCardHolder(ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        public void fillHolder(ItemList object, int position) {
            itemViewBinding.tvText.setText(object.getData().getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}