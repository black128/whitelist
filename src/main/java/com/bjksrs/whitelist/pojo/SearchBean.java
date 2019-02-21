package com.bjksrs.whitelist.pojo;

public class SearchBean {


    @Override
    public String toString() {
        return "SearchBean{" +
                "cmbProvince='" + cmbProvince + '\'' +
                ", cmbCity='" + cmbCity + '\'' +
                ", condition='" + condition + '\'' +
                ", content='" + content + '\'' +
                ", rows=" + rows +
                ", offset=" + offset +
                '}';
    }

    private String cmbProvince;
    private String cmbCity;
    private String condition;
    private String content;

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    private Integer rows;
    private Integer offset;
    public String getCmbProvince() {
        return cmbProvince;
    }

    public void setCmbProvince(String cmbProvince) {
        this.cmbProvince = cmbProvince;
    }

    public String getCmbCity() {
        return cmbCity;
    }

    public void setCmbCity(String cmbCity) {
        this.cmbCity = cmbCity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
