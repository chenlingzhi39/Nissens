package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/9/24.
 */
public class OEDataRequest extends Request {
    private String
            PageIndex,
            ItemsPerPage,
            OriginalFactoryID,
            OriginalFactoryName,
            PartName,
            FactoryLabelID,
            FactoryLabel,
            CategoryID,
            Category,
            ObjectID,
            MatchBrandID,
            MatchBrand,
            FourSPrice,
            Unit,
            StandardSpecificationID,
            StandardSpecification,
            SpecificationDes,
            Packaging,
            Capacity,
            Length,
            Width,
            Height,
            Weight,
            CollisionDegree,
            CollisionSite,
            Remarks,
            IsUniversal,
            IsAccident,
            IsVulnerable,
            IsReturn,
            IsStopProduction,
            OEDataStatus,
            FactoryID,
            PartNameID,
            Brand,
            Series,
            QualityClass,
            QualitySubclass,
            FactoryPrice,
            RetailPrice,
            WholesalePrice,
            ApplyFactoryLabel,
            FactoryModel;


    public String getWidth() {
        return Width;
    }

    public void setWidth(String width) {
        Width = width;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getStandardSpecificationID() {
        return StandardSpecificationID;
    }

    public void setStandardSpecificationID(String standardSpecificationID) {
        StandardSpecificationID = standardSpecificationID;
    }

    public String getStandardSpecification() {
        return StandardSpecification;
    }

    public void setStandardSpecification(String standardSpecification) {
        StandardSpecification = standardSpecification;
    }

    public String getSpecificationDes() {
        return SpecificationDes;
    }

    public void setSpecificationDes(String specificationDes) {
        SpecificationDes = specificationDes;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    public String getPartName() {
        return PartName;
    }

    public void setPartName(String partName) {
        PartName = partName;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getPackaging() {
        return Packaging;
    }

    public void setPackaging(String packaging) {
        Packaging = packaging;
    }

    public String getOriginalFactoryID() {
        return OriginalFactoryID;
    }

    public void setOriginalFactoryID(String originalFactoryID) {
        OriginalFactoryID = originalFactoryID;
    }

    public String getOriginalFactoryName() {
        return OriginalFactoryName;
    }

    public void setOriginalFactoryName(String originalFactoryName) {
        OriginalFactoryName = originalFactoryName;
    }

    public String getOEDataStatus() {
        return OEDataStatus;
    }

    public void setOEDataStatus(String OEDataStatus) {
        this.OEDataStatus = OEDataStatus;
    }

    public String getObjectID() {
        return ObjectID;
    }

    public void setObjectID(String objectID) {
        ObjectID = objectID;
    }

    public String getMatchBrand() {
        return MatchBrand;
    }

    public void setMatchBrand(String matchBrand) {
        MatchBrand = matchBrand;
    }

    public String getMatchBrandID() {
        return MatchBrandID;
    }

    public void setMatchBrandID(String matchBrandID) {
        MatchBrandID = matchBrandID;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getItemsPerPage() {
        return ItemsPerPage;
    }

    public void setItemsPerPage(String itemsPerPage) {
        ItemsPerPage = itemsPerPage;
    }

    public String getIsVulnerable() {
        return IsVulnerable;
    }

    public void setIsVulnerable(String isVulnerable) {
        IsVulnerable = isVulnerable;
    }

    public String getIsUniversal() {
        return IsUniversal;
    }

    public void setIsUniversal(String isUniversal) {
        IsUniversal = isUniversal;
    }

    public String getIsStopProduction() {
        return IsStopProduction;
    }

    public void setIsStopProduction(String isStopProduction) {
        IsStopProduction = isStopProduction;
    }

    public String getIsReturn() {
        return IsReturn;
    }

    public void setIsReturn(String isReturn) {
        IsReturn = isReturn;
    }

    public String getIsAccident() {
        return IsAccident;
    }

    public void setIsAccident(String isAccident) {
        IsAccident = isAccident;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getFourSPrice() {
        return FourSPrice;
    }

    public void setFourSPrice(String fourSPrice) {
        FourSPrice = fourSPrice;
    }

    public String getFactoryLabelID() {
        return FactoryLabelID;
    }

    public void setFactoryLabelID(String factoryLabelID) {
        FactoryLabelID = factoryLabelID;
    }

    public String getFactoryLabel() {
        return FactoryLabel;
    }

    public void setFactoryLabel(String factoryLabel) {
        FactoryLabel = factoryLabel;
    }

    public String getCollisionSite() {
        return CollisionSite;
    }

    public void setCollisionSite(String collisionSite) {
        CollisionSite = collisionSite;
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

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public String getFactoryID() {
        return FactoryID;
    }

    public void setFactoryID(String factoryID) {
        FactoryID = factoryID;
    }

    public OEDataRequest(String itemsPerPage, String pageIndex) {
        ItemsPerPage = itemsPerPage;
        PageIndex=pageIndex;
    }

    public OEDataRequest(String itemsPerPage, String pageIndex, String factoryID) {
        ItemsPerPage = itemsPerPage;
        PageIndex = pageIndex;
        FactoryID = factoryID;
    }

    public OEDataRequest(String itemsPerPage, String pageIndex, String applyFactoryLabel, String series) {
        ItemsPerPage = itemsPerPage;
        PageIndex = pageIndex;
        ApplyFactoryLabel = applyFactoryLabel;
        Series = series;
    }


}
