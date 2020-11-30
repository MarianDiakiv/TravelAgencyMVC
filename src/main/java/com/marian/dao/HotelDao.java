package com.marian.dao;

import com.marian.entity.Country;
import com.marian.entity.Hotel;

import java.util.List;

public interface HotelDao {
    void save(Hotel hotel);
    List<Hotel> getAll();
    List<Hotel> getbuCountry(Country country);
    Hotel getById(int id);
    void update(Hotel hotel);
}
