package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/10/25.
 */

public class ImageRequest extends Request {
    String OriginalFactoryID,OriginalFactoryName,OEPartImageID,PartType="2";

    public ImageRequest(String originalFactoryID) {
        OriginalFactoryID = originalFactoryID;
    }
}
