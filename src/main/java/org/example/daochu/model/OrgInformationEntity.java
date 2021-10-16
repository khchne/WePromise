package org.example.daochu.model;

import java.math.BigInteger;

public class OrgInformationEntity {
    private String id;
    private String name;
    private String service;
    private BigInteger grade;

    @Override
    public String toString() {
        return "OrgInformationEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", service='" + service + '\'' +
                ", grade='" + grade + '\'' +
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
