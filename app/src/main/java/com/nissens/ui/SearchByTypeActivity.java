package com.nissens.ui;

import android.content.Intent;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.ItemShadowDecorator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.SimpleListDividerDecorator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.nissens.R;
import com.nissens.adapter.TypeAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.BrandSeriesXml;
import com.nissens.bean.BrandSeriesXmlRequest;
import com.nissens.bean.Type;
import com.nissens.module.presenter.SearchByTypePresenter;
import com.nissens.module.presenter.SearchByTypePresenterImpl;
import com.nissens.module.view.SearchByTypeView;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC-20160514 on 2016/9/23.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_search_by_type,
        toolbarTitle = R.string.select_type,
        menuId = R.menu.normal
)
public class SearchByTypeActivity extends BaseActivity<SearchByTypePresenter> implements SearchByTypeView, RecyclerViewExpandableItemManager.OnGroupCollapseListener,
        RecyclerViewExpandableItemManager.OnGroupExpandListener {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;
    @BindView(R.id.type_list)
    RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mWrappedAdapter;
    private RecyclerViewExpandableItemManager mRecyclerViewExpandableItemManager;
    private String header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new SearchByTypePresenterImpl(this);
        mPresenter.requestData(gson.toJson(new BrandSeriesXmlRequest()));
    }

    @Override
    public void showEmpty() {
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(List<BrandSeriesXml> brandSeriesXmls) {
        XmlPullParser parser = Xml.newPullParser();
        ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(brandSeriesXmls.get(0).getBrandSeriesXml().getBytes());
        List<Type> types = null;
        List<Type> subTypes = null;
        Type type = new Type();
        Type subType;
        String tag = "";
        int i = 0;
        try {
            parser.setInput(tInputStringStream, "UTF-8");
            int eventCode = parser.getEventType();
            while (eventCode != parser.END_DOCUMENT) {
                switch (eventCode) {
                    case XmlPullParser.START_DOCUMENT:
                        types = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        if (tag.equals("")) {
                            if (i != 0) {
                                tag = parser.getName();
                                type = new Type(parser.getAttributeValue(0), parser.getName());
                                subTypes = new ArrayList<>();
                            } else {
                                i = 1;
                                header = parser.getAttributeValue(0);
                            }
                        } else {
                            subType = new Type();
                            subType.setId(parser.getName());
                            subType.setName(parser.getAttributeValue(0));
                            subTypes.add(subType);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals(tag)) {
                            tag = "";
                            type.setTypes(subTypes);
                            types.add(type);
                        }
                        break;
                }
                eventCode = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //noinspection ConstantConditions
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerViewExpandableItemManager = new RecyclerViewExpandableItemManager(null);
        mRecyclerViewExpandableItemManager.setOnGroupExpandListener(this);
        mRecyclerViewExpandableItemManager.setOnGroupCollapseListener(this);
        mRecyclerViewExpandableItemManager.expandAll();
        //adapter
        final TypeAdapter typeAdapter = new TypeAdapter(types);
        typeAdapter.setHasStableIds(true);
        typeAdapter.setOnItemClickListener(new TypeAdapter.OnItemClickListener() {
            @Override
            public void OnClick(Type group, Type child) {
                if(getIntent().getStringExtra("mode").equals("type"))
                {Intent intent = new Intent(SearchByTypeActivity.this, SelectCarActivity.class);
                intent.putExtra("series", header + " | " + group.getName() + " | " + child.getName());
                Log.i("series", header + " | " + group.getName() + " | " + child.getName());
                startActivity(intent);}else{
                    Intent intent = new Intent(SearchByTypeActivity.this, OEDatasActivity.class);
                    intent.putExtra("series", header + " | " + group.getName() + " | " + child.getName());
                    intent.putExtra("label",getIntent().getStringExtra("label"));
                    Log.i("series", header + " | " + group.getName() + " | " + child.getName());
                    startActivity(intent);
                }
                finish();
            }
        });
        mWrappedAdapter = mRecyclerViewExpandableItemManager.createWrappedAdapter(typeAdapter);       // wrap for expanding

        final GeneralItemAnimator animator = new RefactoredDefaultItemAnimator();

        // Change animations are enabled by default since support-v7-recyclerview v22.
        // Need to disable them when using animation indicator.
        animator.setSupportsChangeAnimations(false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWrappedAdapter);  // requires *wrapped* adapter
        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setHasFixedSize(false);

        // additional decorations
        //noinspection StatementWithEmptyBody
        if (supportsViewElevation()) {
            // Lollipop or later has native drop shadow feature. ItemShadowDecorator is not required.
        } else {
            mRecyclerView.addItemDecoration(new ItemShadowDecorator((NinePatchDrawable) ContextCompat.getDrawable(this, R.drawable.material_shadow_z1)));
        }
        mRecyclerView.addItemDecoration(new SimpleListDividerDecorator(ContextCompat.getDrawable(this, R.drawable.list_divider_h), true));

        mRecyclerViewExpandableItemManager.attachRecyclerView(mRecyclerView);

    }

    private boolean supportsViewElevation() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }

    @Override
    public void showError() {
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        empty.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGroupCollapse(int groupPosition, boolean fromUser) {

    }

    @Override
    public void onGroupExpand(int groupPosition, boolean fromUser) {

    }

    @OnClick(R.id.error)
    public void onClick() {
        error.setVisibility(View.GONE);
        mPresenter.requestData(gson.toJson(new BrandSeriesXmlRequest()));
    }
}
