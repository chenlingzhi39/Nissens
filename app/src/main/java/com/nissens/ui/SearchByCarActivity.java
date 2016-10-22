package com.nissens.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nissens.R;
import com.nissens.adapter.CarTypeAdapter;
import com.nissens.adapter.RecyclerArrayAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.Car;
import com.nissens.bean.CarConditionRequest;
import com.nissens.callback.InputWindowListener;
import com.nissens.module.presenter.CarConditionPresenter;
import com.nissens.module.presenter.CarConditionPresenterImpl;
import com.nissens.module.view.SearchByCarView;
import com.nissens.pinyin.CharacterParser;
import com.nissens.util.InitiateSearch;
import com.nissens.widget.IMMListenerRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC-20160514 on 2016/10/17.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_search_by_car,
        toolbarTitle = R.string.search_by_car,
        menuId = R.menu.search_by_car
)

public class SearchByCarActivity extends BaseActivity<CarConditionPresenter> implements SearchByCarView {
    @BindView(R.id.factory)
    Button factory;
    @BindView(R.id.brand)
    Button brand;
    @BindView(R.id.displacement)
    Button displacement;
    @BindView(R.id.year)
    Button year;
    @BindView(R.id.card_search)
    CardView cardSearch;
    @BindView(R.id.view_search)
    IMMListenerRelativeLayout viewSearch;
    @BindView(R.id.image_search_back)
    ImageView imageSearchBack;
    @BindView(R.id.edit_text_search)
    EditText editTextSearch;
    @BindView(R.id.clearSearch)
    ImageView clearSearch;
    @BindView(R.id.linearLayout_search)
    LinearLayout linearLayoutSearch;
    @BindView(R.id.line_divider)
    View lineDivider;
    @BindView(R.id.listView)
    RecyclerView listView;
    CarTypeAdapter carTypeAdapter;
    ArrayList<String> types;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mToolbarContainer)
    AppBarLayout mToolbarContainer;
    @BindView(R.id.series)
    Button series;
    @BindView(R.id.shadow)
    View shadow;
    @BindView(R.id.gear_box_type)
    Button gearBoxType;
    @BindView(R.id.group)
    Button group;
    @BindView(R.id.confirm)
    Button confirm;
    private int state;
    int[] titles = {R.string.select_factory, R.string.select_brand, R.string.select_car_series, R.string.select_car_group,R.string.select_displacement, R.string.select_gear_box_type, R.string.select_year};
    private InitiateSearch initiateSearch;
    private SparseArray<String> map = new SparseArray<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new CarConditionPresenterImpl(this);
        types = new ArrayList<>();
        InitiateSearch();
        HandleSearch();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showResult(Car car) {

        String[] array = {};
        switch (state) {
            case 0:
                array = car.getCarFactoryName().split("\\|");
                break;
            case 1:
                array = car.getCarBrand().split("\\|");
                break;
            case 2:
                array = car.getCarSeries().split("\\|");
                break;
            case 3:
                array = car.getCheZu().split("\\|");
                break;
            case 4:
                array = car.getDisplacement().split("\\|");
                break;
            case 5:
                array = car.getGearBoxType().split("\\|");
                break;
            case 6:
                array = car.getYear().split("\\|");
                break;
        }
        for (String str : array) {
            if (!TextUtils.isEmpty(str))
                types.add(str);
        }
        carTypeAdapter.addAll(types);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick({R.id.factory, R.id.brand,R.id.group,R.id.displacement, R.id.year, R.id.confirm, R.id.series, R.id.gear_box_type,R.id.vin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.factory:
                state = 0;
                map.put(0, "");
                map.put(1, "");
                map.put(2, "");
                map.put(3, "");
                map.put(4, "");
                map.put(5, "");
                map.put(6, "");
                factory.setText(R.string.select_factory);
                brand.setText(R.string.select_brand);
                series.setText(R.string.select_car_series);
                group.setText(R.string.select_car_group);
                displacement.setText(R.string.select_displacement);
                gearBoxType.setText(R.string.select_gear_box_type);
                year.setText(R.string.select_year);
                break;
            case R.id.brand:
                state = 1;
                map.put(1, "");
                map.put(2, "");
                map.put(3, "");
                map.put(4, "");
                map.put(5, "");
                map.put(6, "");
                brand.setText(R.string.select_brand);
                series.setText(R.string.select_car_series);
                group.setText(R.string.select_car_group);
                displacement.setText(R.string.select_displacement);
                gearBoxType.setText(R.string.select_gear_box_type);
                year.setText(R.string.select_year);
                break;
            case R.id.series:
                state = 2;
                map.put(2, "");
                map.put(3, "");
                map.put(4, "");
                map.put(5, "");
                map.put(6, "");
                series.setText(R.string.select_car_series);
                group.setText(R.string.select_car_group);
                displacement.setText(R.string.select_displacement);
                gearBoxType.setText(R.string.select_gear_box_type);
                year.setText(R.string.select_year);
                break;
            case R.id.group:
                state = 3;
                map.put(3, "");
                map.put(4, "");
                map.put(5, "");
                map.put(6, "");
                group.setText(R.string.select_car_group);
                displacement.setText(R.string.select_displacement);
                gearBoxType.setText(R.string.select_gear_box_type);
                year.setText(R.string.select_year);
                break;
            case R.id.displacement:
                state = 4;
                map.put(4, "");
                map.put(5, "");
                map.put(6, "");
                displacement.setText(R.string.select_displacement);
                gearBoxType.setText(R.string.select_gear_box_type);
                year.setText(R.string.select_year);
                break;
            case R.id.gear_box_type:
                state = 5;
                map.put(5, "");
                map.put(6, "");
                gearBoxType.setText(R.string.select_gear_box_type);
                year.setText(R.string.select_year);
                break;
            case R.id.year:
                state = 6;
                map.put(6, "");
                year.setText(R.string.select_year);
                break;
            case R.id.confirm:
                Intent intent = new Intent(SearchByCarActivity.this, CarsActivity.class);
                intent.putExtra("factory", map.get(0) != null ? map.get(0) : "");
                intent.putExtra("brand", map.get(1) != null ? map.get(1) : "");
                intent.putExtra("series", map.get(2) != null ? map.get(2) : "");
                intent.putExtra("group",map.get(3) != null ? map.get(3) : "");
                intent.putExtra("displacement", map.get(4) != null ? map.get(4) : "");
                intent.putExtra("gear_box_type", map.get(5) != null ? map.get(5) : "");
                intent.putExtra("year", map.get(6) != null ? map.get(6) : "");
                startActivity(intent);
                return;
            case R.id.vin:
                startActivity(new Intent(SearchByCarActivity.this,SearchByVinActivity.class));
                break;
        }
        showDialog(getString(titles[state]));
    }

    private void InitiateSearch() {
        viewSearch.setListener(new InputWindowListener() {
            @Override
            public void show() {

            }

            @Override
            public void hide() {
                Log.i("input", "hide");
                if (cardSearch.getVisibility() == View.VISIBLE)
                    InitiateSearch.handleToolBar1(SearchByCarActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);
            }
        });
        carTypeAdapter = new CarTypeAdapter(this);
        carTypeAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                map.put(state, carTypeAdapter.getItem(position));
                listView.scrollToPosition(0);
                switch (state) {
                    case 0:
                        factory.setText(map.get(state));
                        break;
                    case 1:
                        brand.setText(map.get(state));
                        break;
                    case 2:
                        series.setText(map.get(state));
                        break;
                    case 3:
                        group.setText(map.get(state));
                        break;
                    case 4:
                        displacement.setText(map.get(state));
                        break;
                    case 5:
                        gearBoxType.setText(map.get(state));
                        break;
                    case 6:
                        year.setText(map.get(state));
                        break;
                }
                InitiateSearch.handleToolBar(SearchByCarActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);
            }
        });
        listView.setAdapter(carTypeAdapter);
        listView.setLayoutManager(new GridLayoutManager(this, 2));
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                new MyFilter(types).filter(s);
                if (editTextSearch.getText().toString().length() == 0) {
                    clearSearch.setVisibility(View.GONE);
                } else {
                    clearSearch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSearch.setText("");
                listView.setVisibility(View.VISIBLE);
                ((InputMethodManager) SearchByCarActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        });

    }

    private void HandleSearch() {
        imageSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("search", "back");
                initiateSearch.handleToolBar(SearchByCarActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);
            }
        });
        editTextSearch.requestFocus();
    }

    public void showDialog(String title) {
        types.clear();
        carTypeAdapter.clear();
        CarConditionRequest carConditionRequest = new CarConditionRequest();
        carConditionRequest.setUserID("MobileApp");
        if (!TextUtils.isEmpty(map.get(0)))
        {carConditionRequest = new CarConditionRequest(map);
            carConditionRequest.setUserID("MobileApp");
        }
        else if (!title.equals(getString(titles[0]))) {
            toast(R.string.must_select_factory);
            return;
        }
        Log.i("request", gson.toJson(carConditionRequest));
        mPresenter.requestData(gson.toJson(carConditionRequest));
        editTextSearch.setHint(title);
        initiateSearch.handleToolBar(SearchByCarActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);
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
                    if (string.contains(prefixString)||CharacterParser.getInstance().getSelling(string).contains(prefixString)) {
                        newValues.add(string);
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            carTypeAdapter.clear();
            carTypeAdapter.addAll((ArrayList<String>) results.values);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* switch (item.getItemId()) {
            case R.id.search_by_vin:
                startActivity(new Intent(SearchByCarActivity.this, SearchByVinActivity.class));
                break;
        }*/
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStop() {
        super.onStop();
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(cardSearch.getWindowToken(), 0);
    }
}

