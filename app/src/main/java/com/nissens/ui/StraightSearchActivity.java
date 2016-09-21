package com.nissens.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.adapter.QuickSearchAdapter;
import com.nissens.adapter.RecyclerArrayAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.app.MyApplication;
import com.nissens.base.BaseActivity;
import com.nissens.base.BasePresenter;
import com.nissens.bean.OEData;
import com.nissens.config.Constants;
import com.nissens.helper.ItemHelper;
import com.nissens.presenter.StraightSearchPresenter;
import com.nissens.view.StraightSearchView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.dao.DaoMaster;
import de.greenrobot.dao.DaoSession;
import de.greenrobot.dao.QuickSearch;
import de.greenrobot.dao.QuickSearchDao;

/**
 * Created by PC-20160514 on 2016/9/21.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_straight_search,
        toolbarTitle = R.string.straight_search
)
public class StraightSearchActivity extends BaseActivity<StraightSearchPresenter> implements StraightSearchView{
    @BindView(R.id.quick_search_list)
    RecyclerView listView;
    @BindView(R.id.content)
    EditText editTextSearch;
    @BindView(R.id.search)
    ImageButton search;
    private QuickSearchAdapter quickSearchAdapter;
    private QuickSearchDao quickSearchDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=getChildPresenter();
        ButterKnife.bind(this);

        quickSearchDao = MyApplication.getInstance().getDaoSession().getQuickSearchDao();
        String textColumn = QuickSearchDao.Properties.Id.columnName;
        String orderBy = textColumn + " DESC";
        Cursor cursor = MyApplication.getInstance().getDb().query(quickSearchDao.getTablename(), quickSearchDao.getAllColumns(), null, null, null, null, orderBy);
        if (cursor != null) {
            quickSearchAdapter = new QuickSearchAdapter(this);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                QuickSearch quickSearch = new QuickSearch();
                quickSearchDao.readEntity(cursor, quickSearch, 0);
                quickSearchAdapter.addAll(quickSearch);
            }
            quickSearchAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (position != 0) {
                        QuickSearch quickSearch = new QuickSearch();
                        quickSearch.setAdd_time(new Date(System.currentTimeMillis()));
                        quickSearch.setContent(quickSearchAdapter.getData().get(position).getContent());
                        quickSearchDao.insert(quickSearch);
                        quickSearchDao.delete(quickSearchAdapter.getData().get(position));
                        quickSearchAdapter.remove(position);
                        quickSearchAdapter.add(0, quickSearch);
                    }

                }
            });
            listView.setLayoutManager(new LinearLayoutManager(this));
            listView.setAdapter(quickSearchAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemHelper<>(quickSearchDao, quickSearchAdapter));
            itemTouchHelper.attachToRecyclerView(listView);
            editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        if (editTextSearch.getText().toString().trim().length() > 0) {
                            for (int i = 0; i < quickSearchAdapter.getData().size(); i++) {
                                if (quickSearchAdapter.getData().get(i).getContent().equals(editTextSearch.getText().toString())) {
                                    if(i==0){

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


                        }
                        Map<String, String> params=new HashMap<>();
                        params.put("UserID",Constants.USER_ID);
                        params.put("EncryptCode",Constants.ENCRYPT_CODE);
                        params.put("PageIndex","0");
                        params.put("OriginalFactoryID",editTextSearch.getText().toString());
                        mPresenter.requestDate(params, BasePresenter.RequestMode.FRIST);
                        return true;
                    }
                    return false;
                }
            });
        }

    }

    @OnClick(R.id.search)
    public void onClick() {
            if (editTextSearch.getText().toString().trim().length() > 0) {
                for (int i = 0; i < quickSearchAdapter.getData().size(); i++) {
                    if (quickSearchAdapter.getData().get(i).getContent().equals(editTextSearch.getText().toString())) {
                        if(i==0){


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


            }
        Map<String, String> params=new HashMap<>();
        params.put("UserID",Constants.USER_ID);
        params.put("EncryptCode",Constants.ENCRYPT_CODE);
        params.put("PageIndex","0");
        params.put("OriginalFactoryID",editTextSearch.getText().toString());
        mPresenter.requestDate(params, BasePresenter.RequestMode.FRIST);
    }
    @Override
    protected StraightSearchPresenter getChildPresenter() {
        return new StraightSearchPresenter(this);
    }

    @Override
    public void hasNoMoreDate() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void refreshView() {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public void showEmptyView(String msg) {

    }

    @Override
    public void loadMoreFinish(List dates) {

    }

    @Override
    public void showRefreshFinish(List score) {

    }

    @Override
    public void showToastError() {
        Log.i("sr","show_error");
    }

    @Override
    public void showResult(List<OEData> oeDatas) {
        Log.i("sr","show_result");
    }
}
