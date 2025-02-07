package com.remitly.swiftManager.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name = "SWIFTCodes")
public class SWIFTCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SWIFTID")
    private int SWIFTID;

    @ManyToOne
    @JoinColumn(name = "BankID", nullable = false)
    private Bank bank;

    @Column(name = "SWIFTCode", unique = true, nullable = false, length = 11)
    private String SWIFTCode;

    @Column(name = "Codetype", nullable = false)
    private String codeType;

    @Column(name = "isheadquarter", nullable = false)
    private boolean isHeadquarter;

    @Column(name = "HeadquarterSWIFT")
    private String headquarterSWIFT;

    @OneToMany(mappedBy = "swiftCode", cascade = CascadeType.ALL)
    private Set<BankAddress> addresses;

    public int getSWIFTID() {
        return SWIFTID;
    }

    public void setSWIFTID(int SWIFTID) {
        this.SWIFTID = SWIFTID;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getSWIFTCode() {
        return SWIFTCode;
    }

    public void setSWIFTCode(String SWIFTCode) {
        this.SWIFTCode = SWIFTCode;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getHeadquarterSWIFT() {
        return headquarterSWIFT;
    }

    public void setHeadquarterSWIFT(String headquarterSWIFT) {
        this.headquarterSWIFT = headquarterSWIFT;
    }

    public Set<BankAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<BankAddress> addresses) {
        this.addresses = addresses;
    }

    public boolean isHeadquarter() {
        return isHeadquarter;
    }

    public void setHeadquarter(boolean headquarter) {
        isHeadquarter = headquarter;
    }

}
