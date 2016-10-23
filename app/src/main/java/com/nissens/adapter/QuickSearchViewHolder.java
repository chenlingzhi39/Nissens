package com.nissens.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.nissens.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.dao.QuickSearch;


/**
 * Created by Administrator on 2016/3/30.
 */
public class QuickSearchViewHolder extends BaseViewHolder<QuickSearch> {
    @BindView(R.id.textView)
    TextView textView;
    public QuickSearchViewHolder(ViewGroup parent) {
        super(parent, R.layout.list_quick_search_row);
        ButterKnife.bind(this,itemView);
    }
    @Override
    public void setData(QuickSearch data) {
      textView.setText(data.getContent());
    }
}
