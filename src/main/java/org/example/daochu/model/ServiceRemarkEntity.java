package org.example.daochu.model;

public class ServiceRemarkEntity {
    private String id;
    private String orgId;
    private String endTime;
    private String comment;
    private String state;
    private String grade;

    @Override
    public String toString() {
        return "ServiceRemarkEntity{" +
                "id='" + id + '\'' +
                ", orgId='" + orgId + '\'' +
                ", endTime='" + endTime + '\'' +
                ", comment='" + comment + '\'' +
                ", state='" + state + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
