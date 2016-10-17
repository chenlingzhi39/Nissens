package com.nissens.ui;

import android.content.Intent;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.nissens.bean.Request;
import com.nissens.bean.Type;
import com.nissens.module.presenter.CarXmlPresenter;
import com.nissens.module.presenter.CarXmlPresenterImpl;
import com.nissens.module.view.CarXmlView;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC-20160514 on 2016/9/30.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_select_car,
        toolbarTitle = R.string.select_car,
        menuId = R.menu.normal
)
public class SelectCarActivity extends BaseActivity<CarXmlPresenter> implements CarXmlView, RecyclerViewExpandableItemManager.OnGroupCollapseListener,
        RecyclerViewExpandableItemManager.OnGroupExpandListener {
    @BindView(R.id.type_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mWrappedAdapter;
    private RecyclerViewExpandableItemManager mRecyclerViewExpandableItemManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new CarXmlPresenterImpl(this);
        mPresenter.requestData(gson.toJson(new Request()));
    }

    @Override
    public void showEmpty() {
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(String xml) {
        XmlPullParser parser = Xml.newPullParser();
        ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(xml.getBytes());
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
                            } else
                                i = 1;
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
                Intent intent = new Intent(SelectCarActivity.this, OEDatasActivity.class);
                intent.putExtra("label", child.getName());
                intent.putExtra("series", getIntent().getStringExtra("series"));
                startActivity(intent);
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
        mPresenter.requestData(gson.toJson(new Request()));
    }
}
