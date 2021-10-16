package org.example.daochu.vo;

import java.math.BigInteger;

public class ServiceCommVo {
    private String id;
    private String serAddress;
    private String service;
    private String comment;
    private BigInteger grade;

    @Override
    public String toString() {
        return "ServiceCommVo{" +
                "id='" + id + '\'' +
                ", serAddress='" + serAddress + '\'' +
                ", service='" + service + '\'' +
                ", comment='" + comment + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerAddress() {
        return serAddress;
    }

    public void setSerAddress(String serAddress) {
        this.serAddress = serAddress;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigInteger getGrade() {
        return grade;
    }

    public void setGrade(BigInteger grade) {
        this.grade = grade;
    }
}
