package org.example.daochu.model;

public class ServiceRecordEntity {
    private String id;
    private String hId;
    private String startTime;
    private String serAddress;
    private String detail;
    private String service;

    @Override
    public String toString() {
        return "ServiceRecordEntity{" +
                "id='" + id + '\'' +
                ", hId='" + hId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", serAddress='" + serAddress + '\'' +
                ", detail='" + detail + '\'' +
                ", service='" + service + '\'' +
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
}
