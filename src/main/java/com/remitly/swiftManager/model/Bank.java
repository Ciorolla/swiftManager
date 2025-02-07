package com.remitly.swiftManager.model;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "CountryISO2Code",nullable = false)
    private Country country;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private Set<SWIFTCode> swiftCodes;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setcountryISOCode(Country country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SWIFTCode> getSwiftCodes() {
        return swiftCodes;
    }

    public void setSwiftCodes(Set<SWIFTCode> swiftCodes) {
        this.swiftCodes = swiftCodes;
    }


}
