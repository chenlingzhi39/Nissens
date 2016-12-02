package com.nissens.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public class OEData implements Parcelable{
    private String OriginalFactoryID,
            OriginalFactoryName,
            FactoryID,
            FactoryName,
            FactoryLabel,
            Brand,
            Series,
            QualityClass,
            QualitySubClass,
            CategoryID,
            Category,
            Unit,
            ObjectID,
            MatchBrand,
            SupportBrand,
            FactoryPrice,
            RetailPrice,
            WholesalePrice,
            StandardSpecification,
            SpecificationDes,
            Packaging,
            ApplyLabel,
            FactoryModel,
            IsReturn,
            Remarks,
            Length,
            Width,
            Height,
            Weight,
            CollisionDegree,
            CollisionSite,
            IsUniversal,
            Capacity,
            IsStopProduction,
            ProductType,
            ProduceClass,
            OedataStatus,
            PartName;

    public String getPartName() {
        return PartName;
    }

    public void setPartName(String partName) {
        PartName = partName;
    }

    public String getOedataStatus() {
        return OedataStatus;
    }

    public void setOedataStatus(String oedataStatus) {
        OedataStatus = oedataStatus;
    }

    public String getApplyLabel() {
        return ApplyLabel;
    }

    public void setApplyLabel(String applyLabel) {
        ApplyLabel = applyLabel;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCollisionDegree() {
        return CollisionDegree;
    }

    public void setCollisionDegree(String collisionDegree) {
        CollisionDegree = collisionDegree;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getFactoryID() {
        return FactoryID;
    }

    public void setFactoryID(String factoryID) {
        FactoryID = factoryID;
    }

    public String getCollisionSite() {
        return CollisionSite;
    }

    public void setCollisionSite(String collisionSite) {
        CollisionSite = collisionSite;
    }

    public String getFactoryLabel() {
        return FactoryLabel;
    }

    public void setFactoryLabel(String factoryLabel) {
        FactoryLabel = factoryLabel;
    }

    public String getFactoryModel() {
        return FactoryModel;
    }

    public void setFactoryModel(String factoryModel) {
        FactoryModel = factoryModel;
    }

    public String getFactoryPrice() {
        return FactoryPrice;
    }

    public void setFactoryPrice(String factoryPrice) {
        FactoryPrice = factoryPrice;
    }

    public String getFactoryName() {
        return FactoryName;
    }

    public void setFactoryName(String factoryName) {
        FactoryName = factoryName;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getIsReturn() {
        return IsReturn;
    }

    public void setIsReturn(String isReturn) {
        IsReturn = isReturn;
    }

    public String getIsStopProduction() {
        return IsStopProduction;
    }

    public void setIsStopProduction(String isStopProduction) {
        IsStopProduction = isStopProduction;
    }

    public String getIsUniversal() {
        return IsUniversal;
    }

    public void setIsUniversal(String isUniversal) {
        IsUniversal = isUniversal;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getMatchBrand() {
        return MatchBrand;
    }

    public void setMatchBrand(String matchBrand) {
        MatchBrand = matchBrand;
    }

    public String getObjectID() {
        return ObjectID;
    }

    public void setObjectID(String objectID) {
        ObjectID = objectID;
    }

    public String getOriginalFactoryID() {
        return OriginalFactoryID;
    }

    public void setOriginalFactoryID(String originalFactoryID) {
        OriginalFactoryID = originalFactoryID;
    }

    public String getPackaging() {
        return Packaging;
    }

    public void setPackaging(String packaging) {
        Packaging = packaging;
    }

    public String getOriginalFactoryName() {
        return OriginalFactoryName;
    }

    public void setOriginalFactoryName(String originalFactoryNa) {
        OriginalFactoryName = originalFactoryNa;
    }

    public String getProduceClass() {
        return ProduceClass;
    }

    public void setProduceClass(String produceClass) {
        ProduceClass = produceClass;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getQualityClass() {
        return QualityClass;
    }

    public void setQualityClass(String qualityClass) {
        QualityClass = qualityClass;
    }

    public String getQualitySubClass() {
        return QualitySubClass;
    }

    public void setQualitySubClass(String qualitySubClass) {
        QualitySubClass = qualitySubClass;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getRetailPrice() {
        return RetailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        RetailPrice = retailPrice;
    }

    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    public String getSpecificationDes() {
        return SpecificationDes;
    }

    public void setSpecificationDes(String specificationDes) {
        SpecificationDes = specificationDes;
    }

    public String getStandardSpecification() {
        return StandardSpecification;
    }

    public void setStandardSpecification(String standardSpecification) {
        StandardSpecification = standardSpecification;
    }

    public String getSupportBrand() {
        return SupportBrand;
    }

    public void setSupportBrand(String supportBrand) {
        SupportBrand = supportBrand;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getWholesalePrice() {
        return WholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        WholesalePrice = wholesalePrice;
    }

    public String getWidth() {
        return Width;
    }

    public void setWidth(String width) {
        Width = width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.OriginalFactoryID);
        dest.writeString(this.OriginalFactoryName);
        dest.writeString(this.FactoryID);
        dest.writeString(this.FactoryName);
        dest.writeString(this.FactoryLabel);
        dest.writeString(this.Brand);
        dest.writeString(this.Series);
        dest.writeString(this.QualityClass);
        dest.writeString(this.QualitySubClass);
        dest.writeString(this.CategoryID);
        dest.writeString(this.Category);
        dest.writeString(this.Unit);
        dest.writeString(this.ObjectID);
        dest.writeString(this.MatchBrand);
        dest.writeString(this.SupportBrand);
        dest.writeString(this.FactoryPrice);
        dest.writeString(this.RetailPrice);
        dest.writeString(this.WholesalePrice);
        dest.writeString(this.StandardSpecification);
        dest.writeString(this.SpecificationDes);
        dest.writeString(this.Packaging);
        dest.writeString(this.ApplyLabel);
        dest.writeString(this.FactoryModel);
        dest.writeString(this.IsReturn);
        dest.writeString(this.Remarks);
        dest.writeString(this.Length);
        dest.writeString(this.Width);
        dest.writeString(this.Height);
        dest.writeString(this.Weight);
        dest.writeString(this.CollisionDegree);
        dest.writeString(this.CollisionSite);
        dest.writeString(this.IsUniversal);
        dest.writeString(this.Capacity);
        dest.writeString(this.IsStopProduction);
        dest.writeString(this.ProductType);
        dest.writeString(this.ProduceClass);
        dest.writeString(this.OedataStatus);
        dest.writeString(this.PartName);
    }

    public OEData() {
    }

    protected OEData(Parcel in) {
        this.OriginalFactoryID = in.readString();
        this.OriginalFactoryName = in.readString();
        this.FactoryID = in.readString();
        this.FactoryName = in.readString();
        this.FactoryLabel = in.readString();
        this.Brand = in.readString();
        this.Series = in.readString();
        this.QualityClass = in.readString();
        this.QualitySubClass = in.readString();
        this.CategoryID = in.readString();
        this.Category = in.readString();
        this.Unit = in.readString();
        this.ObjectID = in.readString();
        this.MatchBrand = in.readString();
        this.SupportBrand = in.readString();
        this.FactoryPrice = in.readString();
        this.RetailPrice = in.readString();
        this.WholesalePrice = in.readString();
        this.StandardSpecification = in.readString();
        this.SpecificationDes = in.readString();
        this.Packaging = in.readString();
        this.ApplyLabel = in.readString();
        this.FactoryModel = in.readString();
        this.IsReturn = in.readString();
        this.Remarks = in.readString();
        this.Length = in.readString();
        this.Width = in.readString();
        this.Height = in.readString();
        this.Weight = in.readString();
        this.CollisionDegree = in.readString();
        this.CollisionSite = in.readString();
        this.IsUniversal = in.readString();
        this.Capacity = in.readString();
        this.IsStopProduction = in.readString();
        this.ProductType = in.readString();
        this.ProduceClass = in.readString();
        this.OedataStatus = in.readString();
        this.PartName = in.readString();
    }

    public static final Creator<OEData> CREATOR = new Creator<OEData>() {
        @Override
        public OEData createFromParcel(Parcel source) {
            return new OEData(source);
        }

        @Override
        public OEData[] newArray(int size) {
            return new OEData[size];
        }
    };
}
