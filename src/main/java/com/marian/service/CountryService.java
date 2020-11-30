package com.marian.service;

import com.marian.entity.Country;

import java.util.List;

public interface CountryService {
    void createCountry(Country country);
    List<Country> getAllCountry();
    Country getById(int id);
    Country getByName(String country);
}
