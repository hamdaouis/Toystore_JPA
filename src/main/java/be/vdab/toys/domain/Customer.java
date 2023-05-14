package be.vdab.toys.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private long id;
    private String name;
    private String streetAndNumber;
    private String city;
    private String state;
    private String postalCode;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country country;
    @Version
    private long version;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Country getCountry() {
        return country;
    }

    public long getVersion() {
        return version;
    }
}
