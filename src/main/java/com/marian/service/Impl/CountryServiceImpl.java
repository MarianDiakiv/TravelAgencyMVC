package com.marian.service.Impl;

import com.marian.dao.CountryDao;
import com.marian.entity.Country;
import com.marian.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryServiceImpl implements CountryService {


    private CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public void createCountry(Country country) {
            countryDao.createCountry(country);
    }

    @Override
    public List<Country> getAllCountry() {
        return countryDao.getAllCountry();
    }

    @Override
    public Country getById(int id) {
        return countryDao.getById(id);
    }

    @Override
    public Country getByName(String country) {
        return countryDao.getByName(country);
    }
}
