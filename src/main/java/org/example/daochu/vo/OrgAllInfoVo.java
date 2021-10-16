package org.example.daochu.vo;

import java.math.BigInteger;

public class OrgAllInfoVo {
    private String id;
    private String name;
    private String address;
    private String tel;
    private String website;
    private String charge;
    private String chargePhone;
    private String detail;
    private String service;
    private BigInteger grade;

    @Override
    public String toString() {
        return "OrgAllInfoVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", website='" + website + '\'' +
                ", charge='" + charge + '\'' +
                ", chargePhone='" + chargePhone + '\'' +
                ", detail='" + detail + '\'' +
                ", service='" + service + '\'' +
                ", grade=" + grade +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getChargePhone() {
        return chargePhone;
    }

    public void setChargePhone(String chargePhone) {
        this.chargePhone = chargePhone;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public BigInteger getGrade() {
        return grade;
    }

    public void setGrade(BigInteger grade) {
        this.grade = grade;
    }
}
