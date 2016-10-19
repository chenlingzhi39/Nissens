package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/10/7.
 */

public class BrandOrganizationRequest extends Request {
    private String BrandBusinessID,
    BrandBusinessName,
    OrganizationID,
    OrganizationName,
    OrgProvince,
    OrgCity,
    OrgAddress,
    OrgTel,
    OrgEmail,
    OrgWebUrl,
    OrgFax;

    public BrandOrganizationRequest(String orgProvince,String orgCity) {
        OrgCity = orgCity;
        OrgProvince = orgProvince;
    }

    public BrandOrganizationRequest(String organizationID) {
        OrganizationID = organizationID;
    }
}
