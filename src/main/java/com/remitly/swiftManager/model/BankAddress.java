package com.remitly.swiftManager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bankaddresses")
public class BankAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressid")
    private int id;

    @ManyToOne
    @JoinColumn(name = "swiftid", nullable = false)
    private SWIFTCode swiftCode;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "townname")
    private String townName;

    @Column(name = "timezone")
    private String timeZone;

    public SWIFTCode getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(SWIFTCode swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    public int getId() {
        return id;
    }

}
