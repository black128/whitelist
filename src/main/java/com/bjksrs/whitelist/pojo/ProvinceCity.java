package com.bjksrs.whitelist.pojo;

public class ProvinceCity {

    private String id;
    private String createTime;
    private String companyName;
    private String regNo;
    private String creditCode;
    private String orgNo;
    private String province;
    private String errorProvince;
    private String city;
    private String errorCity;
    private int state;
    private String comments;
    private String address;
    private String telephone;
    private String mobile;

    @Override
    public String toString() {
        return "ProvinceCity{" +
                "id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", companyName='" + companyName + '\'' +
                ", regNo='" + regNo + '\'' +
                ", creditCode='" + creditCode + '\'' +
                ", orgNo='" + orgNo + '\'' +
                ", province='" + province + '\'' +
                ", errorProvince='" + errorProvince + '\'' +
                ", city='" + city + '\'' +
                ", errorCity='" + errorCity + '\'' +
                ", state=" + state +
                ", comments='" + comments + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getErrorProvince() {
        return errorProvince;
    }

    public void setErrorProvince(String errorProvince) {
        this.errorProvince = errorProvince;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getErrorCity() {
        return errorCity;
    }

    public void setErrorCity(String errorCity) {
        this.errorCity = errorCity;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


}
