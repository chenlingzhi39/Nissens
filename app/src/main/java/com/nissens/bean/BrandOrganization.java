package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/10/7.
 */

public class BrandOrganization {
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

    public String getBrandBusinessID() {
        return BrandBusinessID;
    }

    public void setBrandBusinessID(String brandBusinessID) {
        BrandBusinessID = brandBusinessID;
    }

    public String getOrgAddress() {
        return OrgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        OrgAddress = orgAddress;
    }

    public String getBrandBusinessName() {
        return BrandBusinessName;
    }

    public void setBrandBusinessName(String brandBusinessName) {
        BrandBusinessName = brandBusinessName;
    }

    public String getOrganizationID() {
        return OrganizationID;
    }

    public void setOrganizationID(String organizationID) {
        OrganizationID = organizationID;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getOrgCity() {
        return OrgCity;
    }

    public void setOrgCity(String orgCity) {
        OrgCity = orgCity;
    }

    public String getOrgFax() {
        return OrgFax;
    }

    public void setOrgFax(String orgFax) {
        OrgFax = orgFax;
    }

    public String getOrgEmail() {
        return OrgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        OrgEmail = orgEmail;
    }

    public String getOrgTel() {
        return OrgTel;
    }

    public void setOrgTel(String orgTel) {
        OrgTel = orgTel;
    }

    public String getOrgProvince() {
        return OrgProvince;
    }

    public void setOrgProvince(String orgProvince) {
        OrgProvince = orgProvince;
    }

    public String getOrgWebUrl() {
        return OrgWebUrl;
    }

    public void setOrgWebUrl(String orgWebUrl) {
        OrgWebUrl = orgWebUrl;
    }
}
