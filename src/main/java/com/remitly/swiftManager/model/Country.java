package com.remitly.swiftManager.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name  = "Countries")
public class Country {
    @Id
    @Column(name = "CountryISO2Code" , length = 2)
    private String countryISO2Code;
    @Column(name = "countryname", nullable = false)
    private String countryName;


    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Bank> banks;

    public String getCountryISO2Code() {
        return countryISO2Code;
    }

    public void setCountryISO2Code(String countryISO2Code) {
        this.countryISO2Code = countryISO2Code;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Set<Bank> getBanks() {
        return banks;
    }


}
