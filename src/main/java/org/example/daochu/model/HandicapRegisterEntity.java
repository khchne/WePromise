package org.example.daochu.model;

public class HandicapRegisterEntity {
    private String id;
    private String password;
    private String hPhone;
    private String guardianName;
    private String guardianPhone;

    @Override
    public String toString() {
        return "HandicapRegisterEntity{" +
                "id='" + id + '\'' +
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
