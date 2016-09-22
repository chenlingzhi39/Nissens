package com.nissens.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.adapter.QuickSearchAdapter;
import com.nissens.adapter.RecyclerArrayAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.app.MyApplication;
import com.nissens.base.BaseActivity;
import com.nissens.bean.OEData;
import com.nissens.config.Constants;
import com.nissens.helper.ItemHelper;
import com.nissens.manager.MyLayoutManager;
import com.nissens.module.presenter.StraightSearchPresenter;
import com.nissens.module.presenter.StraightSearchPresenterImpl;
import com.nissens.util.InitiateSearch;
import com.nissens.view.StraightSearchView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.dao.QuickSearch;
import de.greenrobot.dao.QuickSearchDao;

/**
 * Created by PC-20160514 on 2016/9/21.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_straight_search,
        toolbarTitle = R.string.straight_search,
        menuId = R.menu.menu_straight_search
)
public class StraightSearchActivity extends BaseActivity<StraightSearchPresenter> implements StraightSearchView {
    @BindView(R.id.edit_text_search)
    EditText editTextSearch;
    @BindView(R.id.image_search_back)
    ImageView imageSearchBack;
    @BindView(R.id.clearSearch)
    ImageView clearSearch;
    @BindView(R.id.linearLayout_search)
    LinearLayout linearLayoutSearch;
    @BindView(R.id.line_divider)
    View lineDivider;
    @BindView(R.id.card_search)
    CardView cardSearch;
    @BindView(R.id.view_search)
    RelativeLayout viewSearch;
    @BindView(R.id.listView)
    RecyclerView listView;
    private QuickSearchAdapter quickSearchAdapter;
    private QuickSearchDao quickSearchDao;
    private InitiateSearch initiateSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        InitiateSearch();
        HandleSearch();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                initiateSearch.handleToolBar(StraightSearchActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void InitiateSearch(){
        quickSearchDao=  MyApplication.getInstance().getDaoSession().getQuickSearchDao();
        String textColumn = QuickSearchDao.Properties.Id.columnName;
        String orderBy = textColumn + " DESC";
        Cursor cursor =  MyApplication.getInstance().getDb().query(quickSearchDao.getTablename(),quickSearchDao.getAllColumns(),null, null, null, null, orderBy);
        if(cursor!=null){
            quickSearchAdapter=new QuickSearchAdapter(this);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                QuickSearch quickSearch=new QuickSearch();
                quickSearchDao.readEntity(cursor, quickSearch, 0);
                quickSearchAdapter.addAll(quickSearch);
            }
            quickSearchAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if(position!=0)
                    { QuickSearch quickSearch=new QuickSearch();
                        quickSearch.setAdd_time(new Date(System.currentTimeMillis()));
                        quickSearch.setContent(quickSearchAdapter.getData().get(position).getContent());
                        quickSearchDao.insert(quickSearch);
                        quickSearchDao.delete(quickSearchAdapter.getData().get(position));
                        quickSearchAdapter.remove(position);
                        quickSearchAdapter.add(0,quickSearch);}

                }
            });
            listView.setLayoutManager(new MyLayoutManager(this));
            listView.setAdapter(quickSearchAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemHelper<QuickSearch>(quickSearchDao,quickSearchAdapter));
            itemTouchHelper.attachToRecyclerView(listView);
        }
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextSearch.getText().toString().length() == 0) {
                    clearSearch.setImageResource(R.mipmap.ic_keyboard_voice);
                } else {
                    clearSearch.setImageResource(R.mipmap.ic_close);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSearch.getText().toString().length() == 0) {

                } else {
                    editTextSearch.setText("");
                    listView.setVisibility(View.VISIBLE);
                    ((InputMethodManager) StraightSearchActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                }
            }
        });
    }
    private void HandleSearch() {
        imageSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("search", "back");
                initiateSearch.handleToolBar(StraightSearchActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);
            }
        });
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (editTextSearch.getText().toString().trim().length() > 0) {
                        for (int i = 0; i < quickSearchAdapter.getData().size(); i++) {
                            if (quickSearchAdapter.getData().get(i).getContent().equals(editTextSearch.getText().toString())) {
                                if(i==0){


                                    initiateSearch.handleToolBar(StraightSearchActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);
                                    return true;
                                }
                                quickSearchDao.delete(quickSearchAdapter.getData().get(i));
                                quickSearchAdapter.remove(i);
                                break;
                            }
                        }
                        QuickSearch quickSearch = new QuickSearch();
                        quickSearch.setAdd_time(new Date(System.currentTimeMillis()));
                        quickSearch.setContent(editTextSearch.getText().toString());
                        quickSearchDao.insert(quickSearch);
                        quickSearchAdapter.add(0, quickSearch);



                        initiateSearch.handleToolBar(StraightSearchActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);

                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showResult(List<OEData> oeDatas) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick(R.id.clearSearch)
    public void onClick() {
        if (editTextSearch.getText().toString().length() == 0) {

        } else {
            editTextSearch.setText("");
            listView.setVisibility(View.VISIBLE);
            ((InputMethodManager) StraightSearchActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        }
    }

}
