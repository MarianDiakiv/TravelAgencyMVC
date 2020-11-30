package com.marian.dao;

import com.marian.entity.Country;

import java.util.List;

public interface CountryDao {
    void createCountry(Country country);
    List<Country> getAllCountry();
    Country getById(int id);
    Country getByName(String country);
}
