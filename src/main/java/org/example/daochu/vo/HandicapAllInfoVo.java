package org.example.daochu.vo;

public class HandicapAllInfoVo {
    private String id;
    private String name;
    private String certId;
    private String hCategory;
    private String hLevel;
    private String password;
    private String hPhone;
    private String guardianName;
    private String guardianPhone;

    @Override
    public String toString() {
        return "HandicapAllInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", certId='" + certId + '\'' +
                ", hCategory='" + hCategory + '\'' +
                ", hLevel='" + hLevel + '\'' +
                ", password='" + password + '\'' +
                ", hPhone='" + hPhone + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", guardianPhone='" + guardianPhone + '\'' +
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String gethPhone() {
        return hPhone;
    }

    public void sethPhone(String hPhone) {
        this.hPhone = hPhone;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }
}
