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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.adapter.OEDataAdapter;
import com.nissens.adapter.QuickSearchAdapter;
import com.nissens.adapter.RecyclerArrayAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.app.MyApplication;
import com.nissens.base.BaseActivity;
import com.nissens.bean.OEData;
import com.nissens.bean.OEDataRequest;
import com.nissens.callback.InputWindowListener;
import com.nissens.helper.ItemHelper;
import com.nissens.manager.MyLayoutManager;
import com.nissens.module.presenter.StraightSearchPresenter;
import com.nissens.module.presenter.StraightSearchPresenterImpl;
import com.nissens.module.view.StraightSearchView;
import com.nissens.util.InitiateSearch;
import com.nissens.widget.DividerItemDecoration;
import com.nissens.widget.IMMListenerRelativeLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.dao.QuickSearch;
import de.greenrobot.dao.QuickSearchDao;

/**
 * Created by PC-20160514 on 2016/9/21.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_search_directly,
        toolbarTitle = R.string.search_directly,
        menuId = R.menu.search_directly
)
public class SearchDirectlyActivity extends BaseActivity<StraightSearchPresenter> implements StraightSearchView {
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
    IMMListenerRelativeLayout viewSearch;
    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.result_list)
    RecyclerView resultList;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;
    @BindView(R.id.clear)
    TextView clear;
    private QuickSearchAdapter quickSearchAdapter;
    private OEDataAdapter oeDataAdapter;
    private QuickSearchDao quickSearchDao;
    private InitiateSearch initiateSearch;
    private OEDataRequest oeDataRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setOverflowShowingAlways();
        InitiateSearch();
        HandleSearch();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                initiateSearch.handleToolBar(SearchDirectlyActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
                    InitiateSearch.handleToolBar1(SearchDirectlyActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);
            }
        });
        oeDataAdapter = new OEDataAdapter(this);
        oeDataAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SearchDirectlyActivity.this, InfoActivity.class);
                intent.putExtra("oeData", oeDataAdapter.getData().get(position));
                startActivity(intent);
            }
        });
        resultList.setAdapter(oeDataAdapter);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        resultList.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));
        mPresenter = new StraightSearchPresenterImpl(this);
        quickSearchDao = MyApplication.getInstance().getDaoSession().getQuickSearchDao();
        String textColumn = QuickSearchDao.Properties.Id.columnName;
        String orderBy = textColumn + " DESC";
        Cursor cursor = MyApplication.getInstance().getDb().query(quickSearchDao.getTablename(), quickSearchDao.getAllColumns(), null, null, null, null, orderBy);
        if (cursor != null) {
            quickSearchAdapter = new QuickSearchAdapter(this);
            if(cursor.getCount()>0){
                clear.setVisibility(View.VISIBLE);
            }
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                QuickSearch quickSearch = new QuickSearch();
                quickSearchDao.readEntity(cursor, quickSearch, 0);
                quickSearchAdapter.addAll(quickSearch);
            }
            quickSearchAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    page = 1;
                    oeDataAdapter.clear();
                    resultList.scrollToPosition(0);
                    getSupportActionBar().setTitle(quickSearchAdapter.getData().get(position).getContent());
                    oeDataRequest = new OEDataRequest("15", page + "", quickSearchAdapter.getItem(position).getContent());
                    mPresenter.requestData(gson.toJson(oeDataRequest));
                    if (position != 0) {
                        QuickSearch quickSearch = new QuickSearch();
                        quickSearch.setAdd_time(new Date(System.currentTimeMillis()));
                        quickSearch.setContent(quickSearchAdapter.getData().get(position).getContent());
                        quickSearchDao.insert(quickSearch);
                        quickSearchDao.delete(quickSearchAdapter.getData().get(position));
                        quickSearchAdapter.remove(position);
                        quickSearchAdapter.add(0, quickSearch);
                    }
                    initiateSearch.handleToolBar(SearchDirectlyActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);
                 /*   Map<String, String> params = new HashMap<>();
                    params.put("UserID", Constants.USER_ID);
                    params.put("EncryptCode", Constants.ENCRYPT_CODE);
                    params.put("PageIndex", "0");
                    params.put("OriginalFactoryID", editTextSearch.getText().toString());*/

                }
            });
            listView.setLayoutManager(new MyLayoutManager(this));
            listView.setAdapter(quickSearchAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemHelper<>(quickSearchDao, quickSearchAdapter,clear));
            itemTouchHelper.attachToRecyclerView(listView);
        }
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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
                ((InputMethodManager) SearchDirectlyActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        });
