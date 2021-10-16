package org.example.daochu.model;

public class ChargeInfoEntity {
    private String id;
    private String chargeName;
    private String chargePhone;

    @Override
    public String toString() {
        return "ChargeInfoEntity{" +
                "id='" + id + '\'' +
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
