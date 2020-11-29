package com.marian.entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "country_name")
    private String country;
    @OneToMany(mappedBy = "country")
    private List<Hotel> hotels = new ArrayList<>();

    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
