package org.example.daochu.model;

public class HandicapCertsEntity {
    private String id;
    private String name;
    private String certId;
    private String hCategory;
    private String hLevel;

    @Override
    public String toString() {
        return "HandicapCertsEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", certId='" + certId + '\'' +
                ", hCategory='" + hCategory + '\'' +
                ", hLevel='" + hLevel + '\'' +
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

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String gethCategory() {
        return hCategory;
    }

    public void sethCategory(String hCategory) {
        this.hCategory = hCategory;
    }

    public String gethLevel() {
        return hLevel;
    }

    public void sethLevel(String hLevel) {
        this.hLevel = hLevel;
    }
}
