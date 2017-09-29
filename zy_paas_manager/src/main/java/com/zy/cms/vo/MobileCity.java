package com.zy.cms.vo;

public class MobileCity {

    private Integer id;

    private String  prefixNum;

    private String  province;

    private String  city;

    private String  areaCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrefixNum() {
        return prefixNum;
    }

    public void setPrefixNum(String prefixNum) {
        this.prefixNum = prefixNum;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
