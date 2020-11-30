package com.marian.service;

import com.marian.entity.Country;
import com.marian.entity.Hotel;

import java.util.List;

public interface HotelService {
    void save(Hotel hotel);
    List<Hotel> getAll();
    List<Hotel> getByCountry(Country country);
    Hotel getById(int id);
    void update(Hotel hotel, int id);
}
