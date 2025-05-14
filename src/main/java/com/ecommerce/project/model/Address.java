package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5,message = "Street name must be atleast 5 chracters")
    private String street;

    @NotBlank
    @Size(min = 5,message = "Building name must be atleast 5 chracters")
    private String buildingName;

    @NotBlank
    @Size(min = 4,message = "City name must be atleast 5 chracters")
    private String city;

    @NotBlank
    @Size(min = 2,message = "State name must be atleast 5 chracters")
    private String state;

    @NotBlank
    @Size(min = 2,message = "Country name must be atleast 5 chracters")
    private String country;

    @NotBlank
    @Size(min = 6,message = "Pincode name must be atleast 5 chracters")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(String street, String buildingName, String city, String country, String state, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.country = country;
        this.state = state;
        this.pincode = pincode;
    }
}
