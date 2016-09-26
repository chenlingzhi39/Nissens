package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
public class Request {
    private String UserID="terry",
            EncryptCode="123456";

    public String getEncryptCode() {
        return EncryptCode;
    }

    public void setEncryptCode(String encryptCode) {
        EncryptCode = encryptCode;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