clear.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        quickSearchDao.deleteAll();
        quickSearchAdapter.clear();
        clear.setVisibility(View.GONE);
    }
});
    }

    private void HandleSearch() {
        imageSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("search", "back");
                initiateSearch.handleToolBar(SearchDirectlyActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);
            }
        });
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (editTextSearch.getText().toString().trim().length() > 0) {
                      /*  Map<String, String> params = new HashMap<>();
                        params.put("UserID", Constants.USER_ID);
                        params.put("EncryptCode", Constants.ENCRYPT_CODE);
                        params.put("PageIndex", "0");
                        params.put("OriginalFactoryID", editTextSearch.getText().toString());*/
                        page = 1;
                        resultList.scrollToPosition(0);
                        oeDataAdapter.clear();
                        oeDataRequest = new OEDataRequest("15", page + "", editTextSearch.getText().toString());
                        mPresenter.requestData(gson.toJson(oeDataRequest));
                        getSupportActionBar().setTitle(editTextSearch.getText().toString());

                        for (int i = 0; i < quickSearchAdapter.getData().size(); i++) {
                            if (quickSearchAdapter.getData().get(i).getContent().equals(editTextSearch.getText().toString())) {
                                if (i == 0) {

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
                        clear.setVisibility(View.VISIBLE);
                        initiateSearch.handleToolBar(SearchDirectlyActivity.this, cardSearch, viewSearch, listView, editTextSearch, lineDivider);
                    }
                    return true;
                }
                return false;
            }
        });
        editTextSearch.requestFocus();
    }

    @Override
    public void showResult(List<OEData> oeDatas) {
        Log.i("showResult", oeDatas.size() + "");
        if (is_first) {
            oeDataAdapter.setError(R.layout.view_more_error).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (oeDataAdapter.getCount() > 0)
                        oeDataAdapter.resumeMore();
                }
            });
            oeDataAdapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    if (oeDataAdapter.getCount() > 0) {
                        if (oeDataAdapter.getCount() > 15) {
                            page += 1;
                            oeDataRequest = new OEDataRequest("15", page + "", getSupportActionBar().getTitle().toString());
                            mPresenter.requestData(gson.toJson(oeDataRequest));
                        } else oeDataAdapter.stopMore();
                    }
                }
            });
            oeDataAdapter.setNoMore(R.layout.view_nomore);
            is_first = false;
        }
        resultList.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        oeDataAdapter.addAll(oeDatas);
    }

    @Override
    public void showEmpty() {
        if (oeDataAdapter.getCount() == 0) {
            if (TextUtils.isEmpty(oeDataRequest.getOriginalFactoryID())) {
                oeDataRequest.setOriginalFactoryID(oeDataRequest.getFactoryID());
                oeDataRequest.setFactoryID(null);
                mPresenter.requestData(gson.toJson(oeDataRequest));
            } else {
                oeDataAdapter.clear();
                empty.setVisibility(View.VISIBLE);
            }
        } else oeDataAdapter.stopMore();
    }

    @Override
    public void showError() {
        if (oeDataAdapter.getCount() == 0) {
            oeDataAdapter.clear();
            error.setVisibility(View.VISIBLE);
        } else oeDataAdapter.pauseMore();
    }

    @Override
    public void showProgress() {
        if (oeDataAdapter.getCount() == 0) {
            error.setVisibility(View.GONE);
            empty.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            resultList.setVisibility(View.GONE);
        }

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }


    @OnClick({R.id.error, R.id.clearSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.error:
                mPresenter.requestData(gson.toJson(oeDataRequest));
                break;
            case R.id.clearSearch:
                listView.setVisibility(View.VISIBLE);
                ((InputMethodManager) SearchDirectlyActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                break;
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
