package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenterImpl;
import com.nissens.bean.Image;
import com.nissens.module.model.ImageModelImpl;
import com.nissens.module.view.OEPartImageView;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/10/25.
 */

public class ImagePresenterImpl extends BasePresenterImpl<OEPartImageView, List<Image>> implements ImagePresenter {
    BaseModel imageModel;

    public ImagePresenterImpl(OEPartImageView view) {
        super(view);
        imageModel = new ImageModelImpl();
    }

    @Override
    public void requestData(String requestData) {
        mSubscription=imageModel.requestSearchData(this, requestData);
    }
    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.showError();
    }

    @Override
    public void requestSuccess(List<Image> images) {
        if(images.size()==0)
            mView.showEmpty();
        else mView.showResult(images);
    }

}
