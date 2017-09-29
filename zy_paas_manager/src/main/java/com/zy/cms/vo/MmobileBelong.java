package com.zy.cms.vo;

public class MmobileBelong {
    
	private String id;

    private String prefixNum;

    private String province;

    private String city;

    private String areaCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPrefixNum() {
        return prefixNum;
    }

    public void setPrefixNum(String prefixNum) {
        this.prefixNum = prefixNum == null ? null : prefixNum.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }
}