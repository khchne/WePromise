package org.example.daochu.vo;

import java.math.BigInteger;

public class ServiceAllInfoVo {
    private String id;
    private String hId;
    private String orgId;
    private String startTime;
    private String serAddress;
    private String detail;
    private String service;
    private String endTime;
    private String comment;
    private String state;
    private BigInteger grade;
    private String chargeName;
    private String chargePhone;

    @Override
    public String toString() {
        return "ServiceAllInfoVo{" +
                "id='" + id + '\'' +
                ", hId='" + hId + '\'' +
                ", orgId='" + orgId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", serAddress='" + serAddress + '\'' +
                ", detail='" + detail + '\'' +
                ", service='" + service + '\'' +
                ", endTime='" + endTime + '\'' +
                ", comment='" + comment + '\'' +
                ", state='" + state + '\'' +
                ", grade='" + grade + '\'' +
                ", chargeName='" + chargeName + '\'' +
                ", chargePhone='" + chargePhone + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSerAddress() {
        return serAddress;
    }

    public void setSerAddress(String serAddress) {
        this.serAddress = serAddress;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigInteger getGrade() {
        return grade;
    }

    public void setGrade(BigInteger grade) {
        this.grade = grade;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public String getChargePhone() {
        return chargePhone;
    }

    public void setChargePhone(String chargePhone) {
        this.chargePhone = chargePhone;
    }
}
