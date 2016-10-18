package com.nissens.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;

import com.nissens.R;
import com.nissens.bean.CarCondition;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by PC-20160514 on 2016/10/18.
 */

public class CarConditionAdapter extends RecyclerArrayAdapter<CarCondition> {
    public int mSelectedItem = 0;

    public CarConditionAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    public class ViewHolder extends BaseViewHolder<CarCondition> {
        @BindView(R.id.search)
        EditText search;
        @BindView(R.id.type_list)
        RecyclerView typeList;
        CarTypeAdapter carTypeAdapter;

        ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_car_condition);
            ButterKnife.bind(this, itemView);
            carTypeAdapter = new CarTypeAdapter(getContext());
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectedItem !=getAdapterPosition()) {
                        mSelectedItem = getAdapterPosition();
                        notifyItemRangeChanged(0, getCount());
                    }
                }
            });
        }

        @Override
        public void setData(final CarCondition data) {
            search.setHint(data.getHint());
            carTypeAdapter.addAll(data.getTypes());
            typeList.setAdapter(carTypeAdapter);
            typeList.setLayoutManager(new GridLayoutManager(getContext(), 2));
            if (mSelectedItem == getAdapterPosition()) {
                typeList.setVisibility(View.VISIBLE);
                search.requestFocus();
            } else {
                typeList.setVisibility(View.GONE);
                search.clearFocus();
            }
            carTypeAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    search.setText(carTypeAdapter.getItem(position));

                }
            });
            search.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    new MyFilter(data.getTypes()).filter(s);

                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void afterTextChanged(Editable s) {
                }
            });
        }

        public class MyFilter extends Filter {
            List<String> mOriginalList;

            public MyFilter(List<String> messages) {
                this.mOriginalList = messages;
            }

            @Override
            protected FilterResults performFiltering(CharSequence prefix) {
                FilterResults results = new FilterResults();
                if (mOriginalList == null) {
                    mOriginalList = new ArrayList<>();
                }


                if (prefix == null || prefix.length() == 0) {
                    results.values = mOriginalList;
                    results.count = mOriginalList.size();
                } else {
                    String prefixString = prefix.toString();
                    final int count = mOriginalList.size();
                    final ArrayList<String> newValues = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        final String string = mOriginalList.get(i);


                        if (string.startsWith(prefixString)) {
                            newValues.add(string);
                        } else {
                            final String[] words = string.split(" ");
                            final int wordCount = words.length;

                            // Start at index 0, in case valueText starts with space(s)
                            for (int k = 0; k < wordCount; k++) {
                                if (words[k].startsWith(prefixString)) {
                                    newValues.add(string);
                                    break;
                                }
                            }
                        }
                    }
                    results.values = newValues;
                    results.count = newValues.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
                carTypeAdapter.clear();
                carTypeAdapter.addAll((ArrayList<String>) results.values);
            }
        }
    }

    public int getmSelectedItem() {
        return mSelectedItem;
    }

    public void setmSelectedItem(int mSelectedItem) {
        this.mSelectedItem = mSelectedItem;
    }
}
